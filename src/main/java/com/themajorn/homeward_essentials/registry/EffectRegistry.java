package com.themajorn.homeward_essentials.registry;

import com.themajorn.homeward_essentials.HomewardEssentials;
import com.themajorn.homeward_essentials.effect.GoldPickledFowlFootEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistry {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, HomewardEssentials.MOD_ID);

    public static final RegistryObject<MobEffect> GOLD_PICKLED_FOWL_FOOT_EFFECT = MOB_EFFECTS.register("gold_pickled_fowl_foot",
            () -> new GoldPickledFowlFootEffect(MobEffectCategory.BENEFICIAL, 16580431));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
