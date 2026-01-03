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
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ImpossibleTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class HAAdvancementProvider extends FabricAdvancementProvider {
    protected HAAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
        final var parentId = Identifier.withDefaultNamespace("husbandry/place_dried_ghast_in_water");
        final var parentKey = ResourceKey.create(Registries.ADVANCEMENT, parentId);

        Advancement.Builder builder = Advancement.Builder.advancement();
        final var parentLookup = provider.lookup(Registries.ADVANCEMENT);
        if (parentLookup.isPresent()) {
            final var parent = parentLookup.get().get(parentKey);
            if (parent.isPresent()) {
                final var holder = parent.get();
                builder = builder.parent(new AdvancementHolder(holder.key().identifier(), holder.value()));
            } else {
                builder = applyParentFallback(builder, parentId);
            }
        } else {
            builder = applyParentFallback(builder, parentId);
        }

        final var happyAirship = builder
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
                .addCriterion("trigger", new Criterion<>(CriteriaTriggers.IMPOSSIBLE, new ImpossibleTrigger.TriggerInstance()))
                .build(HAAdvancements.HAPPY_AIRSHIP);

        consumer.accept(happyAirship);
    }

    @SuppressWarnings("removal")
    private static Advancement.Builder applyParentFallback(Advancement.Builder builder, Identifier parentId) {
        // Fallback for vanilla parent when registry lookup is unavailable.
        return builder.parent(parentId);
    }
}
