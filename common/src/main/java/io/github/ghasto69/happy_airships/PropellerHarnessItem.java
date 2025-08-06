package io.github.ghasto69.happy_airships;

import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;

public class PropellerHarnessItem extends Item {
    public PropellerHarnessItem(Item.Properties properties) {
        super(properties);
    }

    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand usedHand) {
        if (target instanceof HappyGhast ghast) {
            if (target.isAlive() && !ghast.isSaddled() && ghast.isSaddleable()) {
                if (!player.level().isClientSide) {
                    ghast.equipSaddle(ItemStack.EMPTY, SoundSource.NEUTRAL);
                    ghast.setItemSlot(EquipmentSlot.CHEST, stack.copy());
                    PropellerItem.awardAdvancement(player);
                    target.level().gameEvent(target, GameEvent.EQUIP, target.position());
                    stack.shrink(1);
                }

                return InteractionResult.sidedSuccess(player.level().isClientSide);
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public int getEnchantmentValue() {
        return 10;
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return true;
    }
}