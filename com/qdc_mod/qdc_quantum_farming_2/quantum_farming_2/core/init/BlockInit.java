package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init;


import java.util.function.Supplier;

import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.QuantumCropBlock;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.QuantumSaplingBlock;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.QuantumTreeLeavesBlock;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.QuantumTreeLogBlock;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.machines.CropHarvester;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.machines.CropPlanter;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.machines.TreeHarvester;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.machines.TreePlanter;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockInit {
	
	private static final float LOG_BREAK_SECONDS = 1.5f;
	private static final float LEAVES_BREAK_SECONDS = 1.0f;
	
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(QuantumFarming.MOD_ID);

    public static final DeferredBlock<QuantumCropBlock> QUANTUM_CROP_BLOCK = registerBlockNoItem("quantum_crop",
            () -> new QuantumCropBlock(BlockBehaviour.Properties.of()
                    .instabreak().explosionResistance(1000000).sound(SoundType.HARD_CROP).noOcclusion()));

    
    // NATURE
    
    public static final DeferredBlock<QuantumSaplingBlock> QUANTUM_NATURE_SAPLING_BLOCK = registerBlockNoItem("quantum_nature_sapling",
            () -> new QuantumSaplingBlock(BlockBehaviour.Properties.of()
                    .instabreak().explosionResistance(1000000).sound(SoundType.CHERRY_SAPLING).noOcclusion()));
    
    public static final DeferredBlock<QuantumTreeLogBlock> QUANTUM_NATURE_TREE_LOG_BLOCK = registerBlockNoItem("quantum_nature_tree_log_block",
            () -> new QuantumTreeLogBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LOG_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_WOOD)));
    
    public static final DeferredBlock<QuantumTreeLeavesBlock> QUANTUM_NATURE_TREE_LEAVES_BLOCK = registerBlockNoItem("quantum_nature_tree_leaves_block",
            () -> new QuantumTreeLeavesBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LEAVES_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_LEAVES).noOcclusion()));


    
    
    // FOOD
    
    public static final DeferredBlock<QuantumSaplingBlock> QUANTUM_FOOD_SAPLING_BLOCK = registerBlockNoItem("quantum_food_sapling",
            () -> new QuantumSaplingBlock(BlockBehaviour.Properties.of()
                    .instabreak().explosionResistance(1000000).sound(SoundType.CHERRY_SAPLING).noOcclusion()));
    
    public static final DeferredBlock<QuantumTreeLogBlock> QUANTUM_FOOD_TREE_LOG_BLOCK = registerBlockNoItem("quantum_food_tree_log_block",
            () -> new QuantumTreeLogBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LOG_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_WOOD)));
    
    public static final DeferredBlock<QuantumTreeLeavesBlock> QUANTUM_FOOD_TREE_LEAVES_BLOCK = registerBlockNoItem("quantum_food_tree_leaves_block",
            () -> new QuantumTreeLeavesBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LEAVES_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_LEAVES).noOcclusion()));
    
    
    
    
    // METAL
    
    public static final DeferredBlock<QuantumSaplingBlock> QUANTUM_METAL_SAPLING_BLOCK = registerBlockNoItem("quantum_metal_sapling",
            () -> new QuantumSaplingBlock(BlockBehaviour.Properties.of()
                    .instabreak().explosionResistance(1000000).sound(SoundType.CHERRY_SAPLING).noOcclusion()));
    
    public static final DeferredBlock<QuantumTreeLogBlock> QUANTUM_METAL_TREE_LOG_BLOCK = registerBlockNoItem("quantum_metal_tree_log_block",
            () -> new QuantumTreeLogBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LOG_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_WOOD)));
    
    public static final DeferredBlock<QuantumTreeLeavesBlock> QUANTUM_METAL_TREE_LEAVES_BLOCK = registerBlockNoItem("quantum_metal_tree_leaves_block",
            () -> new QuantumTreeLeavesBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LEAVES_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_LEAVES).noOcclusion()));
    
    
    
    // GEM
    
    public static final DeferredBlock<QuantumSaplingBlock> QUANTUM_GEM_SAPLING_BLOCK = registerBlockNoItem("quantum_gem_sapling",
            () -> new QuantumSaplingBlock(BlockBehaviour.Properties.of()
                    .instabreak().explosionResistance(1000000).sound(SoundType.CHERRY_SAPLING).noOcclusion()));
    
    public static final DeferredBlock<QuantumTreeLogBlock> QUANTUM_GEM_TREE_LOG_BLOCK = registerBlockNoItem("quantum_gem_tree_log_block",
            () -> new QuantumTreeLogBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LOG_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_WOOD)));
    
    public static final DeferredBlock<QuantumTreeLeavesBlock> QUANTUM_GEM_TREE_LEAVES_BLOCK = registerBlockNoItem("quantum_gem_tree_leaves_block",
            () -> new QuantumTreeLeavesBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LEAVES_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_LEAVES).noOcclusion()));
    
    
    
    
    
    // ENCHANTED
    
    public static final DeferredBlock<QuantumSaplingBlock> QUANTUM_ENCHANTED_SAPLING_BLOCK = registerBlockNoItem("quantum_enchanted_sapling",
            () -> new QuantumSaplingBlock(BlockBehaviour.Properties.of()
                    .instabreak().explosionResistance(1000000).sound(SoundType.CHERRY_SAPLING).noOcclusion()));
    
    public static final DeferredBlock<QuantumTreeLogBlock> QUANTUM_ENCHANTED_TREE_LOG_BLOCK = registerBlockNoItem("quantum_enchanted_tree_log_block",
            () -> new QuantumTreeLogBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LOG_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_WOOD)));
    
    public static final DeferredBlock<QuantumTreeLeavesBlock> QUANTUM_ENCHANTED_TREE_LEAVES_BLOCK = registerBlockNoItem("quantum_enchanted_tree_leaves_block",
            () -> new QuantumTreeLeavesBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LEAVES_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_LEAVES).noOcclusion()));
    
    
    
    
    // POTION
    
    public static final DeferredBlock<QuantumSaplingBlock> QUANTUM_POTION_SAPLING_BLOCK = registerBlockNoItem("quantum_potion_sapling",
            () -> new QuantumSaplingBlock(BlockBehaviour.Properties.of()
                    .instabreak().explosionResistance(1000000).sound(SoundType.CHERRY_SAPLING).noOcclusion()));
    
    public static final DeferredBlock<QuantumTreeLogBlock> QUANTUM_POTION_TREE_LOG_BLOCK = registerBlockNoItem("quantum_potion_tree_log_block",
            () -> new QuantumTreeLogBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LOG_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_WOOD)));
    
    public static final DeferredBlock<QuantumTreeLeavesBlock> QUANTUM_POTION_TREE_LEAVES_BLOCK = registerBlockNoItem("quantum_potion_tree_leaves_block",
            () -> new QuantumTreeLeavesBlock(BlockBehaviour.Properties.of()
                   .destroyTime(LEAVES_BREAK_SECONDS).explosionResistance(1000000).sound(SoundType.CHERRY_LEAVES).noOcclusion()));
    
    
    
    
    
    public static final DeferredBlock<CropPlanter> CROP_PLANTER = registerBlock("machine_crop_planter",
    		() -> new CropPlanter(BlockBehaviour.Properties.of()
                    .strength(4f).explosionResistance(1000000).sound(SoundType.AMETHYST)));


    
    public static final DeferredBlock<CropHarvester> CROP_HARVESTER = registerBlock("machine_crop_harvester",
            () -> new CropHarvester(BlockBehaviour.Properties.of()
                    .strength(4f).explosionResistance(1000000).sound(SoundType.AMETHYST)));


    
    // tree machines
    
    public static final DeferredBlock<TreePlanter> TREE_PLANTER = registerBlock("machine_tree_planter",
            () -> new TreePlanter(BlockBehaviour.Properties.of()
                    .strength(4f).explosionResistance(1000000).sound(SoundType.AMETHYST)));


    public static final DeferredBlock<TreeHarvester> TREE_HARVESTER = registerBlock("machine_tree_harvester",
            () -> new TreeHarvester(BlockBehaviour.Properties.of()
                    .strength(4f).explosionResistance(1000000).sound(SoundType.AMETHYST)));

    
    
    
    
    
    private static <T extends Block> DeferredBlock<T> registerBlockNoItem(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        
        return toReturn;
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
