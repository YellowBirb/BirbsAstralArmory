package io.github.yellowbirb.birbsastralarmory.init;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.customblockentites.YggdrasilSignEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BirbsAstralArmory.MODID);

    public static final RegistryObject<BlockEntityType<YggdrasilSignEntity>> yggdrasil_sign = BLOCK_ENTITIES.register(
            "yggdrasil_sign_entity", () -> BlockEntityType.Builder.of(YggdrasilSignEntity::new,
                    BlockInit.yggdrasil_wall_sign.get(), BlockInit.yggdrasil_sign.get()).build(null));

}
