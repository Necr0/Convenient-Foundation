package dev.necro.convenientfoundation.common.item.multijump;

import dev.necro.convenientfoundation.common.misc.multijump.MultiJumpEvent;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MultiJumpItem extends Item {
    public int jumps;

    public MultiJumpItem(int jumps, Properties properties) {
        super(properties);
        this.jumps = jumps;
    }

    @SubscribeEvent
    public void gatherMultiJumps(MultiJumpEvent.GatherJumps event){
        if(event.getPlayer().inventory.count(this)>=1){
            event.jumps += this.jumps;
        }
    }

}
