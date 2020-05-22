package dev.necro.convenientfoundation.common.block;

import dev.necro.convenientfoundation.ConvenientFoundation;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(ConvenientFoundation.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ConvenientFoundation.MODID)
public class ModBlocks {
    public static final Block ARCANE_BLOCK = null;

    @SubscribeEvent
    public static void onBlockRegistry(final RegistryEvent.Register<Block> registryEvent) {
        IForgeRegistry<Block> registry = registryEvent.getRegistry();
        registry.register(new Block(Block.Properties.create(Material.IRON, DyeColor.CYAN).hardnessAndResistance(5.0F, 6.0F).sound(SoundType.METAL)).setRegistryName("arcane_block"));
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> registryEvent) {
        IForgeRegistry<Item> registry = registryEvent.getRegistry();
        registry.register(new BlockItem(ARCANE_BLOCK, new Item.Properties().group(ItemGroup.MATERIALS)).setRegistryName(ARCANE_BLOCK.getRegistryName()));
    }
}
