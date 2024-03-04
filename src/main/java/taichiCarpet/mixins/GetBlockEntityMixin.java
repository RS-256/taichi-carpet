package taichiCarpet.mixins;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.network.RegistarPackets;
import taichiCarpet.network.packetTweaks.BlockEntitySyncing;

import taichiCarpet.client.TaichiCarpetClient;

@Environment(EnvType.CLIENT)
@Mixin(World.class)
public class GetBlockEntityMixin {
    @Inject(method = "getBlockEntity", at = @At("TAIL"))
    private void getBlockEntity(BlockPos pos, CallbackInfoReturnable<BlockEntity> cir) {
        if (TaichiCarpetSettings.blockEntitySyncing == 0) return;

        //System.out.println(TaichiCarpetSettings.blockEntitySyncing);

        MinecraftClient mc = MinecraftClient.getInstance();
        Vec3d playerPos = mc.player.getPos();

        if (TaichiCarpetSettings.blockEntitySyncing != -1) {
            if (playerPos.x - pos.getX() > TaichiCarpetSettings.blockEntitySyncing) return;
            if (playerPos.y - pos.getY() > TaichiCarpetSettings.blockEntitySyncing) return;
            if (playerPos.z - pos.getZ() > TaichiCarpetSettings.blockEntitySyncing) return;
        }

        BlockEntity blockEntity = cir.getReturnValue();
        if (blockEntity == null) return;

        PacketByteBuf packetbb =  new PacketByteBuf(Unpooled.buffer());
        packetbb.writeBlockPos(pos);
        TaichiCarpetClient.packetSender.sendPacket( RegistarPackets.onServer.handlers.BLOCKNBT.getId(), packetbb );

        if ( BlockEntitySyncing.blockEntityCacheMap.containsKey(pos) ) {
            NbtCompound nbt = BlockEntitySyncing.blockEntityCacheMap.get(pos);
            blockEntity.readNbt(nbt);
        }
    }
}
