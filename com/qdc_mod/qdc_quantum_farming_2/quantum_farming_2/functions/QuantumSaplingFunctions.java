package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.ItemInit;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

public class QuantumSaplingFunctions {

	public static Block getTreeLogBlockFromSapling(ItemStack stack) {
		Item item = stack.getItem();
		
		
		if (item == ItemInit.QUANTUM_NATURE_SAPLING.get()) {
			return BlockInit.QUANTUM_NATURE_TREE_LOG_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_FOOD_SAPLING.get()) {
			return BlockInit.QUANTUM_FOOD_TREE_LOG_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_METAL_SAPLING.get()) {
			return BlockInit.QUANTUM_METAL_TREE_LOG_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_GEM_SAPLING.get()) {
			return BlockInit.QUANTUM_GEM_TREE_LOG_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_ENCHANTED_SAPLING.get()) {
			return BlockInit.QUANTUM_ENCHANTED_TREE_LOG_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_POTION_SAPLING.get()) {
			return BlockInit.QUANTUM_POTION_TREE_LOG_BLOCK.get();
		}

		return BlockInit.QUANTUM_NATURE_TREE_LOG_BLOCK.get();
	}

	public static Block getTreeLeavesBlockFromSapling(ItemStack stack) {

		Item item = stack.getItem();

		if (item == ItemInit.QUANTUM_NATURE_SAPLING.get()) {
			return BlockInit.QUANTUM_NATURE_TREE_LEAVES_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_FOOD_SAPLING.get()) {
			return BlockInit.QUANTUM_FOOD_TREE_LEAVES_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_METAL_SAPLING.get()) {
			return BlockInit.QUANTUM_METAL_TREE_LEAVES_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_GEM_SAPLING.get()) {
			return BlockInit.QUANTUM_GEM_TREE_LEAVES_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_ENCHANTED_SAPLING.get()) {
			return BlockInit.QUANTUM_ENCHANTED_TREE_LEAVES_BLOCK.get();
		}

		if (item == ItemInit.QUANTUM_POTION_SAPLING.get()) {
			return BlockInit.QUANTUM_POTION_TREE_LEAVES_BLOCK.get();
		}

		return BlockInit.QUANTUM_NATURE_TREE_LEAVES_BLOCK.get();
	}

	public static int getMaxGrowthDuration(ItemStack stack) {

		if(QuantumFarming.VARIABLES.IS_INSTA_GROW)
			return 3;
		
		Item item = stack.getItem();

		if (item == ItemInit.QUANTUM_NATURE_SAPLING.get()) {
			return QuantumFarming.CONSTANTS.SAPLING.DURATION_IN_MINUTES.NATURE*60;
		}
		
		if (item == ItemInit.QUANTUM_FOOD_SAPLING.get()) {
			return QuantumFarming.CONSTANTS.SAPLING.DURATION_IN_MINUTES.FOOD*60;
		}
		
		if (item == ItemInit.QUANTUM_METAL_SAPLING.get()) {
			return QuantumFarming.CONSTANTS.SAPLING.DURATION_IN_MINUTES.METAL*60;
		}
		
		if (item == ItemInit.QUANTUM_GEM_SAPLING.get()) {
			return QuantumFarming.CONSTANTS.SAPLING.DURATION_IN_MINUTES.GEM*60;
		}
		
		if (item == ItemInit.QUANTUM_ENCHANTED_SAPLING.get()) {
			return QuantumFarming.CONSTANTS.SAPLING.DURATION_IN_MINUTES.ENCHANTED*60;
		}
		
		if (item == ItemInit.QUANTUM_POTION_SAPLING.get()) {
			return QuantumFarming.CONSTANTS.SAPLING.DURATION_IN_MINUTES.POTION*60;
		}
		
		return QuantumFarming.CONSTANTS.SAPLING.DURATION_IN_MINUTES.NATURE*60;

	}

}
