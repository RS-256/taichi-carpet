package taichiCarpet.client;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.minecraft.network.PacketByteBuf;
import taichiCarpet.network.RegistarPackets;
import taichiCarpet.network.packetHandlers.BlockInventorySyncing;

import static taichiCarpet.TaichiCarpetExtension.*;

public class TaichiCarpetClient implements ClientModInitializer {

    public static boolean ConnectedServer = false;

    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            ConnectedServer = false;
            BlockInventorySyncing.CacheQueue.clear();

            PacketByteBuf packetbb =  new PacketByteBuf(Unpooled.buffer());
            packetbb.writeString(MOD_VERSION);

            sender.sendPacket( RegistarPackets.onServer.HANDSHAKE, packetbb );
        });

        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> {
            ConnectedServer = false;
            BlockInventorySyncing.CacheQueue.clear();
        });
    }
}
