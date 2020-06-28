package net.stuffz.init;

import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.decorator.CountChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.GenerationStep;
import net.stuffz.feature.*;

public class FeatureInit {

        private static final Feature<DefaultFeatureConfig> NETHERGEYSER_FEATURE = Registry.register(Registry.FEATURE,
                        new Identifier("stuffz", "nethergeyserfeature"),
                        new nethergeyserfeature(DefaultFeatureConfig.CODEC));
        private static final Feature<DefaultFeatureConfig> STONEGEYSER_FEATURE = Registry.register(Registry.FEATURE,
                        new Identifier("stuffz", "stonegeyserfeature"),
                        new stonegeyserfeature(DefaultFeatureConfig.CODEC));
        private static final Feature<DefaultFeatureConfig> IRONBUSH_FEATURE = Registry.register(Registry.FEATURE,
                        new Identifier("stuffz", "ironbushfeature"), new ironbushfeature(DefaultFeatureConfig.CODEC));
        private static final Feature<DefaultFeatureConfig> GOLDBUSH_FEATURE = Registry.register(Registry.FEATURE,
                        new Identifier("stuffz", "goldbushfeature"), new goldbushfeature(DefaultFeatureConfig.CODEC));

        public void init() {

                Registry.BIOME.forEach(this::handleBiome);
                RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));

                Biomes.NETHER_WASTES.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                                NETHERGEYSER_FEATURE.configure(FeatureConfig.DEFAULT)
                                                .createDecoratedFeature(Decorator.COUNT_CHANCE_HEIGHTMAP
                                                                .configure(new CountChanceDecoratorConfig(3, 0.1F))));
                Biomes.GRAVELLY_MOUNTAINS.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                                STONEGEYSER_FEATURE.configure(FeatureConfig.DEFAULT)
                                                .createDecoratedFeature(Decorator.COUNT_CHANCE_HEIGHTMAP
                                                                .configure(new CountChanceDecoratorConfig(2, 0.1F))));
                Biomes.MOUNTAINS.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                                STONEGEYSER_FEATURE.configure(FeatureConfig.DEFAULT)
                                                .createDecoratedFeature(Decorator.COUNT_CHANCE_HEIGHTMAP
                                                                .configure(new CountChanceDecoratorConfig(2, 0.05F))));
        }

        private void handleBiome(Biome biome) {
                Biome.Category category = biome.getCategory();
                if (category == Biome.Category.EXTREME_HILLS || category == Biome.Category.SWAMP
                                || category == Biome.Category.OCEAN) {
                        biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE
                                        .configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE,
                                                        BlockInit.SULFURBLOCK.getDefaultState(), 5))
                                        .createDecoratedFeature(Decorator.COUNT_RANGE
                                                        .configure(new RangeDecoratorConfig(8, 0, 12, 58))));
                }
                if (category == Biome.Category.FOREST) {
                        biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                                        IRONBUSH_FEATURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                        Decorator.COUNT_CHANCE_HEIGHTMAP.configure(
                                                                        new CountChanceDecoratorConfig(2, 0.1F))));
                }
                if (category == Biome.Category.FOREST) {
                        biome.addFeature(GenerationStep.Feature.VEGETAL_DECORATION,
                                        GOLDBUSH_FEATURE.configure(FeatureConfig.DEFAULT).createDecoratedFeature(
                                                        Decorator.COUNT_CHANCE_HEIGHTMAP.configure(
                                                                        new CountChanceDecoratorConfig(2, 0.05F))));
                }
                if (category == Biome.Category.OCEAN) {
                        biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE
                                        .configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE,
                                                        BlockInit.FOSSILBLOCK.getDefaultState(), 1))
                                        .createDecoratedFeature(Decorator.COUNT_RANGE
                                                        .configure(new RangeDecoratorConfig(5, 0, 18, 58))));
                }
                if (category == Biome.Category.OCEAN || category == Biome.Category.ICY) {
                        biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES, Feature.ORE
                                        .configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE,
                                                        BlockInit.YELLOWRUBYORE.getDefaultState(), 1))
                                        .createDecoratedFeature(Decorator.COUNT_RANGE
                                                        .configure(new RangeDecoratorConfig(1, 0, 7, 58))));
                }
        }

}