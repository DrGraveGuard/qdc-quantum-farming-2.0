package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

import com.qdc_mod.qdc_core_4_5.api.QdcApi;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;

public class MachineItemUseFunctions {


	
	public static int getCoreLevel(Item item)
	{

		if(item ==QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.WOOD)
		{
			return 1;
		}
		else if(item ==QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.STONE)
		{
			return 2;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.IRON)
		{
			return 3;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.GOLD)
		{
			return 4;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.DIAMOND)
		{
			return 5;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.EMERALD)
		{
			return 6;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.NETHERITE)
		{
			return 7;
		}
		
		return 1;

	}
	
	
	
	
	
	
	


	public static boolean isUseOnLeftSide(Vec3 hit, BlockPos pos, Direction dir) {

		switch (dir) {

		case EAST: {
			double z = hit.z;
			z = (z - (double) pos.getZ());


			if (z > 0.5d)
				return true;
			else
				return false;
		}
		case NORTH: {
			double x = hit.x;
			x = (x - (double) pos.getX());


			if (x > 0.5d)
				return true;
			else
				return false;
		}
		case SOUTH: {
			double x = hit.x;
			x = (x - (double) pos.getX());

			if (x < 0.5d)
				return true;
			else
				return false;
		}
		case WEST: {
			double z = hit.z;
			z = (z - (double) pos.getZ());


			if (z < 0.5d)
				return true;
			else
				return false;
		}

		}

		return false;
	}

}
