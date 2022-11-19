package io.github.yellowbirb.birbsastralarmory.init;

import com.mojang.math.Vector3f;
import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.templates.BaseFluidType;
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

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation SOAP_OVERLAY_RL = new ResourceLocation(BirbsAstralArmory.MODID, "misc/in_yggdrasil_resin");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, BirbsAstralArmory.MODID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, BirbsAstralArmory.MODID);

    public static final RegistryObject<FluidType> yggdrasil_resin_fluid_type = register("yggdrasil_resin",
            0xA1BA4A4A, 73f, 29f, 29f,
            FluidType.Properties.create().canSwim(false).canDrown(true).supportsBoating(false).canConvertToSource(false)
                    .canExtinguish(true).canHydrate(false).density(15).viscosity(5)
                    .sound(SoundAction.get("drink"), SoundEvents.HONEY_DRINK));

    public static final RegistryObject<FlowingFluid> yggdrasil_resin_source = FLUIDS.register("soap_water_fluid",
            () -> new ForgeFlowingFluid.Source(FluidInit.SOAP_WATER_FLUID_PROPERTIES));
    public static final RegistryObject<FlowingFluid> yggdrasil_resin_flowing = FLUIDS.register("flowing_soap_water",
            () -> new ForgeFlowingFluid.Flowing(FluidInit.SOAP_WATER_FLUID_PROPERTIES));


    public static final ForgeFlowingFluid.Properties SOAP_WATER_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            FluidInit.yggdrasil_resin_fluid_type, yggdrasil_resin_source, yggdrasil_resin_flowing)
            .slopeFindDistance(3).levelDecreasePerBlock(3).block(BlockInit.yggdrasil_resin_block)
            .bucket(ItemInit.yggdrasil_resin_bucket);


    private static RegistryObject<FluidType> register(String name, int tintColor, float r, float g, float b, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, SOAP_OVERLAY_RL,
                tintColor, new Vector3f(r / 255f, g / 255f, b / 255f), properties));
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
    }

}
