package carpetfixes.mixins.optimizations;

import carpetfixes.helpers.FastMath;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import taichiCarpet.TaichiCarpetSettings;

@Mixin(MathHelper.class)
public class MathHelper_hypotMixin {
    @Inject(
            method = "hypot(DD)D",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void hypot(double a, double b, CallbackInfoReturnable<Double> cir) {
        if (TaichiCarpetSettings.optimizedHypot) cir.setReturnValue(FastMath.hypot(a,b));
    }
}