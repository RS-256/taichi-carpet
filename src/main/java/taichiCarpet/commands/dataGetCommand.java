package taichiCarpet.commands;

import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import taichiCarpet.TaichiCarpetSettings;
import net.minecraft.server.command.DataCommand;

public class dataGetCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("dataget")
                .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandDataGet))
                //.redirect(DataCommand)
                // DataCommand(get)にどうにかredirectしたい
        );
    }
}
