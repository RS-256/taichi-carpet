package taichiCarpet.commands;

import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import taichiCarpet.TaichiCarpetSettings;

public class hatCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("hat")
                .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandHat))
                .executes(context -> hatPlayer(context.getSource().getPlayerOrThrow()))
        );
    }

    public static int hatPlayer(ServerPlayerEntity player) {
        ItemStack hat = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack stack = player.getMainHandStack();
        ItemStack stackCopy = stack.copy();
        stackCopy.setCount(1);
        player.equipStack(EquipmentSlot.HEAD, stackCopy);
        if (!player.isCreative()) {
            stack.decrement(1);
            if (player.getInventory().getEmptySlot() < 0) {
                ServerWorld world = player.getServerWorld();
                ItemEntity itemEntity = new ItemEntity(world, player.getX(), player.getY() + 1.0, player.getZ(), hat.copy());
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            } else {
                player.getInventory().insertStack(hat.copy());
            }
        }
        player.playerScreenHandler.sendContentUpdates();

        return 1;
    }
}
