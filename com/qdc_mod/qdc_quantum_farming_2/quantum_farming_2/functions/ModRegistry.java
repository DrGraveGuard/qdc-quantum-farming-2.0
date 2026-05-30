package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockEntityInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.CreativeTabInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.ItemInit;

import net.neoforged.bus.api.IEventBus;

public class ModRegistry {

	public static void registerItems(IEventBus eventBus) {
		ItemInit.register(eventBus);
	}
	

	public static void registerBlocks(IEventBus eventBus) {
		BlockInit.register(eventBus);
	}

	public static void registerCreativeTabs(IEventBus eventBus) {
		CreativeTabInit.register(eventBus);
	}

	public static void registerMenus(IEventBus eventBus) {
		
	}
	
	public static void registerAttachments(IEventBus eventBus) {
		
	}
	
	public static void registerBlockEntities(IEventBus eventBus) {
		BlockEntityInit.register(eventBus);
	}
}
