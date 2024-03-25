package taichiCarpet.client;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import taichiCarpet.network.RegistarPackets;
import taichiCarpet.utils.RayTraceUtils;

public class blockInvSyncTask {
    public static void task(ClientWorld world, Entity cameraEntity) {

        if (world == null) return;

        HitResult trace = RayTraceUtils.getRayTraceFromEntity(world, cameraEntity, false);

        if (trace.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult hitBlock = (BlockHitResult) trace;
        BlockPos pos = hitBlock.getBlockPos();

        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity == null) return;

        if (blockEntity instanceof ChestBlockEntity) {
            BlockPos lcAdjacentPos = getLcAdjacentPos(world, pos);
            if (lcAdjacentPos != null) {
                PacketByteBuf packetbb = new PacketByteBuf(Unpooled.buffer());
                packetbb.writeBlockPos(lcAdjacentPos);
                ClientPlayNetworking.send(RegistarPackets.onServer.BLOCK_INVENTORY, packetbb);
            }
        }

        if (blockEntity instanceof Inventory) {
            PacketByteBuf packetbb =  new PacketByteBuf(Unpooled.buffer());
            packetbb.writeBlockPos(pos);
            ClientPlayNetworking.send(RegistarPackets.onServer.BLOCK_INVENTORY, packetbb);
        }
    }

    private static BlockPos getLcAdjacentPos(ClientWorld world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        ChestType chestType = blockState.get(ChestBlock.CHEST_TYPE);
        if( chestType == ChestType.SINGLE ) return null;

        Direction facing = blockState.get(ChestBlock.FACING);
        Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
        int currentIndex = -1;

        for (int i = 0; i < directions.length; i++) {
            if (directions[i].equals(facing)) {
                currentIndex = i;
                break;
            }
        }

        if (currentIndex == -1) return null;

        int newIndex = 0;
        if (chestType == ChestType.LEFT) {
            newIndex = (currentIndex + 1) % 4;
        } else if (chestType == ChestType.RIGHT) {
            newIndex = (currentIndex - 1 + 4) % 4;
        }
        return pos.offset(directions[newIndex]);
    }
}
