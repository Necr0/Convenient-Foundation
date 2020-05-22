package dev.necro.convenientfoundation.common.misc.coyotetime;

import dev.necro.convenientfoundation.ConvenientFoundation;
import dev.necro.convenientfoundation.common.misc.multijump.MultiJumpEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ConvenientFoundation.MODID)
public class CoyoteTimeHandler {

    private static final String COYOTETIME_TIME_OFF_GROUND_KEY = ConvenientFoundation.MODID + ":coyotetime.timeOffGround";
    private static final String COYOTETIME_JUMPED_KEY = ConvenientFoundation.MODID + ":coyotetime.jumped";

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event){
        PlayerEntity player = event.player;

        CompoundNBT nbt = player.getPersistentData();

        if (player.isAlive() &&
                ((player.onGround && !player.isElytraFlying())
                        || player.isSwimming()
                        || player.isActualySwimming()
                        || player.isSleeping())){
            nbt.putInt(COYOTETIME_TIME_OFF_GROUND_KEY, 0);
            nbt.putBoolean(COYOTETIME_JUMPED_KEY, false);
        } else {
            nbt.putInt(COYOTETIME_TIME_OFF_GROUND_KEY, nbt.getInt(COYOTETIME_TIME_OFF_GROUND_KEY) + 1);
        }
    }

    @SubscribeEvent
    public static void livingJump(LivingEvent.LivingJumpEvent event){
        if(!(event.getEntity() instanceof PlayerEntity)) return;
        PlayerEntity player = (PlayerEntity) event.getEntity();

        CompoundNBT nbt = player.getPersistentData();
        nbt.putBoolean(COYOTETIME_JUMPED_KEY, true);
    }

    @SubscribeEvent
    public static void specialJump(MultiJumpEvent.SpecialJump event){
        PlayerEntity player = event.getPlayer();

        if (player.isAlive() &&
                player.getMotion().y < 0) {

            CompoundNBT nbt = player.getPersistentData();
            if(nbt.getBoolean(COYOTETIME_JUMPED_KEY)) return;

            int timeOffGround = nbt.getInt(COYOTETIME_TIME_OFF_GROUND_KEY);
            if (timeOffGround <= 6) {
                event.setJump(true);
            }
        }
    }
}
