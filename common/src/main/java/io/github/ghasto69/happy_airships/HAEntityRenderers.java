package io.github.ghasto69.happy_airships;

import com.google.common.collect.ImmutableList;
import io.github.ghasto69.happy_airships.dispenser_boat.AbstractDispenserBoatEntity;
import io.github.ghasto69.happy_airships.dispenser_boat.DispenserBoatEntity;
import io.github.ghasto69.happy_airships.dispenser_boat.DispenserBoatRenderer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.*;

public final class HAEntityRenderers {
    public record RendererEntry<T extends Entity>(
            EntityType<? extends T> type,
            EntityRendererProvider<T> provider
    ) {}

    public static final List<RendererEntry<?>> RENDERERS = new ArrayList<>();

    public static <T extends Entity> void register(
            EntityType<? extends T> entityType,
            EntityRendererProvider<T> factory
    ) {
        RENDERERS.add(new RendererEntry<>(entityType, factory));
    }

    static {
        register(HAEntityTypes.OAK_DISPENSER_BOAT, context -> new DispenserBoatRenderer(context, HAModelLayerLocations.OAK_DISPENSER_BOAT));
    }
}
