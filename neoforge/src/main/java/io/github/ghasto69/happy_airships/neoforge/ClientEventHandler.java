package io.github.ghasto69.happy_airships.neoforge;

import io.github.ghasto69.happy_airships.*;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.network.event.RegisterClientPayloadHandlersEvent;

@EventBusSubscriber(modid = ExampleMod.MOD_ID, value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for(final var entry : HAEntityRenderers.RENDERERS) {
            registerRenderer(entry, event);
        }
    }

    private static <T extends Entity> void registerRenderer(HAEntityRenderers.RendererEntry<T> entry, EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(entry.type(), entry.provider());
    }

    @SubscribeEvent
    private static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (final var entry : HAModelLayerLocations.MODEL_LAYER_MAP.entrySet()) {
            event.registerLayerDefinition(entry.getKey(), entry.getValue());
        }
    }

    @SubscribeEvent
    private static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        for (final var keyMapping : HAKeyMappings.KEY_MAPPINGS) {
            event.register(keyMapping);
        }
    }

    @SubscribeEvent
    private static void clientTick(ClientTickEvent.Post event) {
        HAClientEventCallbacks.clientTick();
    }
}
