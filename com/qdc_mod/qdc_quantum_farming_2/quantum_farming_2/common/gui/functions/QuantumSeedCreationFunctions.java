package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.common.gui.functions;

import java.util.ArrayList;
import java.util.List;

import com.qdc_mod.qdc_core_4_5.api.GlobalFuncs;
import com.qdc_mod.qdc_core_4_5.api.ParticleCollection;
import com.qdc_mod.qdc_core_4_5.api.QdcApi;
import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.core.init.ItemInit;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.network.packets.myData.MyData;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.network.PacketDistributor;

public class QuantumSeedCreationFunctions {

	
	
	private static int countCanMake(ItemStack stack)
	{
		ParticleCollection particles = QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(stack);
		
		if(particles == null)
			return 0;
		
		return QdcApi.QDC_CORE.FUNCTIONS.countCanMakeAmount(particles);
	}
	
	
	public static boolean createOneSeed(ItemStack stack) {
		if (countCanMake(stack) > 0) {

			ItemStack newStack = createSeedStack(stack, 1);
			
			int slotIndex = QuantumFarming.curPlayer.getInventory().getSlotWithRemainingSpace(newStack);

			if (slotIndex == -1) {
				slotIndex = QuantumFarming.curPlayer.getInventory().getFreeSlot();
			}

			if (slotIndex > -1) {
				return createSeedItem(stack, slotIndex, 1);
			}
		}
		return false;
	}

	public static boolean createSeedStack(ItemStack stack) {
		if (countCanMake(stack) > 0) {

			ItemStack newStack = createSeedStack(stack, 1);
			
			int slotIndex = QuantumFarming.curPlayer.getInventory().getSlotWithRemainingSpace(newStack);

			if (slotIndex == -1) {
				slotIndex = QuantumFarming.curPlayer.getInventory().getFreeSlot();
			}

			if (slotIndex > -1) {
				int max = 64;
				return createSeedItem(stack, slotIndex, max);
			}
		}
		
		return false;
	}  



	private static boolean createSeedItem(ItemStack stack, int slotIndex, int count) {

		if (slotIndex < 0)
			return false;

		

		
		int canMake = countCanMake(stack);

		if (canMake < 1)
			return false;

		ParticleCollection toRemove = QdcApi.QDC_CORE.FUNCTIONS.getItemParticles(stack).clone();

		int maxStackSize = 64;
		int toMake = count;
		// add items
		if (toMake > maxStackSize) {
			toMake = maxStackSize;
		}

		if (toMake > canMake) {
			toMake = canMake;
		}

		if (QuantumFarming.curPlayer.getInventory().getItem(slotIndex).getItem() == Items.AIR) {
			toRemove.multiply(toMake);

			if (QdcApi.QDC_CORE.FUNCTIONS.removeParticleCollection(toRemove)) {

				ItemStack newStack = createSeedStack(stack, toMake);

				QuantumFarming.curPlayer.getInventory().setItem(slotIndex,newStack.copy());
				
				QuantumFarming.packetItem = newStack.copy();
				
					PacketDistributor.sendToServer(new MyData(slotIndex));
					return true;
			}
		} else {
			if (maxStackSize == 1)
				return false;

			int oldCount = QuantumFarming.curPlayer.getInventory().getItem(slotIndex).getCount();
			int space = maxStackSize - oldCount;

			if (space <= 0)
				return false;

			if (space < toMake) {
				toMake = space;
			}

			toRemove.multiply(toMake);

			if (QdcApi.QDC_CORE.FUNCTIONS.removeParticleCollection(toRemove)) {

				int newCount = oldCount + toMake;

				ItemStack newStack = createSeedStack(stack, newCount);

				QuantumFarming.curPlayer.getInventory().setItem(slotIndex,newStack.copy());
				
				QuantumFarming.packetItem = newStack.copy();
				
					PacketDistributor.sendToServer(new MyData(slotIndex));
				return true;
			}
		}

		return false;

	}
	
	private static ItemStack createSeedStack(ItemStack tool, int count)
	{
		List<ItemStack> itemList = new ArrayList<ItemStack>();
		itemList.add(tool);
		
		ItemStack stack = new ItemStack(ItemInit.QUANTUM_SEED.get(),count);
		
		 ItemContainerContents contents = ItemContainerContents.fromItems(itemList);
	     stack.set(DataComponents.CONTAINER, contents);
	     
	     return stack.copy();
         
	}
}
