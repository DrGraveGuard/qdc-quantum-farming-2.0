package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.blocks.machines;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;



public class BaseMachineBlock extends Block{


	public static final EnumProperty<Direction> FACING = BlockStateProperties.FACING;
	
	public BaseMachineBlock(Properties properties) {
		super(properties.noOcclusion());
		
		
		
		this.registerDefaultState(this.defaultBlockState().setValue(FACING , Direction.NORTH));
	
	}


	
	
	
	

	
	@SuppressWarnings("deprecation")
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
	}
	

	
	@Override
	public BlockState rotate(BlockState state, Rotation direction) {
		// TODO Auto-generated method stub
		return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		// TODO Auto-generated method stub
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
	}
	


	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
	}
}
