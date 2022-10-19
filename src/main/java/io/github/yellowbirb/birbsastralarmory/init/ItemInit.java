package io.github.yellowbirb.birbsastralarmory.init;

import io.github.yellowbirb.birbsastralarmory.BirbsAstralArmory;
import io.github.yellowbirb.birbsastralarmory.init.customentities.BirbsAstralBoat;
import io.github.yellowbirb.birbsastralarmory.init.customitems.BirbsAstralBoatItem;
import io.github.yellowbirb.birbsastralarmory.init.customitems.BirbsAstralChestBoatItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    static CreativeModeTab mytab = BirbsAstralArmory.BAATAB;
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BirbsAstralArmory.MODID);

    // Items
    public static final RegistryObject<Item> placeholder = ITEMS.register("placeholder",
            () -> new Item(new Item.Properties().stacksTo(3).tab(mytab)));
    public static final RegistryObject<Item> placeholder2 = ITEMS.register("placeholder2",
            () -> new Item(new Item.Properties().stacksTo(3).tab(mytab)));

    public static final RegistryObject<Item> yggdrasil_sign = ITEMS.register("yggdrasil_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16).tab(mytab),
            BlockInit.yggdrasil_sign.get(), BlockInit.yggdrasil_wall_sign.get()));

    public static final RegistryObject<Item> yggdrasil_boat = ITEMS.register("yggdrasil_boat",
            () -> new BirbsAstralBoatItem(false,
                    BirbsAstralBoat.BoatType.YGGDRASIL,
                    new Item.Properties().tab(mytab).stacksTo(1)));
    public static final RegistryObject<Item> yggdrasil_chest_boat = ITEMS.register("yggdrasil_chest_boat",
            () -> new BirbsAstralChestBoatItem(true,
                    BirbsAstralBoat.Type.OAK,
                    new Item.Properties().tab(mytab).stacksTo(1)));
}
