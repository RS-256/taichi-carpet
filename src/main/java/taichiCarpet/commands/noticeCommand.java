package taichiCarpet.commands;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.network.ServerPlayerEntity;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.logging.HUD.notice;

import carpet.utils.CommandHelper;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import taichiCarpet.utils.sendMassage;

import java.util.Objects;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;

public class noticeCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("notice")
                .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandNotice))
                .executes(ctx -> sendGlobalMessage(ctx))
                .then(CommandManager.argument("text", string())
                        .executes(ctx -> changeNoticeText(getString(ctx, "text"), ctx))
                )
        );
    }

    public static int changeNoticeText(String text, CommandContext<ServerCommandSource> ctx){
        if(text.equals("#None") || text.isEmpty()) {
            notice.NOTICETEXT = null;
        } else {
            notice.NOTICETEXT = text;
            sendGlobalMessage(ctx);
        }
        return 1;
    }

    public static int sendGlobalMessage(CommandContext<ServerCommandSource> ctx){
        if(notice.NOTICETEXT!=null) sendMassage.sendGlobalMessage(ctx.getSource().getPlayer(), notice.NOTICETEXT);
        return 1;
    }
}
