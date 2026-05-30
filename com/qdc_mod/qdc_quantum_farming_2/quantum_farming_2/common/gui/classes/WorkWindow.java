package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.particle_box.ParticleGuiItemBox;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.functions.QuantumSeedCreationFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.screen.QuantumSeedCreatorScreen.GuiButton;

public class WorkWindow {

	public DiscoveredItem discoveredItem;
	public GuiButton btnOne;
	public GuiButton btnStack;

	public ParticleCollection particles;
	public ParticleGuiItemBox particleGuiItemBox;
	public int canMakeAmount = 0;

	public boolean haveEnoughParticles = false;

	public String onPlayerDuration;

	public WorkWindow(DiscoveredItem potionItem, GuiButton btnOne, GuiButton btnStack) {

		this.discoveredItem = potionItem;
		this.btnOne = btnOne;
		this.btnStack = btnStack;

		calcMakeParticles();

		btnOne.setVisibleState(true);
		btnOne.setEnabledState(true);

		btnStack.setVisibleState(true);
		btnStack.setEnabledState(true);

		refresh();

	}

	private void calcMakeParticles() {

		this.particles = QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(discoveredItem.itemStack);

	}

	public void refresh() {

		if (particles != null) {
			checkIfhaveEnoughParticles();

		}

		if (particleGuiItemBox == null)
			particleGuiItemBox = new ParticleGuiItemBox(particles, false);

		particleGuiItemBox.refresh();

	}

	private void checkIfhaveEnoughParticles() {
		canMakeAmount = QdcApi.QDC_CORE.FUNCTIONS.countCanMakeAmount(particles);

		if (canMakeAmount > 0) {
			haveEnoughParticles = true;
		} else {
			haveEnoughParticles = false;
		}

		btnOne.setEnabledState(haveEnoughParticles);
		btnStack.setEnabledState(haveEnoughParticles);
	}

	public boolean handleButtonClickMakeOne() {

		return QuantumSeedCreationFunctions.createOneSeed(discoveredItem.itemStack);

	}

	public boolean handleButtonClickMakeStack() {

		return QuantumSeedCreationFunctions.createSeedStack(discoveredItem.itemStack);

	}

}
