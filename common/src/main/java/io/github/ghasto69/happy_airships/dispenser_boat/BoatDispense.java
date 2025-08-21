package io.github.ghasto69.happy_airships.dispenser_boat;

import io.github.ghasto69.happy_airships.ExampleMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;

public class BoatDispense implements CustomPacketPayload {
    public static final Type<BoatDispense> TYPE = new Type<>(ExampleMod.id("boat_dispense"));
    public static final BoatDispense INSTANCE = new BoatDispense();
    public static final StreamCodec<RegistryFriendlyByteBuf, BoatDispense> CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public @NotNull Type<BoatDispense> type() {
        return TYPE;
    }

    public static void handleC2S(Player player) {
        final var vehicle = player.getVehicle();

        if(vehicle instanceof AbstractDispenserBoatEntity) {
            ((AbstractDispenserBoatEntity) vehicle).dispense();
        }

        if(vehicle instanceof HappyGhast) {
            final var boats = player.level().getEntitiesOfClass(
                    AbstractDispenserBoatEntity.class,
                    AABB.ofSize(vehicle.position(), 60, 60, 60),
                    entity -> entity.getLeashHolder() == vehicle
            );
            boats.forEach(AbstractDispenserBoatEntity::dispense);
        }
    }
}
