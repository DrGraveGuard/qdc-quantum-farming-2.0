package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;

public class QuantumCropFunctions {

	public static final int MAX_MINUTES = 25;
	public static final int MIN_MINUTES = 5;
	
	
	public static int getMaxTicksLimit()
	{
		if(QuantumFarming.VARIABLES.IS_INSTA_GROW)
			return 1;
		
		return 20;
	}
	
	
	public static int getMaxGrowthDuration(ItemStack stack)
	{
		
		
		int resMinutes = 0;
		
		int maxDamage = stack.getOrDefault(DataComponents.MAX_DAMAGE, 0);
		
		int minutes = (int) ((double)maxDamage / 60d);
		
		if(minutes < MIN_MINUTES)
			resMinutes = MIN_MINUTES;
		else if(minutes > MAX_MINUTES)
			resMinutes = MAX_MINUTES;
		else
			resMinutes = minutes;
		
		int resSeconds = resMinutes*60;
		
		
		
		return resSeconds;
	}
	
	
}
