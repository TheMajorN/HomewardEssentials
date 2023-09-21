package com.themajorn.homeward_essentials.registry;

import com.themajorn.homeward_essentials.HomewardEssentials;
import com.themajorn.homeward_essentials.entity.WarmingStoneBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HomewardEssentials.MOD_ID);

    public static final RegistryObject<BlockEntityType<WarmingStoneBlockEntity>> WARMING_STONE_ENTITY =
            BLOCK_ENTITIES.register("warming_stone",
                    () -> BlockEntityType.Builder.of(WarmingStoneBlockEntity::new,
                            BlockRegistry.WARMING_STONE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
