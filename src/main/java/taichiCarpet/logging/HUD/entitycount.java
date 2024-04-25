package taichiCarpet.logging.HUD;

import carpet.CarpetServer;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.function.LazyIterationConsumer;
import net.minecraft.world.World;
import taichiCarpet.logging.abstractHUDLogger;
import taichiCarpet.mixins.entityCount.ServerWorldMixin;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class entitycount extends abstractHUDLogger {
    public static final String NAME = "entitycount";

    private static final entitycount INSTANCE = new entitycount();

    public static int OW = 0;

    private entitycount() {
        super(NAME);
    }

    public static entitycount getInstance() {
        return INSTANCE;
    }

    @Override
    public Text[] onHudUpdate(String option, PlayerEntity playerEntity) {

        List<String> entitys;
        if(option!=null){
            entitys = Arrays.asList(option.split("\s*,\s*"));
        } else {
            entitys = new ArrayList();
        }

        String res = new String();
        int total = 0;
        for(RegistryKey<World> world : ImmutableList.of(World.OVERWORLD, World.NETHER, World.END)) {
            AtomicInteger v = new AtomicInteger();

            ServerWorld serverWorld = CarpetServer.minecraft_server.getWorld(world);
            ((ServerWorldMixin) serverWorld).getEntityManager().getLookup().forEach(TypeFilter.instanceOf(Entity.class), entity -> {
                String targetEntity = (entity.getType().toString()).replace("entity.minecraft.", "");
                if (entitys.isEmpty() || entitys.contains(targetEntity)) {
                    v.getAndIncrement();
                }
                return LazyIterationConsumer.NextIteration.CONTINUE;
            });

            if (world==World.OVERWORLD) {
                res += " OW:"+v.get();
            } else if (world==World.NETHER) {
                res += " NE:"+v.get();
            } else if (world==World.END) {
                res += " END:"+v.get();
            }
            total += v.get();
        }
        res += " T:"+total;

        return new Text[] {
                Text.of(res)
        };
    }
}
