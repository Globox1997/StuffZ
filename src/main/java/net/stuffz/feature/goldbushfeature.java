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
import net.stuffz.plants.goldbush;

public class goldbushfeature extends Feature<DefaultFeatureConfig> {

  public goldbushfeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos,
      DefaultFeatureConfig config) {
    BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
    Boolean isAirOrGrass;
    Boolean isGrassBlock;
    int booleanCount = 0;
    for (int i = -1; i < 2; i++) {
      for (int u = -1; u < 2; u++) {
        isGrassBlock = world.getBlockState(topPos.north(i).east(u).down()).getBlock().equals(Blocks.GRASS_BLOCK);
        isAirOrGrass = world.getBlockState(topPos.north(i).east(u)).isAir()
            || world.getBlockState(topPos.north(i).east(u)).getBlock().equals(Blocks.GRASS);
        if (isGrassBlock && isAirOrGrass) {
          booleanCount++;
        }
        if (booleanCount == 9) {
          int randomInt = world.getRandom().nextInt(3) + 1;
          for (int k = 0; k < randomInt; k++) {
            int randomIntEast = world.getRandom().nextInt(3) - 1;
            int randomIntSouth = world.getRandom().nextInt(3) - 1;
            world.setBlockState(topPos.east(randomIntEast).south(randomIntSouth),
                BlockInit.GOLDBUSH.getDefaultState().with(goldbush.AGE, 3), 3);
          }
          return true;
        }
      }
    }
    return false;
  }
}