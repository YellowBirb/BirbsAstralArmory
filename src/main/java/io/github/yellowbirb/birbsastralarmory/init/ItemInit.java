package io.github.yellowbirb.birbsastralarmory.init;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.customentities.BirbsAstralBoat;
import io.github.yellowbirb.birbsastralarmory.init.customitems.BirbsAstralBoatItem;
import io.github.yellowbirb.birbsastralarmory.init.customitems.BirbsAstralChestBoatItem;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    static CreativeModeTab mytab = BirbsAstralArmory.BAATAB;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BirbsAstralArmory.MODID);

    // Items
    public static final RegistryObject<Item> placeholder = ITEMS.register("placeholder",
            () -> new Item(new Item.Properties().stacksTo(3)));
    public static final RegistryObject<Item> placeholder2 = ITEMS.register("placeholder2",
            () -> new Item(new Item.Properties().stacksTo(3)));
    public static final RegistryObject<Item> stripped_yggdrasil_log = ITEMS.register("stripped_yggdrasil_log",
            () -> new BlockItem(BlockInit.stripped_yggdrasil_log.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> stripped_yggdrasil_wood = ITEMS.register("stripped_yggdrasil_wood",
            () -> new BlockItem(BlockInit.stripped_yggdrasil_wood.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_button = ITEMS.register("yggdrasil_button",
            () -> new BlockItem(BlockInit.yggdrasil_button.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_door = ITEMS.register("yggdrasil_door",
            () -> new BlockItem(BlockInit.yggdrasil_door.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_fence = ITEMS.register("yggdrasil_fence",
            () -> new BlockItem(BlockInit.yggdrasil_fence.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_fence_gate = ITEMS.register("yggdrasil_fence_gate",
            () -> new BlockItem(BlockInit.yggdrasil_fence_gate.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_leaves = ITEMS.register("yggdrasil_leaves",
            () -> new BlockItem(BlockInit.yggdrasil_leaves.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_log = ITEMS.register("yggdrasil_log",
            () -> new BlockItem(BlockInit.yggdrasil_log.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_branch = ITEMS.register("yggdrasil_branch",
            () -> new BlockItem(BlockInit.yggdrasil_branch.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_planks = ITEMS.register("yggdrasil_planks",
            () -> new BlockItem(BlockInit.yggdrasil_planks.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_pressure_plate = ITEMS.register("yggdrasil_pressure_plate",
            () -> new BlockItem(BlockInit.yggdrasil_pressure_plate.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<BucketItem> yggdrasil_resin_bucket = ITEMS.register("yggdrasil_resin_bucket",
            () -> new BucketItem(FluidInit.yggdrasil_resin.source,
                    new Item.Properties().tab(mytab).craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> yggdrasil_sign = ITEMS.register("yggdrasil_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16).tab(mytab),
            BlockInit.yggdrasil_sign.get(), BlockInit.yggdrasil_wall_sign.get()));
    public static final RegistryObject<Item> yggdrasil_slab = ITEMS.register("yggdrasil_slab",
            () -> new BlockItem(BlockInit.yggdrasil_slab.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_stairs = ITEMS.register("yggdrasil_stairs",
            () -> new BlockItem(BlockInit.yggdrasil_stairs.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_trapdoor = ITEMS.register("yggdrasil_trapdoor",
            () -> new BlockItem(BlockInit.yggdrasil_trapdoor.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_wood = ITEMS.register("yggdrasil_wood",
            () -> new BlockItem(BlockInit.yggdrasil_wood.get(), new Item.Properties().tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_boat = ITEMS.register("yggdrasil_boat",
            () -> new BirbsAstralBoatItem(false,
                    BirbsAstralBoat.BoatType.YGGDRASIL,
                    new Item.Properties().stacksTo(1).tab(mytab)));
    public static final RegistryObject<Item> yggdrasil_chest_boat = ITEMS.register("yggdrasil_chest_boat",
            () -> new BirbsAstralChestBoatItem(true,
                    BirbsAstralBoat.BoatType.YGGDRASIL,
                    new Item.Properties().stacksTo(1).tab(mytab)));
}
