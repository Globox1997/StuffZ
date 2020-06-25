package net.stuffz.tag;

import net.minecraft.data.server.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tag.EntityTypeTags;
import net.minecraft.tag.GlobalTagAccessor;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.tag.Tag.Identified;
import net.minecraft.util.Identifier;
import net.stuffz.main;
import net.minecraft.tag.EntityTypeTags;

public class itemtags {
  // public static final Tag.Identified<Item> UNCRAFT_ITEMS = register(new
  // Identifier("stuffz","uncraft_items"));
  // public static final Tag.Identified<Item> UNCRAFT_ITEMS = register(new
  // Identifier("stuffz", "uncraft_items"));
  public static final Tag<Item> UNCRAFT_ITEMS = register(new Identifier("stuffz", "uncraft_items"));

  public static void init() {
    
  }
  // private static Identified<Item> register(String string) {
  // // new itemtags();
  // // return new Identified<Item>(main.id(string));
  // return UNCRAFT_ITEMS.get(string);
  // //return new EntityTypeTags.CachingTag(Identity.id(id));
  // }

  private static Tag<Item> register(Identifier identifier) {
    return UNCRAFT_ITEMS;
  }

  // private static Tag<Item> register(Identifier identifier) {
  // return UNCRAFT_ITEMS;
  // }

  // private static Identified<Item> register(Identifier identifier) {
  // new itemtags();
  // return UNCRAFT_ITEMS;
  // }

}