package com.themajorn.homeward_essentials.registry;

import com.themajorn.homeward_essentials.HomewardEssentials;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ParticleRegistry {

    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HomewardEssentials.MOD_ID);

    public static final RegistryObject<SimpleParticleType> HOMEWARD_BONE_PARTICLE = PARTICLES.register("homeward_bone_particles",
            () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> WARMING_STONE_PARTICLE = PARTICLES.register("warming_stone_particles",
            () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }
}
