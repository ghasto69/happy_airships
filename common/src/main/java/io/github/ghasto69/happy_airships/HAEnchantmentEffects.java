package io.github.ghasto69.happy_airships;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;

public class HAEnchantmentEffects {
    public static final DataComponentType<EnchantmentValueEffect> SWIFTNESS = Registry.register(
            BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE,
            ExampleMod.id("swiftness"),
            DataComponentType.<EnchantmentValueEffect>builder().persistent(EnchantmentValueEffect.CODEC).build()
    );

    public static void register() {}
}
