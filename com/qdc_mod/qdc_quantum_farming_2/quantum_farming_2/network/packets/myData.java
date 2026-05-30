package com.qdc_mod.qdc_quantum_farming_2.quantum_farming_2.network.packets;

import com.qdc_mod.qdc_quantum_farming_2.QuantumFarming;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class myData {
	
	public record MyData(int slotIndex) implements CustomPacketPayload {

		public static final CustomPacketPayload.Type<MyData> TYPE = new CustomPacketPayload.Type<>(
				ResourceLocation.fromNamespaceAndPath(QuantumFarming.MOD_ID, "my_data"));

		public static final StreamCodec<ByteBuf, MyData> STREAM_CODEC = StreamCodec.composite(
	        ByteBufCodecs.VAR_INT,
	        MyData::slotIndex,
	        MyData::new
	    );

		@Override
		public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
			return TYPE;
		}
	}
	
	

}
