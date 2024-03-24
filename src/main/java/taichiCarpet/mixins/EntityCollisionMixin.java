package taichiCarpet.mixins;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityCollisionMixin {
    @Inject(method = "pushAwayFrom", at = @At("HEAD"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo callbackInfo) {
//        System.out.println(entity);
//        System.out.println(this);
//        if( (Object) this instanceof ShulkerEntity ){
//            if( entity instanceof ShulkerEntity ){
//                callbackInfo.cancel();
//            }
//        }
    }
}
