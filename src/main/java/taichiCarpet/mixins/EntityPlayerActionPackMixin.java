package taichiCarpet.mixins;

import carpet.helpers.EntityPlayerActionPack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import taichiCarpet.TaichiCarpetSettings;

@Mixin(EntityPlayerActionPack.class)
public class EntityPlayerActionPackMixin {
    @ModifyArg(method = "mount", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;startRiding(Lnet/minecraft/entity/Entity;Z)Z"), index = 1)
    private boolean unforcedMount(boolean force) {
        if (TaichiCarpetSettings.disablePassengerLimit) {
            return true;
        }

        return false;
    }
}
