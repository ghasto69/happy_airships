package io.github.ghasto69.happy_airships.mixin;

import io.github.ghasto69.happy_airships.HAEnchantmentEffects;
import io.github.ghasto69.happy_airships.HAItemTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HappyGhast.class)
public abstract class HappyGhastMixin extends Animal {
    protected HappyGhastMixin(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @ModifyArg(
            method = "getRiddenInput",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;scale(D)Lnet/minecraft/world/phys/Vec3;")
    )
    private double modifyFlySpeed(double original) {
        if (getBodyArmorItem().is(HAItemTags.HARNESSES_WITH_PROPELLERS)) {
            var multiplier = 2f;
            for (final var entry : this.getBodyArmorItem().getEnchantments().entrySet()) {
                final var effect = entry.getKey().value().effects().get(HAEnchantmentEffects.SWIFTNESS);
                if(effect == null) continue;
                multiplier = effect.process(entry.getIntValue(), this.getRandom(), multiplier);
            }
            return original * multiplier;
        }

        return original;
    }
}
