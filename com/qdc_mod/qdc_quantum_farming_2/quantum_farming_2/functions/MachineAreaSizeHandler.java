package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;


import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes.WorkAreaSize;

import net.minecraft.world.item.Item;



public class MachineAreaSizeHandler {

	
	
	private static final WorkAreaSize noCore = new WorkAreaSize(0, 5, 5);
	private static final WorkAreaSize core_stone = new WorkAreaSize(1, 7,7 );
	private static final WorkAreaSize core_iron = new WorkAreaSize(2, 9,9 );
	private static final WorkAreaSize core_gold = new WorkAreaSize(3, 11,11 );
	private static final WorkAreaSize core_diamond = new WorkAreaSize(4, 13,13 );
	private static final WorkAreaSize core_emerald = new WorkAreaSize(5, 15,15 );
	private static final WorkAreaSize core_netherite = new WorkAreaSize(6, 17,17 );
	

	
	public static WorkAreaSize getAreaSizeByLevel(int level)
	{
		
		if(level == 0)
		{
			
		}
		else if(level == 1)
		{
			return core_stone;
		}
		else if(level == 2)
		{
			return core_iron;
		}
		else if(level == 3)
		{
			return core_gold;
		}
		else if(level == 4)
		{
			return core_diamond;
		}
		else if(level == 5)
		{
			return core_emerald;
		}
		else if(level == 6)
		{
			return core_netherite;
		}

		
		return noCore;
	}
	
	
	public static WorkAreaSize getAreaSize(Item item)
	{
		
		if(item ==QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.STONE)
		{
			return core_stone;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.IRON)
		{
			return core_iron;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.GOLD)
		{
			return core_gold;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.DIAMOND)
		{
			return core_diamond;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.EMERALD)
		{
			return core_emerald;
		}
		else if(item == QdcApi.QDC_CORE.ITEMS.MACHINE_CORES.NETHERITE)
		{
			return core_netherite;
		}

		
		return noCore;
	}
	
}
