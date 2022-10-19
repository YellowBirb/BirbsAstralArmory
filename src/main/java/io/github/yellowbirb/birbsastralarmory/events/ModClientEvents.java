package io.github.yellowbirb.birbsastralarmory.events;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.EntityInit;
import io.github.yellowbirb.birbsastralarmory.init.customentities.BirbsAstralBoat;
import io.github.yellowbirb.birbsastralarmory.util.render.*;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BirbsAstralArmory.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(EntityInit.birbs_astral_boat.get(), (EntityRendererProvider.Context context) -> new BirbsAstralBoatRenderer(context, false));
        event.registerEntityRenderer(EntityInit.birbs_astral_chest_boat.get(), (EntityRendererProvider.Context context) -> new BirbsAstralBoatRenderer(context, true));

    }


    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {

        for (BirbsAstralBoat.BoatType value : BirbsAstralBoat.BoatType.values()) {
            event.registerLayerDefinition(BirbsAstralBoatRenderer.createBoatModelName(value), () -> BoatModel.createBodyModel(false));
            event.registerLayerDefinition(BirbsAstralBoatRenderer.createChestBoatModelName(value), () -> BoatModel.createBodyModel(true));
        }
    }

}
