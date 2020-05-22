package dev.necro.convenientfoundation.common.misc.multijump;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

public class MultiJumpEvent extends PlayerEvent{
    public MultiJumpEvent(PlayerEntity player) {
        super(player);
    }

    @Cancelable
    public static class Jump extends MultiJumpEvent {
        public Jump(PlayerEntity player) {
            super(player);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    @Cancelable
    public static class SpecialJump extends MultiJumpEvent {
        private boolean jump;

        public SpecialJump(PlayerEntity player) {
            super(player);
        }

        public void setJump(boolean jump){
            this.jump = jump;
        }

        public boolean canJump(){
            return this.jump;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }

    @Cancelable
    public static class GatherJumps extends MultiJumpEvent {
        public int jumps;

        public GatherJumps(PlayerEntity player) {
            super(player);
        }

        @Override
        public boolean isCancelable() {
            return true;
        }
    }
}
