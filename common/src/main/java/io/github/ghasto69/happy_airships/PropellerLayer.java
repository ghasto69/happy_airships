package io.github.ghasto69.happy_airships;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexMultiConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HappyGhastModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public class PropellerLayer<M extends HappyGhastModel> extends RenderLayer<HappyGhastRenderState, M> {
    public PropellerLayer(RenderLayerParent<HappyGhastRenderState, M> renderLayerParent) {
        super(renderLayerParent);
    }

    private static final ResourceLocation RENDER_TYPE_ID = ExampleMod.id("textures/entity/happy_ghast/propeller.png");

    private record Vertex(float x, float y, float z, float u, float v) {}

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int light, HappyGhastRenderState renderState, float f, float g) {
        if (!renderState.bodyItem.is(HAItemTags.HARNESSES_WITH_PROPELLERS)) return;

        boolean hasGlint = renderState.bodyItem.hasFoil();

        // Fan Blades
        SubmitNodeCollector.CustomGeometryRenderer fanRenderer = (pose, vertexConsumer) -> {
            List<Vertex> vertices = List.of(
                    new Vertex(0f, 0f, 0f, 0f, 1f),
                    new Vertex(4f, 0f, 0f, 1f, 1f),
                    new Vertex(4f, 4f, 0f, 1f, 0f),
                    new Vertex(0f, 4f, 0f, 0f, 0f)
            );

            for (Vertex v : vertices) {
                vertexConsumer
                        .addVertex(pose, v.x(), v.y(), v.z())
                        .setColor(255, 255, 255, 255)
                        .setUv(v.u(), v.v())
                        .setOverlay(OverlayTexture.NO_OVERLAY)
                        .setNormal(0f, 0f, 1f)
                        .setLight(light);
            }
        };

        poseStack.pushPose();

        poseStack.translate(-2f, -2.5f, 2f + (1f / 8f));
        poseStack.translate(2f, 2f, 0f);

        float rotation = (renderState.ageInTicks +
                Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks()) *
                (renderState.isRidden ? 1f : 0.1f) * 20f % 360f;

        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        poseStack.translate(-2f, -2f, 0f);

        submitNodeCollector.submitCustomGeometry(poseStack, RenderType.entityCutoutNoCull(RENDER_TYPE_ID), fanRenderer);
        if(hasGlint) submitNodeCollector.submitCustomGeometry(poseStack, RenderType.entityGlint(), fanRenderer);

        poseStack.popPose();

        SubmitNodeCollector.CustomGeometryRenderer shaftRenderer = (pose, vertexConsumer) -> {
            List<Vertex> shaftVertices = List.of(
                    new Vertex(0f, 0f, 0f, 0f, 4f / 16f),
                    new Vertex(1f / 8f, 0f, 0f, 1f / 16f, 4f / 16f),
                    new Vertex(1f / 8f, 4f / 8f, 0f, 1f / 16f, 0f),
                    new Vertex(0f, 4f / 8f, 0f, 0f, 0f)
            );

            for (Vertex v : shaftVertices) {
                vertexConsumer
                        .addVertex(pose, v.x(), v.y(), v.z())
                        .setColor(255, 255, 255, 255)
                        .setUv(v.u(), v.v())
                        .setOverlay(OverlayTexture.NO_OVERLAY)
                        .setNormal(1f, 0f, 0f)
                        .setLight(light);
            }
        };

        ResourceLocation plankTexture = ResourceLocation.withDefaultNamespace("textures/block/dark_oak_planks.png");

        // Fan shaft
        poseStack.pushPose();

        poseStack.translate(2f / 8f, -0.5f, 2f + (1f / 8f));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90f));
        poseStack.mulPose(Axis.YP.rotationDegrees(90f));

        submitNodeCollector.submitCustomGeometry(poseStack, RenderType.entityCutoutNoCull(plankTexture), shaftRenderer);
        if(hasGlint) submitNodeCollector.submitCustomGeometry(poseStack, RenderType.entityGlint(), shaftRenderer);

        poseStack.popPose();

        // Fan shaft perpendicular
        poseStack.pushPose();
        poseStack.translate(0f, -0.5f - 2f / 8f, 2f + (1f / 8f));
        poseStack.mulPose(Axis.YP.rotationDegrees(90f));

        submitNodeCollector.submitCustomGeometry(poseStack, RenderType.entityCutoutNoCull(plankTexture), shaftRenderer);
        if(hasGlint) submitNodeCollector.submitCustomGeometry(poseStack, RenderType.entityGlint(), shaftRenderer);

        poseStack.popPose();
    }
}
