package io.github.ghasto69.happy_airships.fabric.datagen;

import io.github.ghasto69.happy_airships.HAItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

public class HAModelProvider extends FabricModelProvider {
    public HAModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators generator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators generator) {
        generator.generateFlatItem(HAItems.RED_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.ORANGE_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.YELLOW_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.LIME_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.GREEN_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.CYAN_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.LIGHT_BLUE_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.BLUE_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.PURPLE_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.MAGENTA_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.PINK_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.BROWN_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.BLACK_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.GRAY_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.LIGHT_GRAY_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.WHITE_HARNESS, ModelTemplates.FLAT_ITEM);
        generator.generateFlatItem(HAItems.PROPELLER, ModelTemplates.FLAT_ITEM);
    }
}
