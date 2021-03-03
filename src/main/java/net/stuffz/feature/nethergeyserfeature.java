package net.stuffz.feature;

import java.util.Random;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.stuffz.init.BlockInit;
import net.stuffz.init.ConfigInit;

public class nethergeyserfeature extends Feature<DefaultFeatureConfig> {

  public nethergeyserfeature(Codec<DefaultFeatureConfig> codec) {
    super(codec);
  }

  @Override
  public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos,
      DefaultFeatureConfig config) {
    if (!ConfigInit.CONFIG.generate_geysers) {
      return false;
    }
    Boolean isNetherrack;
    Boolean isAir;
    for (int k = 0; k < 254; k++) {
      isNetherrack = world.getBlockState(pos.up(k)).getBlock().equals(Blocks.NETHERRACK);
      isAir = world.getBlockState(pos.up(k + 1)).isAir();
      if (isNetherrack && isAir) {
        int booleanCount = 0;
        for (int i = -1; i < 2; i++) {
          for (int u = -1; u < 2; u++) {
            isNetherrack = world.getBlockState(pos.north(i).east(u).up(k)).getBlock().equals(Blocks.NETHERRACK);
            isAir = world.getBlockState(pos.north(i).east(u).up(k + 1)).isAir();
            if (isNetherrack && isAir) {
              booleanCount++;
            }
            if (booleanCount == 9) {
              world.setBlockState(pos.up(k), BlockInit.NETHERGEYSERBLOCK.getDefaultState(), 3);
              return true;
            }
          }
        }
      }
    }
    return false;
  }
}
