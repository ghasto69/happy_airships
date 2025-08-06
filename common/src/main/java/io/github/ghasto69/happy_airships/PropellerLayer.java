package io.github.ghasto69.happy_airships;

import com.blackgear.vanillabackport.client.level.entities.model.HappyGhastModel;
import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.List;

public class PropellerLayer extends RenderLayer<HappyGhast, HappyGhastModel<HappyGhast>> {

    private static final ResourceLocation RENDER_TYPE_ID =
            ExampleMod.id("textures/entity/happy_ghast/propeller.png");

    public PropellerLayer(RenderLayerParent<HappyGhast, HappyGhastModel<HappyGhast>> renderLayerParent) {
        super(renderLayerParent);
    }

    private record Vertex(float x, float y, float z, float u, float v) {}

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int light, HappyGhast entity, float f, float g, float partialTick, float j, float k, float l) {
        if (!entity.getItemBySlot(EquipmentSlot.CHEST).is(HAItemTags.HARNESSES_WITH_PROPELLERS)) return;

        poseStack.pushPose();

        boolean glint = entity.getItemBySlot(EquipmentSlot.CHEST).hasFoil();
        VertexConsumer glintVertexConsumer = multiBufferSource.getBuffer(RenderType.entityGlint());
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(RENDER_TYPE_ID));
        if (glint) {
            vertexConsumer = VertexMultiConsumer.create(glintVertexConsumer, vertexConsumer);
        }

        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(0f, 4.5f, 0f);

        poseStack.translate(-2f, -2.5f, 2f + (1f / 8f));
        poseStack.translate(2f, 2f, 0f);

        float time = entity.tickCount + partialTick;
        float rotation = (time * (entity.getPassengers().isEmpty() ? 0.1f : 1) * 20) % 360f;

        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        poseStack.translate(-2f, -2f, 0f);

        List<Vertex> vertices = List.of(
                new Vertex(0f, 0f, 0f, 0f, 1f),
                new Vertex(4f, 0f, 0f, 1f, 1f),
                new Vertex(4f, 4f, 0f, 1f, 0f),
                new Vertex(0f, 4f, 0f, 0f, 0f)
        );

        for (Vertex v : vertices) {
            vertexConsumer
                    .addVertex(poseStack.last(), v.x(), v.y(), v.z())
                    .setColor(255, 255, 255, 255)
                    .setUv(v.u(), v.v())
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setNormal(0f, 0f, 1f)
                    .setLight(light);
        }

        poseStack.popPose();

        ResourceLocation plankTexture = ResourceLocation.withDefaultNamespace("textures/block/dark_oak_planks.png");
        VertexConsumer planksConsumer = multiBufferSource.getBuffer(RenderType.entityCutoutNoCull(plankTexture));
        if (glint) {
            planksConsumer = VertexMultiConsumer.create(glintVertexConsumer, planksConsumer);
        }

        List<Vertex> woodenVertices = List.of(
                new Vertex(0f, 0f, 0f, 0f, 4f / 16f),
                new Vertex(1f / 8f, 0f, 0f, 1f / 16f, 4f / 16f),
                new Vertex(1f / 8f, 4f / 8f, 0f, 1f / 16f, 0f),
                new Vertex(0f, 4f / 8f, 0f, 0f, 0f)
        );

        poseStack.pushPose();

        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(0f, 4.5f, 0f);

        poseStack.translate(2f / 8f, -0.5f, 2f + (1f / 8f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90f));
        poseStack.mulPose(Axis.YP.rotationDegrees(90f));

        for (Vertex v : woodenVertices) {
            planksConsumer
                    .addVertex(poseStack.last(), v.x(), v.y(), v.z())
                    .setColor(255, 255, 255, 255)
                    .setUv(v.u(), v.v())
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setNormal(0f, 1f, 0f)
                    .setLight(light);
        }

        poseStack.popPose();

        poseStack.pushPose();

        poseStack.scale(0.25f, 0.25f, 0.25f);
        poseStack.translate(0f, 4.5f, 0f);

        poseStack.translate(0f, -0.5f - 2f / 8f, 2f + (1f / 8f));
        poseStack.mulPose(Axis.YP.rotationDegrees(90f));

        for (Vertex v : woodenVertices) {
            planksConsumer
                    .addVertex(poseStack.last(), v.x(), v.y(), v.z())
                    .setColor(255, 255, 255, 255)
                    .setUv(v.u(), v.v())
                    .setOverlay(OverlayTexture.NO_OVERLAY)
                    .setNormal(1f, 0f, 0f)
                    .setLight(light);
        }

        poseStack.popPose();
    }
}
