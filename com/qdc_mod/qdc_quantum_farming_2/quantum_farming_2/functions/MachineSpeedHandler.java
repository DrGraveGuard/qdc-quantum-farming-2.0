package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;


import com.qdc_mod.qdc_core_4_5.api.QdcApi;

import net.minecraft.world.item.Item;



public class MachineSpeedHandler {

	
	public static int getHoeItemSpeedLevel(Item item)
	{
		
		if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.STONE)
		{
			return 1;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.IRON)
		{
			return 2;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.GOLD)
		{
			return 3;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.DIAMOND)
		{
			return 4;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.EMERALD)
		{
			return 5;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.NETHERITE)
		{
			return 6;
		}

		
		return 0;
	}
	
}
