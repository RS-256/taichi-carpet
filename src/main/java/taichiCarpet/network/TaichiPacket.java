package taichiCarpet.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;

public class TaichiPacket {
    private PacketByteBuf sendPacketbb;

    public TaichiPacket() {
        this.sendPacketbb  = new PacketByteBuf(Unpooled.buffer());
    }

    @Environment(EnvType.CLIENT)
    public void SendPacket(){
        ClientPlayNetworking.send(RegistarPackets.onServer.BLOCK_INVENTORY, this.sendPacketbb);
    }
    @Environment(EnvType.SERVER)
    public void SendPacket(PacketSender sender){
        sender.sendPacket(RegistarPackets.onServer.BLOCK_INVENTORY, this.sendPacketbb);
    }
}
