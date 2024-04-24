package taichiCarpet.mixins.crashFix;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import taichiCarpet.TaichiCarpetSettings;

@Mixin(targets = "net.minecraft.world.block.ChainRestrictedNeighborUpdater$SixWayEntry")
public abstract class ChainRestrictedNeighborUpdaterSixWayEntryMixin {
    @WrapOperation(
            method = "update",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/BlockState;neighborUpdate(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;Lnet/minecraft/util/math/BlockPos;Z)V"
            )
    )
    private void yeetUpdateSuppressionCrash_implOnSixWayEntryUpdate(
            BlockState instance, World world, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean notify,
            Operation<Void> original
    ){
        if (TaichiCarpetSettings.disableCCEUpdateSuppressionCrash||TaichiCarpetSettings.disableOOMUpdateSuppressionCrash||TaichiCarpetSettings.disableSOEUpdateSuppressionCrash) {
            try {
                original.call(instance, world, pos, neighborBlock, neighborPos, notify);
            } catch (ClassCastException e) {
                if(!TaichiCarpetSettings.disableCCEUpdateSuppressionCrash) throw e;
            } catch (OutOfMemoryError e) {
                if(!TaichiCarpetSettings.disableOOMUpdateSuppressionCrash) throw e;
            } catch (StackOverflowError e) {
                if(!TaichiCarpetSettings.disableSOEUpdateSuppressionCrash) throw e;
            }
        } else {
            original.call(instance, world, pos, neighborBlock, neighborPos, notify);
        }
    }
}
