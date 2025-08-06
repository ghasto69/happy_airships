package io.github.ghasto69.happy_airships.fabric.datagen;

import com.blackgear.vanillabackport.common.registries.ModItems;
import io.github.ghasto69.happy_airships.HAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
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
    public void buildRecipes(RecipeOutput recipeOutput) {
        harness(ModItems.WHITE_HARNESS.get(), HAItems.WHITE_HARNESS, recipeOutput);
        harness(ModItems.ORANGE_HARNESS.get(), HAItems.ORANGE_HARNESS, recipeOutput);
        harness(ModItems.MAGENTA_HARNESS.get(), HAItems.MAGENTA_HARNESS, recipeOutput);
        harness(ModItems.LIGHT_BLUE_HARNESS.get(), HAItems.LIGHT_BLUE_HARNESS, recipeOutput);
        harness(ModItems.YELLOW_HARNESS.get(), HAItems.YELLOW_HARNESS, recipeOutput);
        harness(ModItems.LIME_HARNESS.get(), HAItems.LIME_HARNESS, recipeOutput);
        harness(ModItems.PINK_HARNESS.get(), HAItems.PINK_HARNESS, recipeOutput);
        harness(ModItems.GRAY_HARNESS.get(), HAItems.GRAY_HARNESS, recipeOutput);
        harness(ModItems.LIGHT_GRAY_HARNESS.get(), HAItems.LIGHT_GRAY_HARNESS, recipeOutput);
        harness(ModItems.CYAN_HARNESS.get(), HAItems.CYAN_HARNESS, recipeOutput);
        harness(ModItems.PURPLE_HARNESS.get(), HAItems.PURPLE_HARNESS, recipeOutput);
        harness(ModItems.BLUE_HARNESS.get(), HAItems.BLUE_HARNESS, recipeOutput);
        harness(ModItems.BROWN_HARNESS.get(), HAItems.BROWN_HARNESS, recipeOutput);
        harness(ModItems.GREEN_HARNESS.get(), HAItems.GREEN_HARNESS, recipeOutput);
        harness(ModItems.RED_HARNESS.get(), HAItems.RED_HARNESS, recipeOutput);
        harness(ModItems.BLACK_HARNESS.get(), HAItems.BLACK_HARNESS, recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, HAItems.PROPELLER)
                .pattern(" I ")
                .pattern("IWI")
                .pattern(" I ")
                .define('I', Items.IRON_INGOT)
                .define('W', ItemTags.LOGS)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);
    }

    private void harness(Item harness, Item withPropeller, RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, withPropeller)
                .requires(harness)
                .requires(HAItems.PROPELLER)
                .unlockedBy("has_propeller", has(HAItems.PROPELLER))
                .save(recipeOutput);
    }

    @Override
    public @NotNull String getName() {
        return "Recipes";
    }
}
