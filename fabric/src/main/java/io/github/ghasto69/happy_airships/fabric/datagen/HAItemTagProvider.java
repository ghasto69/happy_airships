package io.github.ghasto69.happy_airships.fabric.datagen;

import io.github.ghasto69.happy_airships.HAItemTags;
import io.github.ghasto69.happy_airships.HAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.world.item.DyeColor.*;
import static net.minecraft.world.item.DyeColor.BLACK;
import static net.minecraft.world.item.DyeColor.BLUE;
import static net.minecraft.world.item.DyeColor.BROWN;
import static net.minecraft.world.item.DyeColor.CYAN;
import static net.minecraft.world.item.DyeColor.GRAY;
import static net.minecraft.world.item.DyeColor.GREEN;
import static net.minecraft.world.item.DyeColor.LIGHT_BLUE;
import static net.minecraft.world.item.DyeColor.LIGHT_GRAY;
import static net.minecraft.world.item.DyeColor.LIME;
import static net.minecraft.world.item.DyeColor.MAGENTA;
import static net.minecraft.world.item.DyeColor.PINK;
import static net.minecraft.world.item.DyeColor.PURPLE;
import static net.minecraft.world.item.DyeColor.WHITE;

public class HAItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public HAItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        valueLookupBuilder(HAItemTags.HARNESSES_WITH_PROPELLERS)
                .add(HAItems.RED_HARNESS,
                        HAItems.ORANGE_HARNESS,
                        HAItems.YELLOW_HARNESS,
                        HAItems.LIME_HARNESS,
                        HAItems.GREEN_HARNESS,
                        HAItems.CYAN_HARNESS,
                        HAItems.LIGHT_BLUE_HARNESS,
                        HAItems.BLUE_HARNESS,
                        HAItems.PURPLE_HARNESS,
                        HAItems.MAGENTA_HARNESS,
                        HAItems.PINK_HARNESS,
                        HAItems.BROWN_HARNESS,
                        HAItems.BLACK_HARNESS,
                        HAItems.GRAY_HARNESS,
                        HAItems.LIGHT_GRAY_HARNESS,
                        HAItems.WHITE_HARNESS);
        valueLookupBuilder(ItemTags.HARNESSES)
                .forceAddTag(HAItemTags.HARNESSES_WITH_PROPELLERS);
        valueLookupBuilder(HAItemTags.SWIFTNESS_ENCHANTABLE)
                .add(HAItems.PROPELLER)
                .forceAddTag(HAItemTags.HARNESSES_WITH_PROPELLERS);
    }
}
