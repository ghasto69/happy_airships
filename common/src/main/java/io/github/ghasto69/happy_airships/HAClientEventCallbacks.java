package io.github.ghasto69.happy_airships;

import io.github.ghasto69.happy_airships.dispenser_boat.AbstractDispenserBoatEntity;
import io.github.ghasto69.happy_airships.dispenser_boat.BoatDispense;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.world.entity.animal.HappyGhast;

public class HAClientEventCallbacks {
    public static void clientTick() {
        final var connection = Minecraft.getInstance().getConnection();
        if (connection == null) return;

        final var player = Minecraft.getInstance().player;
        if(player == null) return;

        if(player.getVehicle() instanceof AbstractDispenserBoatEntity || player.getVehicle() instanceof HappyGhast) {
            while (HAKeyMappings.BOAT_DISPENSE.consumeClick()) {
                connection.send(new ServerboundCustomPayloadPacket(BoatDispense.INSTANCE));
            }
        }
    }
}
