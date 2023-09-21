package com.themajorn.homeward_essentials.entity;

import com.themajorn.homeward_essentials.registry.BlockEntityRegistry;
import com.themajorn.homeward_essentials.registry.BlockRegistry;
import com.themajorn.homeward_essentials.registry.SoundRegistry;
import com.themajorn.homeward_essentials.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class WarmingStoneBlockEntity extends BlockEntity implements TickableBlockEntity {
    public WarmingStoneBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BlockEntityRegistry.WARMING_STONE_ENTITY.get(), pPos, pBlockState);
    }

    int tick = 0;
    @Override
    public void tick() {
        if (level != null && !level.isClientSide) {
            int radius = 5;

            for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class,
                    new AABB(worldPosition.offset(-radius, -radius, -radius),
                            worldPosition.offset(radius + 1, radius + 1, radius + 1)))) {
                if (entity instanceof Player && !entity.hasEffect(MobEffects.REGENERATION)) {
                    entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 60, 2));
                }
            }
        }
        tick++;
        if (tick >= 370) {
            level.removeBlock(worldPosition, false);
            tick = 0;
        }
    }
}
