package io.github.ghasto69.happy_airships;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class HAItemTags {
    public static final TagKey<Item>
            SWIFTNESS_ENCHANTABLE = TagKey.create(Registries.ITEM, ExampleMod.id("swiftness_enchantable")),
            HARNESSES_WITH_PROPELLERS = TagKey.create(Registries.ITEM, ExampleMod.id("harnesses_with_propellers"));
}
