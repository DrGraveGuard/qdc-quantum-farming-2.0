package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer;

import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.QuantumCropBlockEntity;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.classes.PosePoint;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.functions.BERFunctions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class QuantumCropBER implements BlockEntityRenderer<QuantumCropBlockEntity> {
	
	private static final PosePoint PERC_POS = new PosePoint(-0, 5f);
	
	private final Font font;
	private final PosePoint lvlPos = new PosePoint(0, -17);
	private final PosePoint percPos = new PosePoint(0, 17);
	
	private float lookToPlayerAngle = 0;
	
	public QuantumCropBER(BlockEntityRendererProvider.Context context) {
		font = context.getFont();
	}

	@Override
	public void render(QuantumCropBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
			MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
		
		
		Level level = pBlockEntity.getLevel();
		if (level == null)
			return;
		
		
		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
		ItemStack stack = pBlockEntity.inventory.getStackInSlot(0);
		
		if(stack.isEmpty())
			return;

//		int lvl = pBlockEntity.mergeLevel;
//		String perc = pBlockEntity.percString;

		int light = getLightLevel(pBlockEntity.getLevel(), pBlockEntity.getBlockPos());

		BlockPos pos = pBlockEntity.getBlockPos();
		

		pPoseStack.pushPose();
		pPoseStack.translate(0.5f, 0.5f, 0.5f);
		
		float scale = (float)(pBlockEntity.growthPerc/100) + 0.1f;
		pPoseStack.scale(scale, scale, scale);
		itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, light, OverlayTexture.NO_OVERLAY, pPoseStack,
				pBufferSource, pBlockEntity.getLevel(), 1);
		
		
//		 scale = 0.25f;
//		pPoseStack.scale(scale, scale, scale);
//		pPoseStack.scale(scale, scale, scale);
//		
//		pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
//		
//		BERFunctions.writeText(PERC_POS, pBlockEntity.growthPercStr, Color.red, font, pPoseStack, pBufferSource, pPackedLight);
//		

		pPoseStack.popPose();
	}

	private int getLightLevel(Level level, BlockPos pos) {
		int bLight = level.getBrightness(LightLayer.BLOCK, pos);
		int sLight = level.getBrightness(LightLayer.SKY, pos);
		return LightTexture.pack(bLight, sLight);
	}


}