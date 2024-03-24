package taichiCarpet.utils;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.OperatorEntry;
import net.minecraft.server.OperatorList;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerUtil {

    public static void setOpLevel(ServerPlayerEntity player, int opLevel) {
        setOpLevel(player, opLevel, false);
    }
    public static void setOpLevel(ServerPlayerEntity player, int opLevel, boolean bypassPlayerLimit) {
        MinecraftServer server = player.getServer();
        PlayerManager manager = server.getPlayerManager();
        OperatorList ops = manager.getOpList();
        ops.add(new OperatorEntry(player.getGameProfile(), opLevel, bypassPlayerLimit));
        manager.sendCommandTree(player);
    }
}
