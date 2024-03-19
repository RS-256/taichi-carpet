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
import taichiCarpet.utils.LimitedQueue;

public class BlockInventorySyncing {

    public static LimitedQueue<BlockPos, NbtCompound> CacheQueue = new LimitedQueue<>(2);

    public static void serverHandler(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf packetbb, PacketSender sender) {
        if (!TaichiCarpetSettings.blockInventorySyncing) return;

        BlockPos blockPos = packetbb.readBlockPos();
        ServerWorld world = player.getServerWorld();

        server.execute(()->{
            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if( blockEntity == null ) return;

            NbtCompound nbt = blockEntity.createNbt();

            if( nbt.getSizeInBytes() > 524288 ) return;

            PacketByteBuf senderPacketbb =  new PacketByteBuf(Unpooled.buffer());

            senderPacketbb.writeNbt(nbt);
            senderPacketbb.writeBlockPos(blockPos);
            sender.sendPacket(RegistarPackets.onServer.handlers.BLOCK_INVENTORY.getId(), senderPacketbb);
        });
    }
    public static void clientHandler(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf packetbb, PacketSender sender) {
        NbtCompound blockInventoryCache = packetbb.readNbt();
        BlockPos blockPos = packetbb.readBlockPos();
        CacheQueue.put(blockPos, blockInventoryCache);
    }
}