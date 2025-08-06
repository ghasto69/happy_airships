package io.github.ghasto69.happy_airships.mixin;

import com.blackgear.vanillabackport.common.level.items.HarnessItem;
import com.llamalad7.mixinextras.sugar.Local;
import io.github.ghasto69.happy_airships.HAItemTags;
import io.github.ghasto69.happy_airships.PropellerItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HarnessItem.class)
public class HarnessItemMixin {
    @Inject(
            method = "interactLivingEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/blackgear/vanillabackport/common/level/entities/happyghast/HappyGhast;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"
            )
    )
    private void inject(ItemStack stack, Player player, LivingEntity target, InteractionHand usedHand, CallbackInfoReturnable<InteractionResult> cir) {
        if(stack.is(HAItemTags.HARNESSES_WITH_PROPELLERS)) {
            PropellerItem.awardAdvancement(player);
        }
    }

    @ModifyArg(
            method = "interactLivingEntity",
            at = @At(value = "INVOKE", target = "Lcom/blackgear/vanillabackport/common/level/entities/happyghast/HappyGhast;setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V"),
            index = 1
    )
    private ItemStack fixEnchants(ItemStack original, @Local(argsOnly = true) ItemStack stack) {
        return stack.copy();
    }
}
