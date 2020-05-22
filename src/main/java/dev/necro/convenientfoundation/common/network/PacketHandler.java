package dev.necro.convenientfoundation.common.network;

import dev.necro.convenientfoundation.ConvenientFoundation;
import dev.necro.convenientfoundation.common.misc.multijump.MultiJumpPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.Optional;

public class PacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(ConvenientFoundation.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void init(){
        int id=0;
        INSTANCE.registerMessage(id++,
                MultiJumpPacket.class,
                MultiJumpPacket::writePacketData,
                MultiJumpPacket::new,
                MultiJumpPacket::processPacket,
                Optional.of(NetworkDirection.PLAY_TO_SERVER));
    }

}
