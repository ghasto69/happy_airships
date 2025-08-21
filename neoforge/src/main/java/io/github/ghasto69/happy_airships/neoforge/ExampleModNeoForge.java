package io.github.ghasto69.happy_airships.neoforge;

import io.github.ghasto69.happy_airships.HAEntityRenderers;
import io.github.ghasto69.happy_airships.HAKeyMappings;
import io.github.ghasto69.happy_airships.HAModelLayerLocations;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import io.github.ghasto69.happy_airships.ExampleMod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    public ExampleModNeoForge(IEventBus bus) {
        // Run our common setup.
        ExampleMod.init();
    }
}
