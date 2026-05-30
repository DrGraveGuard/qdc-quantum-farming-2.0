package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.machines;


import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.tile_entity_tree_planter;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockEntityInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineItemUseFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.functions.MachineSoundFunctions;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class TreePlanter extends BaseMachineBlock implements EntityBlock {

	public TreePlanter(Properties properties) {
		super(properties);
	}


	@Override
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
		// TODO Auto-generated method stub
		
		
		
		if(level.getBlockEntity(pos) instanceof tile_entity_tree_planter te)
		{
			te.onRemove();
		}
		
		
		super.onRemove(state, level, pos, newState, movedByPiston);
	}
	

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand hand, BlockHitResult hit) {

		if (!level.isClientSide) {

			BlockEntity be = level.getBlockEntity(pos);
			tile_entity_tree_planter curTileEntity = null;
				
			if(be instanceof tile_entity_tree_planter te)
			{
				curTileEntity = te;
			}
			
			if (MachineFunctions.isValidOptionTreePlanter(stack)) {
				if (curTileEntity.updateSeed(stack.getItem())) {
					MachineSoundFunctions.playMachineItemInsertedSound(level, pos);
				}
			}
			else 
			{
				if (MachineFunctions.isValidMachineCore(stack.getItem())) {
					if (MachineItemUseFunctions.isUseOnLeftSide(hit.getLocation(), pos, hit.getDirection())) {
						if (curTileEntity.addMachineCore(stack.getItem(), 0)) {
							MachineSoundFunctions.playMachineCoreSound(level, pos);
						}
					} else {
						if (curTileEntity.addMachineCore(stack.getItem(), 1)) {
							MachineSoundFunctions.playMachineCoreSound(level, pos);
						}
					}

				}
			}

		}

		return ItemInteractionResult.CONSUME;
	}


	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
			BlockEntityType<T> blockEntity) {

		if (level.isClientSide) {
			return null;
		}

		return (lvl, pos, blockState, t) -> {
			if (t instanceof tile_entity_tree_planter tile) {
				tile.tick();
			}
		};
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		// TODO Auto-generated method stub
		return BlockEntityInit.TILE_ENTITY_TREE_PLANTER.get().create(pos, state);
	}
}
