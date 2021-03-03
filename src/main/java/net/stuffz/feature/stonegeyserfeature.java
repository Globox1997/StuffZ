package net.stuffz.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.Heightmap;
import net.stuffz.init.BlockInit;
import net.stuffz.init.ConfigInit;

public class stonegeyserfeature extends Feature<DefaultFeatureConfig> {

  public stonegeyserfeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos,
      DefaultFeatureConfig config) {
    if (!ConfigInit.CONFIG.generate_geysers) {
      return false;
    }
    BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
    Boolean isAir;
    Boolean isGrassBlock;
    int booleanCount = 0;
    for (int i = -1; i < 2; i++) {
      for (int u = -1; u < 2; u++) {
        isGrassBlock = world.getBlockState(topPos.north(i).east(u).down()).getBlock().equals(Blocks.STONE);
        isAir = world.getBlockState(topPos.north(i).east(u)).isAir();
        if (isGrassBlock && isAir) {
          booleanCount++;
        }
        if (booleanCount == 9) {
          world.setBlockState(topPos.down(), BlockInit.STONEGEYSERBLOCK.getDefaultState(), 3);
          return true;
        }
      }
    }
    return false;
  }
}
