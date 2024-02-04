package taichiCarpet.logging.HUD;

import taichiCarpet.commands.noticeCommand;
import taichiCarpet.logging.*;

import carpet.utils.Messenger;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class notice extends abstractHUDLogger{

    public static final String NAME = "notice";

    private static final notice INSTANCE = new notice();

    private notice() {
        super(NAME);
    }

    public static notice getInstance() {
        return INSTANCE;
    }

    @Override
    public Text[] onHudUpdate(String option, PlayerEntity playerEntity) {
        return new Text[]{
                Messenger.c(String.format("g %s", noticeCommand.noticeText))
        };
    }
}