package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.QuantumCropBlockEntity;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class QuantumSeed extends Item {

	public QuantumSeed(Properties properties) {
		super(properties);
	}


	@Override
    public Component getName(ItemStack stack) {
       
		ItemContainerContents contents = stack.get(DataComponents.CONTAINER);
        if (contents != null) {
                    	
        	return Component.literal("Quantum Seed " + contents.getStackInSlot(0).getDisplayName().getString());
        	
        }
        
        return super.getName(stack);
		
    }
	
	@Override
	public InteractionResult useOn(UseOnContext ctx) {
		// TODO Auto-generated method stub
		
		
		Level level = ctx.getLevel();
		
		if(!level.isClientSide)
		{
		
		BlockPos blockClickedPos = ctx.getClickedPos();
		
		BlockPos above = blockClickedPos.above();
		
		if(level.getBlockState(above).getBlock().asItem() == Items.AIR)
		{
			level.setBlockAndUpdate(above, BlockInit.QUANTUM_CROP_BLOCK.get().defaultBlockState());
			if(level.getBlockEntity(above) instanceof QuantumCropBlockEntity entity)
			{
				
				
				ItemContainerContents contents = ctx.getItemInHand().get(DataComponents.CONTAINER);
		        if (contents != null) {
		        	entity.setCropAndSeedItems(contents.getStackInSlot(0),ctx.getItemInHand());
		        	
		        	ctx.getItemInHand().shrink(1);
		        	
		        	return InteractionResult.SUCCESS;
		        }
				
				
			}
		}
		
		
		}
		
		
		
		
		
		return InteractionResult.FAIL;
	}
	
}
