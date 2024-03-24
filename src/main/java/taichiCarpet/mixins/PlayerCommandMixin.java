package taichiCarpet.mixins;

import carpet.commands.PlayerCommand;
import carpet.helpers.EntityPlayerActionPack;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(PlayerCommand.class)
public abstract class PlayerCommandMixin {

//    @Shadow(remap = false)
//    private static int manipulate(CommandContext<ServerCommandSource> context, Consumer<EntityPlayerActionPack> action){
//        return 0;
//    }
//
//    @Unique
//    private static int action$TISCM(CommandContext<ServerCommandSource> context, EntityPlayerActionPack.ActionType type, EntityPlayerActionPack.Action action){
//        return manipulate(context, ap -> ap.start(type, action));
//    }
//
//    @Inject(method = "makeActionCommand", at = @At("RETURN"), remap = false)
//    private static void applyPlayerActionEnhancements(String actionName, EntityPlayerActionPack.ActionType type, CallbackInfoReturnable<LiteralArgumentBuilder<ServerCommandSource>> cir) {
//    }
}