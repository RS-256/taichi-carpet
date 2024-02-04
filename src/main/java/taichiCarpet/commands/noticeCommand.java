package taichiCarpet.commands;

import taichiCarpet.TaichiCarpetSettings;

import carpet.utils.CommandHelper;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;

public class noticeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("notice")
                .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandNotice))
                .then(CommandManager.argument("text", string())
                        .executes(context -> changeNotice(getString(context, "text")))
                )
        );
    }

    public static String noticeText;

    public static int changeNotice(String text){

        noticeText = text;

        return 1;
    }

}
