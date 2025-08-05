package io.github.ghasto69.happy_airships.neoforge;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import io.github.ghasto69.happy_airships.ExampleMod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(ExampleMod.MOD_ID)
public final class ExampleModNeoForge {
    private boolean registered = false;

    public ExampleModNeoForge(IEventBus bus) {
        // Run our common setup.
        ExampleMod.init();
        bus.addListener(this::registerEvent);
    }

    private void registerEvent(RegisterEvent event) {
        if(registered) return;
        ExampleMod.registriesReady();
        registered = true;
    }
}
