package taichiCarpet.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class ItemUtils {
    public static boolean isShulkerBox(Item item) {
        return item.equals(Items.SHULKER_BOX) ||
                item.equals(Items.BLACK_SHULKER_BOX) ||
                item.equals(Items.BLUE_SHULKER_BOX) ||
                item.equals(Items.BROWN_SHULKER_BOX) ||
                item.equals(Items.CYAN_SHULKER_BOX) ||
                item.equals(Items.GRAY_SHULKER_BOX) ||
                item.equals(Items.GREEN_SHULKER_BOX) ||
                item.equals(Items.LIGHT_BLUE_SHULKER_BOX) ||
                item.equals(Items.LIGHT_GRAY_SHULKER_BOX) ||
                item.equals(Items.LIME_SHULKER_BOX) ||
                item.equals(Items.MAGENTA_SHULKER_BOX) ||
                item.equals(Items.ORANGE_SHULKER_BOX) ||
                item.equals(Items.PINK_SHULKER_BOX) ||
                item.equals(Items.PURPLE_SHULKER_BOX) ||
                item.equals(Items.RED_SHULKER_BOX) ||
                item.equals(Items.WHITE_SHULKER_BOX) ||
                item.equals(Items.YELLOW_SHULKER_BOX);
    }

    public static boolean isEmptyShulkerBox(ItemStack stack) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null) {
            NbtCompound blockEntityTagNbt = nbt.getCompound("BlockEntityTag");
            if (blockEntityTagNbt != null) {
                if (blockEntityTagNbt.contains("Items")) {
                    return true;
                }
            }
        }
        return false;
    }
}
