package com.themajorn.homeward_essentials.registry;

import com.mojang.serialization.Codec;
import com.themajorn.homeward_essentials.HomewardEssentials;
import com.themajorn.homeward_essentials.loot.AddItemModifier;
import com.themajorn.homeward_essentials.loot.SuspiciousSandLootModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootModifierRegistry {

    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, HomewardEssentials.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_HOMEWARD_BONE =
            LOOT_MODIFIER_SERIALIZERS.register("add_suspicious_sand_item", SuspiciousSandLootModifier.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", AddItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }

}
