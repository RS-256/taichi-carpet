package taichiCarpet.network.packetTweaks;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.network.RegistarPackets;

import java.util.HashMap;
import java.util.Map;

public class BlockEntitySyncing {

    public static Map<BlockPos, NbtCompound> blockEntityCacheMap = new HashMap<>();

    public static void serverHandler(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf packetbb, PacketSender sender) {
        if (TaichiCarpetSettings.blockEntitySyncing == 0) return;

        BlockPos blockPos = packetbb.readBlockPos();
        ServerWorld world = player.getServerWorld();

        server.execute(()->{
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if( blockEntity == null ) return;

            NbtCompound nbt = blockEntity.createNbt();

            PacketByteBuf senderPacketbb =  new PacketByteBuf(Unpooled.buffer());

            senderPacketbb.writeNbt(nbt);
            senderPacketbb.writeBlockPos(blockPos);
            sender.sendPacket(RegistarPackets.onServer.handlers.BLOCKNBT.getId(), senderPacketbb);
        });
    }
    public static void clientHandler(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf packetbb, PacketSender sender) {
        NbtCompound nbt = packetbb.readNbt();
        BlockPos blockPos = packetbb.readBlockPos();
        blockEntityCacheMap.put(blockPos, nbt);
    }
}