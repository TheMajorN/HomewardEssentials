package com.themajorn.homeward_essentials.events;

import com.themajorn.homeward_essentials.HomewardEssentials;
import com.themajorn.homeward_essentials.particle.HomewardBoneParticle;
import com.themajorn.homeward_essentials.particle.WarmingStoneParticle;
import com.themajorn.homeward_essentials.registry.ParticleRegistry;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HomewardEssentials.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerParticleFactories(final RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.HOMEWARD_BONE_PARTICLE.get(),
                HomewardBoneParticle.Provider::new);
        Minecraft.getInstance().particleEngine.register(ParticleRegistry.WARMING_STONE_PARTICLE.get(),
                WarmingStoneParticle.Provider::new);
    }

}
