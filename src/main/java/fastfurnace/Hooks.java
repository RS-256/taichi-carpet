package fastfurnace;

import net.minecraft.item.Item;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;

import java.util.HashMap;
import java.util.Map;

public class Hooks {
    public static final String FABRIC_BURN_TIME = "fabricBurnTime";
    public static Map<Item, Integer> fuelTimeMap = new HashMap<>();
    public static boolean rebuild = true;

    public static void rebuildFuelMap() {
        rebuild = true;
        fuelTimeMap = AbstractFurnaceBlockEntity.createFuelTimeMap();
        rebuild = false;
    }
}