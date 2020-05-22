package dev.necro.convenientfoundation.common.misc;

import dev.necro.convenientfoundation.ConvenientFoundation;
import net.minecraft.block.BlockState;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ConvenientFoundation.MODID)
public class FallingBlockDiggingHandler {

    @SubscribeEvent
    public static void attackEntity(AttackEntityEvent event){
        if (!(event.getTarget() instanceof FallingBlockEntity))
            return;

        PlayerEntity player = event.getPlayer();

        System.out.println(player.getEntityWorld().isRemote);

        ItemStack activeItem = player.getHeldItemMainhand();
        if (!activeItem.getToolTypes().contains(ToolType.SHOVEL))
            return;

        FallingBlockEntity target = (FallingBlockEntity)event.getTarget();

        BlockState targetBlockState = target.getBlockState();

        if ( targetBlockState.getHarvestTool().equals(ToolType.SHOVEL) &&
                targetBlockState.getHarvestLevel() <= activeItem.getHarvestLevel(ToolType.SHOVEL, player, targetBlockState) &&
                activeItem.getDestroySpeed(targetBlockState) >= 6F){
            target.entityDropItem(target.getBlockState().getBlock());
            target.remove();

            World world=target.getEntityWorld();

            activeItem.onBlockDestroyed(world, targetBlockState,null, player);
            activeItem.onBlockDestroyed(world, targetBlockState,null, null);
            activeItem.onBlockDestroyed(world, targetBlockState,null, null);
        }
    }
}
