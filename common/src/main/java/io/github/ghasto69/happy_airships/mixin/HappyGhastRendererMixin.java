package io.github.ghasto69.happy_airships.mixin;

import io.github.ghasto69.happy_airships.PropellerLayer;
import net.minecraft.client.model.HappyGhastModel;
import net.minecraft.client.renderer.entity.AgeableMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HappyGhastRenderer;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;
import net.minecraft.world.entity.animal.HappyGhast;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhastRenderer.class)
@Debug(export = true)
public abstract class HappyGhastRendererMixin extends AgeableMobRenderer<HappyGhast, HappyGhastRenderState, HappyGhastModel> {
    public HappyGhastRendererMixin(EntityRendererProvider.Context context, HappyGhastModel entityModel, HappyGhastModel entityModel2, float f) {
        super(context, entityModel, entityModel2, f);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectRenderLayer(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new PropellerLayer<>(this));
    }
}