package io.github.ghasto69.happy_airships.fabric.client;

import io.github.ghasto69.happy_airships.HAClientEventCallbacks;
import io.github.ghasto69.happy_airships.HAEntityRenderers;
import io.github.ghasto69.happy_airships.HAKeyMappings;
import io.github.ghasto69.happy_airships.HAModelLayerLocations;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

@Environment(EnvType.CLIENT)
public final class ExampleModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.

        for(final var entry : HAEntityRenderers.RENDERERS) {
            registerRenderer(entry);
        }

        for(final var entry : HAModelLayerLocations.MODEL_LAYER_MAP.entrySet()) {
            EntityModelLayerRegistry.registerModelLayer(entry.getKey(), () -> entry.getValue().get());
        }

        for(final var keyMapping : HAKeyMappings.KEY_MAPPINGS) {
            KeyBindingHelper.registerKeyBinding(keyMapping);
        }

        ClientTickEvents.END_WORLD_TICK.register((clientLevel) -> HAClientEventCallbacks.clientTick());
    }

    private <T extends Entity> void registerRenderer(HAEntityRenderers.RendererEntry<T> entry) {
        EntityRendererRegistry.register(entry.type(), entry.provider());
    }
}
