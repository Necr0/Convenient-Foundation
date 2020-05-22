package dev.necro.convenientfoundation.common.entity;

import dev.necro.convenientfoundation.ConvenientFoundation;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = ConvenientFoundation.MODID)
public class ModEntities {
    public static EntityType<UndeadMinerEntity> UNDEAD_MINER = null;

    public static Biome.SpawnListEntry UNDEAD_MINER_SPAWN;

    @SubscribeEvent
    public static void onEntityTypeRegistry(final RegistryEvent.Register<EntityType<?>> registryEvent) {
        IForgeRegistry<EntityType<?>> registry = registryEvent.getRegistry();

        ModEntities.UNDEAD_MINER = (EntityType<UndeadMinerEntity>)EntityType.Builder.<UndeadMinerEntity>create(UndeadMinerEntity::new, EntityClassification.MONSTER)
                .size(0.6F, 1.95F)
                .build("convfound:undead_miner")
                .setRegistryName("undead_miner");
        registry.register(ModEntities.UNDEAD_MINER);
        UNDEAD_MINER_SPAWN = new Biome.SpawnListEntry(ModEntities.UNDEAD_MINER,35,1,1);
    }

    @SubscribeEvent
    public static void onPotentialSpawns(WorldEvent.PotentialSpawns event){
        if (event.getType()==EntityClassification.MONSTER && Feature.MINESHAFT.isPositionInStructure(event.getWorld(),event.getPos())){
            event.getList().add(UNDEAD_MINER_SPAWN);
        }
    }
}
