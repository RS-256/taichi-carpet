package taichiCarpet.mixins.optimizedFallDamageRaycast;

import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.utils.OutOfWorld;

@Mixin(Entity.class)
public class EntityMixin {

    @Redirect(method = "move", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;raycast(Lnet/minecraft/world/RaycastContext;)Lnet/minecraft/util/hit/BlockHitResult;"))
    private BlockHitResult cancelRaycast(World instance, RaycastContext raycastContext) {
        Entity self = (Entity)(Object)this;
        if (
                ( TaichiCarpetSettings.optimizedFallDamageRaycast && raycastContext.getStart().y < raycastContext.getEnd().y ) ||
                ( TaichiCarpetSettings.optimizedFallDamageRaycastOutOfWorld && OutOfWorld.checker(self) )
        ) {
            return BlockHitResult.createMissed(raycastContext.getEnd(), Direction.getFacing(raycastContext.getStart().x, raycastContext.getStart().y, raycastContext.getStart().z), BlockPos.ofFloored(raycastContext.getEnd()));
        } else {
            return instance.raycast(raycastContext);
        }
    }

}
