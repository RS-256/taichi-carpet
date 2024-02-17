package taichiCarpet.logging.HUD;

import taichiCarpet.logging.*;

import carpet.utils.Messenger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.Objects;

public class notice extends abstractHUDLogger{

    public static final String NAME = "notice";

    public static String NOTICETEXT = null;

    private static final notice INSTANCE = new notice();

    private notice() {
        super(NAME);
    }

    public static notice getInstance() {
        return INSTANCE;
    }

    @Override
    public Text[] onHudUpdate(String option, PlayerEntity playerEntity) {
        if (Objects.equals(NOTICETEXT, "reset") || notice.NOTICETEXT == null){
            return null;
        } else {
            return new Text[]{
                    Messenger.c(String.format("g %s", NOTICETEXT))
            };
        }
    }
}