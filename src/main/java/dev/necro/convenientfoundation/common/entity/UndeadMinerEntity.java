package dev.necro.convenientfoundation.common.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class UndeadMinerEntity extends ZombieEntity
{

    public UndeadMinerEntity(EntityType<? extends ZombieEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    protected ItemStack getSkullDrop()
    {
        /** @todo maybe add skull? */
        return ItemStack.EMPTY;
    }

    @Override
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        super.setEquipmentBasedOnDifficulty(difficulty);

        ItemStack pickaxe;
        int pickaxe_level=this.rand.nextInt(18);
        if(pickaxe_level<7)
            pickaxe=new ItemStack(Items.WOODEN_PICKAXE);
        else if(pickaxe_level<12)
            pickaxe=new ItemStack(Items.STONE_PICKAXE);
        else if(pickaxe_level<16)
            pickaxe=new ItemStack(Items.IRON_PICKAXE);
        else if(pickaxe_level<18)
            pickaxe=new ItemStack(Items.DIAMOND_PICKAXE);
        else
            pickaxe=new ItemStack(Items.GOLDEN_PICKAXE);

        int enchantment_level=10+this.rand.nextInt(28);
        pickaxe= EnchantmentHelper.addRandomEnchantment(this.rand, pickaxe, enchantment_level,false);
        if(this.rand.nextInt(42)==0)
            pickaxe.addEnchantment(Enchantments.MENDING,1);
        this.setItemStackToSlot(EquipmentSlotType.MAINHAND, pickaxe);
        this.inventoryHandsDropChances[EquipmentSlotType.MAINHAND.getSlotIndex()] = .25f;
    }
}