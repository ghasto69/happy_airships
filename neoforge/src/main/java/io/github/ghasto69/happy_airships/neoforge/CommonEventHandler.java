package io.github.ghasto69.happy_airships.neoforge;

import io.github.ghasto69.happy_airships.dispenser_boat.BoatDispense;
import io.github.ghasto69.happy_airships.ExampleMod;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.HandlerThread;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = ExampleMod.MOD_ID)
public class CommonEventHandler {
    private static boolean registered = false;

    @SubscribeEvent
    private static void registerEvent(RegisterEvent event) {
        if(registered) return;
        ExampleMod.registriesReady();
        registered = true;
    }

    @SubscribeEvent // on the mod event bus
    public static void register(RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1").executesOn(HandlerThread.NETWORK);
        // All subsequent payloads will register on the network thread

        registrar.playToServer(
                BoatDispense.TYPE,
                BoatDispense.CODEC,
                (payload, context) -> context.player().getServer().execute(() -> BoatDispense.handleC2S(context.player()))
        );
    }
}
