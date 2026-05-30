package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes.QuantumCropParticleData;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockEntityInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.QuantumCropFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.TimeFunctions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class QuantumCropBlockEntity extends BlockEntity {

	public boolean isFullyGrown = false;

	public int maxGrowthDuration = 0;
	public int curGrowth = 0;
	public int growthStepAmount = 2;
	public double growthPerc = 0;
	public String growthPercStr = "0";

	public int regenStepCount = 0;
	public int maxRegenStep = 30;
	public int growthDurationLeft = 0;

	public int curTick = 0;
	public int tickPerStep = QuantumCropFunctions.getMaxTicksLimit();

	public QuantumCropParticleData particleDataGenerated = new QuantumCropParticleData("_generated");
	public QuantumCropParticleData particleDataIncrement = new QuantumCropParticleData("_increment");

	public QuantumCropBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityInit.QUANTUM_CROP_BLOCKENTITY.get(), pPos, pBlockState);

	}
	

	public final ItemStackHandler inventory = new ItemStackHandler(2) {
		@Override
		protected int getStackLimit(int slot, ItemStack stack) {
			return 2;
		}

		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
			if (!level.isClientSide()) {
				level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
			}
		}
	};

	public static void tick(Level level, BlockPos pos, BlockState state, QuantumCropBlockEntity blockEntity) {
		if (level.isClientSide()) {
			return; // Run logic on the server side only (highly recommended)
		}

		if (!blockEntity.isFullyGrown) {

			blockEntity.curTick++;
			if (blockEntity.curTick >= blockEntity.tickPerStep) {

				// increment growth
				blockEntity.curGrowth++;
				blockEntity.calcPerc();
				
				if(blockEntity.curGrowth >= blockEntity.maxGrowthDuration)
				{
					blockEntity.isFullyGrown = true;
					blockEntity.applyFullGrowthBonus();
				}

				// increment regen step count
				blockEntity.regenStepCount++;
				
				if(blockEntity.regenStepCount >= blockEntity.maxRegenStep)
				{
					blockEntity.incrementParticles();
					blockEntity.regenStepCount = 0;
				}

				blockEntity.curTick = 0;
				blockEntity.setChanged();
				level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
			}
		}
	}

	public void displayData()
	{
		calcDurationLeft();
		GlobalFuncs.showInGameMessage("--------------------------");
		GlobalFuncs.showInGameMessage("Item Growing: " + inventory.getStackInSlot(0).getDisplayName().getString());
		GlobalFuncs.showInGameMessage("Growth: " + growthPercStr +"% Time Left: "  + TimeFunctions.convertSecondsToMinutes(growthDurationLeft));
		GlobalFuncs.showInGameMessage("Particles Generated:");
		particleDataGenerated.displayParticles();
	}
	
	public void calcDurationLeft()
	{
		growthDurationLeft = maxGrowthDuration - curGrowth;
	}

	public void applyFullGrowthBonus()
	{
		particleDataGenerated.applyFullGrowthBonus();
	}
	
	
	public void calcPerc()
	{
		growthPerc = (double)((double)curGrowth / (double)maxGrowthDuration )*100d;
		
		if(growthPerc > 100d)
			growthPerc = 100;
		
		growthPercStr = GlobalFuncs.fixDouble(growthPerc);
	}
	
	public void incrementParticles()
	{
		particleDataGenerated.increment(particleDataIncrement);
	}
	
	
	
	public void setData(ItemStack cropItem) {
		particleDataIncrement.set(QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(cropItem).clone());

		maxGrowthDuration = QuantumCropFunctions.getMaxGrowthDuration(cropItem);
	}

	public void setCropAndSeedItems(ItemStack cropItem, ItemStack seedItem) {
		inventory.setStackInSlot(0, cropItem.copy());
		inventory.setStackInSlot(1, seedItem.copy());

		inventory.getStackInSlot(1).setCount(1);

		setData(cropItem);

		setChanged();
	}

	public List<ItemStack> generateStuffToDrop()
	{
List<ItemStack> itemsToDrop = particleDataGenerated.getParticleItemsToDrop();
		
		if (isFullyGrown) {
			itemsToDrop.add(inventory.getStackInSlot(0).copy());
		} else {
			itemsToDrop.add(inventory.getStackInSlot(1).copy());
		}
		
		return itemsToDrop;
	}
	
	public void doDropStuff() {
		
		List<ItemStack> itemsToDrop = generateStuffToDrop();

		
		
		SimpleContainer inv = new SimpleContainer(itemsToDrop.size());
		
		for(int i =0;i< itemsToDrop.size();i++)
		{
			inv.setItem(i, itemsToDrop.get(i));
		}
		


		Containers.dropContents(this.level, this.worldPosition, inv);
	}

	@Override
	protected void saveAdditional(CompoundTag output, Provider provider) {
		super.saveAdditional(output, provider);
		output.put("inventory", inventory.serializeNBT(provider));

		output.putInt("maxGrowthDuration", maxGrowthDuration);
		output.putInt("curGrowth", curGrowth);
		output.putInt("regenStepCount", regenStepCount);
		output.putDouble("growthPerc", growthPerc);
		
		output.putString("growthPercStr", growthPercStr);

		particleDataGenerated.saveAdditional(output);
		particleDataIncrement.saveAdditional(output);
	}

	@Override
	protected void loadAdditional(CompoundTag input, Provider provider) {
		super.loadAdditional(input, provider);
		inventory.deserializeNBT(provider, input.getCompound("inventory"));

		maxGrowthDuration = input.getInt("maxGrowthDuration");
		curGrowth = input.getInt("curGrowth");
		regenStepCount = input.getInt("regenStepCount");
		growthPerc = input.getDouble("growthPerc");
		
		growthPercStr = input.getString("growthPercStr");
		

		particleDataGenerated.loadAdditional(input);
		particleDataIncrement.loadAdditional(input);

	}

	@Override
	public CompoundTag getUpdateTag(Provider provider) {
		// TODO Auto-generated method stub

		CompoundTag nbt = super.getUpdateTag(provider);
		saveAdditional(nbt, provider);
		return nbt;
	}

	@Override
	public void handleUpdateTag(CompoundTag tag, Provider holders) {
		// TODO Auto-generated method stub
		super.handleUpdateTag(tag, holders);
	}

	@Nullable
	@Override
	public Packet<ClientGamePacketListener> getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

}