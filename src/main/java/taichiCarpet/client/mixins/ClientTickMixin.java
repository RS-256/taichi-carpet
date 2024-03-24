package taichiCarpet.client.mixins;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.Inventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.client.TaichiCarpetClient;
import taichiCarpet.network.RegistarPackets;
import taichiCarpet.utils.RayTraceUtils;

@Mixin(MinecraftClient.class)
public abstract class ClientTickMixin {
    @Shadow @Nullable public ClientWorld world;

    @Shadow @Nullable public abstract Entity getCameraEntity();

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        if (!TaichiCarpetClient.ConnectedServer) return;
        if (!TaichiCarpetSettings.blockInventorySyncing) return;
        if (world == null) return;

        HitResult trace = RayTraceUtils.getRayTraceFromEntity(world, getCameraEntity(), false);

        if (trace.getType() != HitResult.Type.BLOCK) return;

        BlockHitResult hitBlock = (BlockHitResult) trace;
        BlockPos pos = hitBlock.getBlockPos();

        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity == null) return;

        if (blockEntity instanceof ChestBlockEntity) {
            BlockState blockState = blockEntity.getCachedState();
            ChestType chestType = blockState.get(ChestBlock.CHEST_TYPE);
            if( chestType != ChestType.SINGLE ) {

                Direction facing = blockState.get(ChestBlock.FACING);
                Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
                int currentIndex = -1;

                for (int i = 0; i < directions.length; i++) {
                    if (directions[i].equals(facing)) {
                        currentIndex = i;
                        break;
                    }
                }

                if (currentIndex == -1) return;

                int newIndex = 0;
                if (chestType == ChestType.LEFT) {
                    newIndex = (currentIndex + 1) % 4;
                } else if (chestType == ChestType.RIGHT) {
                    newIndex = (currentIndex - 1 + 4) % 4;
                }
                BlockPos lc_pos = pos.offset(directions[newIndex]);
                PacketByteBuf lc_packetbb =  new PacketByteBuf(Unpooled.buffer());
                lc_packetbb.writeBlockPos(lc_pos);
                ClientPlayNetworking.send(RegistarPackets.onServer.handlers.BLOCK_INVENTORY.getId(), lc_packetbb);
            }
        }

        if (blockEntity instanceof Inventory) {
            PacketByteBuf packetbb =  new PacketByteBuf(Unpooled.buffer());
            packetbb.writeBlockPos(pos);
            ClientPlayNetworking.send(RegistarPackets.onServer.handlers.BLOCK_INVENTORY.getId(), packetbb);
        }
    }
}
