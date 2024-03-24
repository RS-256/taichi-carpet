package taichiCarpet.commands;

import PCA.util.rule.playerSit.SitEntity;
import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import taichiCarpet.TaichiCarpetSettings;

public class sitCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("sit")
                .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandSit))
                .executes(context -> sitPlayer(context.getSource().getPlayerOrThrow()))
        );
    }

    public static int sitPlayer(ServerPlayerEntity player){

        if(!player.isOnGround()) return 1;

        ServerWorld world = player.getServerWorld();

        DisplayEntity.TextDisplayEntity textDisplayEntity = new DisplayEntity.TextDisplayEntity(EntityType.TEXT_DISPLAY, world);
        textDisplayEntity.setPosition(player.getPos());

        ((SitEntity) textDisplayEntity).setSitEntity(true);
        world.spawnEntity(textDisplayEntity);
        player.setSneaking(false);
        player.startRiding(textDisplayEntity);

        return 1;
    }
}
