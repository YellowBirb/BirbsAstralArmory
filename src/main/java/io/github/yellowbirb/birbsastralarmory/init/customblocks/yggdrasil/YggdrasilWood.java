package io.github.yellowbirb.birbsastralarmory.init.customblocks.yggdrasil;

import io.github.yellowbirb.birbsastralarmory.init.BlockInit;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

public class YggdrasilWood extends RotatedPillarBlock {

    public YggdrasilWood(BlockBehaviour.Properties properties) {

        super(properties);

    }

    @Override
    @Nullable
    public BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate)
    {
        ItemStack itemStack = context.getItemInHand();
        if (!itemStack.canPerformAction(toolAction))
            return null;

        if (ToolActions.AXE_STRIP == toolAction) {

            if (itemStack.getItem() == Items.NETHERITE_AXE) {

                Block block = BlockInit.stripped_yggdrasil_wood.get();
                return block != null ? block.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)) : null;

            } else {
                return null;
            }

        } else {
            return null;
        }
    }

}
