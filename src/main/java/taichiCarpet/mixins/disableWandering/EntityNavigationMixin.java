package taichiCarpet.mixins.disableWandering;

import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.utils.OutOfWorld;

@Mixin(EntityNavigation.class)
public class EntityNavigationMixin {

    @Final
    @Shadow
    protected MobEntity entity;

    @Inject(method = "startMovingAlong(Lnet/minecraft/entity/ai/pathing/Path;D)Z", at = @At("HEAD"), cancellable = true)
    private void startMovingAlong(@Nullable Path path, double speed, CallbackInfoReturnable<Boolean> cir){

        if(TaichiCarpetSettings.disableWanderingOnVehicle){
            if(entity.getVehicle() != null){
                cir.setReturnValue(false);
                return;
            }
        }

        if(TaichiCarpetSettings.disableWanderingOutOfWorld) {
            if(OutOfWorld.checker(entity)){
                cir.setReturnValue(false);
            }
        }
    }
}
