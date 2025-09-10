package io.github.ghasto69.happy_airships.dispenser_boat;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.util.HashMap;
import java.util.Map;

public class DispenserBoatBehaviours {
    protected static final Map<Item, DispenserBoatFunction> REGISTRY = new HashMap<>();

    public static void register(Item item, DispenserBoatFunction function) {
        REGISTRY.put(item, function);
    }

    static {
        register(Items.TNT, (boat, level, itemStack) -> {
            if(!level.getGameRules().getBoolean(GameRules.RULE_TNT_EXPLODES)) return;

            Vec3 facing = Vec3.directionFromRotation(boat.getRotationVector());
            Vec3 position = boat.position().subtract(facing.scale(2));

            PrimedTnt tnt = new PrimedTnt(level, position.x, position.y, position.z, null);
            level.playSound(null, position.x, position.y, position.z, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            level.gameEvent(null, GameEvent.ENTITY_PLACE, position);
            level.addFreshEntity(tnt);

            itemStack.shrink(1);
        });
    }


    @FunctionalInterface
    public interface DispenserBoatFunction {
        void dispense(AbstractDispenserBoatEntity boat, ServerLevel level, ItemStack itemStack);
    }
}
