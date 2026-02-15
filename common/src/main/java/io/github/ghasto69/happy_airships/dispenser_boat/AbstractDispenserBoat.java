package io.github.ghasto69.happy_airships.dispenser_boat;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public abstract class AbstractDispenserBoat extends AbstractBoat {
    public AbstractDispenserBoat(EntityType<? extends AbstractBoat> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }
}
