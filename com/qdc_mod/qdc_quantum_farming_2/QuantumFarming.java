package com.qdc_mod.qdc_quantum_farming_2;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.ModRegistry;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(QuantumFarming.MOD_ID)
public class QuantumFarming {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "qdc_quantum_farming_2";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Player curPlayer = null;
    
    public static ItemStack packetItem;
    
    public static String searchString = "";
    
    public class VARIABLES
    {
		public static final boolean IS_INSTA_GROW = false;
    }
    
    public class CONSTANTS
    {
    	public class CROP
    	{
    		public static final double GROWTH_STEP_PARTICLE_PERCENTAGE =0.01d;
    		public static final double BONUS_PARTICLE_PERCENTAGE =1.5d;
    	}
    	
    	public class SAPLING
    	{
    		public class DURATION_IN_MINUTES
    		{
    		public static final int NATURE = 3;
    		public static final int FOOD = 4;
    		public static final int METAL = 6;
    		public static final int GEM = 10;
    		public static final int ENCHANTED = 15;
    		public static final int POTION = 15;
    		}
    	}
    	
    	public class MACHINES
    	{
    		public static final double FUEL_CHARGE =0.01d;
    	}
    }
    

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public QuantumFarming(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        
    	ModRegistry.registerItems(modEventBus);
		ModRegistry.registerBlocks(modEventBus);
		ModRegistry.registerCreativeTabs(modEventBus);
		ModRegistry.registerMenus(modEventBus);
		ModRegistry.registerAttachments(modEventBus);
		ModRegistry.registerBlockEntities(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_CROP_BLOCK.get(), RenderType.cutout());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_NATURE_SAPLING_BLOCK.get(), RenderType.cutout());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_FOOD_SAPLING_BLOCK.get(), RenderType.cutout());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_METAL_SAPLING_BLOCK.get(), RenderType.cutout());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_GEM_SAPLING_BLOCK.get(), RenderType.cutout());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_ENCHANTED_SAPLING_BLOCK.get(), RenderType.cutout());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_POTION_SAPLING_BLOCK.get(), RenderType.cutout());
    	
    	
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_NATURE_TREE_LEAVES_BLOCK.get(), RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_FOOD_TREE_LEAVES_BLOCK.get(), RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_METAL_TREE_LEAVES_BLOCK.get(), RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_GEM_TREE_LEAVES_BLOCK.get(), RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_ENCHANTED_TREE_LEAVES_BLOCK.get(), RenderType.translucent());
    	ItemBlockRenderTypes.setRenderLayer(BlockInit.QUANTUM_POTION_TREE_LEAVES_BLOCK.get(), RenderType.translucent());
    }

    
	@SubscribeEvent
	public void onPlayerLoad(PlayerEvent.PlayerLoggedInEvent event) {

		curPlayer = event.getEntity();
	}
    
    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        
    }

    
    
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
}
