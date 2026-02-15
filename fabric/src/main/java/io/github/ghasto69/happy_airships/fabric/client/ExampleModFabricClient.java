package io.github.ghasto69.happy_airships.fabric.client;

import io.github.ghasto69.happy_airships.propeller.PropellerModel;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

public final class ExampleModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        EntityModelLayerRegistry.registerModelLayer(PropellerModel.LAYER_LOCATION, PropellerModel::createBodyLayer);
    }
}
