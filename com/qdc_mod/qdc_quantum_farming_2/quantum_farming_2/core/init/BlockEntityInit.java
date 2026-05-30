package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init;

import java.util.function.Supplier;

import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.QuantumCropBlockEntity;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.QuantumSaplingBlockEntity;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.tile_entity_crop_harvester;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.tile_entity_crop_planter;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.tile_entity_tree_harvester;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.tile_entity_tree_planter;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, QuantumFarming.MOD_ID);



    public static final Supplier<BlockEntityType<QuantumCropBlockEntity>> QUANTUM_CROP_BLOCKENTITY =
            BLOCK_ENTITIES.register("quantum_crop_block_entity", () -> BlockEntityType.Builder.of(
            		QuantumCropBlockEntity::new, BlockInit.QUANTUM_CROP_BLOCK.get()).build(null));
    
    

    public static final Supplier<BlockEntityType<QuantumSaplingBlockEntity>> QUANTUM_SAPLING_BLOCKENTITY =
            BLOCK_ENTITIES.register("quantum_sapling_block_entity", () -> BlockEntityType.Builder.of(
            		QuantumSaplingBlockEntity::new, 
            		BlockInit.QUANTUM_NATURE_SAPLING_BLOCK.get(),
            		BlockInit.QUANTUM_FOOD_SAPLING_BLOCK.get(),
            		BlockInit.QUANTUM_METAL_SAPLING_BLOCK.get(),
            		BlockInit.QUANTUM_GEM_SAPLING_BLOCK.get(),
            		BlockInit.QUANTUM_ENCHANTED_SAPLING_BLOCK.get(),
            		BlockInit.QUANTUM_POTION_SAPLING_BLOCK.get()
            		).build(null));
    
    
    
    
    public static final Supplier<BlockEntityType<tile_entity_crop_planter>> TILE_ENTITY_CROP_PLANTER =
            BLOCK_ENTITIES.register("tile_entity_crop_planter", () -> BlockEntityType.Builder.of(
            		tile_entity_crop_planter::new, BlockInit.CROP_PLANTER.get()).build(null));

    public static final Supplier<BlockEntityType<tile_entity_crop_harvester>> TILE_ENTITY_CROP_HARVESTER =
            BLOCK_ENTITIES.register("tile_entity_crop_harvester", () -> BlockEntityType.Builder.of(
            		tile_entity_crop_harvester::new, BlockInit.CROP_HARVESTER.get()).build(null));

    
    public static final Supplier<BlockEntityType<tile_entity_tree_planter>> TILE_ENTITY_TREE_PLANTER =
            BLOCK_ENTITIES.register("tile_entity_tree_planter", () -> BlockEntityType.Builder.of(
            		tile_entity_tree_planter::new, BlockInit.TREE_PLANTER.get()).build(null));
    
    public static final Supplier<BlockEntityType<tile_entity_tree_harvester>> TILE_ENTITY_TREE_HARVESTER =
            BLOCK_ENTITIES.register("tile_entity_tree_harvester", () -> BlockEntityType.Builder.of(
            		tile_entity_tree_harvester::new, BlockInit.TREE_HARVESTER.get()).build(null));
    
    
    
    

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
	
}
