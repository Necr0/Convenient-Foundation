package dev.necro.convenientfoundation;

import dev.necro.convenientfoundation.client.ClientProxy;
import dev.necro.convenientfoundation.common.CommonProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ConvenientFoundation.MODID)
public class ConvenientFoundation
{
    public static final String MODID = "convfound";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static CommonProxy proxy;

    public ConvenientFoundation() {
        ConvenientFoundation.proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }
}
