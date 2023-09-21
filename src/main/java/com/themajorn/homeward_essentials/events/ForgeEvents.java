package com.themajorn.homeward_essentials.events;

import com.themajorn.homeward_essentials.HomewardEssentials;
import com.themajorn.homeward_essentials.item.RepairPowderItem;
import com.themajorn.homeward_essentials.registry.EffectRegistry;
import com.themajorn.homeward_essentials.registry.ItemRegistry;
import com.themajorn.homeward_essentials.registry.ParticleRegistry;
import com.themajorn.homeward_essentials.registry.SoundRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = HomewardEssentials.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {
    public static boolean hadTwig = false;
    private static final List<ItemStack> playerInventoryContents = new ArrayList<>();
    protected static final RandomSource random = RandomSource.create();


    @SubscribeEvent
    public static void playerDieWithSacrificialTwigMainHand(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player && player.getItemInHand(InteractionHand.MAIN_HAND).is(ItemRegistry.SACRIFICIAL_TWIG.get())) {
            hadTwig = true;
            playerInventoryContents.clear();
            for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
                ItemStack itemStack = player.getInventory().getItem(slot);
                playerInventoryContents.add(itemStack.copy());
                player.getInventory().setItem(slot, ItemStack.EMPTY);
            }
        }
    }

    @SubscribeEvent
    public static void playerDieWithSacrificialTwigOffHand(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player player && player.getItemInHand(InteractionHand.OFF_HAND).is(ItemRegistry.SACRIFICIAL_TWIG.get())) {
            hadTwig = true;
            playerInventoryContents.clear();
            for (int slot = 0; slot < player.getInventory().getContainerSize(); slot++) {
                ItemStack itemStack = player.getInventory().getItem(slot);
                playerInventoryContents.add(itemStack.copy());
                player.getInventory().setItem(slot, ItemStack.EMPTY);
            }
        }
    }

    @SubscribeEvent
    public static void playerRespawnWithSacrificialTwig(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && hadTwig) {
            for (ItemStack itemStack : playerInventoryContents) {
                if (!itemStack.isEmpty() && !itemStack.is(ItemRegistry.SACRIFICIAL_TWIG.get())) {
                    player.getInventory().add(itemStack);
                }
            }
            if (player.level().isClientSide) {
                for (int i = 0; i <= 8; i++) {
                    player.level().addParticle(ParticleRegistry.HOMEWARD_BONE_PARTICLE.get(), player.getRandomX(0.8D), player.getRandomY() - 0.25D, player.getRandomZ(0.8D), (random.nextDouble() - 0.3D) * 21.0D, - random.nextDouble(), (random.nextDouble() - 0.3D) * 12.0D);
                }
            }
            player.level().playSound(null, player.getX(), player.getY(), player.getZ(), SoundRegistry.SACRIFICIAL_TWIG_RESPAWN.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
            hadTwig = false;
        }
    }

    @SubscribeEvent
    public static void goldPickledFowlFoot(PlayerXpEvent.XpChange event) {
        Player player = event.getEntity();

        if (event.getEntity() != null && player.hasEffect(EffectRegistry.GOLD_PICKLED_FOWL_FOOT_EFFECT.get())) {
            event.setAmount(event.getAmount() * 2);
        }
    }
}
