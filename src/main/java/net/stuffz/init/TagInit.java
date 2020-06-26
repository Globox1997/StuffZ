package net.stuffz.init;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagInit {

  public static final Tag<Item> UNCRAFT_ITEMS = TagRegistry.item(new Identifier("stuffz", "uncraft_items"));

  public static void init() {
  }

}