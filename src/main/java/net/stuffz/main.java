package net.stuffz;

import net.stuffz.food.*;
import net.stuffz.item.*;
import net.stuffz.plants.*;
import net.stuffz.drink.*;
import net.stuffz.feature.*;
import net.stuffz.block.*;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.GenerationStep;

public class main implements ModInitializer {

      public static final cactusfruit CACTUSFRUIT = new cactusfruit();
      public static final carrotpie CARROTPIE = new carrotpie();
      public static final chocolatebar CHOCOLATEBAR = new chocolatebar();
      public static final dough DOUGH = new dough(new Item.Settings().group(ItemGroup.MISC));
      public static final firestew FIRESTEW = new firestew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
      public static final melonstew MELONSTEW = new melonstew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
      public static final netherstew NETHERSTEW = new netherstew(new Item.Settings().maxCount(1).group(ItemGroup.FOOD));
      public static final speltbread SPELTBREAD = new speltbread();
      public static final velvet VELVET = new velvet();

      public static final hop HOP = new hop(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly()
                  .breakInstantly().sounds(BlockSoundGroup.CROP).build());
      public static final hops HOPS = new hops(new Item.Settings());
      public static final malt MALT = new malt(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly()
                  .breakInstantly().sounds(BlockSoundGroup.CROP).build());
      public static final darkmalt DARKMALT = new darkmalt(new Item.Settings());
      public static final speltwheat SPELTWHEAT = new speltwheat(new Item.Settings().group(ItemGroup.MISC));
      public static final beer BEER = new beer(new Item.Settings().maxCount(1).group(ItemGroup.BREWING));
      public static final darkbeer DARKBEER = new darkbeer(new Item.Settings().maxCount(1).group(ItemGroup.BREWING));
      public static final spelt SPELT = new spelt(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly()
                  .breakInstantly().sounds(BlockSoundGroup.CROP).build());
      public static final nutsbush NUTSBUSH = new nutsbush(FabricBlockSettings.of(Material.PLANT).noCollision()
                  .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).build());
      public static final flax FLAX = new flax(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly()
                  .breakInstantly().sounds(BlockSoundGroup.CROP).build());
      public static final enderblock ENDERBLOCK = new enderblock(
                  FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK).build());
      public static final adenderblock ADENDERBLOCK = new adenderblock(
                  FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK).build());
      public static final healblock HEALBLOCK = new healblock(FabricBlockSettings.copy(Blocks.OBSIDIAN).build());
      public static final nethergeyserblock NETHERGEYSERBLOCK = new nethergeyserblock(
                  FabricBlockSettings.copy(Blocks.STONE).build());
      public static final stonegeyserblock STONEGEYSERBLOCK = new stonegeyserblock(
                  FabricBlockSettings.copy(Blocks.STONE).build());

      public static final tridentstick TRIDENTSTICK = new tridentstick(new Item.Settings().group(ItemGroup.MISC));
      public static final tridenttop TRIDENTTOP = new tridenttop(new Item.Settings().group(ItemGroup.MISC));
      public static final shinydiamond SHINYDIAMOND = new shinydiamond(new Item.Settings().group(ItemGroup.MISC));
      public static final chainmailplate CHAINMAILPLATE = new chainmailplate(new Item.Settings().group(ItemGroup.MISC));

      public static final Identifier GEYSER = new Identifier("stuffz:geyser");
      public static SoundEvent GEYSEREVENT = new SoundEvent(GEYSER);

      private static final Feature<DefaultFeatureConfig> NETHERGEYSER = Registry.register(Registry.FEATURE,
                  new Identifier("stuffz", "nethergeyser"), new nethergeyserfeature(DefaultFeatureConfig::deserialize));
      private static final Feature<DefaultFeatureConfig> STONEGEYSER = Registry.register(Registry.FEATURE,
                  new Identifier("stuffz", "stonegeyser"), new stonegeyserfeature(DefaultFeatureConfig::deserialize));

      @Override
      public void onInitialize() {

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
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "nutsbush"), NUTSBUSH);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "nutsbush"),
                        new BlockItem(NUTSBUSH, new Item.Settings().group(ItemGroup.MISC).food(
                                    (new FoodComponent.Builder()).hunger(2).saturationModifier(0.3F).meat().build())));
            Registry.register(Registry.ITEM, new Identifier("stuffz", "hops"), HOPS);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "beer"), BEER);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "darkbeer"), DARKBEER);

            Registry.register(Registry.BLOCK, new Identifier("stuffz", "spelt"), SPELT);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "spelt"),
                        new BlockItem(SPELT, new Item.Settings().group(ItemGroup.MISC)));
            Registry.register(Registry.ITEM, new Identifier("stuffz", "speltwheat"), SPELTWHEAT);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "hop"),
                        new BlockItem(HOP, new Item.Settings().group(ItemGroup.MISC)));
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "hop"), HOP);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "malt"),
                        new BlockItem(MALT, new Item.Settings().group(ItemGroup.MISC)));
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "malt"), MALT);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "darkmalt"), DARKMALT);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "flax"),
                        new BlockItem(FLAX, new Item.Settings().group(ItemGroup.MISC)));
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "flax"), FLAX);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "enderblock"),
                        new BlockItem(ENDERBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "enderblock"), ENDERBLOCK);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "adenderblock"),
                        new BlockItem(ADENDERBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "adenderblock"), ADENDERBLOCK);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "healblock"),
                        new BlockItem(HEALBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "healblock"), HEALBLOCK);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "nethergeyserblock"),
                        new BlockItem(NETHERGEYSERBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "nethergeyserblock"), NETHERGEYSERBLOCK);
            Registry.register(Registry.ITEM, new Identifier("stuffz", "stonegeyserblock"),
                        new BlockItem(STONEGEYSERBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.register(Registry.BLOCK, new Identifier("stuffz", "stonegeyserblock"), STONEGEYSERBLOCK);

            Registry.register(Registry.SOUND_EVENT, main.GEYSER, GEYSEREVENT);

            looter.init();

            Biomes.NETHER_WASTES.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        NETHERGEYSER.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(100, 10, 0, 128))));
            Biomes.GRAVELLY_MOUNTAINS.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        STONEGEYSER.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(40, 50, 0, 200))));
            Biomes.MOUNTAINS.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                        STONEGEYSER.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(40, 50, 0, 200))));

      }

}

// You are LOVED!!!
// Jesus loves you unconditionaly!