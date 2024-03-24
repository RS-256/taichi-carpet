package fastfurnace.mixin;

import net.minecraft.server.DataPackContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import fastfurnace.Hooks;
import taichiCarpet.TaichiCarpetSettings;

@Mixin(DataPackContents.class)
class ServerResourceManagerMixin {
    @Inject(method = "refresh",at = @At("RETURN"))
    private void rebuildFuelMap(CallbackInfo ci){
        if (!TaichiCarpetSettings.optimizedFurnaces) return;
        Hooks.rebuildFuelMap();
    }
}