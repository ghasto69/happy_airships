package io.github.ghasto69.happy_airships.dispenser_boat;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class DispenserBoatEntity extends AbstractDispenserBoatEntity {
    public DispenserBoatEntity(EntityType<AbstractDispenserBoatEntity> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }

    @Override
    protected double rideHeight(EntityDimensions entityDimensions) {
        return entityDimensions.height() / 3.0F;
    }
}
