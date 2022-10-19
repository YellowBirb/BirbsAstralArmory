package io.github.yellowbirb.birbsastralarmory.init;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.customentities.BYGBoat;
import io.github.yellowbirb.birbsastralarmory.init.customentities.YggdrasilBoat;
import io.github.yellowbirb.birbsastralarmory.init.customentities.YggdrasilChestBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BirbsAstralArmory.MODID);

    public static final RegistryObject<EntityType<YggdrasilBoat>> yggdrasil_boat =
            ENTITIES.register("yggdrasil_boat",
                    () -> EntityType.Builder.<YggdrasilBoat>of(YggdrasilBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(BirbsAstralArmory.MODID+":yggdrasil_boat"));

    public static final RegistryObject<EntityType<YggdrasilChestBoat>> yggdrasil_chest_boat =
            ENTITIES.register("yggdrasil_chest_boat",
                    () -> EntityType.Builder.<YggdrasilChestBoat>of(YggdrasilChestBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(BirbsAstralArmory.MODID+":yggdrasil_chest_boat"));

    public static final RegistryObject<EntityType<BYGBoat>> BYGBOAT =
            ENTITIES.register("byg_boat",
                    () -> EntityType.Builder.<BYGBoat>of(BYGBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(BirbsAstralArmory.MODID+":byg_boat"));

}
