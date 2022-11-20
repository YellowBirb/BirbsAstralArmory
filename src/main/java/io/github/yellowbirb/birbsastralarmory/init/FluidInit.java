package io.github.yellowbirb.birbsastralarmory.init;

import com.mojang.math.Vector3f;
import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.templates.BaseBirbFluidType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class FluidInit {

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BirbsAstralArmory.MODID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, BirbsAstralArmory.MODID);

    public static final BaseBirbFluidType yggdrasil_resin = new BaseBirbFluidType("yggdrasil_resin",
            FluidType.Properties.create(),
            () -> BaseBirbFluidType.createExtension(
                    new BaseBirbFluidType.ClientExtensions(
                            BirbsAstralArmory.MODID,
                            "yggdrasil_resin")
                            .tint(0xA1BA4A4A).fogColor(73f/255f, 29f/255f, 29f/255f)),
            new BaseBirbFluidType.AdditionalProperties().levelDecreasePerBlock(3).tickRate(20),
            BlockInit.yggdrasil_resin_block,
            ItemInit.yggdrasil_resin_bucket);

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }

}
