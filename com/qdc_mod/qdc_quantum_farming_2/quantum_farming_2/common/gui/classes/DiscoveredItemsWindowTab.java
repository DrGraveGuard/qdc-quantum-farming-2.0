package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings;

import net.minecraft.world.item.ItemStack;

public class DiscoveredItemsWindowTab {

	public boolean hasSpace = true;
	
	public List<DiscoveredItem> itemList = new ArrayList<DiscoveredItem>();
	public int curIndex = 0;
	
	public DiscoveredItem curItemHover = null;
	
	public void add(DiscoveredItem potion)
	{
		
		
		if(itemList.size() < QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.ITEMS.ITEM_LIST_MAX)
		{
			itemList.add(potion);
			
			itemList.get(curIndex).setPos(QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.ITEMS.ITEM_POS_LIST.get(curIndex));
			
			
	
			if(itemList.size() == QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.ITEMS.ITEM_LIST_MAX)
			{
				hasSpace = false;
			}
			
			curIndex++;
		}

		
		
		
		
	}
	

	
	
	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		
		curItemHover = null;
		
		for(DiscoveredItem enc : itemList)
		{
			if(enc.checkIfHoveringOver(windowPos, mouseX, mouseY))
			{
				curItemHover = enc;
				break;
			}
		}
		
	}
	
}
