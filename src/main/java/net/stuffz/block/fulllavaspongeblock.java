package net.stuffz.block;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.stuffz.init.BlockInit;

public class fulllavaspongeblock extends Block {

  public fulllavaspongeblock(Settings settings) {
    super(settings);
  }

  @Override
  public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
    if (world.getBiome(pos).getCategory().equals(Biome.Category.ICY)) {
      if (!world.isClient) {
        world.setBlockState(pos, BlockInit.LAVASPONGEBLOCK.getDefaultState(), 3);
      }
      world.syncWorldEvent(2009, pos, 0);
      world.playSound((PlayerEntity) null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 1.0F,
          (1.0F + world.getRandom().nextFloat() * 0.2F) * 0.7F);
    }

  }

  @Override
  @Environment(EnvType.CLIENT)
  public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
    Direction direction = Direction.random(random);
    if (direction != Direction.UP) {
      BlockPos blockPos = pos.offset(direction);
      BlockState blockState = world.getBlockState(blockPos);
      if (!state.isOpaque() || !blockState.isSideSolidFullSquare(world, blockPos, direction.getOpposite())) {
        double d = (double) pos.getX();
        double e = (double) pos.getY();
        double f = (double) pos.getZ();
        if (direction == Direction.DOWN) {
          e -= 0.05D;
          d += random.nextDouble();
          f += random.nextDouble();
        } else {
          e += random.nextDouble() * 0.8D;
          if (direction.getAxis() == Direction.Axis.X) {
            f += random.nextDouble();
            if (direction == Direction.EAST) {
              ++d;
            } else {
              d += 0.05D;
            }
          } else {
            d += random.nextDouble();
            if (direction == Direction.SOUTH) {
              ++f;
            } else {
              f += 0.05D;
            }
          }
        }

        world.addParticle(ParticleTypes.DRIPPING_LAVA, d, e, f, 0.0D, 0.0D, 0.0D);
      }
    }
  }

}