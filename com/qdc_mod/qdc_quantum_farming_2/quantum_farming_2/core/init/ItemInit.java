package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init;

import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.QuantumEnchantedSapling;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.QuantumFoodSapling;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.QuantumGemSapling;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.QuantumMetalSapling;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.QuantumNatureSapling;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.QuantumPotionSapling;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items.QuantumSeed;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemInit {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(QuantumFarming.MOD_ID);

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
	
	
	
	public static final DeferredItem<QuantumSeed> QUANTUM_SEED = ITEMS.register("quantum_seed",
			() -> new QuantumSeed(new Item.Properties()));
	
	

	public static final DeferredItem<QuantumNatureSapling> QUANTUM_NATURE_SAPLING = ITEMS.register("quantum_nature_sapling",
			() -> new QuantumNatureSapling(new Item.Properties()));
	
	public static final DeferredItem<QuantumFoodSapling> QUANTUM_FOOD_SAPLING = ITEMS.register("quantum_food_sapling",
			() -> new QuantumFoodSapling(new Item.Properties()));
	
	public static final DeferredItem<QuantumMetalSapling> QUANTUM_METAL_SAPLING = ITEMS.register("quantum_metal_sapling",
			() -> new QuantumMetalSapling(new Item.Properties()));
	
	public static final DeferredItem<QuantumGemSapling> QUANTUM_GEM_SAPLING = ITEMS.register("quantum_gem_sapling",
			() -> new QuantumGemSapling(new Item.Properties()));
	
	public static final DeferredItem<QuantumEnchantedSapling> QUANTUM_ENCHANTED_SAPLING = ITEMS.register("quantum_enchanted_sapling",
			() -> new QuantumEnchantedSapling(new Item.Properties()));
	
	public static final DeferredItem<QuantumPotionSapling> QUANTUM_POTION_SAPLING = ITEMS.register("quantum_potion_sapling",
			() -> new QuantumPotionSapling(new Item.Properties()));
	
	

}
