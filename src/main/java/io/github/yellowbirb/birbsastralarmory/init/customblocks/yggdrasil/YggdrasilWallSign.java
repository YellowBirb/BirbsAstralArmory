package io.github.yellowbirb.birbsastralarmory.init.customblocks.yggdrasil;

import io.github.yellowbirb.birbsastralarmory.init.customblockentites.YggdrasilSignEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class YggdrasilWallSign extends WallSignBlock {

    public YggdrasilWallSign(BlockBehaviour.Properties properties, WoodType woodType) {

        super(properties, woodType);

    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_154556_, BlockState p_154557_) {
        return new YggdrasilSignEntity(p_154556_, p_154557_);
    }

}
