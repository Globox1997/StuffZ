package net.stuffz;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Items;
import net.minecraft.loot.BinomialLootTableRange;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;

public class looter {

    public static final Identifier[] LONE = new Identifier[] { LootTables.VILLAGE_FISHER_CHEST,
            LootTables.VILLAGE_SAVANNA_HOUSE_CHEST };

    public static final Identifier[] LTWO = new Identifier[] { LootTables.ABANDONED_MINESHAFT_CHEST,
            LootTables.DESERT_PYRAMID_CHEST, LootTables.JUNGLE_TEMPLE_CHEST, LootTables.PILLAGER_OUTPOST_CHEST,
            LootTables.SIMPLE_DUNGEON_CHEST, LootTables.STRONGHOLD_CROSSING_CHEST };

    public static final Identifier[] LTHREE = new Identifier[] { LootTables.SIMPLE_DUNGEON_CHEST,
            LootTables.WOODLAND_MANSION_CHEST, LootTables.VILLAGE_BUTCHER_CHEST, LootTables.VILLAGE_SHEPARD_CHEST };

    public static final Identifier[] LFOUR = new Identifier[] { LootTables.VILLAGE_PLAINS_CHEST,
            LootTables.IGLOO_CHEST_CHEST, LootTables.VILLAGE_SNOWY_HOUSE_CHEST, LootTables.SHIPWRECK_SUPPLY_CHEST,
            LootTables.VILLAGE_TAIGA_HOUSE_CHEST, LootTables.VILLAGE_WEAPONSMITH_CHEST };

    public static final Identifier[] LFIVE = new Identifier[] { LootTables.NETHER_BRIDGE_CHEST };

    private static boolean isone(Identifier lootTable) {
        for (Identifier id : LONE) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    private static boolean istwo(Identifier lootTable) {
        for (Identifier id : LTWO) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isthree(Identifier lootTable) {
        for (Identifier id : LTHREE) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isfour(Identifier lootTable) {
        for (Identifier id : LFOUR) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isfive(Identifier lootTable) {
        for (Identifier id : LFIVE) {
            if (id.equals(lootTable)) {
                return true;
            }
        }
        return false;
    }

    public static void init() {

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:blocks/grass".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(BinomialLootTableRange.create(1, 0.01f)).withEntry(ItemEntry.builder(main.HOP));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:blocks/grass".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(BinomialLootTableRange.create(1, 0.01f)).withEntry(ItemEntry.builder(main.MALT));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:blocks/grass".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(BinomialLootTableRange.create(1, 0.01f)).withEntry(ItemEntry.builder(main.SPELT));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:blocks/sand".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(BinomialLootTableRange.create(1, 0.001f))
                        .withEntry(ItemEntry.builder(Items.GOLD_NUGGET));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:blocks/red_sand".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(BinomialLootTableRange.create(1, 0.001f))
                        .withEntry(ItemEntry.builder(Items.GOLD_NUGGET));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:blocks/gravel".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(BinomialLootTableRange.create(1, 0.001f))
                        .withEntry(ItemEntry.builder(Items.IRON_NUGGET));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:blocks/grass".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(BinomialLootTableRange.create(1, 0.01f)).withEntry(ItemEntry.builder(main.NUTSBUSH));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ("minecraft:blocks/grass".equals(id.toString())) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(BinomialLootTableRange.create(1, 0.01f)).withEntry(ItemEntry.builder(main.FLAX));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isone(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(3, 0.3f)).withEntry(ItemEntry.builder(main.MALT));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isone(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(5, 0.3f)).withEntry(ItemEntry.builder(main.FLAX));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isone(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(3, 0.3f)).withEntry(ItemEntry.builder(main.HOP));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isone(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(5, 0.5f)).withEntry(ItemEntry.builder(main.SPELT));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (istwo(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(1, 0.02f))
                        .withEntry(ItemEntry.builder(main.SHINYDIAMOND));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (istwo(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(5, 0.15f))
                        .withEntry(ItemEntry.builder(main.CHAINMAILPLATE));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isthree(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(4, 0.4f)).withEntry(ItemEntry.builder(main.SPELTWHEAT));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isfour(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(1, 0.2f)).withEntry(ItemEntry.builder(main.CARROTPIE));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isfour(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(4, 0.35f)).withEntry(ItemEntry.builder(main.SPELTBREAD));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isfour(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(1, 0.1f)).withEntry(ItemEntry.builder(main.VELVET));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isfour(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(1, 0.3f)).withEntry(ItemEntry.builder(main.MELONSTEW));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isfive(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(1, 0.2f)).withEntry(ItemEntry.builder(main.FIRESTEW));

                supplier.withPool(poolBuilder);
            }
        });

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if (isfive(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .withRolls(new BinomialLootTableRange(1, 0.2f)).withEntry(ItemEntry.builder(main.NETHERSTEW));

                supplier.withPool(poolBuilder);
            }
        });
    }
}