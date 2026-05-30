package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.functions;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;

public class GuiSoundFunctions {

	
	public static void playClickSound(Player player)
	{
		player.playSound(SoundEvents.UI_BUTTON_CLICK.value(), 0.5f, 1.0f);
	}
	
	public static void playErrorSound(Player player)
	{
		player.playSound(SoundEvents.CREEPER_DEATH, 0.5f, 1.0f);
	}
	
	
}
