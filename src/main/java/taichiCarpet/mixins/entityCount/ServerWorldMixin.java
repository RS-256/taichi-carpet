package taichiCarpet.mixins.entityCount;

import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerWorld.class)
public interface ServerWorldMixin {
    @Accessor("entityManager")
    ServerEntityManager<Entity> getEntityManager();
}
