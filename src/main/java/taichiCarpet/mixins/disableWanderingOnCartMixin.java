package taichiCarpet.mixins;

import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import taichiCarpet.TaichiCarpetSettings;

@Mixin(WanderAroundFarGoal.class)
public class disableWanderingOnCartMixin {

    @Inject(method = "getWanderTarget", at = @At("HEAD"), cancellable = true)
    protected void modifyTryMake(CallbackInfoReturnable<Vec3d> cir){
        if (TaichiCarpetSettings.disableWanderingOnCart) {
            PathAwareEntity mob = ((WanderAroundGoalAccessor)this).getMob();
            if(mob.getVehicle() != null) {
                cir.setReturnValue(null);
            }
        }
    }
}
