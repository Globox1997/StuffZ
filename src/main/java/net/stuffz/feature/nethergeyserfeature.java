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

public class nethergeyserfeature extends Feature<DefaultFeatureConfig> {

  public nethergeyserfeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(ServerWorldAccess serverWorldAccess, StructureAccessor accessor, ChunkGenerator generator,
      Random random, BlockPos pos, DefaultFeatureConfig config) {
    BlockPos test = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());
    if (serverWorldAccess.getBlockState(test).getBlock().equals(Blocks.NETHERRACK)
        && serverWorldAccess.getBlockState(test.down()).getBlock().equals(Blocks.NETHERRACK)
        && serverWorldAccess.getBlockState(test.south()).getBlock().equals(Blocks.NETHERRACK)
        && serverWorldAccess.getBlockState(test.east()).getBlock().equals(Blocks.NETHERRACK)
        && serverWorldAccess.getBlockState(test.north()).getBlock().equals(Blocks.NETHERRACK)
        && serverWorldAccess.getBlockState(test.west()).getBlock().equals(Blocks.NETHERRACK)
        && serverWorldAccess.getBlockState(test.up()).isAir()) {
      serverWorldAccess.setBlockState(test, BlockInit.NETHERGEYSERBLOCK.getDefaultState(), 3);
      return true;
    } else {
      return false;
    }
  }
}
