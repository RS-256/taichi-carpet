package taichiCarpet.client.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.client.TaichiCarpetClient;
import taichiCarpet.client.blockInvSyncTask;

@Mixin(MinecraftClient.class)
public abstract class ClientTickMixin {
    @Shadow @Nullable public ClientWorld world;

    @Shadow @Nullable public abstract Entity getCameraEntity();

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci){
        if (!TaichiCarpetClient.ConnectedServer) return;
        if (TaichiCarpetSettings.blockInventorySyncing) {
            blockInvSyncTask.task(world, getCameraEntity());
        }
    }
}
