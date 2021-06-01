package net.stuffz.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;

@Config(name = "stuffz")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class StuffzConfig implements ConfigData {
  public boolean generate_bushes = true;
  public boolean generate_geysers = true;
  @Comment("Disabling ore generating needs a restart")
  public boolean generate_ores = true;
}