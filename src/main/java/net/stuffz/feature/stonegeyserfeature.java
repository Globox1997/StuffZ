package net.stuffz.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.stuffz.main;

public class stonegeyserfeature extends Feature<DefaultFeatureConfig> {

    public stonegeyserfeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> config) {
        super(config);
    }

    @Override
    public boolean generate(IWorld world, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random,
            BlockPos pos, DefaultFeatureConfig config) {
        BlockPos test = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
        if (world.getBlockState(test).getBlock().equals(Blocks.STONE)
                && world.getBlockState(test.down()).getBlock().equals(Blocks.STONE)
                && world.getBlockState(test.south()).getBlock().equals(Blocks.STONE)
                && world.getBlockState(test.east()).getBlock().equals(Blocks.STONE)
                && world.getBlockState(test.north()).getBlock().equals(Blocks.STONE)
                && world.getBlockState(test.west()).getBlock().equals(Blocks.STONE)
                && world.getBlockState(test.up()).isAir()) {
            world.setBlockState(test, main.STONEGEYSERBLOCK.getDefaultState(), 3);
            return true;
        } else {
            return false;
        }

    }
}
