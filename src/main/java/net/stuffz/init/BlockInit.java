package net.stuffz.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.stuffz.block.*;
import net.stuffz.plants.*;

public class BlockInit {

        public static final hop HOP = new hop(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly()
                        .breakInstantly().sounds(BlockSoundGroup.CROP));
        public static final malt MALT = new malt(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly()
                        .breakInstantly().sounds(BlockSoundGroup.CROP));
        public static final spelt SPELT = new spelt(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly()
                        .breakInstantly().sounds(BlockSoundGroup.CROP));
        public static final nutsbush NUTSBUSH = new nutsbush(FabricBlockSettings.of(Material.PLANT).noCollision()
                        .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
        public static final flax FLAX = new flax(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly()
                        .breakInstantly().sounds(BlockSoundGroup.CROP));
        public static final enderblock ENDERBLOCK = new enderblock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK));
        public static final adenderblock ADENDERBLOCK = new adenderblock(
                        FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK));
        public static final healblock HEALBLOCK = new healblock(FabricBlockSettings.copy(Blocks.OBSIDIAN));
        public static final nethergeyserblock NETHERGEYSERBLOCK = new nethergeyserblock(
                        FabricBlockSettings.copy(Blocks.STONE));
        public static final stonegeyserblock STONEGEYSERBLOCK = new stonegeyserblock(
                        FabricBlockSettings.copy(Blocks.STONE));
        public static final sulfurblock SULFURBLOCK = new sulfurblock(FabricBlockSettings.copy(Blocks.DIAMOND_ORE));
        public static final goldbush GOLDBUSH = new goldbush(FabricBlockSettings.of(Material.PLANT).noCollision()
                        .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
        public static final ironbush IRONBUSH = new ironbush(FabricBlockSettings.of(Material.PLANT).noCollision()
                        .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
        public static final driedclay DRIEDCLAY = new driedclay(FabricBlockSettings.copy(Blocks.CLAY));
        public static final uncraftblock UNCRAFTBLOCK = new uncraftblock(FabricBlockSettings.copy(Blocks.ANVIL));
        public static final BlockEntityType<uncraftblockentity> UNCRAFTBLOCKENTITY = BlockEntityType.Builder
                        .create(uncraftblockentity::new, UNCRAFTBLOCK).build(null);
        public static final yellowrubyblock YELLOWRUBYBLOCK = new yellowrubyblock(
                        FabricBlockSettings.copy(Blocks.DIAMOND_ORE));
        public static final fossilblock FOSSILBLOCK = new fossilblock(FabricBlockSettings.copy(Blocks.DIAMOND_ORE));
        public static final ironladder IRONLADDER = new ironladder(FabricBlockSettings.of(Material.SUPPORTED)
                        .strength(0.6F).sounds(BlockSoundGroup.LADDER).nonOpaque());

        public static void init() {
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "nutsbush"), NUTSBUSH);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "nutsbush"), new BlockItem(NUTSBUSH,
                                new Item.Settings().group(ItemGroup.MISC).food(FoodComponents.SWEET_BERRIES)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "spelt"), SPELT);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "spelt"),
                                new BlockItem(SPELT, new Item.Settings().group(ItemGroup.MISC)));

                Registry.register(Registry.ITEM, new Identifier("stuffz", "hop"),
                                new BlockItem(HOP, new Item.Settings().group(ItemGroup.MISC)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "hop"), HOP);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "malt"),
                                new BlockItem(MALT, new Item.Settings().group(ItemGroup.MISC)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "malt"), MALT);

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
                Registry.register(Registry.ITEM, new Identifier("stuffz", "sulfurblock"),
                                new BlockItem(SULFURBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "sulfurblock"), SULFURBLOCK);
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "goldbush"), GOLDBUSH);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "goldbush"),
                                new BlockItem(GOLDBUSH, new Item.Settings().group(ItemGroup.MISC)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "ironbush"), IRONBUSH);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "ironbush"),
                                new BlockItem(IRONBUSH, new Item.Settings().group(ItemGroup.MISC)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "driedclay"), DRIEDCLAY);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "driedclay"),
                                new BlockItem(DRIEDCLAY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
                Registry.register(Registry.ITEM, new Identifier("stuffz", "uncraftblock"),
                                new BlockItem(UNCRAFTBLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "uncraftblock"), UNCRAFTBLOCK);
                Registry.register(Registry.BLOCK_ENTITY_TYPE, "stuffz:uncraftblockentity", UNCRAFTBLOCKENTITY);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "yellowrubyblock"),
                                new BlockItem(YELLOWRUBYBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "yellowrubyblock"), YELLOWRUBYBLOCK);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "fossilblock"),
                                new BlockItem(FOSSILBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "fossilblock"), FOSSILBLOCK);
                Registry.register(Registry.ITEM, new Identifier("stuffz", "ironladder"),
                                new BlockItem(IRONLADDER, new Item.Settings().group(ItemGroup.DECORATIONS)));
                Registry.register(Registry.BLOCK, new Identifier("stuffz", "ironladder"), IRONLADDER);
        }

}