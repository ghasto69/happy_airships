package io.github.ghasto69.happy_airships.propeller;// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import io.github.ghasto69.happy_airships.ExampleMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.HappyGhastRenderState;

public class PropellerModel extends EntityModel<HappyGhastRenderState> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ExampleMod.id("propeller"), "main");
	private final ModelPart bb_main;

	public PropellerModel(ModelPart root) {
        super(root);
        this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, -24).addBox(0.0F, -24.0F, -12.0F, 0.0F, 24.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(7, 10).addBox(-1.0F, -12.0F, -2.0F, 1.0F, 0.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(11, 10).addBox(-1.0F, -14.0F, 0.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
}