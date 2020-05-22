package dev.necro.convenientfoundation;

import dev.necro.convenientfoundation.client.ClientProxy;
import dev.necro.convenientfoundation.common.CommonProxy;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ConvenientFoundation.MODID)
public class ConvenientFoundation
{
    public static final String MODID = "convfound";

    private static final Logger LOGGER = LogManager.getLogger();

    public static CommonProxy proxy;

    public ConvenientFoundation() {
        ConvenientFoundation.proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }
}
