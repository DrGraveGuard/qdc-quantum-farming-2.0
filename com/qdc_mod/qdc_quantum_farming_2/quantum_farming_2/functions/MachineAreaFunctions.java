package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

import java.util.ArrayList;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.boxes.MachineAreaBox.AreaBox;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.QuantumTreeLeavesBlock;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.QuantumTreeLogBlock;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.machines.BaseMachineBlock;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes.TreeHarvesterItem;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes.WorkAreaSize;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class MachineAreaFunctions {

	public static ArrayList<BlockPos> getTreeHarvesterWorkAreaBlocks_multi(Level world, BlockPos machinePos,
			AreaBox machineBox, WorkAreaSize areaSize) {
		ArrayList<BlockPos> basePosList = getWorkAreaBlocks(world, machinePos, machineBox, areaSize);

		return filterForLogsAndLeaves(world, basePosList);

	}

	private static ArrayList<BlockPos> filterForLogsAndLeaves(Level level, 
			ArrayList<BlockPos> workArea) {
		ArrayList<BlockPos> res = new ArrayList<BlockPos>();

		Item curBlockItem;

		for (BlockPos bp : workArea) {

			if (isValidLogOrLeaves(level, bp)) {
				res.add(bp);
			}
		}

		return res;
	}

	public static boolean isValidLogOrLeaves(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);

		
		boolean isLeaves = state.is(BlockTags.LEAVES);

		
		boolean isLog = state.is(BlockTags.LOGS);
		
		boolean isQuantumTreeBlock = false;
		
		if(state.getBlock() instanceof QuantumTreeLogBlock)
			isQuantumTreeBlock = true;
		
		if(state.getBlock() instanceof QuantumTreeLeavesBlock)
			isQuantumTreeBlock = true;
		
		

		return isLeaves || isLog || isQuantumTreeBlock;
	}

	
	
	public static ArrayList<BlockPos> getWorkAreaBlocks_multi(Level world, BlockPos machinePos, AreaBox machineBox,
			WorkAreaSize areaSize) {
		ArrayList<BlockPos> basePosList = getWorkAreaBlocks(world, machinePos, machineBox, areaSize);
		ArrayList<BlockPos> multiPosList = new ArrayList<BlockPos>();

		for (BlockPos bp : basePosList) {
			multiPosList.add(new BlockPos(bp.getX(), bp.getY() + 2, bp.getZ()));
			multiPosList.add(new BlockPos(bp.getX(), bp.getY() + 1, bp.getZ()));
		}

		return multiPosList;

	}

	public static ArrayList<BlockPos> getWorkAreaBlocks(Level world, BlockPos machinePos, AreaBox machineBox,
			WorkAreaSize areaSize) {
		Direction dir = world.getBlockState(machinePos).getValue(BaseMachineBlock.FACING);

		switch (dir) {
		case NORTH:
			return calc_North(machinePos, machineBox, areaSize);
		case SOUTH:
			return calc_South(machinePos, machineBox, areaSize);
		case WEST:
			return calc_West(machinePos, machineBox, areaSize);
		case EAST:
			return calc_East(machinePos, machineBox, areaSize);
		}

		return null;
	}

	private static ArrayList<BlockPos> calc_North(BlockPos machinePos, AreaBox machineBox, WorkAreaSize areaSize) {
		ArrayList<BlockPos> res = new ArrayList<BlockPos>();

		int midWidth = areaSize.width / 2;

		int xStart = machinePos.getX() - midWidth;

		for (int y = 0; y < machineBox.height; y++) {
			for (int z = 0; z < areaSize.depth; z++) {

				for (int x = 0; x < areaSize.width; x++) {
					res.add(new BlockPos(xStart + x, machinePos.getY() + y, (machinePos.getZ() - 1) - z));
				}

			}
		}

		return res;
	}

	private static ArrayList<BlockPos> calc_South(BlockPos machinePos, AreaBox machineBox, WorkAreaSize areaSize) {
		ArrayList<BlockPos> res = new ArrayList<BlockPos>();

		int midWidth = areaSize.width / 2;

		int xStart = machinePos.getX() - midWidth;

		for (int y = 0; y < machineBox.height; y++) {
			for (int z = 0; z < areaSize.depth; z++) {

				for (int x = 0; x < areaSize.width; x++) {
					res.add(new BlockPos(xStart + x, machinePos.getY() + y, machinePos.getZ() + (z + 1)));
				}

			}
		}

		return res;
	}

	private static ArrayList<BlockPos> calc_West(BlockPos machinePos, AreaBox machineBox, WorkAreaSize areaSize) {
		ArrayList<BlockPos> res = new ArrayList<BlockPos>();

		int midWidth = areaSize.width / 2;

		int zStart = machinePos.getZ() - midWidth;

		for (int y = 0; y < machineBox.height; y++) {
			for (int x = 0; x < areaSize.depth; x++) {

				for (int z = 0; z < areaSize.width; z++) {
					res.add(new BlockPos((machinePos.getX() - 1) - x, machinePos.getY() + y, zStart + z));
				}

			}
		}

		return res;
	}

	private static ArrayList<BlockPos> calc_East(BlockPos machinePos, AreaBox machineBox, WorkAreaSize areaSize) {
		ArrayList<BlockPos> res = new ArrayList<BlockPos>();

		int midWidth = areaSize.width / 2;

		int zStart = machinePos.getZ() - midWidth;

		for (int y = 0; y < machineBox.height; y++) {
			for (int x = 0; x < areaSize.depth; x++) {

				for (int z = 0; z < areaSize.width; z++) {
					res.add(new BlockPos(machinePos.getX() + (x + 1), machinePos.getY() + y, zStart + z));
				}

			}
		}

		return res;
	}

}
