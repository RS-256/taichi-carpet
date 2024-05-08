package Opt.mixins.rule.cceSuppressionCrashFix;

import Opt.utils.exceptions.ThrowableCCESuppression;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import taichiCarpet.TaichiCarpetSettings;

import java.util.Iterator;
import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public class MinecraftServer_Mixin {

    @Inject(
            method = {"tickWorlds"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"
            )},
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    public void injectTickWorlds(BooleanSupplier shouldKeepTicking, CallbackInfo ci, @SuppressWarnings("all") Iterator var2, ServerWorld serverWorld, Throwable throwable) {
        if (TaichiCarpetSettings.cceSuppressionCrashFix && (throwable instanceof ThrowableCCESuppression || throwable.getCause() instanceof ThrowableCCESuppression)) {
            try {
                if (throwable instanceof ThrowableCCESuppression) {
                    System.out.println("CCE Supression Crash:"+((ThrowableCCESuppression) throwable).pos);
                } else {
                    System.out.println("CCE Supression Crash:"+((ThrowableCCESuppression) throwable.getCause()).pos);
                }
            } catch (Exception e){
                System.err.println("Exception when handle CCE in MinecraftServer.tickWorlds(...)");
                e.printStackTrace();
            }
            ci.cancel();
        }
    }
}