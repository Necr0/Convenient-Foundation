package dev.necro.convenientfoundation.common.item.slimebucket;

import dev.necro.convenientfoundation.ConvenientFoundation;
import dev.necro.convenientfoundation.common.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ConvenientFoundation.MODID)
public class SlimeHarvestingHandler {
    private static final ResourceLocation SLIME_ID = new ResourceLocation("minecraft", "slime");
    private static final ResourceLocation MAGMA_CUBE_ID = new ResourceLocation("minecraft", "magma_cube");

    @SubscribeEvent
    public static void entityInteract(PlayerInteractEvent.EntityInteract event){
        ItemStack stack=event.getItemStack();
        PlayerEntity player=event.getPlayer();

        if (stack.getItem() == Items.BUCKET)
        {
            //check entity is a valid candidate for havesting
            if(!(event.getTarget() instanceof SlimeEntity))
                return;
            SlimeEntity target=((SlimeEntity)event.getTarget());

            //is slime alive?
            if(!target.isAlive())
                return;

            //determine what type of slime is being harvested and create item stack accordingly
            ItemStack new_stack=ItemStack.EMPTY;
            ResourceLocation targetRegistryName = target.getType().getRegistryName();
            if(targetRegistryName.equals(SLIME_ID))
                new_stack=new ItemStack(ModItems.SLIME_BUCKET,1);
            else if(targetRegistryName.equals(MAGMA_CUBE_ID))
                new_stack=new ItemStack(ModItems.MAGMA_CREAM_BUCKET,1);
            else
                return;
            Item i;


            World world = event.getWorld();

            //play sound and consume bucket
            player.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
            stack.shrink(1);

            //damage slime
            target.attackEntityFrom(DamageSource.STARVE,1.2f + world.rand.nextFloat());

            //complicated add item into players inventory at the correct position logic
            if (stack.isEmpty()) {
                player.setHeldItem(event.getHand(), new_stack);
            } else {
                player.inventory.placeItemBackInInventory(event.getWorld(), new_stack);
            }

            event.setCanceled(true);
        }
    }
}
