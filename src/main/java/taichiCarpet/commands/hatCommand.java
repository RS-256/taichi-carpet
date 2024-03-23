package taichiCarpet.commands;

import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import taichiCarpet.TaichiCarpetSettings;
import taichiCarpet.utils.ItemUtils;
import taichiCarpet.utils.sendMassage;

public class hatCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("hat")
                .requires(player -> CommandHelper.canUseCommand(player, TaichiCarpetSettings.commandHat))
                .executes(context -> hatPlayer(context.getSource().getPlayerOrThrow()))
        );
    }

    public static int hatPlayer(ServerPlayerEntity player) {
        if (player.isSpectator()) return 1;
        ItemStack hat = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack stack = player.getMainHandStack();

        Item item = stack.getItem();
        if (EnchantmentHelper.fromNbt(hat.getEnchantments()).containsKey(Enchantments.BINDING_CURSE)) {
            sendMassage.sendSourceMessage(player, "Already equipped with an Item with BINDING_CURSE");
            return 1;
        } else if (item.equals(Items.TOTEM_OF_UNDYING)) {
            sendMassage.sendSourceMessage(player, "Items that cannot be equipped: TOTEM_OF_UNDYING");
            return 1;
        } else if (ItemUtils.isShulkerBox(item) && ItemUtils.isEmptyShulkerBox(stack)) {
            sendMassage.sendSourceMessage(player, "Items that cannot be equipped: SHULKER_BOX(notEmpty)");
            return 1;
        }

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
