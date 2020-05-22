package dev.necro.convenientfoundation.common.item;

import dev.necro.convenientfoundation.ConvenientFoundation;
import dev.necro.convenientfoundation.common.item.multijump.MultiJumpItem;
import dev.necro.convenientfoundation.common.item.slimebucket.SlimeBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(ConvenientFoundation.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ConvenientFoundation.MODID)
public class ModItems {
    public static final Item SLIME_BUCKET = null;
    public static final Item MAGMA_CREAM_BUCKET = null;
    public static final Item MULTI_JUMP = null;
    // Dummy Items
    public static final Item ARCANE_DUST = null;
    // Block Items
    public static final Item ARCANE_BLOCK = null;

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> registryEvent) {
        IForgeRegistry<Item> registry = registryEvent.getRegistry();
        registry.register(new SlimeBucketItem(new ItemStack(Items.SLIME_BALL)).setRegistryName("slime_bucket"));
        registry.register(new SlimeBucketItem(new ItemStack(Items.MAGMA_CREAM)).setRegistryName("magma_cream_bucket"));
        registry.register(new MultiJumpItem(1,new Item.Properties().maxStackSize(1).group(ItemGroup.MISC)).setRegistryName("multi_jump"));
        // Dummy Items
        registry.register(new Item(new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName("arcane_dust"));
    }
}
