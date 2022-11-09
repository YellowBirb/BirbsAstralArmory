package io.github.yellowbirb.birbsastralarmory.util;

import com.google.common.collect.ImmutableList;
import io.github.yellowbirb.birbsastralarmory.init.BlockInit;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MangrovePropaguleBlock;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.rootplacers.AboveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacement;
import net.minecraft.world.level.levelgen.feature.rootplacers.MangroveRootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RandomizedIntStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.*;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;

public class YggdrasilTreeGrower extends AbstractMegaTreeGrower {

    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random, boolean hasFlowers) {
      return getConfiguredFeature(random, hasFlowers);
   }

   private static TreeConfiguration.TreeConfigurationBuilder createYggdrasilBranch() {
      return createStraightBlobTree(BlockInit.yggdrasil_log.get(), BlockInit.yggdrasil_leaves.get(), 5, 2, 0, 2).ignoreVines();
   }

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block p_195147_, Block p_195148_, int p_195149_, int p_195150_, int p_195151_, int p_195152_) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(p_195147_),
                new StraightTrunkPlacer(p_195149_, p_195150_, p_195151_), BlockStateProvider.simple(p_195148_),
                new BlobFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1));
    }

    private static final BeehiveDecorator BEEHIVE_0002 = new BeehiveDecorator(0.002F);
    private static final BeehiveDecorator BEEHIVE_001 = new BeehiveDecorator(0.01F);
    private static final BeehiveDecorator BEEHIVE_002 = new BeehiveDecorator(0.02F);
    private static final BeehiveDecorator BEEHIVE_005 = new BeehiveDecorator(0.05F);
    private static final BeehiveDecorator BEEHIVE = new BeehiveDecorator(1.0F);
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_OAK = FeatureUtils.register("yggdrasil_oak", Feature.TREE, createOak().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_DARK_OAK = FeatureUtils.register("yggdrasil_dark_oak", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_BIRCH = FeatureUtils.register("yggdrasil_birch", Feature.TREE, createBirch().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_ACACIA = FeatureUtils.register("yggdrasil_acacia", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new ForkingTrunkPlacer(5, 2, 2), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new AcaciaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_SPRUCE = FeatureUtils.register("yggdrasil_spruce", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_PINE = FeatureUtils.register("yggdrasil_pine", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new StraightTrunkPlacer(6, 4, 0), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new PineFoliagePlacer(ConstantInt.of(1), ConstantInt.of(1), UniformInt.of(3, 4)), new TwoLayersFeatureSize(2, 0, 2))).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_JUNGLE_TREE = FeatureUtils.register("yggdrasil_jungle_tree", Feature.TREE, createJungleTree().decorators(ImmutableList.of(new CocoaDecorator(0.2F))).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_FANCY_OAK = FeatureUtils.register("yggdrasil_fancy_oak", Feature.TREE, createFancyOak().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_JUNGLE_TREE_NO_VINE = FeatureUtils.register("yggdrasil_jungle_tree_no_vine", Feature.TREE, createJungleTree().ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_MEGA_JUNGLE_TREE = FeatureUtils.register("yggdrasil_mega_jungle_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new MegaJungleTrunkPlacer(10, 2, 19), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new MegaJungleFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2), new TwoLayersFeatureSize(1, 1, 2))).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_MEGA_SPRUCE = FeatureUtils.register("yggdrasil_mega_spruce", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new GiantTrunkPlacer(13, 2, 14), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)), new TwoLayersFeatureSize(1, 1, 2))).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_MEGA_PINE = FeatureUtils.register("yggdrasil_mega_pine", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new GiantTrunkPlacer(13, 2, 14), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(3, 7)), new TwoLayersFeatureSize(1, 1, 2))).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_SUPER_BIRCH_BEES_0002 = FeatureUtils.register("yggdrasil_super_birch_bees_0002", Feature.TREE, createSuperBirch().decorators(ImmutableList.of(BEEHIVE_0002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_SUPER_BIRCH_BEES = FeatureUtils.register("yggdrasil_super_birch_bees", Feature.TREE, createSuperBirch().decorators(ImmutableList.of(BEEHIVE)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_SWAMP_OAK = FeatureUtils.register("yggdrasil_swamp_oak", Feature.TREE, createStraightBlobTree(BlockInit.yggdrasil_log.get(), BlockInit.yggdrasil_leaves.get(), 5, 3, 0, 3).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_JUNGLE_BUSH = FeatureUtils.register("yggdrasil_jungle_bush", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new StraightTrunkPlacer(1, 0, 0), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new BushFoliagePlacer(ConstantInt.of(2), ConstantInt.of(1), 2), new TwoLayersFeatureSize(0, 0, 0))).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_AZALEA_TREE = FeatureUtils.register("yggdrasil_azalea_tree", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new BendingTrunkPlacer(4, 2, 0, 3, UniformInt.of(1, 2)), new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BlockInit.yggdrasil_leaves.get().defaultBlockState(), 3).add(BlockInit.yggdrasil_leaves.get().defaultBlockState(), 1)), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 50), new TwoLayersFeatureSize(1, 0, 1))).dirt(BlockStateProvider.simple(Blocks.ROOTED_DIRT)).forceDirt().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_MANGROVE = FeatureUtils.register("yggdrasil_mangrove", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new UpwardsBranchingTrunkPlacer(2, 1, 4, UniformInt.of(1, 4), 0.5F, UniformInt.of(0, 1), Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 70), Optional.of(new MangroveRootPlacer(UniformInt.of(1, 3), BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), Optional.of(new AboveRootPlacement(BlockStateProvider.simple(Blocks.MOSS_CARPET), 0.5F)), new MangroveRootPlacement(Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), HolderSet.direct(Block::builtInRegistryHolder, Blocks.MUD, BlockInit.yggdrasil_log.get()), BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), 8, 15, 0.2F))), new TwoLayersFeatureSize(2, 0, 2))).decorators(List.of(BEEHIVE_001)).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_TALL_MANGROVE = FeatureUtils.register("yggdrasil_tall_mangrove", Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new UpwardsBranchingTrunkPlacer(4, 1, 9, UniformInt.of(1, 6), 0.5F, UniformInt.of(0, 1), Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_LOGS_CAN_GROW_THROUGH)), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new RandomSpreadFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0), ConstantInt.of(2), 70), Optional.of(new MangroveRootPlacer(UniformInt.of(3, 7), BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), Optional.of(new AboveRootPlacement(BlockStateProvider.simple(Blocks.MOSS_CARPET), 0.5F)), new MangroveRootPlacement(Registry.BLOCK.getOrCreateTag(BlockTags.MANGROVE_ROOTS_CAN_GROW_THROUGH), HolderSet.direct(Block::builtInRegistryHolder, Blocks.MUD, BlockInit.yggdrasil_log.get()), BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), 8, 15, 0.2F))), new TwoLayersFeatureSize(3, 0, 2))).decorators(List.of(BEEHIVE_001)).ignoreVines().build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_OAK_BEES_0002 = FeatureUtils.register("yggdrasil_oak_bees_0002", Feature.TREE, createOak().decorators(List.of(BEEHIVE_0002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_OAK_BEES_002 = FeatureUtils.register("yggdrasil_oak_bees_002", Feature.TREE, createOak().decorators(List.of(BEEHIVE_002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_OAK_BEES_005 = FeatureUtils.register("yggdrasil_oak_bees_005", Feature.TREE, createOak().decorators(List.of(BEEHIVE_005)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_BIRCH_BEES_0002 = FeatureUtils.register("yggdrasil_birch_bees_0002", Feature.TREE, createBirch().decorators(List.of(BEEHIVE_0002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_BIRCH_BEES_002 = FeatureUtils.register("yggdrasil_birch_bees_002", Feature.TREE, createBirch().decorators(List.of(BEEHIVE_002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_BIRCH_BEES_005 = FeatureUtils.register("yggdrasil_birch_bees_005", Feature.TREE, createBirch().decorators(List.of(BEEHIVE_005)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_FANCY_OAK_BEES_0002 = FeatureUtils.register("yggdrasil_fancy_oak_bees_0002", Feature.TREE, createFancyOak().decorators(List.of(BEEHIVE_0002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_FANCY_OAK_BEES_002 = FeatureUtils.register("yggdrasil_fancy_oak_bees_002", Feature.TREE, createFancyOak().decorators(List.of(BEEHIVE_002)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_FANCY_OAK_BEES_005 = FeatureUtils.register("yggdrasil_fancy_oak_bees_005", Feature.TREE, createFancyOak().decorators(List.of(BEEHIVE_005)).build());
    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> YGGDRASIL_FANCY_OAK_BEES = FeatureUtils.register("yggdrasil_fancy_oak_bees", Feature.TREE, createFancyOak().decorators(List.of(BEEHIVE)).build());


    private static TreeConfiguration.TreeConfigurationBuilder createOak() {
        return createStraightBlobTree(BlockInit.yggdrasil_log.get(), BlockInit.yggdrasil_leaves.get(), 4, 2, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createBirch() {
        return createStraightBlobTree(BlockInit.yggdrasil_log.get(), BlockInit.yggdrasil_leaves.get(), 5, 2, 0, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createSuperBirch() {
        return createStraightBlobTree(BlockInit.yggdrasil_log.get(), BlockInit.yggdrasil_leaves.get(), 5, 2, 6, 2).ignoreVines();
    }

    private static TreeConfiguration.TreeConfigurationBuilder createJungleTree() {
        return createStraightBlobTree(BlockInit.yggdrasil_log.get(), BlockInit.yggdrasil_leaves.get(), 4, 8, 0, 2);
    }

    private static TreeConfiguration.TreeConfigurationBuilder createFancyOak() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(BlockInit.yggdrasil_log.get()), new FancyTrunkPlacer(3, 11, 0), BlockStateProvider.simple(BlockInit.yggdrasil_leaves.get()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {

        switch (p_222910_.nextInt(18)) {
            case 0:
                return YGGDRASIL_OAK;
            case 1:
            default:
                return YGGDRASIL_BIRCH;
            case 2:
                return YGGDRASIL_ACACIA;
            case 3:
                return YGGDRASIL_SPRUCE;
            case 4:
                return YGGDRASIL_PINE;
            case 5:
                return YGGDRASIL_JUNGLE_TREE;
            case 6:
                return YGGDRASIL_FANCY_OAK;
            case 7:
                return YGGDRASIL_JUNGLE_TREE_NO_VINE;
            case 8:
                switch (p_222910_.nextInt(2)) {
                    case 0:
                    default:
                        return YGGDRASIL_SUPER_BIRCH_BEES_0002;
                    case 1:
                        return YGGDRASIL_SUPER_BIRCH_BEES;
                }
            case 9:
                return YGGDRASIL_SWAMP_OAK;
            case 11:
                return YGGDRASIL_JUNGLE_BUSH;
            case 12:
                return YGGDRASIL_AZALEA_TREE;
            case 13:
                return YGGDRASIL_MANGROVE;
            case 14:
                return YGGDRASIL_TALL_MANGROVE;
            case 15:
                switch (p_222910_.nextInt(3)) {
                    case 0:
                    default:
                        return YGGDRASIL_OAK_BEES_0002;
                    case 1:
                        return YGGDRASIL_OAK_BEES_002;
                    case 2:
                        return YGGDRASIL_OAK_BEES_005;
                }
            case 16:
                switch (p_222910_.nextInt(3)) {
                    case 0:
                    default:
                        return YGGDRASIL_BIRCH_BEES_0002;
                    case 1:
                        return YGGDRASIL_BIRCH_BEES_002;
                    case 2:
                        return YGGDRASIL_BIRCH_BEES_005;
                }
            case 17:
                switch (p_222910_.nextInt(4)) {
                    case 0:
                    default:
                        return YGGDRASIL_FANCY_OAK_BEES_0002;
                    case 1:
                        return YGGDRASIL_FANCY_OAK_BEES_002;
                    case 2:
                        return YGGDRASIL_FANCY_OAK_BEES_005;
                    case 3:
                        return YGGDRASIL_FANCY_OAK_BEES;
                }

        }

    }

    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource p_222941_) {
        switch (p_222941_.nextInt(4)) {
            case 0:
                return YGGDRASIL_DARK_OAK;
            case 1:
                return YGGDRASIL_MEGA_JUNGLE_TREE;
            case 2:
            default:
                return YGGDRASIL_MEGA_PINE;
            case 3:
                return YGGDRASIL_MEGA_SPRUCE;
        }
    }

}