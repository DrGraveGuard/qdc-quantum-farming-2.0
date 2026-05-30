package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.BaseQuantumSaplingItem;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.QuantumSeed;

import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;

public class MachineFunctions {

	private static final List<Item> MACHINE_CORES = generateMachineCoreList();


	

	public static boolean isValidOptionTreePlanter(ItemStack stack) {
		
		if(stack.getItem() instanceof BaseQuantumSaplingItem)
			return true;
		
		
		return stack.is(ItemTags.SAPLINGS);
	}
	
	
	


	
	
	
	public static boolean isValidSeedOption(Item item) {

		if (item instanceof QuantumSeed qSeed) {
			return true;
		}
		

		
		Block blockToPlace = Block.byItem(item);

		if (blockToPlace != Blocks.AIR) {
			if (isCropBlock(blockToPlace)) {
				return true;
			}
		}

		return false;
	}

	public static boolean isCropBlock(Block block) {
		if (block instanceof CropBlock) {

			return true;
		}

		return false;
	}

	private static boolean isInArray(Item[] items, Item item) {
		for (Item i : items) {
			if (i == item)
				return true;
		}

		return false;
	}

	public static boolean isValidMachineCore(Item item) {
		for (Item i : MACHINE_CORES) {
			if (i == item)
				return true;
		}

		return false;
	}

	private static List<Item> generateMachineCoreList() {
		ArrayList<Item> res = new ArrayList<Item>();

		res.add(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.WOOD);
		res.add(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.STONE);
		res.add(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.IRON);
		res.add(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.GOLD);
		res.add(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.DIAMOND);
		res.add(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.EMERALD);
		res.add(QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.NETHERITE);

		return res;

	}

}
