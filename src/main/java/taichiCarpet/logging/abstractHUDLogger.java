package taichiCarpet.logging;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public abstract class abstractHUDLogger {
    private final String NAME;

    public abstractHUDLogger(String name) {
        this.NAME = name;
    }

    public String getName() {
        return this.NAME;
    }

    public abstract Text[] onHudUpdate(String option, PlayerEntity playerEntity);

}
