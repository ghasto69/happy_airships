package io.github.ghasto69.happy_airships.fabric.datagen;

import io.github.ghasto69.happy_airships.ExampleMod;
import io.github.ghasto69.happy_airships.HAAdvancements;
import io.github.ghasto69.happy_airships.HAItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class HAAdvancementProvider extends FabricAdvancementProvider {
    protected HAAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        final var happyAirship = Advancement.Builder.advancement()
                .parent(ResourceLocation.withDefaultNamespace("husbandry/place_dried_ghast_in_water"))
                .display(
                        HAItems.PROPELLER,
                        Component.translatable("advancement.happy_airships.happy_airship"),
                        Component.translatable("advancement.happy_airships.happy_airship.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("trigger", CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
                .build(HAAdvancements.HAPPY_AIRSHIP);

        consumer.accept(happyAirship);
    }
}
