package io.github.ghasto69.happy_airships.fabric;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.world.item.CreativeModeTab;

public class MultiloaderUtilsImpl {
    public static CreativeModeTab.Builder tabBuilder() {
        return FabricItemGroup.builder();
    }
}
