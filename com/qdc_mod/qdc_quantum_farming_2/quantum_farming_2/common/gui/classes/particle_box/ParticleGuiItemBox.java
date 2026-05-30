package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.particle_box;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.ParticleItem;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.WORK_WINDOW;

public class ParticleGuiItemBox {

	public List<ParticleGuiItem> particleList;

	public ParticleGuiItem curHoverItem = null;
	
	public ParticleGuiItemBox(ParticleCollection particles, boolean isDisassembler) {
		extractData(particles, isDisassembler);

	}

	public void refresh()
	{
		for(ParticleGuiItem item : particleList)
		{
			item.checkForColor();
		}
	}
	
	private void extractData(ParticleCollection particles, boolean isDisassembler) {
		particleList = null;



		particleList = new ArrayList<ParticleGuiItem>();

		particles.setParticleList();

		int index = 0;

		for (ParticleItem item : particles.particleList) {
			Point curPos = WORK_WINDOW.PARTICLES.PARTICLE_POS_LIST.get(index);

			particleList.add(new ParticleGuiItem(item.type, item.amount, curPos, isDisassembler));

			index++;
		}

	}

	public boolean checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		curHoverItem = null;
		
		if(particleList == null)
			return false;
		
		
		
		for(ParticleGuiItem item : particleList)
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
