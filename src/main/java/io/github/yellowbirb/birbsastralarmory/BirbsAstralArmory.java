package io.github.yellowbirb.birbsastralarmory;

import io.github.yellowbirb.birbsastralarmory.init.*;
import io.github.yellowbirb.birbsastralarmory.networking.BirbPackets;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

@Mod(BirbsAstralArmory.MODID)
public class BirbsAstralArmory {
    public static final String MODID = "birbsastralarmory";


    //TODO: comes here
    //
    //TODO: check loot tables and yggdrasiltreegrower
    //TODO: my structure no spawn :(

    public BirbsAstralArmory() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.BLOCKS.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);

        ItemInit.ITEMS.register(bus);

        EntityInit.ENTITIES.register(bus);

        SoundsInit.SOUND_EVENTS.register(bus);

        StructureInit.STRUCTURE_TYPES.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        WoodType.register(BlockInit.yggdrasil_wood_type);
        Sheets.addWoodType(BlockInit.yggdrasil_wood_type);
        BlockEntityRenderers.register(BlockEntityInit.yggdrasil_sign.get(), SignRenderer::new);

    }

    private void setup(FMLCommonSetupEvent event) {

        ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockInit.yggdrasil_branch.getId(), BlockInit.potted_yggdrasil_branch);

        BirbPackets.register();

    }

    public static final CreativeModeTab BAATAB = new CreativeModeTab("Birb's Astral Blocks") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return ItemInit.placeholder2.get().getDefaultInstance();
        }
        @Override
        public void fillItemList(NonNullList<ItemStack> p_40778_) {

            p_40778_.add(new ItemStack(ItemInit.yggdrasil_planks.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_branch.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_log.get()));
            p_40778_.add(new ItemStack(ItemInit.stripped_yggdrasil_log.get()));
            p_40778_.add(new ItemStack(ItemInit.stripped_yggdrasil_wood.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_wood.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_leaves.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_slab.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_fence.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_stairs.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_button.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_pressure_plate.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_door.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_trapdoor.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_fence_gate.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_boat.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_chest_boat.get()));
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_sign.get()));

        }
    };
}
