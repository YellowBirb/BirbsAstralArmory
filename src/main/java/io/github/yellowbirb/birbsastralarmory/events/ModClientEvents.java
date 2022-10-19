package io.github.yellowbirb.birbsastralarmory.events;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.EntityInit;
import io.github.yellowbirb.birbsastralarmory.init.customentities.BYGBoat;
import io.github.yellowbirb.birbsastralarmory.util.render.*;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BirbsAstralArmory.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(EntityInit.yggdrasil_boat.get(), YggdrasilBoatRenderer::new);
        event.registerEntityRenderer(EntityInit.yggdrasil_chest_boat.get(), YggdrasilChestBoatRenderer::new);
        event.registerEntityRenderer(EntityInit.BYGBOAT.get(), (EntityRendererProvider.Context context) -> new BYGBoatRenderer(context, false));

    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        event.registerLayerDefinition(YggdrasilBoatModel.layerLocation, YggdrasilBoatModel::createBodyModel);
        event.registerLayerDefinition(YggdrasilChestBoatModel.layerLocation, YggdrasilChestBoatModel::createBodyModel);
        for (BYGBoat.BYGType value : BYGBoat.BYGType.values()) {
            event.registerLayerDefinition(BYGBoatRenderer.createBoatModelName(value), () -> BoatModel.createBodyModel(false));
        }
    }

}
