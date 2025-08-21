package io.github.ghasto69.happy_airships.fabric;

import io.github.ghasto69.happy_airships.dispenser_boat.BoatDispense;
import net.fabricmc.api.ModInitializer;

import io.github.ghasto69.happy_airships.ExampleMod;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public final class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        ExampleMod.init();
        ExampleMod.registriesReady();

        PayloadTypeRegistry.playC2S().register(BoatDispense.TYPE, BoatDispense.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(BoatDispense.TYPE,
                (payload, context) -> context.server().execute(() -> BoatDispense.handleC2S(context.player())));
    }
}
