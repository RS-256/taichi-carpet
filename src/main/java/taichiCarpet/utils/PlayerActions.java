package taichiCarpet.utils;

import carpet.patches.EntityPlayerMPFake;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public class PlayerActions {
    public static final int THROW_CTRL_Q = 1;
    public class fill {
        private static List<ServerPlayerEntity> players = new ArrayList<>();
        public static void fillAll(ServerPlayerEntity player, ShulkerBoxScreenHandler screenHandler) {
            for (int index = 63 - 36; index < 63; index++) {  // 63-36=27
            //for (int index = 63 - 36; index < screenHandler.slots.size(); index++) {
                Slot slot = screenHandler.getSlot(index);
                //Slot slot = screenHandler.slots.get(index);
                if (slot.hasStack()) {
                    ItemStack itemStack = slot.getStack();
                    if (itemStack.getItem().canBeNested()) {
                        screenHandler.quickMove(player, index);
                        if (slot.hasStack()) {
                            player.onHandledScreenClosed();
                        }
                    } else {
                        screenHandler.onSlotClick(index, THROW_CTRL_Q, SlotActionType.THROW, player);
                    }
                }
            }
        }
        public static void addPlayer(ServerPlayerEntity player){
            players.add(player);
        }
        public static void removePlayer(ServerPlayerEntity player){
            players.remove(player);
        }
        public static boolean containsPlayer(ServerPlayerEntity player){
            return players.contains(player);
        }
    }


    public class clean {
        private static List<ServerPlayerEntity> players = new ArrayList<>();
        public static void cleanAll(ServerPlayerEntity player, ShulkerBoxScreenHandler screenHandler) {
            for (int index = 0; index < 27; index++) {
                //for (int index = 63 - 36; index < screenHandler.slots.size(); index++) {
                Slot slot = screenHandler.getSlot(index);
                //Slot slot = screenHandler.slots.get(index);
                if (slot.hasStack()) {
                    screenHandler.onSlotClick(index, THROW_CTRL_Q, SlotActionType.THROW, player);
                }
            }
            screenHandler.onSlotClick(0, THROW_CTRL_Q, SlotActionType.THROW, player);
        }
        public static void addPlayer(ServerPlayerEntity player){
            players.add(player);
        }
        public static void removePlayer(ServerPlayerEntity player){
            players.remove(player);
        }
        public static boolean containsPlayer(ServerPlayerEntity player){
            return players.contains(player);
        }
    }
}
