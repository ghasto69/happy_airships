package io.github.ghasto69.happy_airships;

import io.github.ghasto69.happy_airships.dispenser_boat.AbstractDispenserBoatEntity;
import io.github.ghasto69.happy_airships.dispenser_boat.DispenserBoatEntity;
import net.minecraft.client.renderer.entity.AbstractBoatRenderer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class HAEntityTypes {
    public static final EntityType<AbstractDispenserBoatEntity> OAK_DISPENSER_BOAT =
            boat("oak_dispenser_boat", DispenserBoatEntity::new, () -> HAItems.OAK_DISPENSER_BOAT);

    @FunctionalInterface
    private interface BoatFactory {
        AbstractDispenserBoatEntity create(EntityType<AbstractDispenserBoatEntity> entityType, Level level, Supplier<Item> item);
    }

    private static EntityType<AbstractDispenserBoatEntity> boat(String name, BoatFactory boatFactory, Supplier<Item> item) {
        final var id = ExampleMod.id(name);
        final var rkey = ResourceKey.create(Registries.ENTITY_TYPE, id);
        return Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                id,
                EntityType.Builder.<AbstractDispenserBoatEntity>of((x, y) -> boatFactory.create(x, y, item), MobCategory.MISC).noLootTable().sized(1.375F, 0.5625F).eyeHeight(0.5625F).clientTrackingRange(10).build(rkey)
        );
    }

    public static void register() {}
}
