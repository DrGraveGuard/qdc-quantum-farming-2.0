package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.network;

import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;
import com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.network.packets.myData.MyData;

import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class ServerPayloadHandler {


	public static void handleInventoryDataOnMain(final MyData data, final IPayloadContext ctx) {
		// Do something with the data, on the main thread

		Player player = ctx.player();

		if (QuantumFarming.packetItem != null)
			player.getInventory().setItem(data.slotIndex(), QuantumFarming.packetItem.copy());

	}



}
