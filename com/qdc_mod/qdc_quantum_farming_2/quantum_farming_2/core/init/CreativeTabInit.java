package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init;

import java.util.function.Supplier;

import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabInit {

	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister
			.create(Registries.CREATIVE_MODE_TAB, QuantumFarming.MOD_ID);

	public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register(
			"qdc_quantum_farming_2_creative_tab",
			() -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemInit.QUANTUM_NATURE_SAPLING.get()))
					.title(Component
							.translatable("creativetab.qdc_quantum_farming_2.qdc_quantum_farming_2_creative_tab"))
					.displayItems((itemDisplayParameters, output) -> {
						output.accept(BlockInit.CROP_PLANTER.get());
						output.accept(BlockInit.CROP_HARVESTER.get());
						output.accept(BlockInit.TREE_PLANTER.get());
						output.accept(BlockInit.TREE_HARVESTER.get());
						output.accept(ItemInit.QUANTUM_NATURE_SAPLING.get());
						output.accept(ItemInit.QUANTUM_FOOD_SAPLING.get());
						output.accept(ItemInit.QUANTUM_METAL_SAPLING.get());
						output.accept(ItemInit.QUANTUM_GEM_SAPLING.get());
						output.accept(ItemInit.QUANTUM_ENCHANTED_SAPLING.get());
						output.accept(ItemInit.QUANTUM_POTION_SAPLING.get());

					}).build());

//    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB = CREATIVE_MODE_TAB.register("qdc_machines_creative_tab",
//            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ItemInit.QUANTUM_KNOWLEDGE.get()))
//                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Qdc.MOD_ID, "qdc_core_creative_tab"))
//                    .title(Component.translatable("creativetab.qdc_core_4.qdc_machines_creative_tab"))
//                    .displayItems((itemDisplayParameters, output) -> {
//                        output.accept(ModBlocks.BISMUTH_BLOCK);
//                        output.accept(ModBlocks.BISMUTH_ORE);
//
//                    }).build());

	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TAB.register(eventBus);
	}

}
