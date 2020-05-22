package dev.necro.convenientfoundation.data;

import dev.necro.convenientfoundation.ConvenientFoundation;
import dev.necro.convenientfoundation.common.block.ModBlocks;
import dev.necro.convenientfoundation.common.item.ModItems;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.RecipeProvider {
    public RecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.ARCANE_BLOCK)
                .patternLine("xxx")
                .patternLine("xxx")
                .patternLine("xxx")
                .key('x', ModItems.ARCANE_DUST)
                .setGroup(ConvenientFoundation.MODID)
                .addCriterion("arcane_dust", InventoryChangeTrigger.Instance.forItems(ModItems.ARCANE_DUST))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.ARCANE_DUST,9)
                .addIngredient(ModItems.ARCANE_BLOCK)
                .setGroup(ConvenientFoundation.MODID)
                .addCriterion("arcane_block", InventoryChangeTrigger.Instance.forItems(ModItems.ARCANE_BLOCK))
                .build(consumer);

        ShapelessRecipeBuilder.shapelessRecipe(Items.SLIME_BALL)
                .addIngredient(ModItems.SLIME_BUCKET)
                .setGroup(ConvenientFoundation.MODID)
                .addCriterion("slime_bucket", InventoryChangeTrigger.Instance.forItems(ModItems.SLIME_BUCKET))
                .build(consumer);
        ShapelessRecipeBuilder.shapelessRecipe(Items.MAGMA_CREAM)
                .addIngredient(ModItems.MAGMA_CREAM_BUCKET)
                .setGroup(ConvenientFoundation.MODID)
                .addCriterion("magma_cream_bucket", InventoryChangeTrigger.Instance.forItems(ModItems.MAGMA_CREAM_BUCKET))
                .build(consumer);
    }
}
