package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes;

import java.math.BigDecimal;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.ParticleItemConversionFunctions;

import net.minecraft.world.item.ItemStack;

public class QuantumCropBoxPaticleItem {

	public ParticleType type;
	public BigDecimal amount = new BigDecimal("0");
	public String stringVal = "0";

	public List<ItemStack> getParticleItemsToDrop()
	{
		return ParticleItemConversionFunctions.generateParticleItemsList(type, amount);
	}
	
	
	public void displayData()
	{
		if(!isEmpty())
		{
			GlobalFuncs.showInGameMessage(type + " " + stringVal);
		}
	}
	
	public void applyFullGrowthBonus() {
		amount = amount.multiply(new BigDecimal(QuantumFarming.CONSTANTS.CROP.BONUS_PARTICLE_PERCENTAGE));
		fixString();
	}
	
	public QuantumCropBoxPaticleItem(ParticleType type) {
		this.type = type;
	}
	
	public void setValue(double value)
	{
		this.amount = new BigDecimal(value);
		fixString();
	}
	
	public boolean isEmpty()
	{
		if(amount.compareTo(new BigDecimal("0")) == 0)
			return true;
		
		return false;
	}
	
	
	public void multuply(double multiplyAmount)
	{
		amount = amount.multiply(new BigDecimal(multiplyAmount));
		fixString();
	}
	
	public void increment(BigDecimal toAdd)
	{
		amount = amount.add(toAdd);
		fixString();
	}
	public void decrement(BigDecimal toRemove)
	{
		amount = amount.subtract(toRemove);
		fixString();
	}
	
	
	public void fixString()
	{
		this.stringVal = GlobalFuncs.formatNumber(amount,4);
	}
	
}
