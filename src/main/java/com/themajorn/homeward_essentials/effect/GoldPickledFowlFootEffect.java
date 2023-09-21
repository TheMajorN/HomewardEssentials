package com.themajorn.homeward_essentials.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.behavior.VillagerMakeLove;
import net.minecraft.world.entity.ai.behavior.VillagerPanicTrigger;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.npc.Villager;
import net.minecraftforge.common.VillagerTradingManager;
import net.minecraftforge.event.village.VillagerTradesEvent;

// This entire class is a figurehead.
public class GoldPickledFowlFootEffect extends MobEffect {
    public GoldPickledFowlFootEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}