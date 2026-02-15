package io.github.ghasto69.happy_airships.propeller;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.ghasto69.happy_airships.ExampleMod;
import io.github.ghasto69.happy_airships.HAItemTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.animal.ghast.HappyGhastModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class PropellerLayer extends RenderLayer<HappyGhastRenderState, HappyGhastModel> {
    RenderType renderType;
    PropellerModel model;

    public PropellerLayer(RenderLayerParent<HappyGhastRenderState, HappyGhastModel> renderLayerParent, EntityModelSet modelSet) {
        super(renderLayerParent);
        model = new PropellerModel(modelSet.bakeLayer(PropellerModel.LAYER_LOCATION));
        renderType = RenderTypes.entityCutoutNoCull(ExampleMod.id("textures/entity/happy_ghast/propeller.png"));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int i, HappyGhastRenderState renderState, float f, float g) {
        if(!renderState.bodyItem.is(HAItemTags.HARNESSES_WITH_PROPELLERS)) return;

        poseStack.pushPose();

        poseStack.scale(2, 2, 2);
        poseStack.mulPose(Axis.YP.rotationDegrees(270));

        poseStack.translate(1 + 1/16f, -1 + 12/16f, 0);

        float rotation = (renderState.ageInTicks +
                Minecraft.getInstance().getDeltaTracker().getGameTimeDeltaTicks()) *
                (renderState.isRidden ? 1f : 0.1f) * 20f % 360f;

        poseStack.mulPose(Axis.XP.rotationDegrees(rotation));

        poseStack.translate(0, -12/16f, 0);

        submitNodeCollector.submitModel(this.model, renderState, poseStack, this.renderType, i, OverlayTexture.NO_OVERLAY, renderState.outlineColor, null);
//        if(renderState.bodyItem.hasFoil()) {
//            submitNodeCollector.submitModel(this.model, renderState, poseStack, RenderType.glint(), i, OverlayTexture.NO_OVERLAY, renderState.outlineColor, null);
//        }
        poseStack.popPose();
    }
}
