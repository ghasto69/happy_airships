package io.github.ghasto69.happy_airships.neoforge;

import io.github.ghasto69.happy_airships.ExampleMod;
import io.github.ghasto69.happy_airships.PropellerModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value = ExampleMod.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(value = Dist.CLIENT)
public class ExampleModNeoForgeClient {
    @SubscribeEvent
    public static void layersEvent(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(PropellerModel.LAYER_LOCATION, PropellerModel::createBodyLayer);
    }
}
