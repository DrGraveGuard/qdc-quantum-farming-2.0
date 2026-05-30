package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.inventory;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.ItemInit;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class InventoryItem {

	public int displayIndex = -1;
	public int actualIndex = -1;
	public Point pos;
	public Point size;
	public ItemStack stack;
	public boolean isHoveringOver = false;



	public String id;

	public boolean isEmpty = false;

	public boolean isHoveringOverMainItem = false;
	private final int maxDisplayNameLen = 30;

	public InventoryItem(int displayIndex, int actualIndex, Point pos, Point size) {

		this.displayIndex = displayIndex;
		this.actualIndex = actualIndex;
		this.pos = pos;
		this.size = size;

	}

	public InventoryItem(ItemStack stack) {
		setStack(stack);
	}

	public boolean isQuantumSeed() {
		if (stack.getItem() == ItemInit.QUANTUM_SEED.get())
			return true;

		return false;
	}


	public void setStack(ItemStack is) {
		stack = is;

		if (stack.getItem() == Items.AIR)
			isEmpty = true;
		else
		{
			isEmpty = false;
		
			this.id = GlobalFuncs.generateItemDataString(is);
		}

	}

	public boolean isSame(InventoryItem toCompare) {
		if (isEmpty)
			return false;

		if (this.id.equals(toCompare.id))
		{
			if(isQuantumSeed())
			{
				if(stack.getDisplayName().getString().equals(toCompare.stack.getDisplayName().getString()))
					return true;
				else
					return false;
			}
			
			return true;
		}
		return false;
	}

	public boolean isSameDisplayIndex(int index) {
		if (this.displayIndex == index)
			return true;

		return false;
	}

	public boolean checkIfHoveringOver(Point windowPos, int x, int y) {
		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, size, new Point(x, y));

		return isHoveringOver;
	}

}
