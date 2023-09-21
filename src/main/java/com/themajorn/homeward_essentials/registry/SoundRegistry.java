package com.themajorn.homeward_essentials.registry;

import com.themajorn.homeward_essentials.HomewardEssentials;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegistry {

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HomewardEssentials.MOD_ID);

    public static final RegistryObject<SoundEvent> HOMEWARD_BONE_1 = registerSound("homeward_bone_1");
    public static final RegistryObject<SoundEvent> HOMEWARD_BONE_2 = registerSound("homeward_bone_2");
    public static final RegistryObject<SoundEvent> HOMEWARD_BONE_3 = registerSound("homeward_bone_3");
    public static final RegistryObject<SoundEvent> HOMEWARD_BONE_LOOP = registerSound("homeward_bone_loop");
    public static final RegistryObject<SoundEvent> HOMEWARD_BONE_TELEPORT = registerSound("homeward_bone_teleport");
    public static final RegistryObject<SoundEvent> SACRIFICIAL_TWIG_RESPAWN = registerSound("sacrificial_twig_respawn");
    public static final RegistryObject<SoundEvent> REPAIR_POWDER_SHAKE = registerSound("repair_powder_shake");
    public static final RegistryObject<SoundEvent> WARMING_STONE_SOUND = registerSound("warming_stone");

    public static RegistryObject<SoundEvent> registerSound(String name) {
        ResourceLocation id = new ResourceLocation(HomewardEssentials.MOD_ID, name);
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
