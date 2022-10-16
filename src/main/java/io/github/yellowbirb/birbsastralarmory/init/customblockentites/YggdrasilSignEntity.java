package io.github.yellowbirb.birbsastralarmory.init.customblockentites;

import io.github.yellowbirb.birbsastralarmory.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class YggdrasilSignEntity extends SignBlockEntity {

    public YggdrasilSignEntity(BlockPos blockPos, BlockState blockState) {

        super(blockPos, blockState);

    }

    @Override
    public BlockEntityType<?> getType() {

        return BlockEntityInit.yggdrasil_sign.get();

    }

}
