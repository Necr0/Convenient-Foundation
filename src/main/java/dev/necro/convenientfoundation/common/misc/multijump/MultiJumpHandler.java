package dev.necro.convenientfoundation.common.misc.multijump;

import dev.necro.convenientfoundation.ConvenientFoundation;
import dev.necro.convenientfoundation.client.misc.PlayerMovementInputEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ConvenientFoundation.MODID)
public class MultiJumpHandler {

    private static final String MULTIJUMP_JUMPS_NBT_KEY = ConvenientFoundation.MODID + ":multijump.jumps";

    private static final String JUMP_TICKS_FIELD_NAME = "field_70773_bE";
    private static final Field JUMP_TICKS_FIELD = ObfuscationReflectionHelper.findField(LivingEntity.class, JUMP_TICKS_FIELD_NAME);

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;

        if (player.isAlive() &&
                ((player.onGround && !player.isElytraFlying())
                        || player.isSwimming()
                        || player.isActualySwimming()
                        || player.isSleeping())){
            int jumps = player.getPersistentData().getInt(MULTIJUMP_JUMPS_NBT_KEY);
            if (jumps != 0){
                player.getPersistentData().putInt(MULTIJUMP_JUMPS_NBT_KEY, 0);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void playerJumpInput(PlayerMovementInputEvent.JumpInputEvent event){
        attemptPlayerJump(event.getPlayer(), LogicalSide.CLIENT);
    }

    public static void playerJumpPacket(PlayerEntity player){
        attemptPlayerJump(player, LogicalSide.SERVER);
    }

    public static void attemptPlayerJump(PlayerEntity player, LogicalSide side){
        try {
            if (player.isAlive() &&
                    JUMP_TICKS_FIELD.getInt(player) != 10 &&
                    !player.onGround &&
                    !player.isElytraFlying() &&
                    !player.isSwimming() &&
                    !player.isActualySwimming() &&
                    !player.isSleeping()) {

                MultiJumpEvent.SpecialJump specialJumpEvent = new MultiJumpEvent.SpecialJump(player);
                if (!MinecraftForge.EVENT_BUS.post(specialJumpEvent) && specialJumpEvent.canJump()){
                    MultiJumpHandler.performJump(player, side);
                } else {
                    MultiJumpEvent.GatherJumps gatherJumpsEvent = new MultiJumpEvent.GatherJumps(player);
                    if (MinecraftForge.EVENT_BUS.post(gatherJumpsEvent)) return;

                    int jumps = player.getPersistentData().getInt(MULTIJUMP_JUMPS_NBT_KEY);
                    if (jumps < gatherJumpsEvent.jumps) {
                        MultiJumpHandler.performJump(player, side);
                        player.getPersistentData().putInt(MULTIJUMP_JUMPS_NBT_KEY, jumps + 1);
                    }
                }
            }
        } catch (IllegalAccessException e){
            LOGGER.error(e);
        }
    }

    public static void performJump(PlayerEntity player, LogicalSide side){
        if (MinecraftForge.EVENT_BUS.post(new MultiJumpEvent.Jump(player))) return;
        player.jump();
    }
}
