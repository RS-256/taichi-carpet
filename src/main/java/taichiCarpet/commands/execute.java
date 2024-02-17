package taichiCarpet.commands;

import net.minecraft.server.command.ServerCommandSource;

public class execute {
    public static void executeCommand(ServerCommandSource source, String command){
        source.getServer().getCommandManager().executeWithPrefix(source, command);
        System.out.println("command : '" + command + "' is executed");
    }
}
