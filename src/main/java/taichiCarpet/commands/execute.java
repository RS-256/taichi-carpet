package taichiCarpet.commands;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import net.minecraft.server.network.ServerPlayerEntity;

public class execute {
    public static void executeCommand(ServerCommandSource source, String command){
        source.getServer().getCommandManager().executeWithPrefix(source, command);
        System.out.println("command : '" + command + "' is executed");
    }

    public static void sendGlobalMessage(ServerCommandSource source, Text message){
        source.getServer().getPlayerManager().getPlayerList().forEach(player ->
                player.sendMessage(message)
        );
    }

    public static void sendSourceMessage(ServerCommandSource source, Text message){
        source.sendMessage(message);
    }
}
