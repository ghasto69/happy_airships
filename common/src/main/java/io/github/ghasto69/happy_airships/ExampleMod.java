package io.github.ghasto69.happy_airships;

import net.minecraft.resources.ResourceLocation;

public final class ExampleMod {
    public static final String MOD_ID = "happy_airships";

    public static void init() {
        // Write common init code here.
    }

    public static void registriesReady() { // Runs when it is safe to use registries, same as init() on Fabric.
        HAItems.register();
        HAEnchantmentEffects.register();
    }

    public static ResourceLocation id(String path) { return ResourceLocation.fromNamespaceAndPath(MOD_ID, path); }
}
