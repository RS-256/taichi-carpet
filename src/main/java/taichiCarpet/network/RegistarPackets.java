package taichiCarpet.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import taichiCarpet.network.packetTweaks.BlockInventorySyncing;
import taichiCarpet.network.packetTweaks.Handshake;

import static taichiCarpet.TaichiCarpetExtension.MOD_ID;
public class RegistarPackets {
    public static class onServer {
        public enum handlers {

            HANDSHAKE (Handshake::serverHandler),
            BLOCK_INVENTORY (BlockInventorySyncing::serverHandler);

            private ServerPlayNetworking.PlayChannelHandler handler;
            handlers(ServerPlayNetworking.PlayChannelHandler handler) {
                this.handler = handler;
            }
            public ServerPlayNetworking.PlayChannelHandler getHandler() {
                return handler;
            }
            public Identifier getId() {
                return new Identifier(MOD_ID, this.name().toLowerCase());
            }
        };

        public static void register() {
            for(handlers handler:handlers.values()) {
                ServerPlayNetworking.registerGlobalReceiver(handler.getId(), handler.getHandler());
            }
        }
    }


    public static class onClient {
        public enum handlers {

            HANDSHAKE (Handshake::clientHandler),
            BLOCK_INVENTORY (BlockInventorySyncing::clientHandler);

            private ClientPlayNetworking.PlayChannelHandler handler;
            handlers(ClientPlayNetworking.PlayChannelHandler handler) {
                this.handler = handler;
            }
            public ClientPlayNetworking.PlayChannelHandler getHandler() {
                return handler;
            }

            public Identifier getId() {
                return new Identifier(MOD_ID, this.name().toLowerCase());
            }
        };

        public static void register() {
            for(handlers handler:handlers.values()) {
                ClientPlayNetworking.registerGlobalReceiver(handler.getId(), handler.getHandler());
            }
        }
    }
}
