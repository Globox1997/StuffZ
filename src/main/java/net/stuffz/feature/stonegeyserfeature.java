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
import net.stuffz.init.BlockInit;

public class stonegeyserfeature extends Feature<DefaultFeatureConfig> {

  public stonegeyserfeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(ServerWorldAccess serverWorldAccess, StructureAccessor accessor, ChunkGenerator generator,
      Random random, BlockPos pos, DefaultFeatureConfig config) {
    BlockPos test = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
    if (serverWorldAccess.getBlockState(test).getBlock().equals(Blocks.STONE)
        && serverWorldAccess.getBlockState(test.down()).getBlock().equals(Blocks.STONE)
        && serverWorldAccess.getBlockState(test.south()).getBlock().equals(Blocks.STONE)
        && serverWorldAccess.getBlockState(test.east()).getBlock().equals(Blocks.STONE)
        && serverWorldAccess.getBlockState(test.north()).getBlock().equals(Blocks.STONE)
        && serverWorldAccess.getBlockState(test.west()).getBlock().equals(Blocks.STONE)
        && serverWorldAccess.getBlockState(test.up()).isAir()) {
      serverWorldAccess.setBlockState(test, BlockInit.STONEGEYSERBLOCK.getDefaultState(), 3);
      return true;
    } else {
      return false;
    }
  }
}
