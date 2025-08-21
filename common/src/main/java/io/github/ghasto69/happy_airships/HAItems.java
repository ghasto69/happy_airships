package io.github.ghasto69.happy_airships;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;

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

    public static final PropellerItem PROPELLER = register("propeller", PropellerItem::new, new Item.Properties().enchantable(10));

    public static final BoatItem
            OAK_DISPENSER_BOAT = register("oak_dispenser_boat", p -> new BoatItem(HAEntityTypes.OAK_DISPENSER_BOAT, p), new Item.Properties());

    private static Item harness(DyeColor dyeColor) {
        final var entities = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ENTITY_TYPE);
        return register(dyeColor.getSerializedName() + "_harness_with_propeller", Item::new, new Item.Properties()
                .component(
                        DataComponents.EQUIPPABLE, Equippable
                                .builder(EquipmentSlot.BODY)
                                .setEquipSound(SoundEvents.HARNESS_EQUIP)
                                .setAsset(EquipmentAssets.HARNESSES.get(dyeColor))
                                .setAllowedEntities(entities.getOrThrow(EntityTypeTags.CAN_EQUIP_HARNESS))
                                .setEquipOnInteract(true)
                                .setCanBeSheared(true)
                                .build()
                )
                .enchantable(10)
        );
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> function, Item.Properties properties) {
        final var location = ExampleMod.id(name);
        return Registry.register(
                BuiltInRegistries.ITEM,
                location,
                function.apply(properties.setId(ResourceKey.create(Registries.ITEM, location)))
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
