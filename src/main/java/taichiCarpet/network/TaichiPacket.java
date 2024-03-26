package taichiCarpet.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import taichiCarpet.utils.LimitedQueueList;

public class TaichiPacket {

    private static LimitedQueueList<TaichiPacket> packetsQueueList = new LimitedQueueList<TaichiPacket>(10);

    private PacketByteBuf sendPacketbb;
    private Identifier identifier;

    public TaichiPacket(Identifier identifier) {
        this.sendPacketbb  = new PacketByteBuf(Unpooled.buffer());
        this.identifier    = identifier;
    }

    public void execute() {
        packetsQueueList.add(this);
    }

    @Environment(EnvType.CLIENT)
    public void SendPacket(){
        ClientPlayNetworking.send(identifier, this.sendPacketbb);
    }
    @Environment(EnvType.SERVER)
    public void SendPacket(PacketSender sender){
        sender.sendPacket(identifier, this.sendPacketbb);
    }
}
