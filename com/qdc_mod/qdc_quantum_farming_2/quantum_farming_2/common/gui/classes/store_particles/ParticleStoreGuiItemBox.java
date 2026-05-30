package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.store_particles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.PARTICLE_STORE_WINDOW;

public class ParticleStoreGuiItemBox {

	public List<ParticleStoreGuiItem> particleList;

	public ParticleStoreGuiItem curHoverItem = null;
	
	public ParticleStoreGuiItemBox() {
		setup();

	}

	private void setup() {

		particleList = new ArrayList<ParticleStoreGuiItem>();
		
		addNewItem(ParticleType.NATURE,0);
		addNewItem(ParticleType.FOOD,1);
		addNewItem(ParticleType.METAL,2);
		addNewItem(ParticleType.GEM,3);
		addNewItem(ParticleType.ENCHANTED,4);
		addNewItem(ParticleType.POTION,5);
		
	}

	private void addNewItem(ParticleType type, int index)
	{
		particleList.add(new ParticleStoreGuiItem(type, PARTICLE_STORE_WINDOW.ITEMS.ITEM_POS_LIST.get(index)));
		
		
	}
	
	public void update()
	{
		if(particleList != null)
		for(ParticleStoreGuiItem item : particleList)
		{
			item.update();
		}
	}
	
	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		curHoverItem = null;
		
		if(particleList == null)
			return false;
		
		
		
		for(ParticleStoreGuiItem item : particleList)
		{
			if(item.checkIfHoveringOver(windowPos, mouseX, mouseY) != null)
			{
				curHoverItem = item;
				return true;
			}
		}

		
		return false;
	}
	
}
