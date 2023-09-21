package com.themajorn.homeward_essentials.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class FoodRegistry {

    public static final FoodProperties GOLD_PICKLED_FOWL_FOOT_FOOD = new FoodProperties.Builder().fast().alwaysEat()
            .effect(() -> new MobEffectInstance(EffectRegistry.GOLD_PICKLED_FOWL_FOOT_EFFECT.get(), 3600), 1).build();

}
