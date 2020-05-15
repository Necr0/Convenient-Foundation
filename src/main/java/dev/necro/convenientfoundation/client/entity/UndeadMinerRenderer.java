package dev.necro.convenientfoundation.client.entity;

import dev.necro.convenientfoundation.ConvenientFoundation;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class UndeadMinerRenderer extends ZombieRenderer {
    private static final ResourceLocation UNDEAD_MINER_ZOMBIE_TEXTURES = new ResourceLocation(ConvenientFoundation.MODID,"textures/entity/undead_miner.png");

    public UndeadMinerRenderer(EntityRendererManager rendererManager) {
        super(rendererManager);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public ResourceLocation getEntityTexture(ZombieEntity entity) {
        return UNDEAD_MINER_ZOMBIE_TEXTURES;
    }
}
