package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.MachineAreaBox;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes.MachineConstants;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes.MachineData;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes.WorkAreaSize;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.functions.BERFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockEntityInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineAreaFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineAreaSizeHandler;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineSpeedHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class tile_entity_crop_harvester extends BlockEntity {

	public boolean isWorking = false;

	public MachineData machineData = new MachineData(Component.translatable("item.qdc_quantum_farming_2.machine_crop_harvester").getString());

	public int speed = 0;
	public int size = 0;

	public WorkAreaSize workAreaSize;

	public Block placeSeedItem = null;
	public boolean hasGrowthLevel = false;

	public ParticleCollection fuelParticles = null;
	public int workAreaIndex = 0;

	public int tickPerc = 0;

	public ItemStackHandler itemHandler = createItemHandler();

	public ArrayList<BlockPos> workArea = null;

	public int curTick = 0;
	public int nextTarget = 100;
	public int curDelayTicks = 0;
	public int idleTickCount = 170;

	public int maxIdleTicks = 1700;
	public int maxDelayTicks = 16;

	public int speedLevelValue = 2;
	public int idleSpeedLevelValue = 100;

	public boolean hasFuelParticles = true;
	public boolean powered = true;

	private boolean isSpeedSet = false;

	private boolean isMultiBlock;

	public tile_entity_crop_harvester(BlockPos pos, BlockState state) {
		super(BlockEntityInit.TILE_ENTITY_CROP_HARVESTER.get(), pos, state);

	}

	public ItemStack getSizeStack() {
		return itemHandler.getStackInSlot(MachineConstants.SIZE_SLOT);
	}

	public ItemStack getSpeedStack() {
		return itemHandler.getStackInSlot(MachineConstants.SPEED_SLOT);
	}

	public ItemStack getSeedStack() {
		return itemHandler.getStackInSlot(MachineConstants.SEED_SLOT);
	}

	public void setSeedStack(ItemStack stack) {
		itemHandler.setStackInSlot(MachineConstants.SEED_SLOT, stack.copy());
	}

	public boolean addMachineCore(Item item, int index) {

		if (index < 0 || index > 1)
			return false;

		if (itemHandler.getStackInSlot(index).getItem() == item)
			return false;

		itemHandler.setStackInSlot(index, new ItemStack(item));

		setWorkAreaSize();
		setSpeed();
		setChanged();
		level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);

		GlobalFuncs.showInGameMessage("--------------------------");
		GlobalFuncs.showInGameMessage(machineData._machineName + " : " + getBlockPos());

		if (index == 0) {

			GlobalFuncs.showInGameMessage("Area size set to: " + machineData._sizeString);
		} else {

			GlobalFuncs.showInGameMessage("Machine speed set to: " + machineData._speedString);
		}

		return true;
	}

	private void setSpeed() {
		speed = MachineSpeedHandler.getHoeItemSpeedLevel(itemHandler.getStackInSlot(1).getItem());

		updateTickCountsBySpeed();

		machineData._speedString = (speed + 1) + "/7";
	}

	private void setWorkAreaSize() {
		workAreaSize = MachineAreaSizeHandler.getAreaSize(itemHandler.getStackInSlot(0).getItem());
		size = workAreaSize.level;

		if (isMultiBlock) {
			workArea = MachineAreaFunctions.getWorkAreaBlocks_multi(getLevel(), getBlockPos(),
					MachineAreaBox.box_crop_harvester, workAreaSize);
		} else {

			workArea = MachineAreaFunctions.getWorkAreaBlocks(getLevel(), getBlockPos(),
					MachineAreaBox.box_crop_harvester, workAreaSize);
		}

		workAreaIndex = 0;

		machineData._sizeString = workAreaSize.width + "x" + workAreaSize.depth;
	}

	private ItemStackHandler createItemHandler() {
		return new ItemStackHandler(3) {
			@Override
			protected void onContentsChanged(int slot) {
				// TODO Auto-generated method stub

				setChanged();

				if (slot == 0) {
					setWorkAreaSize();
				}

				if (slot == 1) {
					setSpeed();

				}

			}

		};
	}

	public void updateTickCountsBySpeed() {
		idleTickCount = maxIdleTicks - (speed * idleSpeedLevelValue);

		curDelayTicks = maxDelayTicks - (speed * speedLevelValue);
		nextTarget = curDelayTicks;
	}

	public void sendToClient() {
		if (level != null) {

			if (getSeedStack() == null)
				machineData._mainItemName = "";
			else
				machineData._mainItemName = BERFunctions.getItemName(getSeedStack());

			if (!powered) {
				machineData._toolTip = "No redstone signal!!";
				machineData._toolTipColor = Color.red;

			} else if (!hasFuelParticles) {
				machineData._toolTip = "No Fuel Particles!!!!";
				machineData._toolTipColor = Color.red;

			} else if (isWorking) {
				machineData._toolTip = "Working... " + tickPerc + "%";
				machineData._toolTipColor = Color.green;
			} else {
				machineData._toolTip = "Idle... " + tickPerc + "%";
				machineData._toolTipColor = Color.blue;
			}
		}

		level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);

	}

	public void tick() {
		// TODO Auto-generated method stub

		if (!level.isClientSide) {
			if (!isSpeedSet) {
				updateTickCountsBySpeed();
				isSpeedSet = true;
			}

			if (workAreaSize == null) {
				loadWorkAreaSize();
			}
			sendToClient();

			hasFuelParticles = checkIfHasFuel();

			if (workArea == null) {

				if (isMultiBlock) {
					workArea = MachineAreaFunctions.getWorkAreaBlocks_multi(getLevel(), getBlockPos(),
							MachineAreaBox.box_crop_harvester, workAreaSize);
				} else {

					workArea = MachineAreaFunctions.getWorkAreaBlocks(getLevel(), getBlockPos(),
							MachineAreaBox.box_crop_harvester, workAreaSize);
				}
			}

			else if (level.getDirectSignalTo(getBlockPos()) > 0) {

				powered = true;

				if (!isWorking) {
					curTick++;

					if (curTick % 20 == 0) {
						calcPerc(curTick, nextTarget);
					}

					if (curTick >= nextTarget) {
						isWorking = true;
						nextTarget = curDelayTicks;
						curTick = 0;

						tickPerc = 0;
					}
				} else {
					// check if there is charges
					if (hasFuelParticles) {
						curTick++;

						if (curTick >= nextTarget) {

							curTick = 0;

							if (isCropGrown()) {

								if (QdcApi.QDC_CORE.FUNCTIONS.removeParticleCollection(fuelParticles)) {
									harvestCrop();
								}
							}

							workAreaIndex++;

							calcPerc(workAreaIndex, workArea.size());

							if (workAreaIndex == workArea.size()) {
								workAreaIndex = 0;
								isWorking = false;
								curTick = 0;
								tickPerc = 0;
								nextTarget = idleTickCount;
							}

						}
					}
				}

			}
		}

	}

	private boolean checkIfHasFuel() {
		if (fuelParticles == null) {
			fuelParticles = new ParticleCollection();
			fuelParticles.addNatureParticles(QuantumFarming.CONSTANTS.MACHINES.FUEL_CHARGE);
		}

		if (QdcApi.QDC_CORE.FUNCTIONS.countCanMakeAmount(fuelParticles) > 0) {
			return true;
		}

		return false;
	}

	private List<ItemStack> getCropDrop(BlockPos pos) {
		List<ItemStack> res = new ArrayList<ItemStack>();

		BlockState curState = level.getBlockState(pos);

		res = Block.getDrops(curState, (ServerLevel) level, workArea.get(workAreaIndex),
				level.getBlockEntity(workArea.get(workAreaIndex)));

		return res;
	}

	private void harvestCrop() {
		List<ItemStack> res = getCropDrop(workArea.get(workAreaIndex));

		if(level.getBlockEntity(workArea.get(workAreaIndex)) instanceof QuantumCropBlockEntity be)
		{
			res = be.generateStuffToDrop();
		}
		
		level.removeBlock(workArea.get(workAreaIndex), true);

		if (res != null) {
			for (ItemStack is : res) {

				setSeedStack(is);
				QdcApi.QDC_CORE.FUNCTIONS.disassembleItems(is, is.getCount());
			}
		}

	}

	private boolean isCropGrown() {
		
		if(level.getBlockEntity(workArea.get(workAreaIndex)) instanceof QuantumCropBlockEntity be)
		{
			return be.isFullyGrown;
		}
		
		
		BlockState curBlockState = level.getBlockState(workArea.get(workAreaIndex));

		if (curBlockState == null)
			return false;

		if (curBlockState.getBlock() instanceof CropBlock cropBlock) {

			if (cropBlock.isMaxAge(curBlockState)) {
				return true;
			}

		}

		return false;
	}

	private void calcPerc(int cur, int max) {
		float f_cur = (float) cur;
		float f_max = (float) max;

		tickPerc = (int) (f_cur / f_max * 100);

	}

	private void loadWorkAreaSize() {
		workAreaSize = MachineAreaSizeHandler.getAreaSizeByLevel(size);

		if (workAreaSize == null) {
			workAreaSize = MachineAreaSizeHandler.getAreaSizeByLevel(0);
		}
	}

	private void dropStuffs(BlockPos pos) {
		NonNullList<ItemStack> list = NonNullList.create();
		list.add(new ItemStack(BlockInit.CROP_HARVESTER));

		Containers.dropContents(level, pos, list);

	}

	public void onRemove() {
		// TODO Auto-generated method stub

		dropStuffs(getBlockPos());

	}

	@Override
	protected void saveAdditional(CompoundTag output, Provider provider) {

		super.saveAdditional(output, provider);

		output.put("inventory", itemHandler.serializeNBT(provider));

		output.putInt("speed", speed);

		output.putInt("size", size);

		output.putInt("perc", tickPerc);

		machineData.save(output);

	}

	@Override
	protected void loadAdditional(CompoundTag input, Provider provider) {

		super.loadAdditional(input, provider);

		itemHandler.deserializeNBT(provider, input.getCompound("inventory"));

		speed = input.getInt("speed");

		size = input.getInt("size");
		loadWorkAreaSize();

		updateTickCountsBySpeed();

		tickPerc = input.getInt("perc");

		machineData.load(input);

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
