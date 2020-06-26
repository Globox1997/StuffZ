package net.stuffz.init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SoundInit {

  public static final Identifier GEYSER = new Identifier("stuffz:geyser");
  public static SoundEvent GEYSEREVENT = new SoundEvent(GEYSER);

  public static void init() {
    Registry.register(Registry.SOUND_EVENT, GEYSER, GEYSEREVENT);
  }

}