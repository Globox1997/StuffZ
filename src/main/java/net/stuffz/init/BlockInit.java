package net.stuffz.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
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
import net.stuffz.block.entity.*;
import net.stuffz.plants.*;

public class BlockInit {

    public static final Hop HOP = new Hop(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final Spelt SPELT = new Spelt(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final NutsBush NUTSBUSH = new NutsBush(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final Flax FLAX = new Flax(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final EnderBlock ENDERBLOCK = new EnderBlock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK));
    public static final AdenderBlock ADENDERBLOCK = new AdenderBlock(FabricBlockSettings.copy(Blocks.REDSTONE_BLOCK));
    public static final HealBlock HEALBLOCK = new HealBlock(FabricBlockSettings.copy(Blocks.OBSIDIAN));
    public static final NetherGeyserBlock NETHERGEYSERBLOCK = new NetherGeyserBlock(FabricBlockSettings.copy(Blocks.STONE));
    public static final StoneGeyserBlock STONEGEYSERBLOCK = new StoneGeyserBlock(FabricBlockSettings.copy(Blocks.STONE));
    public static final SulfurBlock SULFURBLOCK = new SulfurBlock(FabricBlockSettings.copy(Blocks.DIAMOND_ORE));
    public static final GoldBush GOLDBUSH = new GoldBush(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final IronBush IRONBUSH = new IronBush(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final DriedClay DRIEDCLAY = new DriedClay(FabricBlockSettings.copy(Blocks.CLAY));
    public static final UncraftBlock UNCRAFTBLOCK = new UncraftBlock(FabricBlockSettings.copy(Blocks.ANVIL));
    public static final BlockEntityType<UncraftBlockEntity> UNCRAFTBLOCKENTITY = FabricBlockEntityTypeBuilder.create(UncraftBlockEntity::new, UNCRAFTBLOCK).build(null);
    public static final YellowRubyBlock YELLOWRUBYBLOCK = new YellowRubyBlock(FabricBlockSettings.copy(Blocks.DIAMOND_ORE));
    public static final FossilBlock FOSSILBLOCK = new FossilBlock(FabricBlockSettings.copy(Blocks.DIAMOND_ORE));
    public static final IronLadder IRONLADDER = new IronLadder(FabricBlockSettings.of(Material.DECORATION).strength(0.6F).sounds(SoundInit.IRONLADDER_GROUP).nonOpaque());
    public static final BrewingBarrel BREWINGBARREL = new BrewingBarrel(FabricBlockSettings.of(Material.WOOD).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final BlockEntityType<BrewingBarrelEntity> BREWINGBARRELENTITY = FabricBlockEntityTypeBuilder.create(BrewingBarrelEntity::new, BREWINGBARREL).build(null);
    public static final YellowRubyOre YELLOWRUBYORE = new YellowRubyOre(FabricBlockSettings.copy(Blocks.DIAMOND_BLOCK));
    public static final LavaSpongeBlock LAVASPONGEBLOCK = new LavaSpongeBlock(FabricBlockSettings.copy(Blocks.STONE));
    public static final FullLavaSpongeBlock FULLLAVASPONGEBLOCK = new FullLavaSpongeBlock(FabricBlockSettings.copy(Blocks.STONE));

    public static void init() {
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "nutsbush"), NUTSBUSH);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "nutsbush"), new BlockItem(NUTSBUSH, new Item.Settings().group(ItemGroup.MISC).food(FoodComponents.SWEET_BERRIES)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "spelt"), SPELT);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "spelt"), new BlockItem(SPELT, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("stuffz", "hop"), new BlockItem(HOP, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "hop"), HOP);

        Registry.register(Registry.ITEM, new Identifier("stuffz", "flax"), new BlockItem(FLAX, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "flax"), FLAX);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "enderblock"), new BlockItem(ENDERBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "enderblock"), ENDERBLOCK);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "adenderblock"), new BlockItem(ADENDERBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "adenderblock"), ADENDERBLOCK);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "healblock"), new BlockItem(HEALBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "healblock"), HEALBLOCK);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "nethergeyserblock"), new BlockItem(NETHERGEYSERBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "nethergeyserblock"), NETHERGEYSERBLOCK);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "stonegeyserblock"), new BlockItem(STONEGEYSERBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "stonegeyserblock"), STONEGEYSERBLOCK);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "sulfurblock"), new BlockItem(SULFURBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "sulfurblock"), SULFURBLOCK);
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "goldbush"), GOLDBUSH);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "goldbush"), new BlockItem(GOLDBUSH, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "ironbush"), IRONBUSH);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "ironbush"), new BlockItem(IRONBUSH, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "driedclay"), DRIEDCLAY);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "driedclay"), new BlockItem(DRIEDCLAY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("stuffz", "uncraftblock"), new BlockItem(UNCRAFTBLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "uncraftblock"), UNCRAFTBLOCK);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, "stuffz:uncraftblockentity", UNCRAFTBLOCKENTITY);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "yellowrubyblock"), new BlockItem(YELLOWRUBYBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "yellowrubyblock"), YELLOWRUBYBLOCK);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "fossilblock"), new BlockItem(FOSSILBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "fossilblock"), FOSSILBLOCK);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "ironladder"), new BlockItem(IRONLADDER, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "ironladder"), IRONLADDER);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "brewingbarrel"), new BlockItem(BREWINGBARREL, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "brewingbarrel"), BREWINGBARREL);
        Registry.register(Registry.BLOCK_ENTITY_TYPE, "stuffz:brewingbarrelentity", BREWINGBARRELENTITY);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "yellowrubyore"), new BlockItem(YELLOWRUBYORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "yellowrubyore"), YELLOWRUBYORE);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "lavaspongeblock"), new BlockItem(LAVASPONGEBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "lavaspongeblock"), LAVASPONGEBLOCK);
        Registry.register(Registry.ITEM, new Identifier("stuffz", "fulllavaspongeblock"), new BlockItem(FULLLAVASPONGEBLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("stuffz", "fulllavaspongeblock"), FULLLAVASPONGEBLOCK);
    }

}