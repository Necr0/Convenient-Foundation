package dev.necro.convenientfoundation.common;

import dev.necro.convenientfoundation.common.block.ModBlocks;
import dev.necro.convenientfoundation.common.entity.ModEntities;
import dev.necro.convenientfoundation.common.item.ModItems;
import dev.necro.convenientfoundation.common.item.slimebucket.SlimeHarvestingHandler;
import dev.necro.convenientfoundation.common.misc.FallingBlockDiggingHandler;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonProxy {
    protected IEventBus modEventBus;

    public CommonProxy(){
        modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        registerListeners(modEventBus);
    }
    
    public void registerListeners(IEventBus modEventBus){
        modEventBus.addListener(this::setup);
        PlayerInteractEvent.RightClickBlock ev=null;

        MinecraftForge.EVENT_BUS.register(ModItems.class);
        MinecraftForge.EVENT_BUS.register(ModBlocks.class);
        MinecraftForge.EVENT_BUS.register(ModEntities.class);
        MinecraftForge.EVENT_BUS.register(SlimeHarvestingHandler.class);
        MinecraftForge.EVENT_BUS.register(FallingBlockDiggingHandler.class);
    }

    public void setup(final FMLCommonSetupEvent event)
    {

    }
}
