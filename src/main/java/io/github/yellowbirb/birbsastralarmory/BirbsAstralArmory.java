package io.github.yellowbirb.birbsastralarmory;

import io.github.yellowbirb.birbsastralarmory.init.*;
import io.github.yellowbirb.birbsastralarmory.networking.BirbPackets;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;

@Mod(BirbsAstralArmory.MODID)
public class BirbsAstralArmory {
    public static final String MODID = "birbsastralarmory";


    //TODO: comes here
    //
    //TODO: recipes, see: external libraries/net.minecraft:client:extra:1.19.2/client-extra.jar/data/minecraft/recipes
    //TODO: check loot tables


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

    public static final CreativeModeTab BAATAB = new CreativeModeTab(MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return ItemInit.placeholder2.get().getDefaultInstance();
        }
    };
}
