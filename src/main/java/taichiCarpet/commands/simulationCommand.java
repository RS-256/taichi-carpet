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


public class simulationCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("simulation")
                .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandSimulation))
                .executes(context -> executeQuerySimulation(context.getSource().withLevel(3)))
                .then(CommandManager.argument("distance", integer(0, 32))
                        .executes(context -> executeChangeSimulation(context.getSource().withLevel(3), getInteger(context, "distance")))
                )
        );
    }

    public static int executeChangeSimulation(ServerCommandSource source, int distance){

        String command = "carpet simulationDistance " + distance;
        String message = "simulationDistance is now " + distance;

        execute.executeCommand(source, command);
        sendMassage.sendGlobalMessage(Objects.requireNonNull(source.getPlayer()), message);

        return 1;
    }

    public static int executeQuerySimulation(ServerCommandSource source){

        String command = "carpet simulationDistance";

        execute.executeCommand(source, command);

        return 1;
    }
}
