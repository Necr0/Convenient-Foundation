package dev.necro.convenientfoundation.client.misc;

import dev.necro.convenientfoundation.ConvenientFoundation;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.MovementInput;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ConvenientFoundation.MODID, value = Dist.CLIENT)
public class PlayerMovementInputEvent extends PlayerEvent {

    private static boolean wasSneaking = false;
    private static boolean wasJumping = false;

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        if (event.side == LogicalSide.SERVER) return;

        ClientPlayerEntity player = (ClientPlayerEntity)event.player;

        if(player==null||event.phase!=TickEvent.Phase.START) return;

        MovementInput input = player.movementInput;

        if(!wasJumping && input.jump){
            MinecraftForge.EVENT_BUS.post(new PlayerMovementInputEvent.JumpInputEvent(player));
        }
        if(!wasSneaking && input.sneaking){
            MinecraftForge.EVENT_BUS.post(new PlayerMovementInputEvent.SneakInputEvent(player));
        } else if(wasSneaking && !input.sneaking) {
            MinecraftForge.EVENT_BUS.post(new PlayerMovementInputEvent.UnsneakInputEvent(player));
        }

        wasJumping=input.jump;
        wasSneaking=input.sneaking;
    }


    public PlayerMovementInputEvent(PlayerEntity player) {
        super(player);
    }

    public static class SneakInputEvent extends PlayerMovementInputEvent {

        public SneakInputEvent(PlayerEntity player) {
            super(player);
        }
    }

    public static class UnsneakInputEvent extends PlayerMovementInputEvent {
        public UnsneakInputEvent(PlayerEntity player) {
            super(player);
        }
    }

    public static class JumpInputEvent extends PlayerMovementInputEvent {
        public JumpInputEvent(PlayerEntity player) {
            super(player);
        }
    }
}
