package io.github.ghasto69.happy_airships.fabric.datagen;

import io.github.ghasto69.happy_airships.HAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class HARecipeProvider extends FabricRecipeProvider {
    public HARecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NotNull RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                shaped(RecipeCategory.MISC, HAItems.PROPELLER)
                        .pattern(" I ")
                        .pattern("IWI")
                        .pattern(" I ")
                        .define('I', Items.IRON_INGOT)
                        .define('W', ItemTags.LOGS)
                        .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                        .save(recipeOutput);

                harness(Items.WHITE_HARNESS, HAItems.WHITE_HARNESS);
                harness(Items.ORANGE_HARNESS, HAItems.ORANGE_HARNESS);
                harness(Items.MAGENTA_HARNESS, HAItems.MAGENTA_HARNESS);
                harness(Items.LIGHT_BLUE_HARNESS, HAItems.LIGHT_BLUE_HARNESS);
                harness(Items.YELLOW_HARNESS, HAItems.YELLOW_HARNESS);
                harness(Items.LIME_HARNESS, HAItems.LIME_HARNESS);
                harness(Items.PINK_HARNESS, HAItems.PINK_HARNESS);
                harness(Items.GRAY_HARNESS, HAItems.GRAY_HARNESS);
                harness(Items.LIGHT_GRAY_HARNESS, HAItems.LIGHT_GRAY_HARNESS);
                harness(Items.CYAN_HARNESS, HAItems.CYAN_HARNESS);
                harness(Items.PURPLE_HARNESS, HAItems.PURPLE_HARNESS);
                harness(Items.BLUE_HARNESS, HAItems.BLUE_HARNESS);
                harness(Items.BROWN_HARNESS, HAItems.BROWN_HARNESS);
                harness(Items.GREEN_HARNESS, HAItems.GREEN_HARNESS);
                harness(Items.RED_HARNESS, HAItems.RED_HARNESS);
                harness(Items.BLACK_HARNESS, HAItems.BLACK_HARNESS);
            }

            private void harness(Item harness, Item withPropeller) {
                shapeless(RecipeCategory.MISC, withPropeller)
                        .requires(harness)
                        .requires(HAItems.PROPELLER)
                        .unlockedBy("has_propeller", has(HAItems.PROPELLER))
                        .save(recipeOutput);
            }
        };
    }

    @Override
    public @NotNull String getName() {
        return "Recipes";
    }
}
