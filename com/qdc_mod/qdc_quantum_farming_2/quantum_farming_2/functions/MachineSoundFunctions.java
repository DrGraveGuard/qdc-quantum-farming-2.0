package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;

public class MachineSoundFunctions {

	
	public static void playMachineCoreSound(Level level, BlockPos pos)
	{
		  level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.PISTON_EXTEND, SoundSource.BLOCKS, 1.0F, 1.0F);

	}
	
	
	public static void playMachineItemInsertedSound(Level level, BlockPos pos)
	{
		  level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);

	}
	
	public static void playEnchantSound(Level level, BlockPos pos)
	{
		  level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

	}
	
	public static void playToolSwapSound(Level level, BlockPos pos)
	{
		  level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

	}
	
	
}
