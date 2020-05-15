package dev.necro.convenientfoundation.common.item.slimebucket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class SlimeBucketItem extends Item {
    private static final ResourceLocation SLIME_ID = new ResourceLocation("minecraft", "slime");
    private static final ResourceLocation MAGMA_CUBE_ID = new ResourceLocation("minecraft", "magma_cube");

    public ItemStack contents;

    public SlimeBucketItem(@Nonnull ItemStack contents) {
        super(new Item.Properties()
                    .containerItem(Items.BUCKET)
                    .group(ItemGroup.MISC)
                    .maxStackSize(1));
        this.contents=contents;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack=playerIn.getHeldItem(handIn);

        if(worldIn.isRemote)
            return ActionResult.resultSuccess(this.getContainerItem(stack));

        playerIn.inventory.placeItemBackInInventory(worldIn, contents.copy());

        return ActionResult.resultSuccess(this.getContainerItem(stack));
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        ResourceLocation targetRegistryName = target.getType().getRegistryName();

        if(targetRegistryName.equals(SLIME_ID))
            return true;
        else if(targetRegistryName.equals(MAGMA_CUBE_ID))
            return true;

        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}

