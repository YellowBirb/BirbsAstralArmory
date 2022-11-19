package io.github.yellowbirb.birbsastralarmory.events;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.customblocks.yggdrasil.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= BirbsAstralArmory.MODID, bus=Mod.EventBusSubscriber.Bus.FORGE)
public class ModCommonEvents {

    @SubscribeEvent
    public static void stopYggdrasilLogBreak(PlayerEvent.BreakSpeed event) {

        if (event.getState().getBlock() instanceof YggdrasilLog || event.getState().getBlock() instanceof StrippedYggdrasilLog || event.getState().getBlock() instanceof YggdrasilWood || event.getState().getBlock() instanceof StrippedYggdrasilWood) {
            if(!(event.getEntity().getMainHandItem().getItem() == Items.NETHERITE_AXE)) {
                event.setCanceled(true);
            }
        }
    }
}
