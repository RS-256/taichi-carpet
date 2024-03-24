package PCA.mixin.rule.playerSit;

import PCA.util.rule.playerSit.SitEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisplayEntity.TextDisplayEntity.class)
public abstract class MixinTextDisplayEntity extends DisplayEntity implements SitEntity {
    private boolean sitEntity = false;

    protected MixinTextDisplayEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public boolean isSitEntity() {
        return sitEntity;
    }

    @Override
    public void setSitEntity(boolean isSitEntity) {
        this.sitEntity = isSitEntity;
        this.setInvisible(isSitEntity);
    }

    @Override
    protected void removePassenger(Entity passenger) {
        if (this.isSitEntity()) {
            this.setPosition(this.getX(), this.getY() + 0.16, this.getZ());
            this.kill();
        }
        super.removePassenger(passenger);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At(value = "RETURN"))
    private void postWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        if (this.sitEntity) {
            nbt.putBoolean("SitEntity", true);
        }
    }

    @Inject(method = "readCustomDataFromNbt", at = @At(value = "RETURN"))
    private void postReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("SitEntity", NbtElement.BYTE_TYPE)) {
            this.sitEntity = nbt.getBoolean("SitEntity");
        }
    }
}