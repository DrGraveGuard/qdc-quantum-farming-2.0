package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.classes;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.screen.QuantumSeedCreatorScreen.GuiButton;

import net.minecraft.world.item.ItemStack;

public class DiscoveredItemsWindow {

	public List<DiscoveredItemsWindowTab> windowTabList = new ArrayList<DiscoveredItemsWindowTab>();

	public DiscoveredItemsWindowTab curWindowTab = null;
	public DiscoveredItem curItem = null;
	public int curWindowTabIndex = 1;
	public int windowTabCount = 1;
	public GuiButton btnNext = null;
	public GuiButton btnPrev = null;
	public int tabIndex = 0;

	public String tabStr = "";

	public boolean isEmpty = true;

	private List<ItemStack> discoveredItemsList;

	public DiscoveredItemsWindow(GuiButton btnPrev, GuiButton btnNext) {

		this.btnNext = btnNext;
		this.btnPrev = btnPrev;

		btnPrev.setEnabledState(false);
		btnNext.setEnabledState(false);
		
		discoveredItemsList = QdcApi.QDC_CORE.FUNCTIONS.getAllDiscoveredToolsAndWeapons();

		doSearch();

	}


	
	private void generateTabString() {
		tabStr = curWindowTabIndex + "/" + windowTabCount;
	}

	public void checkIfHoveringOver(Point windowPos, int mouseX, int mouseY) {
		if (curWindowTab != null) {
			curWindowTab.checkIfHoveringOver(windowPos, mouseX, mouseY);

		}
	}

	public void clear() {
		windowTabList = new ArrayList<DiscoveredItemsWindowTab>();
		curWindowTab = null;
		curItem = null;

		curWindowTabIndex = 1;
		windowTabCount = 1;

		tabIndex = 0;
		isEmpty = true;
		handleButtons();
	}

	private void handleButtons() {

		if (curWindowTabIndex <= 1) {
			btnPrev.setEnabledState(false);
		} else {
			btnPrev.setEnabledState(true);
		}

		if (curWindowTabIndex == windowTabList.size()) {
			btnNext.setEnabledState(false);
		} else {
			btnNext.setEnabledState(true);
		}

		if (windowTabCount <= 1) {
			btnNext.setEnabledState(false);
			btnPrev.setEnabledState(false);
		}
	}

	public void setFirstWindowTab() {
		if (windowTabList.size() > 0) {
			curWindowTab = windowTabList.get(0);
		} else {
			curWindowTab = null;
			curWindowTabIndex = 0;
			windowTabCount = 0;
		}

		generateTabString();
	}

	public void doSearch() {

		clear();

		String search = QuantumFarming.searchString;

		for (ItemStack is : discoveredItemsList) {
			if(is.getDisplayName().getString().toLowerCase().contains(search.toLowerCase())){
					if (windowTabList.size() == 0) {

						windowTabList.add(new DiscoveredItemsWindowTab());
						tabIndex = 0;
					}

					if (windowTabList.get(tabIndex).hasSpace) {
						
					} else {
						windowTabList.add(new DiscoveredItemsWindowTab());
						windowTabCount++;
						tabIndex++;
					}

					
					
					windowTabList.get(tabIndex).add(new DiscoveredItem(is));
					isEmpty = false;

			}
		}

		handleButtons();
		setFirstWindowTab();

	}

	public boolean isSameHoverItem(DiscoveredItem item) {
		if (curWindowTab != null)
			if (curWindowTab.curItemHover != null) {
				if (item.isSameItem(curWindowTab.curItemHover)) {
					return true;
				}
			}

		return false;
	}

	public void handleItemClick() {
		if (curWindowTab != null) {
			if (curWindowTab.curItemHover != null) {
				curItem = curWindowTab.curItemHover;
			}
		}
	}

	public boolean handleNextClick() {
		if (curWindowTabIndex - 1 < windowTabList.size() - 1) {
			curWindowTabIndex++;
			curWindowTab = windowTabList.get(curWindowTabIndex - 1);
			generateTabString();
			handleButtons();
			return true;
		}

		return false;
	}

	public boolean handlePrevClick() {
		if (curWindowTabIndex > 1) {
			curWindowTabIndex--;
			curWindowTab = windowTabList.get(curWindowTabIndex - 1);
			generateTabString();
			handleButtons();
			return true;
		}
		return false;
	}

}
