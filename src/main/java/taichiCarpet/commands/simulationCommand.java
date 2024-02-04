package taichiCarpet.commands;

import taichiCarpet.TaichiCarpetSettings;

import carpet.utils.CommandHelper;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

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
        Text message = Text.of("simulationDistance is now " + distance);

        execute.executeCommand(source, command);
        execute.sendGlobalMessage(source, message);

        return 1;
    }

    public static int executeQuerySimulation(ServerCommandSource source){

        String command = "carpet simulationDistance";

        execute.executeCommand(source, command);

        return 1;
    }
}
