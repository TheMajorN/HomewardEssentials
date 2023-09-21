package com.themajorn.homeward_essentials.item;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.themajorn.homeward_essentials.registry.ItemRegistry;
import com.themajorn.homeward_essentials.registry.SoundRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.joml.Quaternionf;

import java.util.function.Consumer;

public class RepairPowderItem extends Item {
    public RepairPowderItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (pPlayer.getItemInHand(InteractionHand.OFF_HAND).is(ItemRegistry.REPAIR_POWDER.get()) && pPlayer.getItemInHand(InteractionHand.MAIN_HAND).isRepairable()) {
            pPlayer.setSpeed(0.3F);
            pPlayer.startUsingItem(pHand);
            return InteractionResultHolder.success(itemstack);
        } else {
            return InteractionResultHolder.fail(itemstack);
        }
    }

    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pCount) {
        double f = (pStack.getUseDuration() - pCount);
        ItemStack repairPowder = pLivingEntity.getItemInHand(InteractionHand.OFF_HAND);
        ItemStack itemToRepair = pLivingEntity.getItemInHand(InteractionHand.MAIN_HAND);
        int repairAmountPerTick = 16;

        if (f % 10 == 0) {
            pLevel.playSound(null, pLivingEntity.getX(), pLivingEntity.getY(), pLivingEntity.getZ(), SoundRegistry.REPAIR_POWDER_SHAKE.get(), SoundSource.PLAYERS, 1.0F, 1.4F);
            if (!pLivingEntity.isInvulnerable() && itemToRepair.isDamaged()) {
                repairPowder.setDamageValue(repairPowder.getDamageValue() + repairAmountPerTick);
            }
            itemToRepair.setDamageValue(itemToRepair.getDamageValue() - repairAmountPerTick);
        }

        if (repairPowder.getDamageValue() >= 1600) {
            pLivingEntity.setItemInHand(InteractionHand.OFF_HAND, Items.AIR.getDefaultInstance());
        }
    }



    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

}
