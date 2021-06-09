package net.stuffz.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.stuffz.feature.GoldBushFeature;
import net.stuffz.feature.IronBushFeature;
import net.stuffz.feature.NetherGeyserFeature;
import net.stuffz.feature.StoneGeyserFeature;

public class FeatureInit {
        // Ore Gen


        public static ConfiguredFeature<?, ?> FOSSIL_ORE = Feature.ORE
                .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                        BlockInit.FOSSILBLOCK.getDefaultState(), 4)) // vein size
                .uniformRange(YOffset.fixed(0), YOffset.fixed(24)) // off set
                .spreadHorizontally().repeat(3); // number of veins per chunk

        public static ConfiguredFeature<?, ?> SULFUR_ORE = Feature.ORE
                        .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                                        BlockInit.SULFURBLOCK.getDefaultState(), 5))
                .uniformRange(YOffset.fixed(12), YOffset.fixed(32)).spreadHorizontally()
                        .repeat(5);
        public static ConfiguredFeature<?, ?> RUBY_ORE = Feature.ORE
                        .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                                        BlockInit.YELLOWRUBYORE.getDefaultState(), 1))
                .uniformRange(YOffset.fixed(2), YOffset.fixed(12)).spreadHorizontally()
                        .repeat(2);

        // Bushes
        private static final Feature<DefaultFeatureConfig> GOLD_BUSH = new GoldBushFeature(DefaultFeatureConfig.CODEC);
        public static final ConfiguredFeature<?, ?> GOLD_BUSH_CONFIGURED = GOLD_BUSH.configure(FeatureConfig.DEFAULT)
                        .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(20)));
        private static final Feature<DefaultFeatureConfig> IRON_BUSH = new IronBushFeature(DefaultFeatureConfig.CODEC);
        public static final ConfiguredFeature<?, ?> IRON_BUSH_CONFIGURED = IRON_BUSH.configure(FeatureConfig.DEFAULT)
                        .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(10)));

        // Geysers
        private static final Feature<DefaultFeatureConfig> STONE_GEYSER = new StoneGeyserFeature(
                        DefaultFeatureConfig.CODEC);
        public static final ConfiguredFeature<?, ?> STONE_GEYSER_CONFIGURED = STONE_GEYSER
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(8)));
        private static final Feature<DefaultFeatureConfig> NETHER_GEYSER = new NetherGeyserFeature(
                        DefaultFeatureConfig.CODEC);
        public static final ConfiguredFeature<?, ?> NETHER_GEYSER_CONFIGURED = NETHER_GEYSER
                        .configure(FeatureConfig.DEFAULT)
                        .decorate(Decorator.CHANCE.configure(new ChanceDecoratorConfig(16)));

        public static void init() {
                // Bushes
                Registry.register(Registry.FEATURE, new Identifier("stuffz", "gold_bush"), GOLD_BUSH);
                RegistryKey<ConfiguredFeature<?, ?>> goldBush = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                                new Identifier("stuffz", "gold_bush"));
                Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, goldBush.getValue(), GOLD_BUSH_CONFIGURED);
                BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.FOREST),
                                GenerationStep.Feature.RAW_GENERATION, goldBush);

                Registry.register(Registry.FEATURE, new Identifier("stuffz", "iron_bush"), IRON_BUSH);
                RegistryKey<ConfiguredFeature<?, ?>> ironBush = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                                new Identifier("stuffz", "iron_bush"));
                Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, ironBush.getValue(), IRON_BUSH_CONFIGURED);
                BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.FOREST),
                                GenerationStep.Feature.RAW_GENERATION, ironBush);
                // Geysers
                Registry.register(Registry.FEATURE, new Identifier("stuffz", "stone_geyser"), STONE_GEYSER);
                RegistryKey<ConfiguredFeature<?, ?>> stoneGeyser = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                                new Identifier("stuffz", "stone_geyser"));
                Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, stoneGeyser.getValue(),
                                STONE_GEYSER_CONFIGURED);
                BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.EXTREME_HILLS),
                                GenerationStep.Feature.RAW_GENERATION, stoneGeyser);
                Registry.register(Registry.FEATURE, new Identifier("stuffz", "nether_geyser"), NETHER_GEYSER);
                RegistryKey<ConfiguredFeature<?, ?>> netherGeyser = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                                new Identifier("stuffz", "nether_geyser"));
                Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, netherGeyser.getValue(),
                                NETHER_GEYSER_CONFIGURED);
                BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.NETHER),
                                GenerationStep.Feature.VEGETAL_DECORATION, netherGeyser);

                // Ore Gen
                if (ConfigInit.CONFIG.generate_ores) {
                        RegistryKey<ConfiguredFeature<?, ?>> fossilOre = RegistryKey.of(
                                        Registry.CONFIGURED_FEATURE_KEY, new Identifier("stuffz", "fossil_ore"));
                        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, fossilOre.getValue(), FOSSIL_ORE);
                        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.OCEAN),
                                        GenerationStep.Feature.UNDERGROUND_ORES, fossilOre);
                        RegistryKey<ConfiguredFeature<?, ?>> sulfurOre = RegistryKey.of(
                                        Registry.CONFIGURED_FEATURE_KEY, new Identifier("stuffz", "sulfur_ore"));
                        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, sulfurOre.getValue(), SULFUR_ORE);
                        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP),
                                        GenerationStep.Feature.UNDERGROUND_ORES, sulfurOre);
                        RegistryKey<ConfiguredFeature<?, ?>> rubyOre = RegistryKey
                                        .of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("stuffz", "ruby_ore"));
                        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, rubyOre.getValue(), RUBY_ORE);
                        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.OCEAN),
                                        GenerationStep.Feature.UNDERGROUND_ORES, rubyOre);
                }
        }
}