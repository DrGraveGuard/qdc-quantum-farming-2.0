package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity;

import java.awt.Color;
import java.util.ArrayList;

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
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.BaseQuantumSaplingItem;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockEntityInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.ItemInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineAreaFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineAreaSizeHandler;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineSpeedHandler;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class tile_entity_tree_planter extends BlockEntity {

	public boolean isWorking = false;

	public MachineData machineData = new MachineData(Component.translatable("item.qdc_quantum_farming_2.machine_tree_planter").getString());

	public Item[] options = new Item[] { Items.OAK_SAPLING, Items.SPRUCE_SAPLING, Items.BIRCH_SAPLING,
			Items.JUNGLE_SAPLING, Items.ACACIA_SAPLING, Items.DARK_OAK_SAPLING, Items.CHERRY_SAPLING };

	public int speed = 0;
	public int size = 0;

	public WorkAreaSize workAreaSize;

	public Block placeSeedItem = null;

	public ParticleCollection seedParticles = null;
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

	public boolean hasFuel = false;
	public boolean hasItemParticles = false;
	public boolean powered = true;
	public boolean isInStorage = true;

	private boolean isSpeedSet = false;

	public tile_entity_tree_planter(BlockPos pos, BlockState state) {
		super(BlockEntityInit.TILE_ENTITY_TREE_PLANTER.get(), pos, state);

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

	public boolean updateSeed(Item item) {

		if (getSeedStack().getItem() == item)
			return false;

		itemHandler.setStackInSlot(MachineConstants.SEED_SLOT, new ItemStack(item));

		loadSeedItem();
		setChanged();

		GlobalFuncs.showInGameMessage("--------------------------");
		GlobalFuncs.showInGameMessage(machineData._machineName + " : " + getBlockPos());

		GlobalFuncs.showInGameMessage("Crop set to: " + BERFunctions.getItemName(getSeedStack()));

		seedParticles = null;
		
		return true;

	}

	private void setWorkAreaSize() {
		workAreaSize = MachineAreaSizeHandler.getAreaSize(itemHandler.getStackInSlot(0).getItem());
		size = workAreaSize.level;

		workArea = MachineAreaFunctions.getWorkAreaBlocks(getLevel(), getBlockPos(), MachineAreaBox.box_crop_planter,
				workAreaSize);

		workAreaIndex = 0;

		machineData._sizeString = workAreaSize.width + "x" + workAreaSize.depth;
	}

	private void setSpeed() {
		speed = MachineSpeedHandler.getHoeItemSpeedLevel(itemHandler.getStackInSlot(1).getItem());

		updateTickCountsBySpeed();

		machineData._speedString = (speed + 1) + "/7";
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

			@Override
			public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
				// TODO Auto-generated method stub
				return super.insertItem(slot, stack, simulate);
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

			if (!getSeedStack().isEmpty()) {

				machineData._mainItemName = BERFunctions.getItemName(getSeedStack());

				if (!powered) {
					machineData._toolTip = "No redstone signal!!";
					machineData._toolTipColor = Color.red;
				} else if (!isInStorage) {
					machineData._toolTip = "No Saplings in Storage!!!!";
					machineData._toolTipColor = Color.red;
				} else if (!hasFuel) {
					machineData._toolTip = "No Fuel Particles!!!!";
					machineData._toolTipColor = Color.red;
				} else if (!hasItemParticles) {
					machineData._toolTip = "No Item Particles!!!!";
					machineData._toolTipColor = Color.red;
				} else if (isWorking) {
					machineData._toolTip = "Working... " + tickPerc + "%";
					machineData._toolTipColor = Color.green;
				} else {
					machineData._toolTip = "Idle... " + tickPerc + "%";
					machineData._toolTipColor = Color.blue;
				}
			} else {
				machineData._toolTip = "No Sapling selected!!";
				machineData._toolTipColor = Color.red;

				machineData._mainItemName = "";
			}

			level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
		}
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

			hasFuel = checkIfHasFuel();

			hasItemParticles = checkIfHasItemParticles();

			sendToClient();
			if (!getSeedStack().isEmpty()) {
				powered = false;
				if (workArea == null) {

					workArea = MachineAreaFunctions.getWorkAreaBlocks(getLevel(), getBlockPos(),
							MachineAreaBox.box_crop_planter, workAreaSize);

				}
				if (seedParticles == null) {
					updateParticles();

				}

				if (level.getDirectSignalTo(getBlockPos()) > 0) {
					powered = true;
					if (!isWorking) {
						curTick++;

						if (curTick % 10 == 0) {
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
						if (checkIfHasItemParticles()) {
							curTick++;

							if (curTick >= nextTarget) {

								curTick = 0;

								if (hasItemParticles) {

									if (canPlaceSapling()) {
										level.setBlockAndUpdate(workArea.get(workAreaIndex),
												placeSeedItem.defaultBlockState());
										
										if (level.getBlockEntity(workArea.get(workAreaIndex)) instanceof QuantumSaplingBlockEntity entity) {

											 entity.setSapling(getSeedStack());
										}

										QdcApi.QDC_CORE.FUNCTIONS.removeParticleCollection(seedParticles);

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

	}

	private boolean canPlaceSapling() {
		Block below = level.getBlockState(workArea.get(workAreaIndex).below()).getBlock();
		Block sameLevel = level.getBlockState(workArea.get(workAreaIndex)).getBlock();

		if (!isSameBlock(below, Blocks.DIRT) && !isSameBlock(below, Blocks.GRASS_BLOCK))
			return false;

		if (!isSameBlock(sameLevel, Blocks.AIR))
			return false;

		return true;
	}

	private boolean isSameBlock(Block a, Block b) {
		if (a == b)
			return true;

		return false;
	}

	private void calcPerc(int cur, int max) {
		float f_cur = (float) cur;
		float f_max = (float) max;

		tickPerc = (int) (f_cur / f_max * 100);

		sendToClient();
	}

	private void loadWorkAreaSize() {
		workAreaSize = MachineAreaSizeHandler.getAreaSizeByLevel(size);

		if (workAreaSize == null) {
			workAreaSize = MachineAreaSizeHandler.getAreaSizeByLevel(0);
		}
	}

	private void loadSeedItem() {
		if (!getSeedStack().isEmpty()) {

			if (level != null) {
				level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
			}
			placeSeedItem = getSeedCropBlock(getSeedStack());

		} else {
			seedParticles = null;
			placeSeedItem = null;
		}

	}

	private Block getSeedCropBlock(ItemStack seedItem) {

		Item seed = seedItem.getItem();

		if (seed instanceof BaseQuantumSaplingItem) {
			if (seed == ItemInit.QUANTUM_NATURE_SAPLING.get()) {
				GlobalFuncs.msg("is nature sapling");

				return BlockInit.QUANTUM_NATURE_SAPLING_BLOCK.get();
			}

			if (seed == ItemInit.QUANTUM_FOOD_SAPLING.get())
				return BlockInit.QUANTUM_FOOD_SAPLING_BLOCK.get();

			if (seed == ItemInit.QUANTUM_METAL_SAPLING.get())
				return BlockInit.QUANTUM_METAL_SAPLING_BLOCK.get();

			if (seed == ItemInit.QUANTUM_GEM_SAPLING.get())
				return BlockInit.QUANTUM_GEM_SAPLING_BLOCK.get();

			if (seed == ItemInit.QUANTUM_ENCHANTED_SAPLING.get())
				return BlockInit.QUANTUM_ENCHANTED_SAPLING_BLOCK.get();

			if (seed == ItemInit.QUANTUM_POTION_SAPLING.get())
				return BlockInit.QUANTUM_POTION_SAPLING_BLOCK.get();

		}

		return Block.byItem(seedItem.getItem());

	}

	private void updateParticles() {
		seedParticles = QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(getSeedStack());

		if (seedParticles != null) {
			seedParticles = seedParticles.clone();
			seedParticles.addNatureParticles(QuantumFarming.CONSTANTS.MACHINES.FUEL_CHARGE);

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

	private boolean checkIfHasItemParticles() {
		if (seedParticles == null)
			return false;

		if (QdcApi.QDC_CORE.FUNCTIONS.countCanMakeAmount(seedParticles) > 0) {
			return true;
		}

		return false;
	}

	private void dropStuffs(BlockPos pos) {
		NonNullList<ItemStack> list = NonNullList.create();
		list.add(new ItemStack(BlockInit.TREE_PLANTER.get()));

		Containers.dropContents(level, pos, list);

	}

	public void onRemove() {
		// TODO Auto-generated method stub

		dropStuffs(getBlockPos());

	}

	@Override
	protected void saveAdditional(CompoundTag output, Provider provider) {

		output.put("inventory", itemHandler.serializeNBT(provider));

		output.putInt("speed", speed);

		output.putInt("size", size);

		output.putInt("perc", tickPerc);

		machineData.save(output);

		super.saveAdditional(output, provider);

	}

	@Override
	protected void loadAdditional(CompoundTag input, Provider provider) {

		itemHandler.deserializeNBT(provider, input.getCompound("inventory"));

		speed = input.getInt("speed");
		size = input.getInt("size");
		loadWorkAreaSize();

		loadSeedItem();
		updateTickCountsBySpeed();

		tickPerc = input.getInt("perc");

		machineData.load(input);

		super.loadAdditional(input, provider);
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
