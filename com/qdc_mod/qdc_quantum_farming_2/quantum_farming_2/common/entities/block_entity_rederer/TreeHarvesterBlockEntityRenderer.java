package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity.tile_entity_tree_harvester;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.classes.PosePoint;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.entities.block_entity_rederer.functions.BERFunctions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class TreeHarvesterBlockEntityRenderer implements BlockEntityRenderer<tile_entity_tree_harvester> {

	private final Color TITLE_COLOR = BERFunctions.COLORS.TITLE_COLOR;
	private final Color TEXT_COLOR = BERFunctions.COLORS.TEXT_COLOR;
	private final Color MAIN_ITEN_NAME_COLOR = BERFunctions.COLORS.MAIN_ITEN_NAME_COLOR;
	
	
	private final float PLAYER_ANGLE_OFFSET = BERFunctions.PLAYER_ANGLE_OFFSET;

	private final BlockEntityRendererProvider.Context context;

	private float lookToPlayerAngle = 0;

	private String machineName = null;

	public TreeHarvesterBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
		this.context = ctx;
	}

	@Override
	public void render(@NotNull tile_entity_tree_harvester te, float pPartialTick, @NotNull PoseStack poseStack,
			@NotNull MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

		ItemStack mainStack = te.getSeedStack();
		ItemStack sizeStack = te.itemHandler.getStackInSlot(0);
		ItemStack speedStack = te.itemHandler.getStackInSlot(1);

		Level level = te.getLevel();
		if (level == null)
			return;

		this.machineName = te.machineData._machineName;

		Player player = Minecraft.getInstance().player;
		String toolTip = te.machineData._toolTip;
		Color toolTipColor = te.machineData._toolTipColor;
		Font font = this.context.getFont();
		BlockPos pos = te.getBlockPos().above();

		int packedLight = LightTexture.pack(level.getBrightness(LightLayer.BLOCK, pos),
				level.getBrightness(LightLayer.SKY, pos));

		lookToPlayerAngle = BERFunctions.calcToPlayerAngle(player, pos, PLAYER_ANGLE_OFFSET);

		poseStack.pushPose();

		poseStack.translate(0.5, 1.7f, 0.5);
		poseStack.mulPose(Axis.YN.rotationDegrees(lookToPlayerAngle));

		// draw background window
		drawBackground(poseStack, pBuffer, level, packedLight);

		// move stuff a bit to the front
		poseStack.translate(0, 0, 0.05F);

		// draw the icons
		drawIcons(mainStack, sizeStack, speedStack, poseStack, pBuffer, level, packedLight);

		// write the strings
		writeAllText(toolTip, te.machineData._sizeString, te.machineData._speedString,te.machineData._mainItemName, toolTipColor, font, poseStack,
				pBuffer, packedLight);

		poseStack.popPose();

	}

	private void drawBackground(PoseStack poseStack, MultiBufferSource buffer, Level level, int packedLight) {
		this.context.getItemRenderer().renderStatic(new ItemStack(QdcApi.QDC_CORE.ITEMS.MACHINE_ITEMS.MACHINE_SCREEN),
				ItemDisplayContext.FIXED, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, level, 0);

	}

	private final PosePoint mainIconPos = BERFunctions.POSITIONS.MAIN_ICON_POS;
	private final PosePoint sizeIconPos = BERFunctions.POSITIONS.SIZE_ICON_POS;
	private final PosePoint speedIconPos = BERFunctions.POSITIONS.SPEED_ICON_POS;

	private void drawIcons(ItemStack mainItemStack, ItemStack sizeStack, ItemStack speedStack, PoseStack ps,
			MultiBufferSource buffer, Level level, int packedLight) {

		float scale = 0.25f;
		ps.scale(scale, scale, scale);

		// draw main selection icon
		if (mainItemStack != null) {
			BERFunctions.drawIcon(mainIconPos, context, mainItemStack, ps, buffer, level, packedLight);
		}

		if (sizeStack.getItem() != Items.AIR) {
			BERFunctions.drawIcon(sizeIconPos, context, sizeStack, ps, buffer, level, packedLight);
		}

		if (speedStack.getItem() != Items.AIR) {
			BERFunctions.drawIcon(speedIconPos, context, speedStack, ps, buffer, level, packedLight);
		}

	}
	
	private final PosePoint mainItemNamePos = BERFunctions.POSITIONS.mainItemNamePos;
	private final PosePoint mainTextPos = BERFunctions.POSITIONS.mainTextPos;
	private final PosePoint machineNamePos = BERFunctions.POSITIONS.machineNamePos;
	private final PosePoint sizeTitlePos = BERFunctions.POSITIONS.sizeTitlePos;
	private final PosePoint speetTitlePos = BERFunctions.POSITIONS.speetTitlePos;
	private final PosePoint sizeValuePos = BERFunctions.POSITIONS.sizeValuePos;
	private final PosePoint speedValuePos =BERFunctions.POSITIONS.speedValuePos;

	private void writeAllText(String mainText, String sizeString, String speedString, String mainItemName, Color color, Font font,
			PoseStack ps, MultiBufferSource buffer, int packedLight) {

		float scale = 0.025f;
		ps.scale(scale, scale, scale);

		ps.mulPose(Axis.XP.rotationDegrees(180));

		BERFunctions.writeText(mainTextPos, mainText, color, font, ps, buffer, packedLight);
		BERFunctions.writeText(machineNamePos, machineName, TITLE_COLOR, font, ps, buffer, packedLight);

		if(!mainItemName.isEmpty())
		BERFunctions.writeText(mainItemNamePos, mainItemName, MAIN_ITEN_NAME_COLOR, font, ps, buffer, packedLight);

		BERFunctions.writeText(sizeTitlePos, "Size", TITLE_COLOR, font, ps, buffer, packedLight);
		BERFunctions.writeText(speetTitlePos, "Speed", TITLE_COLOR, font, ps, buffer, packedLight);

		BERFunctions.writeText(sizeValuePos, sizeString, TEXT_COLOR, font, ps, buffer, packedLight);
		BERFunctions.writeText(speedValuePos, speedString, TEXT_COLOR, font, ps, buffer, packedLight);

	}

}
