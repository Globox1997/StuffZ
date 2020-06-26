package net.stuffz.init;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.stuffz.drink.*;
import net.stuffz.food.*;
import net.stuffz.item.*;

public class ItemInit {

  public static final cactusfruit CACTUSFRUIT = new cactusfruit();
  public static final carrotpie CARROTPIE = new carrotpie();
  public static final chocolatebar CHOCOLATEBAR = new chocolatebar();
  public static final dough DOUGH = new dough(new Item.Settings().group(ItemGroup.MISC));
  public static final firestew FIRESTEW = new firestew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
  public static final melonstew MELONSTEW = new melonstew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
  public static final netherstew NETHERSTEW = new netherstew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
  public static final speltbread SPELTBREAD = new speltbread();
  public static final velvet VELVET = new velvet();
  public static final sulfur SULFUR = new sulfur(new Item.Settings().group(ItemGroup.MISC));

  public static final hops HOPS = new hops(new Item.Settings());
  public static final darkmalt DARKMALT = new darkmalt(new Item.Settings());
  public static final speltwheat SPELTWHEAT = new speltwheat(new Item.Settings().group(ItemGroup.MISC));
  public static final beer BEER = new beer(new Item.Settings().maxCount(1).group(ItemGroup.BREWING));
  public static final darkbeer DARKBEER = new darkbeer(new Item.Settings().maxCount(1).group(ItemGroup.BREWING));

  public static final tridentstick TRIDENTSTICK = new tridentstick(new Item.Settings().group(ItemGroup.MISC));
  public static final tridenttop TRIDENTTOP = new tridenttop(new Item.Settings().group(ItemGroup.MISC));
  public static final shinydiamond SHINYDIAMOND = new shinydiamond(new Item.Settings().group(ItemGroup.MISC));
  public static final chainmailplate CHAINMAILPLATE = new chainmailplate(new Item.Settings().group(ItemGroup.MISC));
  public static final yellowruby YELLOWRUBY = new yellowruby(new Item.Settings().group(ItemGroup.MISC));

  public static final ironhammer IRONHAMMER = new ironhammer(1F, -2.8F, ToolMaterials.IRON, ironhammer.EFFECTIVE_BLOCKS,
      new Item.Settings().group(ItemGroup.TOOLS).maxDamage(461));

  public static void init() {
    Registry.register(Registry.ITEM, new Identifier("stuffz", "tridentstick"), TRIDENTSTICK);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "tridenttop"), TRIDENTTOP);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "shinydiamond"), SHINYDIAMOND);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "speltbread"), SPELTBREAD);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "cactusfruit"), CACTUSFRUIT);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "carrotpie"), CARROTPIE);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "chocolatebar"), CHOCOLATEBAR);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "dough"), DOUGH);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "firestew"), FIRESTEW);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "melonstew"), MELONSTEW);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "netherstew"), NETHERSTEW);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "velvet"), VELVET);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "chainmailplate"), CHAINMAILPLATE);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "hops"), HOPS);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "beer"), BEER);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "darkbeer"), DARKBEER);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "sulfur"), SULFUR);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "ironhammer"), IRONHAMMER);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "yellowruby"), YELLOWRUBY);
  }

}