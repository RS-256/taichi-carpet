package taichiCarpet.mixins.disablePushFromAway;

import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import taichiCarpet.TaichiCarpetSettings;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    private World world;

    @Inject(method = "pushAwayFrom(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    public void pushAwayFrom(Entity entity, CallbackInfo callbackInfo) {
        if(TaichiCarpetSettings.disablePushEntityOutOfWorld) {
            RegistryKey<World> dim = world.getRegistryKey();
            if(dim.equals(World.OVERWORLD)){
                if( entity.prevY > 319 || entity.prevY < -64 ){
                    callbackInfo.cancel();
                    return;
                }
            } else if (dim.equals(World.NETHER)){
                if( entity.prevY > 255 || entity.prevY < 0 ){
                    callbackInfo.cancel();
                    return;
                }
            } else if (dim.equals(World.END)){
                if( entity.prevY > 255 || entity.prevY < 0 ){
                    callbackInfo.cancel();
                    return;
                }
            }
        }
    }
}
