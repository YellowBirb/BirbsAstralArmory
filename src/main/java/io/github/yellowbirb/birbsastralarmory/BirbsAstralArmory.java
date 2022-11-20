package io.github.yellowbirb.birbsastralarmory;

import io.github.yellowbirb.birbsastralarmory.init.*;
import io.github.yellowbirb.birbsastralarmory.networking.BirbPackets;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
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
    //TODO: my structure shouldnt replace this much dirt; Solution: two parts: trunk and crown; also theres 2 grass {:(}
    //TODO: Step 1: Fluid (move registryobjects from fluidregitrycontainer to iteminit for sorting)you must create a sense of scarcity shells will sell much better if the people think they're rare you see bare with me put them on island stockpile 'em high 'til they're rarer than a diamond
    //TODO: Step 2: Armor make the poeple think that they want really want 'em really fucking want 'em hit 'em like bronson
    //TODO: Step 3: Armor Interact with Fluid (different armors in code, same in game) It's Monopoly invest inside some property start a corporation make a logo do it properly
    //TODO: Step 4: Weapons, Tools EXPAND EXPAND EXPAND EXPAND clear forest make land fresh blood on hands
    //TODO: Step 5: Astral Entity Entity (dialog) why just shells why limit yourself she sells seashells sell oil as well
    //TODO: Step 6: new stuff (dungeons (any), aether island (end), nether globe (nether)) sell guns sell oil sell diamond sell rock sell water to a fish sell the time to a clock
    //TODO: Step 7: rigorous testing

    public BirbsAstralArmory() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.BLOCKS.register(bus);
        BlockEntityInit.BLOCK_ENTITIES.register(bus);

        ItemInit.ITEMS.register(bus);

        EntityInit.ENTITIES.register(bus);

        FluidInit.register(bus);

        SoundsInit.SOUND_EVENTS.register(bus);

        StructureInit.STRUCTURE_TYPES.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {

        WoodType.register(BlockInit.yggdrasil_wood_type);
        Sheets.addWoodType(BlockInit.yggdrasil_wood_type);
        BlockEntityRenderers.register(BlockEntityInit.yggdrasil_sign.get(), SignRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(FluidInit.yggdrasil_resin.source.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(FluidInit.yggdrasil_resin.flowing.get(), RenderType.translucent());

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
            p_40778_.add(new ItemStack(ItemInit.yggdrasil_resin_bucket.get()));

        }
    };
}
