package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class ParticleItemConversionFunctions {

	private static double wholeParticlesValue = -1;
	private static double fragmentParticlesValue = -1;


	public static List<ItemStack> generateParticleItemsList(ParticleType type, BigDecimal amount)
	{
		List<ItemStack> res = new ArrayList<ItemStack>();
		
		Item wholeParticleItem = QdcApi.QDC_CORE.FUNCTIONS.getParticleIconItem(type);
		Item fragParticleItem = QdcApi.QDC_CORE.FUNCTIONS.getParticleFragmenyIconItem(type);
		
		
				
				
		int[] particleItemsAmount = countItemParticlesNeeded(amount);
		
		res.addAll(generateStackList(wholeParticleItem, particleItemsAmount[0]));
		res.addAll(generateStackList(fragParticleItem, particleItemsAmount[1]));
		
		
		
		return res;
	}
	
	private static List<ItemStack> generateStackList(Item item, int count)
	{
		ItemStack stack = new ItemStack(item);
		
		int maxStackSize = stack.getMaxStackSize();
		
		int whole = (int)((double)count / (double) maxStackSize);
		
		int wholeCount = whole*maxStackSize;
		
		int rem  = count - wholeCount;
		
		List<ItemStack> res = new ArrayList<ItemStack>();
		
		for(int i =0;i<whole;i++)
		{
			res.add(new ItemStack(item, maxStackSize));
		}
		
		if(rem > 0)
		{
			res.add(new ItemStack(item, rem));
		}
		
		return res;
	}
	


	public static int[] countItemParticlesNeeded(BigDecimal amount) {
		
		if(wholeParticlesValue == -1)
			setupParticleValues();
		
		MathContext mc = new MathContext(2);
		
		int wholeParticleCount = amount.divide(new BigDecimal(wholeParticlesValue), mc).intValue();

		BigDecimal wholeTotalParticleAmount = new BigDecimal(wholeParticleCount).multiply(new BigDecimal(wholeParticlesValue), mc);
		
		BigDecimal reminader = amount.subtract(wholeTotalParticleAmount);

		
		int fragmentParticleCount = reminader.divide(new BigDecimal(fragmentParticlesValue), mc).intValue();
		
		
		return new int[] {wholeParticleCount,fragmentParticleCount};
		
		

	}
	
	private static void setupParticleValues()
	{
		ParticleCollection wholeParticles = QdcApi.QDC_CORE.FUNCTIONS
				.getItemParticles(new ItemStack(QdcApi.QDC_CORE.ITEMS.PARTICLE_ITEM.NATURE));
		wholeParticlesValue = wholeParticles.getParticles(ParticleType.NATURE);
		
		
		ParticleCollection fragmentParticles = QdcApi.QDC_CORE.FUNCTIONS
				.getItemParticles(new ItemStack(QdcApi.QDC_CORE.ITEMS.PARTICLE_FRAGMENT_ITEM.NATURE));
		fragmentParticlesValue = fragmentParticles.getParticles(ParticleType.NATURE);
	}

}
