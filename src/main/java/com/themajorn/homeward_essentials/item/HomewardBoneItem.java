package com.themajorn.homeward_essentials.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.themajorn.homeward_essentials.registry.ItemRegistry;
import com.themajorn.homeward_essentials.registry.ParticleRegistry;
import com.themajorn.homeward_essentials.registry.SoundRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.joml.Quaternionf;

import java.util.function.Consumer;

public class HomewardBoneItem extends Item {
    public HomewardBoneItem(Properties pProperties) {
        super(pProperties);
    }

    protected final RandomSource random = RandomSource.create();

    @Override
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof ServerPlayer player) {
            player.setSpeed(1.0F);
            boolean hasBed = player.getRespawnPosition() != null;
            BlockPos bed = player.getRespawnPosition();
            int i = this.getUseDuration(pStack) - pTimeLeft;
            if (i < 0) return;
            if ((i > 180) && hasBed) {
                player.teleportTo(bed.getX(), bed.getY(), bed.getZ());
                pLevel.playSound(null, bed.getX(), bed.getY(), bed.getZ(), SoundRegistry.HOMEWARD_BONE_TELEPORT.get(), SoundSource.BLOCKS, 1.0F, 2.0F);
                if (pLevel.isClientSide) {
                    spawnTeleportParticles(pLevel, pEntityLiving);
                }
                if (!player.getAbilities().instabuild) {
                    pStack.shrink(1);
                }
            } else {
                pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.BAMBOO_HIT, SoundSource.PLAYERS, 1.0F, 2.0F);
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            }
        }

    private void spawnTeleportParticles(Level pLevel, LivingEntity pEntityLiving) {
        for (int j = 0; j < 360; j++) {
            if (j % 5 == 0) {
                pLevel.addParticle(ParticleRegistry.HOMEWARD_BONE_PARTICLE.get(),
                        pEntityLiving.getX() + 0.5D, pEntityLiving.getY() + 1, pEntityLiving.getZ() + 0.5D,
                        Math.cos(j) * 0.25D, 0.15D, Math.sin(j) * 0.25D);
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).is(ItemRegistry.HOMEWARD_BONE.get())) {
            pPlayer.setSpeed(0.3F);
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.consume(itemstack);
        } else {
            return InteractionResultHolder.fail(ItemRegistry.HOMEWARD_BONE.get().getDefaultInstance());
        }
    }

    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pCount) {
        if (!pLevel.isClientSide) {
            double f = (pStack.getUseDuration() - pCount);
            if (f % 40 == 0 && f >= 200) {
                pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundRegistry.HOMEWARD_BONE_LOOP.get(), SoundSource.PLAYERS, 0.3F, 1.0F);
            }
            if (f == 0) {
                pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundRegistry.HOMEWARD_BONE_1.get(), SoundSource.PLAYERS, 0.1F, 1.0F);
            }

            if (f == 60) {
                pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundRegistry.HOMEWARD_BONE_2.get(), SoundSource.PLAYERS, 0.3F, 1.0F);
            }

            if (f == 120) {
                pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundRegistry.HOMEWARD_BONE_3.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
            }
        }
        if (pLevel.isClientSide) {
            double f = (pStack.getUseDuration() - pCount);

            for (int i = 0; i <= Math.min(6, f / 80); i++) {
                double speedMultiplier = 2.0D + i * 2.0D;
                double yOffset = -0.25D;
                double spread = 0.7D;
                double spreadMultiplier = 2.0D + i * 2.0D;

                pLivingEntity.level().addParticle(
                        ParticleRegistry.HOMEWARD_BONE_PARTICLE.get(),
                        pLivingEntity.getRandomX(spread),
                        pLivingEntity.getRandomY() + yOffset,
                        pLivingEntity.getRandomZ(spread),
                        (this.random.nextDouble() - 0.5D) * speedMultiplier,
                        -this.random.nextDouble(),
                        (this.random.nextDouble() - 0.5D) * spreadMultiplier
                );
            }
        }
    }

    public boolean useOnRelease(ItemStack pStack) {
        return pStack.is(this);
    }

    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {

            private static final HumanoidModel.ArmPose HOMEWARD_BONE = HumanoidModel.ArmPose.create("HOMEWARD_BONE", false, (model, entity, arm) -> {
                if (arm == HumanoidArm.RIGHT) {
                    model.rightArm.xRot = (float) (-30);
                    model.rightArm.yRot = (float) (35);
                    model.rightArm.zRot = (float) (-15);
                    model.leftArm.xRot = (float) (-30);
                    model.leftArm.yRot = (float) (-35);
                    model.leftArm.zRot = (float) (-15);
                }
            });

            @Override
            public HumanoidModel.ArmPose getArmPose(LivingEntity entityLiving, InteractionHand hand, ItemStack itemStack) {
                if (!itemStack.isEmpty()) {
                    if (entityLiving.getUsedItemHand() == hand && entityLiving.getUseItemRemainingTicks() > 0) {
                        return HOMEWARD_BONE;
                    }
                }
                return HumanoidModel.ArmPose.EMPTY;
            }

            @Override
            public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
                int i = arm == HumanoidArm.RIGHT ? 1 : -1;
                poseStack.translate(i * 0.56F, -0.52F, -0.72F);
                if (player.getUseItem() == itemInHand && player.isUsingItem()) {
                    poseStack.translate(i * -0.70F, i * -0.20F, i * -0.0);
                    poseStack.mulPose(new Quaternionf().mul(Axis.YP.rotationDegrees(90F)));
                }
                return true;
            }
        });
    }
}
