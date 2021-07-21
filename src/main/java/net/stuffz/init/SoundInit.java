package net.stuffz.init;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundInit {

    public static final Identifier GEYSER = new Identifier("stuffz:geyser");
    public static SoundEvent GEYSER_EVENT = new SoundEvent(GEYSER);
    public static final Identifier HAMMERHIT = new Identifier("stuffz:hammerhit");
    public static SoundEvent HAMMERHIT_EVENT = new SoundEvent(HAMMERHIT);
    public static final Identifier BARRELHIT = new Identifier("stuffz:barrelhit");
    public static SoundEvent BARRELHIT_EVENT = new SoundEvent(BARRELHIT);
    public static final Identifier IRONLADDER = new Identifier("stuffz:ironladder");
    public static SoundEvent IRONLADDER_EVENT = new SoundEvent(IRONLADDER);
    public static BlockSoundGroup IRONLADDER_GROUP = new BlockSoundGroup(1F, 1F, SoundEvents.BLOCK_METAL_BREAK, IRONLADDER_EVENT, SoundEvents.BLOCK_METAL_PLACE, SoundEvents.BLOCK_METAL_HIT,
            SoundEvents.BLOCK_METAL_FALL);

    public static void init() {
        Registry.register(Registry.SOUND_EVENT, GEYSER, GEYSER_EVENT);
        Registry.register(Registry.SOUND_EVENT, HAMMERHIT, HAMMERHIT_EVENT);
        Registry.register(Registry.SOUND_EVENT, BARRELHIT, BARRELHIT_EVENT);
        Registry.register(Registry.SOUND_EVENT, IRONLADDER, IRONLADDER_EVENT);
    }

}