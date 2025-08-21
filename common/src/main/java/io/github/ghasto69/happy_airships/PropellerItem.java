package io.github.ghasto69.happy_airships;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.HappyGhast;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.function.Consumer;

public class PropellerItem extends Item {
    public static final String TOOLTIP_KEY = "tooltip.happy-air-travel.propeller";

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

    /*
    @Override
    public void appendHoverText(
        ItemStack itemStack,
        TooltipContext context,
        TooltipDisplay display,
        Consumer<Component> consumer,
        TooltipFlag tooltipFlag
    ) {
        consumer.accept(Component.translatable(TOOLTIP_KEY).withStyle(ChatFormatting.GRAY));
    }
    */

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        Level level = player.level();
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if (entity instanceof HappyGhast ghast) {
                ItemStack handItem = player.getItemInHand(hand);

                Item harnessItem = ghast.getBodyArmorItem().getItem();
                Item harnessWithPropeller = null;

                if (harnessItem == Items.WHITE_HARNESS) {
                    harnessWithPropeller = HAItems.WHITE_HARNESS;
                } else if (harnessItem == Items.ORANGE_HARNESS) {
                    harnessWithPropeller = HAItems.ORANGE_HARNESS;
                } else if (harnessItem == Items.MAGENTA_HARNESS) {
                    harnessWithPropeller = HAItems.MAGENTA_HARNESS;
                } else if (harnessItem == Items.LIGHT_BLUE_HARNESS) {
                    harnessWithPropeller = HAItems.LIGHT_BLUE_HARNESS;
                } else if (harnessItem == Items.YELLOW_HARNESS) {
                    harnessWithPropeller = HAItems.YELLOW_HARNESS;
                } else if (harnessItem == Items.LIME_HARNESS) {
                    harnessWithPropeller = HAItems.LIME_HARNESS;
                } else if (harnessItem == Items.PINK_HARNESS) {
                    harnessWithPropeller = HAItems.PINK_HARNESS;
                } else if (harnessItem == Items.GRAY_HARNESS) {
                    harnessWithPropeller = HAItems.GRAY_HARNESS;
                } else if (harnessItem == Items.LIGHT_GRAY_HARNESS) {
                    harnessWithPropeller = HAItems.LIGHT_GRAY_HARNESS;
                } else if (harnessItem == Items.CYAN_HARNESS) {
                    harnessWithPropeller = HAItems.CYAN_HARNESS;
                } else if (harnessItem == Items.PURPLE_HARNESS) {
                    harnessWithPropeller = HAItems.PURPLE_HARNESS;
                } else if (harnessItem == Items.BLUE_HARNESS) {
                    harnessWithPropeller = HAItems.BLUE_HARNESS;
                } else if (harnessItem == Items.BROWN_HARNESS) {
                    harnessWithPropeller = HAItems.BROWN_HARNESS;
                } else if (harnessItem == Items.GREEN_HARNESS) {
                    harnessWithPropeller = HAItems.GREEN_HARNESS;
                } else if (harnessItem == Items.RED_HARNESS) {
                    harnessWithPropeller = HAItems.RED_HARNESS;
                } else if (harnessItem == Items.BLACK_HARNESS) {
                    harnessWithPropeller = HAItems.BLACK_HARNESS;
                }


                if (harnessWithPropeller != null) {
                    ItemStack upgraded = new ItemStack(harnessWithPropeller);

                    Holder<Enchantment> swiftness = player.registryAccess().getOrThrow(HAEnchantments.SWIFTNESS);
                    int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(swiftness, handItem);
                    if (enchantmentLevel > 0) {
                        upgraded.enchant(swiftness, enchantmentLevel);
                    }

                    ghast.setBodyArmorItem(upgraded);

                    handItem.shrink(1);
                    awardAdvancement(player);

                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
