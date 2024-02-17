package taichiCarpet.commands;

import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.utils.sendMassage;

import carpet.utils.CommandHelper;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Objects;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;


public class viewCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("view")
                        .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandView))
                        .executes(context -> executeQueryView(context.getSource().withLevel(3)))
                        .then(CommandManager.argument("distance", integer(0, 32))
                                .executes(context -> executeChangeView(context.getSource().withLevel(3), getInteger(context, "distance")))
                        )
        );
    }

    public static int executeChangeView(ServerCommandSource source, int distance){

        String command = "carpet viewDistance " + distance;
        String message = "viewDistance is now " + distance;

        execute.executeCommand(source, command);
        sendMassage.sendGlobalMessage(Objects.requireNonNull(source.getPlayer()), message);

        return 1;
    }

    public static int executeQueryView(ServerCommandSource source){

        String command = "carpet viewDistance";

        execute.executeCommand(source, command);

        return 1;
    }
}
