package io.github.ghasto69.happy_airships.mixin;

import io.github.ghasto69.happy_airships.HAItemTags;
import io.github.ghasto69.happy_airships.PropellerItem;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Equippable.class)
public class EquippableMixin {
    @Inject(
            method = "equipOnTarget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"
            )
    )
    private void inject(Player player, LivingEntity livingEntity, ItemStack itemStack, CallbackInfoReturnable<InteractionResult> cir) {
        if(itemStack.is(HAItemTags.HARNESSES_WITH_PROPELLERS)) {
            PropellerItem.awardAdvancement(player);
        }
    }
}
