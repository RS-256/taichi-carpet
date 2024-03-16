package carpetfixes.mixins.optimizations.rounding;

import carpetfixes.helpers.FastMath;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import taichiCarpet.TaichiCarpetSettings;

@Mixin(value = PlayerEntity.class, priority = 1010)
public class PlayerEntityMixin {


    @Redirect(
            method = "increaseTravelMotionStats(DDD)V",
            require = 0,
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;round(D)J"
            )
    )
    private long fasterRound(double value) {
        return TaichiCarpetSettings.optimizedRounding ? FastMath.round(value) : Math.round(value);
    }


    @Redirect(
            method = "handleFallDamage(FFLnet/minecraft/entity/damage/DamageSource;)Z",
            require = 0,
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/Math;round(D)J"
            )
    )
    private long fasterRoundFall(double value) {
        return TaichiCarpetSettings.optimizedRounding ? FastMath.round(value) : Math.round(value);
    }
}