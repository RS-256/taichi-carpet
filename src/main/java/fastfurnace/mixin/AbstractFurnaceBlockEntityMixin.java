package fastfurnace.mixin;

import net.minecraft.item.Item;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import fastfurnace.Hooks;
import taichiCarpet.TaichiCarpetSettings;

import java.util.Map;

@Mixin(AbstractFurnaceBlockEntity.class)
class AbstractFurnaceBlockEntityMixin {

    @Shadow
    int burnTime;

    @Inject(method = "createFuelTimeMap", at = @At("HEAD"), cancellable = true)
    private static void rebuildFuelTimeMap(CallbackInfoReturnable<Map<Item, Integer>> ci) {
        if (!TaichiCarpetSettings.optimizedFurnaces) return;
        if (Hooks.rebuild) return;
        ci.setReturnValue(Hooks.fuelTimeMap);
    }

    @Inject(method = "writeNbt", at = @At("RETURN"))
    private void saveBurntime(NbtCompound compoundTag, CallbackInfo ci) {
        if (!TaichiCarpetSettings.optimizedFurnaces) return;
        compoundTag.putInt(Hooks.FABRIC_BURN_TIME, this.burnTime);
    }

    @Inject(method = "readNbt", at = @At("RETURN"))
    private void loadBurntime(NbtCompound tag, CallbackInfo ci) {
        if (!TaichiCarpetSettings.optimizedFurnaces) return;
        this.burnTime = tag.getInt(Hooks.FABRIC_BURN_TIME);
    }
}