package io.github.ghasto69.happy_airships.fabric.datagen;

import io.github.ghasto69.happy_airships.HAEnchantmentEffects;
import io.github.ghasto69.happy_airships.HAEnchantments;
import io.github.ghasto69.happy_airships.HAItemTags;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;

public class HappyAirshipsDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final var pack = fabricDataGenerator.createPack();
        pack.addProvider(HADynamicRegistryProvider::new);
        pack.addProvider(HAItemTagProvider::new);
        pack.addProvider(HAModelProvider::new);
        pack.addProvider(HAEnchantmentTagProvider::new);
        pack.addProvider(HAAdvancementProvider::new);
        pack.addProvider(HARecipeProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.ENCHANTMENT, context -> {
            final var items = context.lookup(Registries.ITEM);
            context.register(HAEnchantments.SWIFTNESS, Enchantment.enchantment(Enchantment.definition(
                                    items.getOrThrow(HAItemTags.SWIFTNESS_ENCHANTABLE),
                                    10,
                                    2,
                                    Enchantment.dynamicCost(1, 11),
                                    Enchantment.dynamicCost(21, 11),
                                    1,
                                    EquipmentSlotGroup.BODY
                            ))
                            .withSpecialEffect(HAEnchantmentEffects.SWIFTNESS, new AddValue(LevelBasedValue.perLevel(0.5F)))
                            .build(HAEnchantments.SWIFTNESS.location())
            );
        });
    }
}
