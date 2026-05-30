package com.qdc_mod.qdc_quantum_farming_2;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.CropHarvesterBlockEntityRenderer;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.CropPlanterBlockEntityRenderer;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.QuantumCropBER;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.TreeHarvesterBlockEntityRenderer;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.TreePlanterBlockEntityRenderer;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockEntityInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.network.ServerPayloadHandler;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.network.packets.myData.MyData;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

// This class will not load on dedicated servers. Accessing client side code from here is safe.
@Mod(value = QuantumFarming.MOD_ID, dist = Dist.CLIENT)
// You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
@EventBusSubscriber(modid = QuantumFarming.MOD_ID, value = Dist.CLIENT)
public class QuantumFarmingClient {
    public QuantumFarmingClient(ModContainer container) {
        // Allows NeoForge to create a config screen for this mod's configs.
        // The config screen is accessed by going to the Mods screen > clicking on your mod > clicking on config.
        // Do not forget to add translations for your config options to the en_us.json file.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        // Some client setup code
        QuantumFarming.LOGGER.info("HELLO FROM CLIENT SETUP");
        QuantumFarming.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
    
    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BlockEntityInit.QUANTUM_CROP_BLOCKENTITY.get(), QuantumCropBER::new);
        event.registerBlockEntityRenderer(BlockEntityInit.TILE_ENTITY_CROP_PLANTER.get(), CropPlanterBlockEntityRenderer::new);
    	event.registerBlockEntityRenderer(BlockEntityInit.TILE_ENTITY_CROP_HARVESTER.get(), CropHarvesterBlockEntityRenderer::new);
    	event.registerBlockEntityRenderer(BlockEntityInit.TILE_ENTITY_TREE_PLANTER.get(), TreePlanterBlockEntityRenderer::new);
    	event.registerBlockEntityRenderer(BlockEntityInit.TILE_ENTITY_TREE_HARVESTER.get(), TreeHarvesterBlockEntityRenderer::new);
    }
    
    @SubscribeEvent
	public static void register(final RegisterPayloadHandlersEvent event) {
		final PayloadRegistrar registrar = event.registrar("1");
		registrar.playBidirectional(MyData.TYPE, MyData.STREAM_CODEC, ServerPayloadHandler::handleInventoryDataOnMain);
	}
}
