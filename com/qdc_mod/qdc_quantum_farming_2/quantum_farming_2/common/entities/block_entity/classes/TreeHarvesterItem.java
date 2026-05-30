package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes;

import net.minecraft.world.item.Item;

public class TreeHarvesterItem {

	
	public Item[] validItems = new Item[] {};
	
	public TreeHarvesterItem(Item[] validItems)
	{
		this.validItems = validItems.clone();
	}
	
	public boolean isValidItem(Item item)
	{
		for(Item i : validItems)
		{
			if(i == item)
				return true;
		}
		
		return false;
	}

}
