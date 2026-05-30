package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.items;

import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.QuantumSaplingBlockEntity;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.BlockInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.ItemInit;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class QuantumEnchantedSapling extends BaseQuantumSaplingItem {

	public QuantumEnchantedSapling(Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult useOn(UseOnContext ctx) {
		// TODO Auto-generated method stub

		Level level = ctx.getLevel();

		if (!level.isClientSide) {

			BlockPos blockClickedPos = ctx.getClickedPos();

			BlockPos above = blockClickedPos.above();

			if (level.getBlockState(above).getBlock().asItem() == Items.AIR) {
				 level.setBlockAndUpdate(above,
				 BlockInit.QUANTUM_ENCHANTED_SAPLING_BLOCK.get().defaultBlockState());
				 
				if (level.getBlockEntity(above) instanceof QuantumSaplingBlockEntity entity) {

					 entity.setSapling(new ItemStack(ItemInit.QUANTUM_ENCHANTED_SAPLING.get()));

					ctx.getItemInHand().shrink(1);

					return InteractionResult.SUCCESS;

				}
			}

		}

		return InteractionResult.FAIL;
	}

}
