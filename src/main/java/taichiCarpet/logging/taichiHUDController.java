package taichiCarpet.logging;

import carpet.logging.LoggerRegistry;

import taichiCarpet.logging.HUD.*;

public class taichiHUDController {
    public static void updateHUD() {
        doHudLogging(taichiLoggerRegistry.__notice, notice.getInstance());
        doHudLogging(taichiLoggerRegistry.__autosave, autosave.getInstance());
        doHudLogging(taichiLoggerRegistry.__entitycount, entitycount.getInstance());
    }

    private static void doHudLogging(boolean condition, abstractHUDLogger logger) {
        if (condition) {
            LoggerRegistry.getLogger(logger.getName()).log(logger::onHudUpdate);
        }
    }
}
