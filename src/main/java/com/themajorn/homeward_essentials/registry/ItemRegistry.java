package com.themajorn.homeward_essentials.registry;

import com.themajorn.homeward_essentials.HomewardEssentials;
import com.themajorn.homeward_essentials.item.HomewardBoneItem;
import com.themajorn.homeward_essentials.item.RepairPowderItem;
import com.themajorn.homeward_essentials.item.SacrificialTwigItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HomewardEssentials.MOD_ID);

    public static final RegistryObject<Item> HOMEWARD_BONE = ITEMS.register("homeward_bone",
            () -> new HomewardBoneItem(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> SACRIFICIAL_TWIG = ITEMS.register("sacrificial_twig",
            () -> new SacrificialTwigItem(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> REPAIR_POWDER = ITEMS.register("repair_powder",
            () -> new RepairPowderItem(new Item.Properties().durability(1600).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> GOLD_PICKLED_FOWL_FOOT = ITEMS.register("gold_pickled_fowl_foot",
            () -> new Item(new Item.Properties().food(FoodRegistry.GOLD_PICKLED_FOWL_FOOT_FOOD).rarity(Rarity.RARE)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
