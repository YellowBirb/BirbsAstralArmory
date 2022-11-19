package io.github.yellowbirb.birbsastralarmory.init;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.customblocks.yggdrasil.*;
import io.github.yellowbirb.birbsastralarmory.util.YggdrasilTreeGrower;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BirbsAstralArmory.MODID);

    public static final WoodType yggdrasil_wood_type =
            WoodType.create(new ResourceLocation(BirbsAstralArmory.MODID, "yggdrasil").toString());

    public static final RegistryObject<Block> stripped_yggdrasil_log = BLOCKS.register("stripped_yggdrasil_log",
            () -> new StrippedYggdrasilLog(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> stripped_yggdrasil_wood = BLOCKS.register("stripped_yggdrasil_wood",
            () -> new StrippedYggdrasilWood(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> yggdrasil_button = BLOCKS.register("yggdrasil_button",
            () -> new YggdrasilButton(BlockBehaviour.Properties.of(Material.DECORATION)
                    .strength(15f, 1200f)
                    .noCollission()
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> yggdrasil_door = BLOCKS.register("yggdrasil_door",
            () -> new YggdrasilDoor(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30.0F, 1200f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));
    public static final RegistryObject<Block> yggdrasil_fence = BLOCKS.register("yggdrasil_fence",
            () -> new YggdrasilFence(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200f)
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> yggdrasil_fence_gate = BLOCKS.register("yggdrasil_fence_gate",
            () -> new YggdrasilFenceGate(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200f)
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> yggdrasil_leaves = BLOCKS.register("yggdrasil_leaves",
            () -> new YggdrasilLeaves(BlockBehaviour.Properties.of(Material.LEAVES)
                    .strength(0.2f, 1200f)
                    .noOcclusion()
                    .sound(SoundType.GRASS)));
    public static final RegistryObject<Block> yggdrasil_log = BLOCKS.register("yggdrasil_log",
        () -> new YggdrasilLog(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30.0f, 1200.0f)
                .requiresCorrectToolForDrops()
                .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> yggdrasil_branch = BLOCKS.register("yggdrasil_branch",
            () -> new YggdrasilBranch(new YggdrasilTreeGrower(), BlockBehaviour.Properties.of(Material.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.GRASS)));
    public static final RegistryObject<Block> potted_yggdrasil_branch = BLOCKS.register("potted_yggdrasil_branch",
            () -> new FlowerPotBlock(
                    () -> (FlowerPotBlock) Blocks.FLOWER_POT,
                    BlockInit.yggdrasil_branch,
                    BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));
    public static final RegistryObject<Block> yggdrasil_planks = BLOCKS.register("yggdrasil_planks",
            () -> new YggdrasilPlanks(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200.0f)
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> yggdrasil_pressure_plate = BLOCKS.register("yggdrasil_pressure_plate",
            ()-> new YggdrasilPressurePlate(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.of(Material.WOOD)
                            .noCollission()
                            .strength(15F, 1200f)
                            .sound(SoundType.WOOD)));
    public static final RegistryObject<LiquidBlock> yggdrasil_resin_block = BLOCKS.register("yggdrasil_resin_block",
            () -> new LiquidBlock(FluidInit.yggdrasil_resin_source, BlockBehaviour.Properties.copy(Blocks.WATER)));
    public static final RegistryObject<Block> yggdrasil_sign = BLOCKS.register("yggdrasil_sign",
            () -> new YggdrasilSign(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f,1200f)
                    .noCollission()
                    .sound(SoundType.WOOD), yggdrasil_wood_type));
    public static final RegistryObject<Block> yggdrasil_wall_sign = BLOCKS.register("yggdrasil_wall_sign",
            () -> new YggdrasilWallSign(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f,1200f)
                    .noCollission()
                    .sound(SoundType.WOOD), yggdrasil_wood_type));
    public static final RegistryObject<Block> yggdrasil_slab = BLOCKS.register("yggdrasil_slab",
            () -> new YggdrasilSlab(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200f)
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> yggdrasil_stairs = BLOCKS.register("yggdrasil_stairs",
            () -> new YggdrasilStairs(() -> BlockInit.yggdrasil_planks.get().defaultBlockState(),BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200f)
                    .sound(SoundType.WOOD)));
    public static final RegistryObject<Block> yggdrasil_trapdoor = BLOCKS.register("yggdrasil_trapdoor",
            () -> new YggdrasilTrapdoor(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200f)
                    .sound(SoundType.WOOD)
                    .noOcclusion()));
    public static final RegistryObject<Block> yggdrasil_wood = BLOCKS.register("yggdrasil_wood",
            () -> new YggdrasilWood(BlockBehaviour.Properties.of(Material.WOOD)
                    .strength(30f, 1200f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.WOOD)));






}
