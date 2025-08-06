package io.github.ghasto69.happy_airships;

import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import com.blackgear.vanillabackport.common.registries.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public class PropellerItem extends Item {
    public PropellerItem(Properties properties) {
        super(properties);
    }

    public static void awardAdvancement(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        var server = player.level().getServer();

        var advancement = server.getAdvancements().get(HAAdvancements.HAPPY_AIRSHIP);
        if (advancement != null) {
            serverPlayer.getAdvancements().award(advancement, "trigger");
        }
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        Level level = player.level();
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            if (entity instanceof HappyGhast ghast) {
                ItemStack handItem = player.getItemInHand(hand);

                Item harnessItem = ghast.getItemBySlot(EquipmentSlot.CHEST).getItem();
                Item harnessWithPropeller = null;

                if (harnessItem == ModItems.WHITE_HARNESS.get()) {
                    harnessWithPropeller = HAItems.WHITE_HARNESS;
                } else if (harnessItem == ModItems.ORANGE_HARNESS.get()) {
                    harnessWithPropeller = HAItems.ORANGE_HARNESS;
                } else if (harnessItem == ModItems.MAGENTA_HARNESS.get()) {
                    harnessWithPropeller = HAItems.MAGENTA_HARNESS;
                } else if (harnessItem == ModItems.LIGHT_BLUE_HARNESS.get()) {
                    harnessWithPropeller = HAItems.LIGHT_BLUE_HARNESS;
                } else if (harnessItem == ModItems.YELLOW_HARNESS.get()) {
                    harnessWithPropeller = HAItems.YELLOW_HARNESS;
                } else if (harnessItem == ModItems.LIME_HARNESS.get()) {
                    harnessWithPropeller = HAItems.LIME_HARNESS;
                } else if (harnessItem == ModItems.PINK_HARNESS.get()) {
                    harnessWithPropeller = HAItems.PINK_HARNESS;
                } else if (harnessItem == ModItems.GRAY_HARNESS.get()) {
                    harnessWithPropeller = HAItems.GRAY_HARNESS;
                } else if (harnessItem == ModItems.LIGHT_GRAY_HARNESS.get()) {
                    harnessWithPropeller = HAItems.LIGHT_GRAY_HARNESS;
                } else if (harnessItem == ModItems.CYAN_HARNESS.get()) {
                    harnessWithPropeller = HAItems.CYAN_HARNESS;
                } else if (harnessItem == ModItems.PURPLE_HARNESS.get()) {
                    harnessWithPropeller = HAItems.PURPLE_HARNESS;
                } else if (harnessItem == ModItems.BLUE_HARNESS.get()) {
                    harnessWithPropeller = HAItems.BLUE_HARNESS;
                } else if (harnessItem == ModItems.BROWN_HARNESS.get()) {
                    harnessWithPropeller = HAItems.BROWN_HARNESS;
                } else if (harnessItem == ModItems.GREEN_HARNESS.get()) {
                    harnessWithPropeller = HAItems.GREEN_HARNESS;
                } else if (harnessItem == ModItems.RED_HARNESS.get()) {
                    harnessWithPropeller = HAItems.RED_HARNESS;
                } else if (harnessItem == ModItems.BLACK_HARNESS.get()) {
                    harnessWithPropeller = HAItems.BLACK_HARNESS;
                }


                if (harnessWithPropeller != null) {
                    ItemStack upgraded = new ItemStack(harnessWithPropeller);

                    Holder<Enchantment> swiftness = player.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(HAEnchantments.SWIFTNESS);
                    int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(swiftness, handItem);
                    if (enchantmentLevel > 0) {
                        upgraded.enchant(swiftness, enchantmentLevel);
                    }

                    ghast.setItemSlot(EquipmentSlot.CHEST, upgraded);

                    handItem.shrink(1);
                    awardAdvancement(player);

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
