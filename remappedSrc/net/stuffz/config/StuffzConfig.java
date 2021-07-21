package net.stuffz.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "stuffz")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class StuffzConfig implements ConfigData {
  public boolean generate_bushes = true;
  public boolean generate_geysers = true;
  @Comment("Disabling ore generating needs a restart")
  public boolean generate_ores = true;
}