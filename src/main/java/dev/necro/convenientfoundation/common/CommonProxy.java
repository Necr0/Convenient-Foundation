package dev.necro.convenientfoundation.common;

import dev.necro.convenientfoundation.common.network.PacketHandler;
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
    }

    public void setup(final FMLCommonSetupEvent event) {
        PacketHandler.init();
    }
}
