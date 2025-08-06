package io.github.ghasto69.happy_airships;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Function;

import static net.minecraft.world.item.DyeColor.*;

public class HAItems {
    public static final Item
            RED_HARNESS = harness(RED),
            ORANGE_HARNESS = harness(ORANGE),
            YELLOW_HARNESS = harness(YELLOW),
            LIME_HARNESS = harness(LIME),
            GREEN_HARNESS = harness(GREEN),
            CYAN_HARNESS = harness(CYAN),
            LIGHT_BLUE_HARNESS = harness(LIGHT_BLUE),
            BLUE_HARNESS = harness(BLUE),
            PURPLE_HARNESS = harness(PURPLE),
            MAGENTA_HARNESS = harness(MAGENTA),
            PINK_HARNESS = harness(PINK),
            BROWN_HARNESS = harness(BROWN),
            BLACK_HARNESS = harness(BLACK),
            GRAY_HARNESS = harness(GRAY),
            LIGHT_GRAY_HARNESS = harness(LIGHT_GRAY),
            WHITE_HARNESS = harness(WHITE);

    public static final PropellerItem PROPELLER = register("propeller", PropellerItem::new, new Item.Properties().stacksTo(1));

    private static Item harness(DyeColor dyeColor) {
        return register(dyeColor.getSerializedName() + "_harness_with_propeller", PropellerHarnessItem::new, new Item.Properties().stacksTo(1));
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> function, Item.Properties properties) {
        final var location = ExampleMod.id(name);
        return Registry.register(
                BuiltInRegistries.ITEM,
                location,
                function.apply(properties)
        );
    }

    public static void register() {
        final var tab = MultiloaderUtils
                .tabBuilder()
                .displayItems((displayContext, entries) -> entries.acceptAll(List.of(
                        new ItemStack(RED_HARNESS),
                        new ItemStack(ORANGE_HARNESS),
                        new ItemStack(YELLOW_HARNESS),
                        new ItemStack(LIME_HARNESS),
                        new ItemStack(GREEN_HARNESS),
                        new ItemStack(CYAN_HARNESS),
                        new ItemStack(LIGHT_BLUE_HARNESS),
                        new ItemStack(BLUE_HARNESS),
                        new ItemStack(PURPLE_HARNESS),
                        new ItemStack(MAGENTA_HARNESS),
                        new ItemStack(PINK_HARNESS),
                        new ItemStack(BROWN_HARNESS),
                        new ItemStack(BLACK_HARNESS),
                        new ItemStack(GRAY_HARNESS),
                        new ItemStack(LIGHT_GRAY_HARNESS),
                        new ItemStack(WHITE_HARNESS),
                        new ItemStack(PROPELLER)
                )))
                .icon(HAItems.RED_HARNESS::getDefaultInstance)
                .title(Component.translatable("itemGroup.happy_airships"))
                .build();
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ExampleMod.id("tab"), tab);
    }
}
