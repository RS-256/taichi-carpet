package taichiCarpet.logging.HUD;

import carpet.CarpetServer;
import carpet.utils.Messenger;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import taichiCarpet.logging.abstractHUDLogger;

public class autosave extends abstractHUDLogger {
    public static final String NAME = "autosave";

    private static final autosave INSTANCE = new autosave();

    private autosave() {
        super(NAME);
    }

    public static autosave getInstance() {
        return INSTANCE;
    }

    @Override
    public Text[] onHudUpdate(String option, PlayerEntity playerEntity) {

        int gameTick = CarpetServer.minecraft_server.getTicks();
        int previous = gameTick % 6000;

        if (gameTick != 0 && previous == 0) {
            previous = 6000;
        }
        int next = 6000 - previous;

        String color =  next <= 100 ? "§d" : next <= 500 ? "§c" : next <= 1000 ? "§e" : "§2";

        return new Text[] {
                Messenger.c(String.format("g Prev:%s %d §rNext:%s %d",color, previous, color, next))
        };
    }
}
