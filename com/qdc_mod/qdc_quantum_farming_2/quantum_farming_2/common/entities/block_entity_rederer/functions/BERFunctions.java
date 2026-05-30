package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.functions;

import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.classes.PosePoint;

import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BERFunctions {

	public static final float PLAYER_ANGLE_OFFSET = -90f;

	public class COLORS {
		public static final Color TITLE_COLOR = Color.cyan;
		public static final Color TEXT_COLOR = Color.white;
		public static final Color MAIN_ITEN_NAME_COLOR = Color.yellow;
	}

	public class POSITIONS {
		public static final PosePoint MAIN_ICON_POS = new PosePoint(0, 0.9f);
		public static final PosePoint SIZE_ICON_POS = new PosePoint(-0.9f, -1.1f);
		public static final PosePoint SPEED_ICON_POS = new PosePoint(0.9f, -1.1f);

		public static final PosePoint mainItemNamePos = new PosePoint(0, -17f);
		public static final PosePoint mainTextPos = new PosePoint(0, -5f);
		public static final PosePoint machineNamePos = new PosePoint(0, -65);
		public static final PosePoint sizeTitlePos = new PosePoint(-35f, 5f);
		public static final PosePoint speetTitlePos = new PosePoint(35f, 5f);
		public static final PosePoint sizeValuePos = new PosePoint(-35f, 17);
		public static final PosePoint speedValuePos = new PosePoint(35f, 17);

		
		public class QUARRY
		{
			private static final float SIZE_CORE_ICON_WIDTH = 1f;
			private static final float SIZE_CORE_ICON_Y_POS = -1.75f;
			
			private static final float SIZE_CORE_TITLE_WIDTH = 28f;
			private static final float SIZE_CORE_TITLE_Y_POS = 15f;
			
			private static final float SIZE_CORE_VALUE_Y_POS = 25f;
			
			
			public static final PosePoint X_SIZE_ICON_POS = new PosePoint(-1.9f, SIZE_CORE_ICON_Y_POS);
			public static final PosePoint Y_SIZE_ICON_POS = new PosePoint(X_SIZE_ICON_POS.x+SIZE_CORE_ICON_WIDTH, SIZE_CORE_ICON_Y_POS);
			public static final PosePoint Z_SIZE_ICON_POS = new PosePoint(Y_SIZE_ICON_POS.x+SIZE_CORE_ICON_WIDTH, SIZE_CORE_ICON_Y_POS);
			
			public static final PosePoint X_SIZE_TITLE_POS = new PosePoint(-53f, SIZE_CORE_TITLE_Y_POS);
			public static final PosePoint Y_SIZE_TITLE_POS = new PosePoint(X_SIZE_TITLE_POS.x+SIZE_CORE_TITLE_WIDTH, SIZE_CORE_TITLE_Y_POS);
			public static final PosePoint Z_SIZE_TITLE_POS = new PosePoint(Y_SIZE_TITLE_POS.x+SIZE_CORE_TITLE_WIDTH, SIZE_CORE_TITLE_Y_POS);
			
			
			public static final PosePoint X_SIZE_VALUE_POS = new PosePoint(X_SIZE_TITLE_POS.x, SIZE_CORE_VALUE_Y_POS);
			public static final PosePoint Y_SIZE_VALUE_POS = new PosePoint(Y_SIZE_TITLE_POS.x, SIZE_CORE_VALUE_Y_POS);
			public static final PosePoint Z_SIZE_VALUE_POS = new PosePoint(Z_SIZE_TITLE_POS.x, SIZE_CORE_VALUE_Y_POS);
			
			
			
			
			public static final PosePoint SPEED_ICON_POS = new PosePoint(1.1f, -1.1f);
			
			
			public static final PosePoint MAIN_ICON_POS = new PosePoint(0, 0.9f);
			public static final PosePoint MAIN_ITEM_NAME_POS = new PosePoint(0, -17f);
			public static final PosePoint MAIN_TEXT_POS = new PosePoint(0, -5f);
			public static final PosePoint MACHINE_NAME_POS = new PosePoint(0, -65);
			

			public static final PosePoint SIZE_TITLE_POS = new PosePoint(-55f, 5f);
			public static final PosePoint SIZE_VALUE_POS = new PosePoint(-55f, 17);
			
			public static final PosePoint SPEED_TITLE_POS = new PosePoint(45f, 5f);			
			public static final PosePoint SPEED_VALUE_POS = new PosePoint(45f, 17);
		}
		
	}

	public static float calcToPlayerAngle(Player player, BlockPos pos, float offset) {
		float angle = getAngle(new PosePoint((float) player.position().x, (float) player.position().z),
				new PosePoint(pos.getX(), pos.getZ()));

		return angle + offset;
	}

	private static float getAngle(PosePoint targetA, PosePoint targetB) {
		float angle = (float) Math.toDegrees(Math.atan2(targetA.y - targetB.y, targetA.x - targetB.x));

		if (angle < 0) {
			angle += 360;
		}

		return angle;
	}

	public static void writeText(PosePoint pp, String toWrite, Color color, Font font, PoseStack ps,
			MultiBufferSource buffer, int packedLight) {

		float textWidth = font.width(toWrite) / 2;

		ps.translate(pp.x, pp.y, 0f);

		ps.translate(textWidth * -1, 0F, 0f);
		font.drawInBatch(toWrite, 0, 0, color.getRGB(), false, ps.last().pose(), buffer, Font.DisplayMode.NORMAL, 0,
				packedLight);

		ps.translate(textWidth, 0F, 0f);

		ps.translate(pp.getInverse().x, pp.getInverse().y, 0f);
	}


	public static void drawDecorationIcon(PosePoint pp, BlockEntityRendererProvider.Context ctx, ItemStack stack, PoseStack ps,
			MultiBufferSource buffer, Level level, int packedLight, float angle) {

		ps.translate(pp.x, pp.y, pp.z);
		
		ps.mulPose(Axis.ZP.rotationDegrees(angle +180));
		
		ctx.getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, packedLight, OverlayTexture.NO_OVERLAY, ps,
				buffer, level, 0);

		ps.mulPose(Axis.ZP.rotationDegrees(360 -(angle+180)));
		ps.translate(pp.getInverse().x, pp.getInverse().y, pp.getInverse().z);
	}
	
	public static void drawIcon(PosePoint pp, BlockEntityRendererProvider.Context ctx, ItemStack stack, PoseStack ps,
			MultiBufferSource buffer, Level level, int packedLight) {

		ps.translate(pp.x, pp.y, 0);
		ctx.getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, packedLight, OverlayTexture.NO_OVERLAY, ps,
				buffer, level, 0);

		ps.translate(pp.getInverse().x, pp.getInverse().y, 0);
	}

	public static String getItemName(ItemStack is) {
		if (is != null) {
			if (!is.isEmpty()) {
				return is.getDisplayName().getString();
			}
		}
		return "";
	}
}
