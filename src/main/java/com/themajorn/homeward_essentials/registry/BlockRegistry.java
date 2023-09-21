package com.themajorn.homeward_essentials.registry;

import com.themajorn.homeward_essentials.HomewardEssentials;
import com.themajorn.homeward_essentials.block.WarmingStoneBlock;
import net.minecraft.client.particle.EndRodParticle;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, HomewardEssentials.MOD_ID);

    public static final RegistryObject<Block> WARMING_STONE = registerBlock("warming_stone",
            () -> new WarmingStoneBlock(BlockBehaviour.Properties.copy(Blocks.STONE).lightLevel(getLightLevel(12)).strength(999)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static ToIntFunction<BlockState> getLightLevel(int light) {
        return (state) -> light; // Return the desired light level (in this case, 5)
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ItemRegistry.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
