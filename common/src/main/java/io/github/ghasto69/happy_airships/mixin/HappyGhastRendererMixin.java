package io.github.ghasto69.happy_airships.mixin;

import com.blackgear.vanillabackport.client.level.entities.model.HappyGhastHarnessModel;
import com.blackgear.vanillabackport.client.level.entities.model.HappyGhastModel;
import com.blackgear.vanillabackport.client.level.entities.renderer.AgeableMobRenderer;
import com.blackgear.vanillabackport.client.level.entities.renderer.HappyGhastRenderer;
import com.blackgear.vanillabackport.client.registries.ModModelLayers;
import com.blackgear.vanillabackport.common.level.entities.happyghast.HappyGhast;
import io.github.ghasto69.happy_airships.GhastHarnessLayer;
import io.github.ghasto69.happy_airships.PropellerLayer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HappyGhastRenderer.class)
public abstract class HappyGhastRendererMixin extends AgeableMobRenderer<HappyGhast, HappyGhastModel<HappyGhast>> {
    public HappyGhastRendererMixin(EntityRendererProvider.Context context, HappyGhastModel<HappyGhast> adultModel, HappyGhastModel<HappyGhast> babyModel, float shadowRadius) {
        super(context, adultModel, babyModel, shadowRadius);
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void injectRenderLayer(EntityRendererProvider.Context context, CallbackInfo ci) {
        this.addLayer(new GhastHarnessLayer<>(this, new HappyGhastHarnessModel<>(context.bakeLayer(ModModelLayers.HAPPY_GHAST_HARNESS))));
        this.addLayer(new PropellerLayer(this));
    }
}