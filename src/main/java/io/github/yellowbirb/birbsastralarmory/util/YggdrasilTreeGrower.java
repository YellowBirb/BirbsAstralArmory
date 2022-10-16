package io.github.yellowbirb.birbsastralarmory.util;

import io.github.yellowbirb.birbsastralarmory.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import org.jetbrains.annotations.Nullable;

public class YggdrasilTreeGrower extends AbstractTreeGrower {

    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(ServerLevel level, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, RandomSource random, boolean hasFlowers) {
      return getConfiguredFeature(random, hasFlowers);
   }

   private static TreeConfiguration.TreeConfigurationBuilder createYggdrasilSprout() {
      return createStraightBlobTree(BlockInit.yggdrasil_log.get(), BlockInit.yggdrasil_leaves.get(), 5, 2, 0, 2).ignoreVines();
   }

   public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> yggdrasil_sprout = FeatureUtils.register("ygdrasil_sprout", Feature.TREE, createYggdrasilSprout().build());

    private static TreeConfiguration.TreeConfigurationBuilder createStraightBlobTree(Block p_195147_, Block p_195148_, int p_195149_, int p_195150_, int p_195151_, int p_195152_) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(p_195147_),
                new StraightTrunkPlacer(p_195149_, p_195150_, p_195151_), BlockStateProvider.simple(p_195148_),
                new BlobFoliagePlacer(ConstantInt.of(p_195152_), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1));
    }

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_222910_, boolean p_222911_) {
        return yggdrasil_sprout;
    }
}