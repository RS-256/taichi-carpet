package carpetfixes.mixins.optimizations.rounding;

import carpetfixes.helpers.FastMath;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import taichiCarpet.TaichiCarpetSettings;

@Mixin(value = ChunkGenerator.class, priority = 1010)
public class ChunkGeneratorMixin {


//    @Redirect(
//            method = "*",
//            require = 0,
//            at = @At(
//                    value = "INVOKE",
//                    target = "Ljava/lang/Math;round(D)J"
//            )
//    )
//    private long fasterRound(double value) {
//        return TaichiCarpetSettings.optimizedRounding ? FastMath.round(value) : Math.round(value);
//    }
}