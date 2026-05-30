package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes;

import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.CONSTANTS;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.ParticleItemConversionFunctions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

public class DiscoveredItem {


	public ItemStack itemStack;
	public String name;
	public String id;

	public boolean isHoveringOver;
	public Point pos;




	
	public DiscoveredItem(ItemStack itemStack) {
		
		this.itemStack = itemStack;
		this.name = GlobalFuncs.getItemName(itemStack.getItem());
		this.id = GlobalFuncs.generateItemDataString(itemStack);
	}
	
	public void setPos(Point pos) {
		this.pos = new Point(pos);
	}

	public boolean isSameItem(DiscoveredItem other) {
		if (this.id.equals(other.id))
			return true;

		return false;
	}

	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		// in item list
		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, QuantumSeedCreatorScreenSettings.CONSTANTS.ITEMS.SIZE,
				new Point(mouseX, mouseY));

		return isHoveringOver;
	}

}
