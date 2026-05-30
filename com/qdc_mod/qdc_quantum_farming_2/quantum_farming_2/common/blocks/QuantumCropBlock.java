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
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

public class QuantumCropBlock extends BaseEntityBlock  implements EntityBlock {

	public static final MapCodec<QuantumCropBlock> CODEC = simpleCodec(QuantumCropBlock::new);

	public QuantumCropBlock(Properties properties) {
		super(properties);
	}

	
	@Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        // Create helper ensures your ticker matches the expected BlockEntityType
        return createTickerHelper(type,BlockEntityInit.QUANTUM_CROP_BLOCKENTITY.get(), QuantumCropBlockEntity::tick);
    }
	

	
	
	@Override
	public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest,
			FluidState fluid) {
		
		if (!level.isClientSide)
			if (level.getBlockEntity(pos) instanceof QuantumCropBlockEntity blockEntity) {
				blockEntity.doDropStuff();
			}
		
		
		return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
	}
	
	
	
	
	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
		return new QuantumCropBlockEntity(pPos, pState);
	}

	@Override
	protected RenderShape getRenderShape(BlockState pState) {
		return RenderShape.MODEL;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand jand, BlockHitResult hit) {

		if (!level.isClientSide)
			if (level.getBlockEntity(pos) instanceof QuantumCropBlockEntity blockEntity) {
				blockEntity.displayData();
			}
		
		
		return ItemInteractionResult.SUCCESS;

	}

}
