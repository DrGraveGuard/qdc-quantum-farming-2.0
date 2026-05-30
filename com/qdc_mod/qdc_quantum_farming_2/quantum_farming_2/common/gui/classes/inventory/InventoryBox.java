package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.inventory;

import java.awt.Point;
import java.util.List;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.INVENTORY;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

public class InventoryBox {

	public List<InventoryItem> inventoryItemList = INVENTORY.generateInventoryItemList();

	public InventoryItem curItemHover = null;
	public InventoryItem curClickedItem = null;


	
	public void updateCurDisassemblyItem()
	{
		if(curClickedItem != null)
		{
			if(curClickedItem.stack.getItem() == Items.AIR)
			{
				curClickedItem = null;
			}
		}
	}
	
	
	
	
	
	
	
	public InventoryBox(Player player) {

		refresh(player);
	}
	
	public void refresh(Player player)
	{
		for (int i = 0; i < 36; i++) {
			InventoryItem item = inventoryItemList.get(i);

			item.setStack(player.getInventory().getItem(item.actualIndex));
		}

	}

	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		
		curItemHover =null;
		
		for(InventoryItem item : inventoryItemList)
		{
			if(item.checkIfHoveringOver(windowPos, mouseX, mouseY))
			{
				curItemHover = item;
				break;
			}
		}
		
	}


}
