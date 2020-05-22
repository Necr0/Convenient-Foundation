package dev.necro.convenientfoundation.common.item.multijump;

import dev.necro.convenientfoundation.ConvenientFoundation;
import dev.necro.convenientfoundation.common.item.ModItems;
import dev.necro.convenientfoundation.common.misc.multijump.MultiJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, modid = ConvenientFoundation.MODID)
public class MultiJumpItemHandler {

    @SubscribeEvent
    public static void gatherMultiJumps(MultiJumpEvent.GatherJumps event){
        if(event.getPlayer().inventory.count(ModItems.MULTI_JUMP)>=1){
            event.jumps += ((MultiJumpItem)ModItems.MULTI_JUMP).jumps;
        }
    }
}
