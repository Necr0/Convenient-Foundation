package dev.necro.convenientfoundation.common.misc.multijump;

import dev.necro.convenientfoundation.common.network.BasePacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.io.IOException;
import java.util.function.Supplier;

public class MultiJumpPacket extends BasePacket<MultiJumpPacket> {

    public MultiJumpPacket(PacketBuffer buf) {
        super(buf);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {}

    @Override
    public void writePacketData(PacketBuffer buf) {}

    @Override
    public void processPacket(Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();
        PlayerEntity player = context.getSender();
        if (player != null){
            MultiJumpHandler.playerJumpPacket(player);
        }
    }
}
