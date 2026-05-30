package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.MapCodec;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.QuantumCropBlockEntity;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

public class QuantumTreeLeavesBlock extends Block{

	
	public QuantumTreeLeavesBlock(Properties properties) {
		super(properties);
	}


}
