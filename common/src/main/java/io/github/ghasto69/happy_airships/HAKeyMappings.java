package io.github.ghasto69.happy_airships;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HAKeyMappings {
    public static final List<KeyMapping> KEY_MAPPINGS = new ArrayList<>();

    private static KeyMapping register(KeyMapping keyMapping) {
        KEY_MAPPINGS.add(keyMapping);
        return keyMapping;
    }

    public static final KeyMapping BOAT_DISPENSE =
            register(new KeyMapping("key.boat_dispense", GLFW.GLFW_KEY_H, "category.happy_airships"));
}
