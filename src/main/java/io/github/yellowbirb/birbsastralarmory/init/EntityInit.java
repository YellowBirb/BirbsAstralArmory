package io.github.yellowbirb.birbsastralarmory.init;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.customentities.BirbsAstralBoat;
import io.github.yellowbirb.birbsastralarmory.init.customentities.BirbsAstralChestBoat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {

    public static DeferredRegister<EntityType<?>> ENTITIES
            = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BirbsAstralArmory.MODID);

    public static final RegistryObject<EntityType<BirbsAstralBoat>> birbs_astral_boat =
            ENTITIES.register("byg_boat",
                    () -> EntityType.Builder.<BirbsAstralBoat>of(BirbsAstralBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(BirbsAstralArmory.MODID+":birbs_astral_boat"));

    public static final RegistryObject<EntityType<BirbsAstralChestBoat>> birbs_astral_chest_boat =
            ENTITIES.register("birbs_astral_chest_boat",
                    () -> EntityType.Builder.<BirbsAstralChestBoat>of(BirbsAstralChestBoat::new, MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(BirbsAstralArmory.MODID+":birbs_astral_chest_boat"));



}
