package io.github.ghasto69.happy_airships;

import com.blackgear.vanillabackport.client.level.entities.model.HappyGhastHarnessModel;
import com.blackgear.vanillabackport.client.level.entities.model.HappyGhastModel;
import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import com.blackgear.vanillabackport.core.VanillaBackport;
import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.Optional;

public class GhastHarnessLayer<T extends HappyGhast> extends RenderLayer<T, HappyGhastModel<T>> {
    private static final Map<ItemStack, ResourceLocation> TEXTURE_BY_ITEM = (new ImmutableMap.Builder<ItemStack, ResourceLocation>())
            .put(new ItemStack(HAItems.WHITE_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/white_harness.png"))
            .put(new ItemStack(HAItems.ORANGE_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/orange_harness.png"))
            .put(new ItemStack(HAItems.MAGENTA_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/magenta_harness.png"))
            .put(new ItemStack(HAItems.LIGHT_BLUE_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/light_blue_harness.png"))
            .put(new ItemStack(HAItems.YELLOW_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/yellow_harness.png"))
            .put(new ItemStack(HAItems.LIME_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/lime_harness.png"))
            .put(new ItemStack(HAItems.PINK_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/pink_harness.png"))
            .put(new ItemStack(HAItems.GRAY_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/gray_harness.png"))
            .put(new ItemStack(HAItems.LIGHT_GRAY_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/light_gray_harness.png"))
            .put(new ItemStack(HAItems.CYAN_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/cyan_harness.png"))
            .put(new ItemStack(HAItems.PURPLE_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/purple_harness.png"))
            .put(new ItemStack(HAItems.BLUE_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/blue_harness.png"))
            .put(new ItemStack(HAItems.BROWN_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/brown_harness.png"))
            .put(new ItemStack(HAItems.GREEN_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/green_harness.png"))
            .put(new ItemStack(HAItems.RED_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/red_harness.png"))
            .put(new ItemStack(HAItems.BLACK_HARNESS), VanillaBackport.resource("textures/entity/ghast/harness/black_harness.png"))
            .build();
    private final HappyGhastHarnessModel<T> model;

    public GhastHarnessLayer(RenderLayerParent<T, HappyGhastModel<T>> renderer, HappyGhastHarnessModel<T> model) {
        super(renderer);
        this.model = model;
    }

    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        Optional<ResourceLocation> texture = TEXTURE_BY_ITEM.entrySet().stream().filter((entry) -> entity.getItemBySlot(EquipmentSlot.CHEST).is(((ItemStack)entry.getKey()).getItem())).map(Map.Entry::getValue).findFirst();
        if (texture.isPresent() && entity.isSaddled()) {
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
            this.model.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            boolean glint = entity.getItemBySlot(EquipmentSlot.CHEST).hasFoil();
            VertexConsumer vertices = buffer.getBuffer(RenderType.entityCutoutNoCull((texture.get())));
            VertexConsumer glintVertexConsumer = buffer.getBuffer(RenderType.entityGlint());
            if(glint) {
                vertices = VertexMultiConsumer.create(glintVertexConsumer, vertices);
            }

            this.model.renderToBuffer(poseStack, vertices, packedLight, OverlayTexture.NO_OVERLAY);
        }

    }
}