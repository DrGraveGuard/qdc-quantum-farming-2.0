package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.screen;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GuiDrawFunctions;
import com.qdc_mod.qdc_core_4_5.api.ModButton;
import com.qdc_mod.qdc_core_4_5.api.ModGuiTitle;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TextureColor;
import com.qdc_mod.qdc_core_4_5.api.QdcApi.QDC_CORE.ENUMS.TtleType;
import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.DiscoveredItem;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.DiscoveredItemsWindow;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.WorkWindow;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.inventory.InventoryBox;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.inventory.InventoryItem;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.particle_box.ParticleGuiItem;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.store_particles.ParticleStoreGuiItem;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes.store_particles.ParticleStoreGuiItemBox;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.functions.GuiSoundFunctions;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.GlobalSettings;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.CONSTANTS;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.INVENTORY;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.MAIN_TITLE;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.MAIN_WINDOW;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.WORK_WINDOW;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.settings.QuantumSeedCreatorScreenSettings.WORK_WINDOW.CAN_MAKE_SECTION;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class QuantumSeedCreatorScreen extends Screen {

	private final Point WINDOW_SIZE = MAIN_WINDOW.SIZE;
	private final Point WINDOW_POS = new Point(0, 0);
	private DiscoveredItemsWindow discoveredItemsWindow = null;
	private InventoryBox inventoryBox = null;
	private WorkWindow workWindow = null;

	ParticleStoreGuiItemBox particleStoreGuiItemBox = new ParticleStoreGuiItemBox();

	public enum btnType {

		BTN_PREV, BTN_NEXT, BTN_MAIN_MENU, BTN_MAKE_ONE, BTN_MAKE_STACK

	}

	public class GuiButton extends ModButton {

		public btnType type;

		public GuiButton(Point pos, Point size, String text, btnType type) {
			super(pos, size, text);

			this.type = type;
		}

	}

	public List<GuiButton> buttonList = null;
	public List<GuiButton> assemblerDisassemblerButtonList = null;
	private btnType curHoverItem = null;

	private GuiButton btnPrev = new GuiButton(QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.BUTTONS.PREV.POS,
			QuantumSeedCreatorScreenSettings.CONSTANTS.BUTTON.SIZE, "<", btnType.BTN_PREV);

	private GuiButton btnNext = new GuiButton(QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.BUTTONS.NEXT.POS,
			QuantumSeedCreatorScreenSettings.CONSTANTS.BUTTON.SIZE, ">", btnType.BTN_NEXT);

	private GuiButton btnMainMenu = new GuiButton(QuantumSeedCreatorScreenSettings.MAIN_MENU_BUTTON.POS,
			QuantumSeedCreatorScreenSettings.MAIN_MENU_BUTTON.SIZE, "Main Menu", btnType.BTN_MAIN_MENU);

	private GuiButton btnMakeOne = new GuiButton(QuantumSeedCreatorScreenSettings.WORK_WINDOW.BUTTON_MAKE_ONE.POS,
			QuantumSeedCreatorScreenSettings.WORK_WINDOW.BUTTON_MAKE_ONE.SIZE, "Create One Seed", btnType.BTN_MAKE_ONE);
	
	private GuiButton btnMakeStack = new GuiButton(QuantumSeedCreatorScreenSettings.WORK_WINDOW.BUTTON_MAKE_STACK.POS,
			QuantumSeedCreatorScreenSettings.WORK_WINDOW.BUTTON_MAKE_STACK.SIZE, "Create a Stack of Seeds", btnType.BTN_MAKE_STACK);

	private List<GuiButton> initGuiButtons() {
		List<GuiButton> res = new ArrayList<GuiButton>();

		res.add(btnPrev);

		res.add(btnNext);

		res.add(btnMainMenu);

		res.add(btnMakeOne);
		
		res.add(btnMakeStack);

		return res;
	}

	public void checkIfHoveringOverButton(int mouseX, int mouseY) {

		curHoverItem = null;

		for (GuiButton b : buttonList) {

			if (b.checkIfHoveringOver(WINDOW_POS, mouseX, mouseY)) {
				curHoverItem = b.type;
			}

		}

	}

	public QuantumSeedCreatorScreen() {
		super(Component.literal("QDC Quantum Potion Overload Screen"));

	}

	private final Point TXT_POS = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.SEARCH_WINDOW.POS;
	private final Point TXT_SIZE = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.SEARCH_WINDOW.SIZE;
	EditBox txtSearch;

	@Override
	protected void init() {

		discoveredItemsWindow = new DiscoveredItemsWindow(btnPrev, btnNext);

		this.WINDOW_POS.x = this.width / 2 - this.WINDOW_SIZE.x / 2;
		this.WINDOW_POS.y = this.height / 2 - this.WINDOW_SIZE.y / 2;

		buttonList = initGuiButtons();

		btnMakeOne.setVisibleState(false);
		btnMakeOne.setEnabledState(false);
		
		btnMakeStack.setVisibleState(false);
		btnMakeOne.setEnabledState(false);

		if (inventoryBox == null) {
			refreshInventory();
		}

		workWindow = null;

		this.txtSearch = new EditBox(this.font, this.WINDOW_POS.x + TXT_POS.x, this.WINDOW_POS.y + TXT_POS.y,
				TXT_SIZE.x, TXT_SIZE.y, Component.literal("txtSearch"));
		this.txtSearch.setCanLoseFocus(false);
		this.txtSearch.setTextColor(Color.white.getRGB());
		this.txtSearch.setBordered(true);
		this.txtSearch.setMaxLength(20);
		this.txtSearch.setResponder(this::onSearchChanged);
		this.txtSearch.setValue(QuantumFarming.searchString);
		this.addRenderableWidget(this.txtSearch);
		this.txtSearch.setEditable(true);

	}

	public void onSearchChanged(String search) {
		QuantumFarming.searchString = search;

		discoveredItemsWindow.doSearch();
	}

	private void refreshInventory() {
		inventoryBox = new InventoryBox(QuantumFarming.curPlayer);
	}

	@Override
	protected void setInitialFocus() {
		this.setInitialFocus(this.txtSearch);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 256) {
			this.minecraft.player.closeContainer();
		}

		return !this.txtSearch.keyPressed(keyCode, scanCode, modifiers) && !this.txtSearch.canConsumeInput()
				? super.keyPressed(keyCode, scanCode, modifiers)
				: true;
	}

	public void removed() {
		super.removed();
	}

	@Override
	public boolean isPauseScreen() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void render(GuiGraphics GuiGraphics, int x, int y, float partialTicks) {
		super.render(GuiGraphics, x, y, partialTicks);
		// TODO Auto-generated method stub

		this.WINDOW_POS.x = this.width / 2 - this.WINDOW_SIZE.x / 2;
		this.WINDOW_POS.y = this.height / 2 - this.WINDOW_SIZE.y / 2;

		drawStorageParticlesWindow(GuiGraphics);

		particleStoreGuiItemBox.checkIfHoveringOver(WINDOW_POS, x, y);

		drawMainWindow(GuiGraphics);

		checkIfHoveringOverButton(x, y);
		discoveredItemsWindow.checkIfHoveringOver(WINDOW_POS, x, y);
		inventoryBox.checkIfHoveringOver(WINDOW_POS, x, y);

		if (workWindow != null) {
			if (workWindow.particleGuiItemBox != null) {
				workWindow.particleGuiItemBox.checkIfHoveringOver(WINDOW_POS, x, y);
			}
		}

		drawToolTip(GuiGraphics, x, y);

	}

	private void drawStorageParticlesWindow(GuiGraphics gg) {

		for (ParticleStoreGuiItem item : particleStoreGuiItemBox.particleList) {
			writeString(gg, item.strVal, item.pos.x + 17, item.pos.y + 1, Color.white);

			drawItemIcon(gg, item.icon, item.pos.x + 1, item.pos.y - 3);
		}

	}

	private final TextureColor windowFillColor = GlobalSettings.WINDOW.WINDOW_FILL_COLOR;
	private final TextureColor windowBorderColor = GlobalSettings.WINDOW.WINDOW_BORDER_COLOR;

	public void drawMainWindow(GuiGraphics gg) {
		// draw main window

		drawMainTitle(gg);

		if (discoveredItemsWindow != null)
			drawDiscoveredItemsWindow(gg);

		drawSearchSection(gg);

		drawInventorySection(gg);
		
		if (workWindow != null)
			drawWorkWindow(gg);
		drawButtons(gg);

		tickCurDataItem();
	}

	private void tickCurDataItem() {

	}

	private void drawToolTip(GuiGraphics gg, int x, int y) {

		if (discoveredItemsWindow != null) {
			if (discoveredItemsWindow.curWindowTab != null) {
				if (discoveredItemsWindow.curWindowTab.curItemHover != null) {
					drawToolTipWindow(gg, discoveredItemsWindow.curWindowTab.curItemHover.itemStack, x, y);
				}
			}
		}

		if (inventoryBox.curItemHover != null) {
			drawToolTipWindow(gg, inventoryBox.curItemHover.stack, x, y);
		}

		if (workWindow != null) {
			if (workWindow.particleGuiItemBox != null) {
				if (workWindow.particleGuiItemBox.curHoverItem != null) {
					drawToolTipWindow(gg, new ItemStack(workWindow.particleGuiItemBox.curHoverItem.icon), x, y);
				}
			}
		}

		if (particleStoreGuiItemBox != null) {
			if (particleStoreGuiItemBox.curHoverItem != null) {
				drawToolTipWindow(gg, new ItemStack(particleStoreGuiItemBox.curHoverItem.icon), x, y);
			}
		}

	}

	public void drawButtons(GuiGraphics gg) {
		for (GuiButton button : buttonList) {

			drawButton(gg, button);
		}
	}

	private final Point MAIN_TITLE_POS = MAIN_TITLE.POS;
	private final Point MAIN_TITLE_SIZE = MAIN_TITLE.SIZE;

	private final ModGuiTitle mainTitleObject = new ModGuiTitle(TtleType.MAIN_TITLE, "QDC Quantum Seed Creator",
			MAIN_TITLE_POS, MAIN_TITLE_SIZE);

	public void drawMainTitle(GuiGraphics gg) {

		drawTitle(gg, mainTitleObject);

	}

	private final Point SEARCH_POS = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.SEARCH_WINDOW.POS;
	private final Point SEARCH_SIZE = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.SEARCH_WINDOW.SIZE;

	public void drawSearchSection(GuiGraphics gg) {
		// draw main window
		drawRectangleWithBorder(gg, SEARCH_POS, SEARCH_SIZE, windowFillColor, windowBorderColor);

	}

	private final Point ITEM_LIST_WINDOW_TITLE_POS = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.TITLE.POS;
	private final Point ITEM_LIST_WINDOW_TITLE_SIZE = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.TITLE.SIZE;

	private final Point ITEM_LIST_WINDOW_WINDOW_POS = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.POS;
	private final Point ITEM_LIST_WINDOW_WINDOW_SIZE = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.SIZE;

	private final Point ITEM_LIST_WINDOW_TAB_INDEX_POS = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.TAB_INDEX.POS;
	private final Color ITEM_LIST_WINDOW_TAB_INDEX_TEXT_COLOR = QuantumSeedCreatorScreenSettings.DISCOVERED_ITEMS_WINDOW.TAB_INDEX.TEXT_COLOR;

	private final Point ITEM_SIZE = CONSTANTS.ITEMS.SIZE;

	private final TextureColor ITEM_LIST_WINDOW_ITEM_BORDER_COLOR = CONSTANTS.ITEMS.BORDER_COLOR;
	private final TextureColor ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER = CONSTANTS.ITEMS.BORDER_COLOR_HOVER;
	private final TextureColor ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_ACTIVE = CONSTANTS.ITEMS.BORDER_COLOR_ACTIVE;

	private final TextureColor DISCOVERED_ITEM_BG_COLOR = CONSTANTS.ITEMS.BG_COLOR;

	private final ModGuiTitle mainTitleObj = new ModGuiTitle(TtleType.MAIN_TITLE, "Discovered Tools and Weapons",
			ITEM_LIST_WINDOW_TITLE_POS, ITEM_LIST_WINDOW_TITLE_SIZE);

	public void drawDiscoveredItemsWindow(GuiGraphics gg) {
		// draw main window
		drawRectangleWithBorder(gg, ITEM_LIST_WINDOW_WINDOW_POS, ITEM_LIST_WINDOW_WINDOW_SIZE, windowFillColor,
				windowBorderColor);

		// draw title

		drawTitle(gg, mainTitleObj);

		// draw tab index
		writeStringCentred(gg, discoveredItemsWindow.curWindowTabIndex + "/" + discoveredItemsWindow.windowTabCount,
				ITEM_LIST_WINDOW_TAB_INDEX_POS.x, ITEM_LIST_WINDOW_TAB_INDEX_POS.y,
				ITEM_LIST_WINDOW_TAB_INDEX_TEXT_COLOR);

		if (discoveredItemsWindow.curWindowTab != null) {
			if (discoveredItemsWindow.curWindowTab.itemList != null) {

				for (DiscoveredItem ai : discoveredItemsWindow.curWindowTab.itemList) {

					if (workWindow != null) {
						if (ai.isSameItem(workWindow.discoveredItem)) {
							drawRectangle(gg, ai.pos.x - 2, ai.pos.y - 2, ITEM_SIZE.x + 4, ITEM_SIZE.y + 4,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_ACTIVE);

							drawRectangle(gg, ai.pos.x - 1, ai.pos.y - 1, ITEM_SIZE.x + 2, ITEM_SIZE.y + 2,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
						}
					}

					if (discoveredItemsWindow.isSameHoverItem(ai)) {

						{
							drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE, DISCOVERED_ITEM_BG_COLOR,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);
						}

					} else {

						{
							drawRectangleWithBorder(gg, ai.pos, ITEM_SIZE, DISCOVERED_ITEM_BG_COLOR,
									ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
						}

					}

					drawItemIcon(gg, ai.itemStack, ai.pos.x + 1, ai.pos.y + 1);
				}
			}
		}

	}

	private final Point WORK_WINDOW_POS = QuantumSeedCreatorScreenSettings.WORK_WINDOW.POS;
	private final Point WORK_WINDOW_SIZE = QuantumSeedCreatorScreenSettings.WORK_WINDOW.SIZE;

	private final Point WORK_WINDOW_TITLE_POS = QuantumSeedCreatorScreenSettings.WORK_WINDOW.WORK_WINDOW_TITLE.POS;
	private final Point WORK_WINDOW_TITLE_SIZE = QuantumSeedCreatorScreenSettings.WORK_WINDOW.WORK_WINDOW_TITLE.SIZE;

	private final ModGuiTitle workTitleObj = new ModGuiTitle(TtleType.MAIN_TITLE, "Create Quantum Seed",
			WORK_WINDOW_TITLE_POS, WORK_WINDOW_TITLE_SIZE);

	public void drawWorkWindow(GuiGraphics gg) {
		// draw main window

		if (workWindow != null) {

			drawRectangleWithBorder(gg, WORK_WINDOW_POS, WORK_WINDOW_SIZE, windowFillColor, windowBorderColor);

			drawTitle(gg, workTitleObj);

			drawMainItem(gg);

			drawItemParticlesWindow(gg);

			drawCanMake(gg);

		}

	}

	private final Point INFO_ITEM_MAIN_ITEM_WINDOW_POS = QuantumSeedCreatorScreenSettings.WORK_WINDOW.MAIN_ITEM.POS;
	private final Point INFO_ITEM_MAIN_ITEM_WINDOW_SIZE = QuantumSeedCreatorScreenSettings.WORK_WINDOW.MAIN_ITEM.SIZE;
	private final Point INFO_ITEM_NAME_POS = QuantumSeedCreatorScreenSettings.WORK_WINDOW.MAIN_ITEM.NAME.TEXT_POS;
	private final Color INFO_ITEM_NAME_COLOR = QuantumSeedCreatorScreenSettings.WORK_WINDOW.MAIN_ITEM.NAME.TEXT_COLOR;

	private final Point INFO_ITEM_ICON_POS = QuantumSeedCreatorScreenSettings.WORK_WINDOW.MAIN_ITEM.ICON.POS;
private final TextureColor INFO_ITEM_MAIN_ITEM_DEFAULT_BG = QuantumSeedCreatorScreenSettings.WORK_WINDOW.MAIN_ITEM.COLOR_DEFAULT;

	public void drawMainItem(GuiGraphics gg) {

		drawRectangleWithBorder(gg, INFO_ITEM_MAIN_ITEM_WINDOW_POS, INFO_ITEM_MAIN_ITEM_WINDOW_SIZE,
				INFO_ITEM_MAIN_ITEM_DEFAULT_BG, windowBorderColor);

		drawItemIcon(gg, workWindow.discoveredItem.itemStack, INFO_ITEM_ICON_POS.x, INFO_ITEM_ICON_POS.y);

		writeString(gg, workWindow.discoveredItem.name, INFO_ITEM_NAME_POS.x, INFO_ITEM_NAME_POS.y,
				INFO_ITEM_NAME_COLOR);

		

	}

	private final Point INFO_WINDOW_PARTICLES_TITLE_POS = WORK_WINDOW.PARTICLES.TITLE.POS;
	private final Point INFO_WINDOW_PARTICLES_TITLE_SIZE = WORK_WINDOW.PARTICLES.TITLE.SIZE;

	private final ModGuiTitle particleTitleObj_assembler = new ModGuiTitle(TtleType.SUB_TITLE, "Particles Needed:",
			INFO_WINDOW_PARTICLES_TITLE_POS, INFO_WINDOW_PARTICLES_TITLE_SIZE);

	private void drawItemParticlesWindow(GuiGraphics gg) {
		if (workWindow.particleGuiItemBox != null) {

			drawTitle(gg, particleTitleObj_assembler);

			for (ParticleGuiItem item : workWindow.particleGuiItemBox.particleList) {
				drawParticleSegment(gg, item);
			}
		}

	}

	private void drawParticleSegment(GuiGraphics gg, ParticleGuiItem particleItem) {

		drawRectangleWithBorder(gg, particleItem.pos, WORK_WINDOW.PARTICLES.SIZE, particleItem.bgColor,
				windowBorderColor);

		writeString(gg, particleItem.strVal, particleItem.pos.x + 18, particleItem.pos.y + 5, particleItem.textColor);

		drawItemIcon(gg, particleItem.icon, particleItem.pos.x + 1, particleItem.pos.y + 1);
	}

	private final Point CAN_MAKE_TITLE_POS = CAN_MAKE_SECTION.POS;
	private final Point CAN_MAKE_TITLE_SIZE = CAN_MAKE_SECTION.SIZE;

	private final Point CAN_MAKE_TEXT_POS = CAN_MAKE_SECTION.TEXT_POS;

	private final Color CAN_MAKE_TEXT_COLOR = CAN_MAKE_SECTION.TEXT_COLOR;

	private final ModGuiTitle canMakeTitleObj = new ModGuiTitle(TtleType.SUB_TITLE, "Can Make:", CAN_MAKE_TITLE_POS,
			CAN_MAKE_TITLE_SIZE);

	private void drawCanMake(GuiGraphics gg) {

		if (workWindow.discoveredItem != null) {
			drawTitle(gg, canMakeTitleObj);

			writeString(gg, workWindow.canMakeAmount + "", CAN_MAKE_TEXT_POS.x, CAN_MAKE_TEXT_POS.y,
					CAN_MAKE_TEXT_COLOR);

		}

	}

	private final Point INVENTORY_WINDOW_POS = INVENTORY.POS;
	private final Point INVENTORY_WINDOW_SIZE = INVENTORY.SIZE;

	private final Point INVENTORY_TITLE_POS = INVENTORY.TITLE.POS;
	private final Point INVENTORY_TITLE_SIZE = INVENTORY.TITLE.SIZE;

	private final ModGuiTitle inventoryTitleObject = new ModGuiTitle(TtleType.MAIN_TITLE, "Inventory",
			INVENTORY_TITLE_POS, INVENTORY_TITLE_SIZE);

	public void drawInventorySection(GuiGraphics gg) {
		// draw main window
		drawRectangleWithBorder(gg, INVENTORY_WINDOW_POS, INVENTORY_WINDOW_SIZE, windowFillColor, windowBorderColor);

		drawTitle(gg, inventoryTitleObject);

		for (InventoryItem item : inventoryBox.inventoryItemList) {

			if (inventoryBox.curItemHover != null) {
				if (item.isSame(inventoryBox.curItemHover)) {

					drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.GRAY_1,
							ITEM_LIST_WINDOW_ITEM_BORDER_COLOR_HOVER);

				} else {
					if (item.isQuantumSeed()) {
						drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.BLUE_2,
								ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
					}
				}

			} else {
				if (item.isQuantumSeed()) {
					drawRectangleWithBorder(gg, item.pos, ITEM_SIZE, TextureColor.BLUE_2,
							ITEM_LIST_WINDOW_ITEM_BORDER_COLOR);
				}
			}

			drawItemIcon(gg, item.stack, item.pos.x + 1, item.pos.y + 1, item.stack.getCount());
		}

	}

	private void drawTitle(GuiGraphics gg, ModGuiTitle titleObj) {

		GuiDrawFunctions.drawTitle(gg, font, WINDOW_POS, titleObj);

	}

	private void drawButton(GuiGraphics gg, ModButton btn) {

		GuiDrawFunctions.drawButton(gg, font, WINDOW_POS, btn);

	}

	public void drawWindowTitle(GuiGraphics gg, Point pos, Point size, String text) {
		GuiDrawFunctions.drawWindowTitle(gg, WINDOW_POS, pos, size, font, text);
	}

	private void drawItemIcon(GuiGraphics gg, Item item, int x, int y) {
		GuiDrawFunctions.drawItemIcon(gg, font, WINDOW_POS, item, x, y);
	}

	private void drawItemIcon(GuiGraphics gg, ItemStack stack, int x, int y) {
		GuiDrawFunctions.drawItemIcon(gg, font, WINDOW_POS, stack, x, y);
	}

//	private void drawItemIcon(GuiGraphics gg, Item item, int x, int y, int count) {
//		GuiDrawFunctions.drawItemIcon(gg, font, WINDOW_POS, item, x, y, count);
//	}

	private void drawItemIcon(GuiGraphics gg, ItemStack stack, int x, int y, int count) {
		GuiDrawFunctions.drawItemIcon(gg, font, WINDOW_POS, stack, x, y, count);
	}

	private void drawToolTipWindow(GuiGraphics gg, ItemStack itemStack, int x, int y) {

		if (itemStack.getItem() != Items.AIR)
			GuiDrawFunctions.drawToolTipWindow(gg, this.font, itemStack, x, y);
	}

	private void drawRectangleWithBorder(GuiGraphics gg, Point pos, Point size, TextureColor fillColor,
			TextureColor borderColor) {
		GuiDrawFunctions.drawRectangleWithBorder(gg, WINDOW_POS, pos, size, fillColor, borderColor);
	}

	private void drawRectangle(GuiGraphics gg, int xPos, int yPos, int width, int height, TextureColor color) {

		GuiDrawFunctions.drawRectangle(gg, WINDOW_POS, xPos, yPos, width, height, color);

	}

	private void writeString(GuiGraphics gg, String text, int x, int y, Color color) {
		GuiDrawFunctions.writeString(gg, font, WINDOW_POS, text, x, y, color);

	}

	private void writeStringCentred(GuiGraphics gg, String text, int x, int y, Color color) {
		GuiDrawFunctions.writeStringCentred(gg, font, WINDOW_POS, text, x, y, color);

	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {

		if (curHoverItem != null) {
			switch (curHoverItem) {

			case BTN_MAIN_MENU:
				QdcApi.QDC_CORE.FUNCTIONS.showMainMenuScreen();
				playClickSound();

				break;

			case BTN_NEXT:
				if (discoveredItemsWindow.handleNextClick()) {
					playClickSound();
				}

				break;
			case BTN_PREV:
				if (discoveredItemsWindow.handlePrevClick()) {
					playClickSound();
				}
				break;

			case BTN_MAKE_ONE:
				if (btnMakeOne.isClickable()) {
					if (workWindow.handleButtonClickMakeOne()) {
						particleStoreGuiItemBox.update();
						workWindow.refresh();
						refreshInventory();
						playClickSound();
					}
				}

				break;
				
			case BTN_MAKE_STACK:
				if (btnMakeStack.isClickable()) {
					if (workWindow.handleButtonClickMakeStack()) {
						particleStoreGuiItemBox.update();
						workWindow.refresh();
						refreshInventory();
						playClickSound();
					}
				}

				break;

			default:
				break;

			}

		}

		if (discoveredItemsWindow != null) {
			if (discoveredItemsWindow.curWindowTab != null) {
				if (discoveredItemsWindow.curWindowTab.curItemHover != null) {
					workWindow = new WorkWindow(discoveredItemsWindow.curWindowTab.curItemHover, btnMakeOne, btnMakeStack);
				}
			}
		}

		return false;
	}

	private void playClickSound() {
		GuiSoundFunctions.playClickSound(Minecraft.getInstance().player);
	}

	private void playErrorSound() {
		GuiSoundFunctions.playErrorSound(Minecraft.getInstance().player);
	}
}