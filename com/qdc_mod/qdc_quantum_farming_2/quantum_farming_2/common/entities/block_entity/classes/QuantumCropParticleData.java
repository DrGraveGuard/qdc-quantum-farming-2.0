package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection.ParticleType;
import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.ParticleItemConversionFunctions;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class QuantumCropParticleData {

	public QuantumCropBoxPaticleItem NATURE = new QuantumCropBoxPaticleItem(ParticleType.NATURE);
	public QuantumCropBoxPaticleItem FOOD = new QuantumCropBoxPaticleItem(ParticleType.FOOD);
	public QuantumCropBoxPaticleItem METAL = new QuantumCropBoxPaticleItem(ParticleType.METAL);
	public QuantumCropBoxPaticleItem GEM = new QuantumCropBoxPaticleItem(ParticleType.GEM);

	public QuantumCropBoxPaticleItem[] boxItemArray = new QuantumCropBoxPaticleItem[] { NATURE, FOOD, METAL, GEM };

	public String id;

	public QuantumCropParticleData(String id) {
		this.id = id;
	}

	public void displayParticles() {
		NATURE.displayData();
		FOOD.displayData();
		METAL.displayData();
		GEM.displayData();
	}

	public void applyFullGrowthBonus() {
		for (QuantumCropBoxPaticleItem item : boxItemArray) {

			if (!item.isEmpty()) {
				item.applyFullGrowthBonus();
			}
		}
	}

	public List<ItemStack> getParticleItemsToDrop() {
		List<ItemStack> res = new ArrayList<ItemStack>();

		for (QuantumCropBoxPaticleItem item : boxItemArray) {

			if (!item.isEmpty()) {
				res.addAll(item.getParticleItemsToDrop());
			}
		}

		return res;
	}

	public void set(ParticleCollection particles) {

		double perc = QuantumFarming.CONSTANTS.CROP.GROWTH_STEP_PARTICLE_PERCENTAGE;

		for (QuantumCropBoxPaticleItem item : boxItemArray) {
			double particleAmount = particles.getParticles(item.type);

			if (particleAmount > 0d) {
				item.setValue(particleAmount);
				item.multuply(perc);
			}
		}

	}

	public void increment(QuantumCropParticleData incrementData) {

		for (int i = 0; i < boxItemArray.length; i++) {
			if (!incrementData.boxItemArray[i].isEmpty()) {
				this.boxItemArray[i].increment(incrementData.boxItemArray[i].amount);
				this.boxItemArray[i].fixString();
			}
		}

	}

	public QuantumCropBoxPaticleItem getItemByType(ParticleType type) {
		for (QuantumCropBoxPaticleItem item : boxItemArray) {
			if (item.type == type)
				return item;
		}

		return null;
	}

	public void saveAdditional(CompoundTag output) {
		output.putString("nature_part_regen" + id, NATURE.amount.toString());
		output.putString("food_part_regen" + id, FOOD.amount.toString());
		output.putString("metal_part_regen" + id, METAL.amount.toString());
		output.putString("gem_part_regen" + id, GEM.amount.toString());

	}

	public void loadAdditional(CompoundTag input) {
		NATURE.amount = new BigDecimal(input.getString("nature_part_regen" + id));
		NATURE.fixString();

		FOOD.amount = new BigDecimal(input.getString("food_part_regen" + id));
		FOOD.fixString();

		METAL.amount = new BigDecimal(input.getString("metal_part_regen" + id));
		METAL.fixString();

		GEM.amount = new BigDecimal(input.getString("gem_part_regen" + id));
		GEM.fixString();

	}
}
