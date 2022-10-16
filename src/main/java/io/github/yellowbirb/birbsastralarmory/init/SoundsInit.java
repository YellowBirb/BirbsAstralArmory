package io.github.yellowbirb.birbsastralarmory.init;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundsInit {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS , BirbsAstralArmory.MODID);

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name,
                () -> new SoundEvent(new ResourceLocation(BirbsAstralArmory.MODID, name)));
    }

    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}
