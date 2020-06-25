package net.stuffz.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.stuffz.main;
import net.stuffz.plants.ironbush;

public class ironbushfeature extends Feature<DefaultFeatureConfig> {

  public ironbushfeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(ServerWorldAccess serverWorldAccess, StructureAccessor accessor, ChunkGenerator generator,
      Random random, BlockPos pos, DefaultFeatureConfig config) {
    BlockPos bush1 = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    BlockPos bush2 = new BlockPos(pos.getX() + 2, pos.getY(), pos.getZ() - 1);
    if (serverWorldAccess.getBlockState(bush1).isAir() && serverWorldAccess.getBlockState(bush2).isAir()
        && serverWorldAccess.getBlockState(bush1.down()).getBlock().equals(Blocks.GRASS_BLOCK)
        && serverWorldAccess.getBlockState(bush1.down().south()).getBlock().equals(Blocks.GRASS_BLOCK)
        && serverWorldAccess.getBlockState(bush1.down().east()).getBlock().equals(Blocks.GRASS_BLOCK)
        && serverWorldAccess.getBlockState(bush1.down().north()).getBlock().equals(Blocks.GRASS_BLOCK)
        && serverWorldAccess.getBlockState(bush1.down().west()).getBlock().equals(Blocks.GRASS_BLOCK)
        && serverWorldAccess.getBlockState(bush1.south()).isAir()
        && serverWorldAccess.getBlockState(bush2.down().north(2)).getBlock().equals(Blocks.GRASS_BLOCK)
        && serverWorldAccess.getBlockState(bush2.down()).getBlock().equals(Blocks.GRASS_BLOCK)
        && serverWorldAccess.getBlockState(bush2.north()).isAir() && serverWorldAccess.getBlockState(bush1.up()).isAir()
        && serverWorldAccess.getBlockState(bush2.up()).isAir()) {
      serverWorldAccess.setBlockState(bush1, main.IRONBUSH.getDefaultState().with(ironbush.AGE, 3), 3);
      serverWorldAccess.setBlockState(bush2, main.IRONBUSH.getDefaultState().with(ironbush.AGE, 3), 3);
      return true;
    } else {
      return false;
    }
  }
}
