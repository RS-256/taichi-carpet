package taichiCarpet.logging;

import carpet.logging.HUDLogger;
import carpet.logging.LoggerRegistry;
import taichiCarpet.logging.HUD.autosave;
import taichiCarpet.logging.HUD.entitycount;
import taichiCarpet.logging.HUD.notice;

import java.lang.reflect.Field;

public class taichiLoggerRegistry {

    public static boolean __notice;
    public static boolean __autosave;
    public static boolean __entitycount;

    public static void registerLoggers(){
        LoggerRegistry.registerLogger(notice.NAME, standardHUDLogger(notice.NAME, null, null));
        LoggerRegistry.registerLogger(autosave.NAME, standardHUDLogger(autosave.NAME, null, null));
        LoggerRegistry.registerLogger(entitycount.NAME, standardHUDLogger(entitycount.NAME, null, null));

    }

    public static HUDLogger standardHUDLogger(String logName, String def, String [] options) {
        return new HUDLogger(getLoggerField(logName), logName, def, options, false);
    }

    public static Field getLoggerField(String logName) {
        try {
            return taichiLoggerRegistry.class.getField("__" + logName);
        }
        catch (NoSuchFieldException e) {
            throw new RuntimeException();
        }
    }



}
