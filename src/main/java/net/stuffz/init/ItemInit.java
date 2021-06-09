package net.stuffz.init;

import net.minecraft.block.ComposterBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.stuffz.drink.*;
import net.stuffz.food.*;
import net.stuffz.item.*;

public class ItemInit {

  public static final CactusFruit CACTUSFRUIT = new CactusFruit();
  public static final CarrotPie CARROTPIE = new CarrotPie();
  public static final ChocolateBar CHOCOLATEBAR = new ChocolateBar();
  public static final Dough DOUGH = new Dough(new Item.Settings().group(ItemGroup.MISC));
  public static final FireStew FIRESTEW = new FireStew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
  public static final MelonStew MELONSTEW = new MelonStew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
  public static final NetherStew NETHERSTEW = new NetherStew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
  public static final SpeltBread SPELTBREAD = new SpeltBread();
  public static final Velvet VELVET = new Velvet();
  public static final Sulfur SULFUR = new Sulfur(new Item.Settings().group(ItemGroup.MISC));

  public static final Hops HOPS = new Hops(new Item.Settings().group(ItemGroup.MISC));
  public static final DarkMalt DARKMALT = new DarkMalt(new Item.Settings().group(ItemGroup.MISC));
  public static final Malt MALT = new Malt(new Item.Settings().group(ItemGroup.MISC));
  public static final SpeltWheat SPELTWHEAT = new SpeltWheat(new Item.Settings().group(ItemGroup.MISC));
  public static final Beer BEER = new Beer(new Item.Settings().maxCount(1).group(ItemGroup.BREWING));
  public static final DarkBeer DARKBEER = new DarkBeer(new Item.Settings().maxCount(1).group(ItemGroup.BREWING));

  public static final TridentStick TRIDENTSTICK = new TridentStick(new Item.Settings().group(ItemGroup.MISC));
  public static final TridentTop TRIDENTTOP = new TridentTop(new Item.Settings().group(ItemGroup.MISC));
  public static final ShinyDiamond SHINYDIAMOND = new ShinyDiamond(new Item.Settings().group(ItemGroup.MISC));
  public static final ChainmailPlate CHAINMAILPLATE = new ChainmailPlate(new Item.Settings().group(ItemGroup.MISC));
  public static final YellowRuby YELLOWRUBY = new YellowRuby(new Item.Settings().group(ItemGroup.MISC));
  public static final BedrockRemover BEDROCKREMOVER = new BedrockRemover(new Item.Settings().group(ItemGroup.TOOLS));

  public static final IronHammer IRONHAMMER = new IronHammer(1F, -2.8F, ToolMaterials.IRON, BlockTags.ANVIL,
      new Item.Settings().group(ItemGroup.TOOLS).maxDamage(461));

  public static void registerCompostableItem(ItemConvertible item, float chance) {
    if (item.asItem() != Items.AIR) {
      ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(item.asItem(), chance);
    }
  }

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
    Registry.register(Registry.ITEM, new Identifier("stuffz", "speltwheat"), SPELTWHEAT);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "darkmalt"), DARKMALT);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "bedrockremover"), BEDROCKREMOVER);
    Registry.register(Registry.ITEM, new Identifier("stuffz", "malt"), MALT);

    registerCompostableItem(BlockInit.FLAX, 0.3F);
    registerCompostableItem(BlockInit.NUTSBUSH, 0.3F);
    registerCompostableItem(BlockInit.SPELT, 0.3F);
    registerCompostableItem(BlockInit.HOP, 0.3F);
    registerCompostableItem(BlockInit.GOLDBUSH, 0.85F);
    registerCompostableItem(BlockInit.IRONBUSH, 0.65F);
    registerCompostableItem(ItemInit.DOUGH, 0.3F);
    registerCompostableItem(ItemInit.HOPS, 0.3F);
    registerCompostableItem(ItemInit.DOUGH, 0.3F);
    registerCompostableItem(ItemInit.CACTUSFRUIT, 0.3F);
    registerCompostableItem(ItemInit.SPELTWHEAT, 0.65F);
    registerCompostableItem(ItemInit.DARKMALT, 0.65F);
    registerCompostableItem(ItemInit.VELVET, 0.85F);
    registerCompostableItem(ItemInit.SPELTBREAD, 0.85F);
    registerCompostableItem(ItemInit.CARROTPIE, 0.85F);
    registerCompostableItem(ItemInit.CHOCOLATEBAR, 0.85F);
    registerCompostableItem(ItemInit.MALT, 0.3F);
  }

}