package io.github.ghasto69.happy_airships;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.CreativeModeTab;

public class MultiloaderUtils {
    @ExpectPlatform
    public static CreativeModeTab.Builder tabBuilder() {
        throw new AssertionError();
    }
}
