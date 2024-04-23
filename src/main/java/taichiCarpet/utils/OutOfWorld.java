package taichiCarpet.utils;

import net.minecraft.entity.Entity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;

public class OutOfWorld {
    public static boolean checker(Entity entity){
        RegistryKey<World> dim = entity.getWorld().getRegistryKey();
        if(dim.equals(World.OVERWORLD)){
            return entity.prevY > 319 || entity.prevY < -64;
        } else if (dim.equals(World.NETHER)){
            return entity.prevY > 255 || entity.prevY < 0;
        } else if (dim.equals(World.END)){
            return entity.prevY > 255 || entity.prevY < 0;
        }
        return false;
    }
}
