package taichiCarpet.commands;

import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.logging.HUD.notice;

import carpet.utils.CommandHelper;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Objects;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;

public class noticeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("notice")
                .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandNotice))
                .then(CommandManager.argument("text", string())
                        .executes(context -> changeNoticeText(getString(context, "text")))
                )
        );
    }

    public static int changeNoticeText(String text){
        if(text.equals("#None")) {
            notice.NOTICETEXT = null;
        } else {
            notice.NOTICETEXT = text;
        }
        return 1;
    }
}
