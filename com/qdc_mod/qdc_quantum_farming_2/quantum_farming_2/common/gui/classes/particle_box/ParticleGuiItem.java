package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.particle_box;

import java.awt.Color;
import java.awt.Point;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.GuiFunctions;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.WORK_WINDOW;

import net.minecraft.world.item.Item;

public class ParticleGuiItem {

	public ParticleType type;
	public double amount;
	public Item icon;
	public Point pos;
	public TextureColor bgColor;
	public Color textColor;
	public String strVal = "";

	public boolean isHoveringOver = false;
	boolean isDisassembler = false;

	public ParticleGuiItem(ParticleType type, double amount, Point pos, boolean isDisassembler) {

		this.type = type;
		this.amount = amount;
		this.pos = pos;

		this.strVal = GlobalFuncs.fixDouble(amount);

		this.icon = QdcApi.QDC_CORE.FUNCTIONS.getParticleIconItem(type);

		this.isDisassembler = isDisassembler;

		checkForColor();
	}

	public void checkForColor() {
		if (isDisassembler) {
			bgColor = WORK_WINDOW.PARTICLES.COLOR_DEFAULT;
			textColor = WORK_WINDOW.PARTICLES.TEXT_COLOR_DEFAULT;
		} else {

			if (QdcApi.QDC_CORE.FUNCTIONS.hasEnoughParticles(type, amount)) {
				bgColor = WORK_WINDOW.PARTICLES.COLOR_HAVE_ENOUGH;
				textColor = WORK_WINDOW.PARTICLES.TEXT_COLOR_HAVE;
			} else {
				bgColor = WORK_WINDOW.PARTICLES.COLOR_NOT_HAVE_ENOUGH;
				textColor = WORK_WINDOW.PARTICLES.TEXT_COLOR_NOT_HAVE;
			}
		}
	}

	public ParticleGuiItem checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {

		isHoveringOver = GuiFunctions.isHoveringOver(windowPos, pos, WORK_WINDOW.PARTICLES.SIZE,
				new Point(mouseX, mouseY));

		if (isHoveringOver)
			return this;

		return null;
	}
}
