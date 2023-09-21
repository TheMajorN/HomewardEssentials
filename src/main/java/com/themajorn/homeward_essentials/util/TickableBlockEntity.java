package com.themajorn.homeward_essentials.util;

import com.themajorn.homeward_essentials.registry.BlockEntityRegistry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;

public interface TickableBlockEntity {
    void tick();

    static <T extends BlockEntity>BlockEntityTicker<T> getTickerHelper(Level level) {
        return level.isClientSide() ? null : (level1, pos, state, blockEntity) -> ((TickableBlockEntity)blockEntity).tick();
    }
}
