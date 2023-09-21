package com.themajorn.homeward_essentials.block;

import com.themajorn.homeward_essentials.registry.BlockEntityRegistry;
import com.themajorn.homeward_essentials.registry.ParticleRegistry;
import com.themajorn.homeward_essentials.registry.SoundRegistry;
import com.themajorn.homeward_essentials.util.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WarmingStoneBlock extends Block implements EntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public WarmingStoneBlock(Properties pProperties) {
        super(pProperties);
    }

    public static final VoxelShape SHAPE = Block.box(7.25, 0, 7, 8.75, 0.5, 9);

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return BlockEntityRegistry.WARMING_STONE_ENTITY.get().create(pPos, pState);
    }

    @Override
    public SoundType getSoundType(BlockState state, LevelReader level, BlockPos pos, @Nullable Entity entity) {
        return new SoundType(1.0F, 1.0F, SoundEvents.GLASS_BREAK, SoundEvents.STONE_STEP, SoundRegistry.WARMING_STONE_SOUND.get(), SoundEvents.STONE_HIT, SoundEvents.STONE_FALL);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return TickableBlockEntity.getTickerHelper(pLevel);
    }

    @Override
    protected void spawnDestroyParticles(Level pLevel, Player pPlayer, BlockPos pPos, BlockState pState) {
        pLevel.levelEvent(pPlayer, 2001, pPos, 198);
    }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom) {
        double centerX = (double) pPos.getX() + 0.5D;
        double centerY = (double) pPos.getY() + 1.0D;
        double centerZ = (double) pPos.getZ() + 0.5D;

        // Define the radius of the circular path
        double radius = 2.0D + pRandom.nextDouble() * 2.0D;

        // Calculate the angle increment to distribute particles evenly
        double angleIncrement = 2.0D * Math.PI / 20.0D; // You can adjust the number of particles by changing the denominator

        for (int i = 0; i < 20; i++) {
            double angle = i * angleIncrement;

            // Add some randomness to the angle for non-uniform particle distribution
            angle += pRandom.nextDouble() * Math.PI / 8.0D - Math.PI / 16.0D;

            // Calculate the particle position on the circular path
            double xOffset = radius * Math.cos(angle);
            double zOffset = radius * Math.sin(angle);

            // Offset the particle position by the block's center
            double particleX = centerX + xOffset;
            double particleY = centerY;
            double particleZ = centerZ + zOffset;

            // Calculate the rotation angle based on particle position
            double rotationAngle = Math.atan2(zOffset, xOffset);

            // Create particles at the calculated position with clockwise rotation
            pLevel.addParticle(ParticleRegistry.WARMING_STONE_PARTICLE.get(), particleX, particleY, particleZ,
                    -Math.sin(rotationAngle) * 0.05D, 0.0D, Math.cos(rotationAngle) * 0.05D);
        }
    }
}
