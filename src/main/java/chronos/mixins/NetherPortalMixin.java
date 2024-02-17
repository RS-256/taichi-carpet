package chronos.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.dimension.NetherPortal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import taichiCarpet.TaichiCarpetSettings;

@Mixin(NetherPortal.class)
public class NetherPortalMixin {

    @Inject(method = "findOpenPosition", at = @At("HEAD"), cancellable = true)
    private static void cancelCollisionCheck(Vec3d fallback, ServerWorld world, Entity entity, EntityDimensions dimensions, CallbackInfoReturnable<Vec3d> cir) {
        if (TaichiCarpetSettings.disableNetherPortalCollisionCheck) {
            cir.setReturnValue(fallback);
        }
    }
}