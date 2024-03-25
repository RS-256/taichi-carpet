package taichiCarpet.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import taichiCarpet.network.packetHandlers.BlockInventorySyncing;
import taichiCarpet.network.packetHandlers.Handshake;

import java.util.HashMap;
import java.util.Map;

import static taichiCarpet.TaichiCarpetExtension.MOD_ID;
public class RegistarPackets {
    public static class onServer {
        public static Identifier HANDSHAKE = new Identifier(MOD_ID, "handshake");
        public static Identifier BLOCK_INVENTORY = new Identifier(MOD_ID, "block_inventory");


        public static void register() {
            ServerPlayNetworking.registerGlobalReceiver(HANDSHAKE, Handshake::serverHandler);
            ServerPlayNetworking.registerGlobalReceiver(BLOCK_INVENTORY, BlockInventorySyncing::serverHandler);
        }

//        public enum handlers {
//
//            HANDSHAKE (Handshake::serverHandler),
//            BLOCK_INVENTORY (BlockInventorySyncing::serverHandler);
//
//            private ServerPlayNetworking.PlayChannelHandler handler;
//            handlers(ServerPlayNetworking.PlayChannelHandler handler) {
//                this.handler = handler;
//            }
//            public ServerPlayNetworking.PlayChannelHandler getHandler() {
//                return handler;
//            }
//            public static Map<handlers, Identifier> ids = new HashMap<>();
//            public Identifier getId() {
//                Identifier id = ids.get(this);
//                if (id==null) {
//                    id = new Identifier(MOD_ID, this.name().toLowerCase());
//                    ids.put(this, id);
//                }
//                return id;
//            }
//        };
//        public static void register() {
//            for(handlers handler:handlers.values()) {
//                ServerPlayNetworking.registerGlobalReceiver(handler.getId(), handler.getHandler());
//            }
//        }
    }


    public static class onClient {
        public static Identifier HANDSHAKE = new Identifier(MOD_ID, "handshake");
        public static Identifier BLOCK_INVENTORY = new Identifier(MOD_ID, "block_inventory");


        public static void register() {
            ClientPlayNetworking.registerGlobalReceiver(HANDSHAKE, Handshake::clientHandler);
            ClientPlayNetworking.registerGlobalReceiver(BLOCK_INVENTORY, BlockInventorySyncing::clientHandler);
        }
//        public enum handlers {
//
//            HANDSHAKE (Handshake::clientHandler),
//            BLOCK_INVENTORY (BlockInventorySyncing::clientHandler);
//
//            private ClientPlayNetworking.PlayChannelHandler handler;
//            handlers(ClientPlayNetworking.PlayChannelHandler handler) {
//                this.handler = handler;
//            }
//            public ClientPlayNetworking.PlayChannelHandler getHandler() {
//                return handler;
//            }
//            public static Map<handlers, Identifier> ids = new HashMap<>();
//            public Identifier getId() {
//                Identifier id = ids.get(this);
//                if (id==null) {
//                    id = new Identifier(MOD_ID, this.name().toLowerCase());
//                    ids.put(this, id);
//                }
//                return id;
//            }
//        };
//        public static void register() {
//            for(handlers handler: handlers.values()) {
//                ClientPlayNetworking.registerGlobalReceiver(handler.getId(), handler.getHandler());
//            }
//        }
    }
}
