package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity;

import org.jetbrains.annotations.Nullable;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.tree_shape_box.TreeShapeBox;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockEntityInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.QuantumSaplingFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.TimeFunctions;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class QuantumSaplingBlockEntity extends BlockEntity {

	public boolean isFullyGrown = false;

	public int maxGrowthDuration = 0;
	public int curGrowth = 0;
	public int growthStepAmount = 2;
	public double growthPerc = 0;
	public String growthPercStr = "0";


	public int growthDurationLeft = 0;

	public int curTick = 0;
	public int tickPerStep = 20;

	public Block treeLogBlock = null;
	public Block treeLeavesBlock = null;


	public QuantumSaplingBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(BlockEntityInit.QUANTUM_SAPLING_BLOCKENTITY.get(), pPos, pBlockState);

	}
	

	public final ItemStackHandler inventory = new ItemStackHandler(1) {
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

	public static void tick(Level level, BlockPos pos, BlockState state, QuantumSaplingBlockEntity blockEntity) {
		if (level.isClientSide()) {
			return; // Run logic on the server side only (highly recommended)
		}

		
		
		if (!blockEntity.isFullyGrown) {

			blockEntity.curTick++;
			if (blockEntity.curTick >= blockEntity.tickPerStep) {

				// increment growth
				blockEntity.curGrowth++;
				
				if(blockEntity.curGrowth >= blockEntity.maxGrowthDuration)
				{
					blockEntity.isFullyGrown = true;
					blockEntity.generateTree();
				}


				blockEntity.curTick = 0;
				blockEntity.setChanged();
				level.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL);
			}
		}
	}

	public void displayData()
	{

		calcPerc();
		calcDurationLeft();
		GlobalFuncs.showInGameMessage("--------------------------");
		GlobalFuncs.showInGameMessage("Sapling Growing: " + inventory.getStackInSlot(0).getDisplayName().getString());
		GlobalFuncs.showInGameMessage("Growth: " + growthPercStr +"%");
		GlobalFuncs.showInGameMessage("Time Left: " + TimeFunctions.convertSecondsToMinutes(growthDurationLeft));
		
	}
	

	public void generateTree()
	{
		treeLogBlock = QuantumSaplingFunctions.getTreeLogBlockFromSapling(inventory.getStackInSlot(0));
		treeLeavesBlock = QuantumSaplingFunctions.getTreeLeavesBlockFromSapling(inventory.getStackInSlot(0));
		
		TreeShapeBox.growTree(level, getBlockPos(), treeLogBlock, treeLeavesBlock);
	}
	
	public void calcDurationLeft()
	{
		growthDurationLeft = maxGrowthDuration - curGrowth;
	}
	
	public void calcPerc()
	{
		growthPerc = (double)((double)curGrowth / (double)maxGrowthDuration )*100d;
		
		if(growthPerc > 100d)
			growthPerc = 100;
		
		growthPercStr = GlobalFuncs.fixDouble(growthPerc);
	}
	

	
	
	public void setData(ItemStack saplingItem) {
		
		maxGrowthDuration = QuantumSaplingFunctions.getMaxGrowthDuration(saplingItem);
	}

	public void setSapling(ItemStack saplingItem) {
	
		inventory.setStackInSlot(0, saplingItem.copy());
		inventory.getStackInSlot(0).setCount(1);

		setData(saplingItem);

		setChanged();
	}



	@Override
	protected void saveAdditional(CompoundTag output, Provider provider) {
		super.saveAdditional(output, provider);
		output.put("inventory", inventory.serializeNBT(provider));

		output.putInt("maxGrowthDuration", maxGrowthDuration);
		output.putInt("curGrowth", curGrowth);
		output.putDouble("growthPerc", growthPerc);
		
		output.putString("growthPercStr", growthPercStr);

	}

	@Override
	protected void loadAdditional(CompoundTag input, Provider provider) {
		super.loadAdditional(input, provider);
		inventory.deserializeNBT(provider, input.getCompound("inventory"));

		maxGrowthDuration = input.getInt("maxGrowthDuration");
		curGrowth = input.getInt("curGrowth");
		growthPerc = input.getDouble("growthPerc");
		
		growthPercStr = input.getString("growthPercStr");
		

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