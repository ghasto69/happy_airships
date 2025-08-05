package io.github.ghasto69.happy_airships;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class HAEnchantments {
    public static final ResourceKey<Enchantment> SWIFTNESS =
            ResourceKey.create(Registries.ENCHANTMENT, ExampleMod.id("swiftness"));
}
