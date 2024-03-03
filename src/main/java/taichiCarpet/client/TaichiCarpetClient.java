package taichiCarpet.client;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import taichiCarpet.network.RegistarPackets;

import static taichiCarpet.TaichiCarpetExtension.*;

public class TaichiCarpetClient implements ClientModInitializer {

    public static PacketSender packetSender;

    @Override
    public void onInitializeClient() {
        RegistarPackets.onClient.register();
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            PacketByteBuf packetbb =  new PacketByteBuf(Unpooled.buffer());
            packetbb.writeString(MOD_VERSION);

            packetSender = sender;

            sender.sendPacket( RegistarPackets.onServer.handlers.HANDSHAKE.getId(), packetbb );
        });
    }
}
