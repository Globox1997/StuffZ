package net.stuffz.init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundInit {

  public static final Identifier GEYSER = new Identifier("stuffz:geyser");
  public static SoundEvent GEYSER_EVENT = new SoundEvent(GEYSER);
  public static final Identifier HAMMERHIT = new Identifier("stuffz:hammerhit");
  public static SoundEvent HAMMERHIT_EVENT = new SoundEvent(HAMMERHIT);

  public static void init() {
    Registry.register(Registry.SOUND_EVENT, GEYSER, GEYSER_EVENT);
    Registry.register(Registry.SOUND_EVENT, HAMMERHIT, HAMMERHIT_EVENT);
  }

}