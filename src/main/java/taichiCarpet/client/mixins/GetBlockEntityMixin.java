package taichiCarpet.client.mixins;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.network.packetTweaks.BlockInventorySyncing;

@Environment(EnvType.CLIENT)
@Mixin(World.class)
public class GetBlockEntityMixin {
    @Inject(method = "getBlockEntity", at = @At("TAIL"))
    private void getBlockEntity(BlockPos pos, CallbackInfoReturnable<BlockEntity> cir) {
        if (!TaichiCarpetSettings.blockInventorySyncing) return;

        BlockEntity blockEntity = cir.getReturnValue();
        if (blockEntity == null) return;

        NbtCompound nbt = BlockInventorySyncing.CacheQueue.get(pos);
        if (nbt != null){
            blockEntity.readNbt(nbt);
        }
        //cir.setReturnValue(BlockInventorySyncing.blockInventoryCache);
    }
}
