package taichiCarpet.utils;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.logging.HUD.notice;

import java.util.Objects;

public class sendMassage {

    public static void sendGlobalMessage(ServerPlayerEntity source, String message){
        Objects.requireNonNull(source.getServer()).getPlayerManager().getPlayerList().forEach(player ->
                player.sendMessage(Text.of(message))
        );
    }

    public static void sendSourceMessage(ServerPlayerEntity source, String message){
        source.sendMessage(Text.of(message));
    }

    public static void loginMessageNotifier(ServerPlayerEntity player){
        if (TaichiCarpetSettings.loginNotifier == true && notice.NOTICETEXT != null){
            sendSourceMessage(player, notice.NOTICETEXT);

        }

    }
}
