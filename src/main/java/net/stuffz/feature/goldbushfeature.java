package net.stuffz.feature;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.stuffz.main;

public class goldbushfeature extends Feature<DefaultFeatureConfig> {

  public goldbushfeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> config) {
    super(config);
  }

  @Override
  public boolean generate(IWorld world, StructureAccessor accessor,
      ChunkGenerator<? extends ChunkGeneratorConfig> generator, Random random, BlockPos pos,
      DefaultFeatureConfig config) {
    BlockPos bush1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    BlockPos bush2 = new BlockPos(bush1.getX() + 1, bush1.getY(), bush1.getZ() + 2);
    if (world.getBlockState(bush1).isAir() && world.getBlockState(bush2).isAir()
        && world.getBlockState(bush1.down()).getBlock().equals(Blocks.GRASS_BLOCK)
        && world.getBlockState(bush1.down().south()).getBlock().equals(Blocks.GRASS_BLOCK)
        && world.getBlockState(bush1.down().east()).getBlock().equals(Blocks.GRASS_BLOCK)
        && world.getBlockState(bush1.down().north()).getBlock().equals(Blocks.GRASS_BLOCK)
        && world.getBlockState(bush1.down().west()).getBlock().equals(Blocks.GRASS_BLOCK)
        && world.getBlockState(bush1.south()).isAir()
        && world.getBlockState(bush2.down().north(2)).getBlock().equals(Blocks.GRASS_BLOCK)
        && world.getBlockState(bush2.down()).getBlock().equals(Blocks.GRASS_BLOCK)
        && world.getBlockState(bush2.north()).isAir() && world.getBlockState(bush1.up()).isAir()
        && world.getBlockState(bush2.up()).isAir()) {
      world.setBlockState(bush1, main.GOLDBUSH.getDefaultState(), 3);
      world.setBlockState(bush2, main.GOLDBUSH.getDefaultState(), 3);
      return true;
    } else {
      return false;
    }
  }
}
