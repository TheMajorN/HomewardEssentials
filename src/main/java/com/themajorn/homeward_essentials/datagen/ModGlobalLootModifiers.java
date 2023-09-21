package com.themajorn.homeward_essentials.datagen;

import com.themajorn.homeward_essentials.HomewardEssentials;
import com.themajorn.homeward_essentials.loot.AddItemModifier;
import com.themajorn.homeward_essentials.loot.SuspiciousSandLootModifier;
import com.themajorn.homeward_essentials.registry.BlockRegistry;
import com.themajorn.homeward_essentials.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiers extends GlobalLootModifierProvider {
    public ModGlobalLootModifiers(PackOutput output) {
        super(output, HomewardEssentials.MOD_ID);
    }

    @Override
    protected void start() {
        add("homeward_bone_from_desert_well_suspicious_sand", new SuspiciousSandLootModifier(new LootItemCondition[] {
            new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_well")).build() }, ItemRegistry.HOMEWARD_BONE.get()));
        add("homeward_bone_from_trail_ruins_common_suspicious_sand", new SuspiciousSandLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_common")).build() }, ItemRegistry.HOMEWARD_BONE.get()));
        add("homeward_bone_from_trail_ruins_rare_suspicious_sand", new SuspiciousSandLootModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/trail_ruins_rare")).build() }, ItemRegistry.HOMEWARD_BONE.get()));

        add("gold_pickled_fowl_foot_from_chicken", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/chicken")).build(),
                LootItemRandomChanceCondition.randomChance(0.2F).build() }, ItemRegistry.GOLD_PICKLED_FOWL_FOOT.get()));

        add("sacrificial_twig_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ItemRegistry.SACRIFICIAL_TWIG.get()));
        add("sacrificial_twig_from_jungle_temple", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build()
        }, ItemRegistry.SACRIFICIAL_TWIG.get()));
        add("sacrificial_twig_from_jungle_temple_dispenser", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple_dispenser")).build()
        }, ItemRegistry.SACRIFICIAL_TWIG.get()));
        add("sacrificial_twig_from_pillager_outpost", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/pillager_outpost")).build(),
                LootItemRandomChanceCondition.randomChance(0.6F).build()
        }, ItemRegistry.SACRIFICIAL_TWIG.get()));
        add("sacrificial_twig_from_simple_dungeon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/simple_dungeon")).build(),
                LootItemRandomChanceCondition.randomChance(0.4F).build()
        }, ItemRegistry.SACRIFICIAL_TWIG.get()));
        add("sacrificial_twig_from_woodland_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.2F).build()
        }, ItemRegistry.SACRIFICIAL_TWIG.get()));

        add("repair_powder_from_mansion", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/woodland_mansion")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ItemRegistry.REPAIR_POWDER.get()));
        add("repair_powder_from_mineshaft", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/abandoned_mineshaft")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ItemRegistry.REPAIR_POWDER.get()));
        add("repair_powder_from_nether_bridge", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ItemRegistry.REPAIR_POWDER.get()));
        add("repair_powder_from_bastion_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, ItemRegistry.REPAIR_POWDER.get()));

        add("warming_stone_from_stronghold_corridor", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_corridor")).build(),
                LootItemRandomChanceCondition.randomChance(0.8F).build()
        }, BlockRegistry.WARMING_STONE.get().asItem()));
        add("warming_stone_from_stronghold_crossing", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold_crossing")).build(),
                LootItemRandomChanceCondition.randomChance(0.6F).build()
        }, BlockRegistry.WARMING_STONE.get().asItem()));
        add("warming_stone_from_igloo_chest", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/igloo_chest")).build(),
                LootItemRandomChanceCondition.randomChance(0.8F).build()
        }, BlockRegistry.WARMING_STONE.get().asItem()));
        add("warming_stone_from_underwater_ruin_big", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_big")).build(),
                LootItemRandomChanceCondition.randomChance(0.5F).build()
        }, BlockRegistry.WARMING_STONE.get().asItem()));
        add("warming_stone_from_underwater_ruin_small", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/underwater_ruin_small")).build(),
                LootItemRandomChanceCondition.randomChance(0.4F).build()
        }, BlockRegistry.WARMING_STONE.get().asItem()));
        add("warming_stone_from_shipwreck_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.7F).build()
        }, BlockRegistry.WARMING_STONE.get().asItem()));
        add("warming_stone_from_shipwreck_supply", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck_supply")).build(),
                LootItemRandomChanceCondition.randomChance(0.4F).build()
        }, BlockRegistry.WARMING_STONE.get().asItem()));

    }
}
