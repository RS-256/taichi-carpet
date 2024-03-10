package taichiCarpet.commands;

import PCA.util.rule.playerSit.SitEntity;
import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.decoration.ArmorStandEntity;
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

        ServerWorld world = player.getServerWorld();
        ArmorStandEntity armorStandEntity = new ArmorStandEntity(world, player.getX(), player.getY() - 0.16, player.getZ());

        ((SitEntity) armorStandEntity).setSitEntity(true);
        world.spawnEntity(armorStandEntity);
        player.setSneaking(false);
        player.startRiding(armorStandEntity);

        return 1;
    }
}
