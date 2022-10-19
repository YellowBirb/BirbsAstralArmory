package io.github.yellowbirb.birbsastralarmory.util.render;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.customentities.BirbsAstralBoat;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Map;
import java.util.stream.Stream;

public class BirbsAstralBoatRenderer extends EntityRenderer<BirbsAstralBoat>{

    private final Map<BirbsAstralBoat.BoatType, Pair<ResourceLocation, BoatModel>> boatResources;

    public BirbsAstralBoatRenderer(EntityRendererProvider.Context context, boolean hasChest) {
        super(context);
        this.shadowRadius = 0.8F;
        this.boatResources =
                Stream.of(BirbsAstralBoat.BoatType.values()).collect(ImmutableMap.toImmutableMap((boatType) -> {
                    return boatType;
                }, (boatType) -> {
                    return Pair.of(new ResourceLocation(BirbsAstralArmory.MODID, getTextureLocation(boatType, hasChest)), this.createBoatModel(context, boatType, hasChest));
                }));
    }

    private BoatModel createBoatModel(EntityRendererProvider.Context context, BirbsAstralBoat.BoatType bygType, boolean hasChest) {
        ModelLayerLocation modellayerlocation = hasChest ? createChestBoatModelName(bygType) : createBoatModelName(bygType);
        return new BoatModel(context.bakeLayer(modellayerlocation), hasChest);
    }

    public static ModelLayerLocation createChestBoatModelName(BirbsAstralBoat.BoatType type) {
        return new ModelLayerLocation(new ResourceLocation(BirbsAstralArmory.MODID, "chest_boat/" + type.getName()), "main");
    }

    public static ModelLayerLocation createBoatModelName(BirbsAstralBoat.BoatType type) {
        return new ModelLayerLocation(new ResourceLocation(BirbsAstralArmory.MODID, "boat/" + type.getName()), "main");
    }

    private static String getTextureLocation(BirbsAstralBoat.BoatType bygType, boolean hasChest) {
        return hasChest ? "textures/entity/chest_boat/" + bygType.getName() + ".png" : "textures/entity/boat/" + bygType.getName() + ".png";
    }

    @Override
    public void render(BirbsAstralBoat boat, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource multiBufferSource, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 0.375D, 0.0D);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F - entityYaw));
        float h = (float) boat.getHurtTime() - partialTicks;
        float j = boat.getDamage() - partialTicks;
        if (j < 0.0F) {
            j = 0.0F;
        }

        if (h > 0.0F) {
            matrixStackIn.mulPose(Vector3f.XP.rotationDegrees(Mth.sin(h) * h * j / 10.0F * (float) boat.getHurtDir()));
        }

        float k = boat.getBubbleAngle(partialTicks);
        if (!Mth.equal(k, 0.0F)) {
            matrixStackIn.mulPose(new Quaternion(new Vector3f(1.0F, 0.0F, 1.0F), boat.getBubbleAngle(partialTicks), true));
        }

        Pair<ResourceLocation, BoatModel> pair = this.boatResources.get(boat.getBirbsAstralBoatType());
        ResourceLocation resourceLocation = (ResourceLocation) pair.getFirst();
        BoatModel boatModel = (BoatModel) pair.getSecond();
        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        boatModel.setupAnim(boat, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(boatModel.renderType(resourceLocation));
        boatModel.renderToBuffer(matrixStackIn, vertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        if (!boat.isUnderWater()) {
            VertexConsumer vertexConsumer2 = multiBufferSource.getBuffer(RenderType.waterMask());
            boatModel.waterPatch().render(matrixStackIn, vertexConsumer2, packedLightIn, OverlayTexture.NO_OVERLAY);
        }

        matrixStackIn.popPose();
        super.render(boat, entityYaw, partialTicks, matrixStackIn, multiBufferSource, packedLightIn);
    }


    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(BirbsAstralBoat boat) {
        return this.boatResources.get(boat.getBoatType()).getFirst();
    }

}
