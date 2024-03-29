package net.stuffz.block;

import java.util.Queue;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.stuffz.init.BlockInit;

public class LavaSpongeBlock extends Block {

  public LavaSpongeBlock(Settings settings) {
    super(settings);
  }

  @Override
  public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
    if (!oldState.isOf(state.getBlock())) {
      this.update(world, pos);
    }
  }

  @Override
  public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos,
      boolean notify) {
    this.update(world, pos);
    super.neighborUpdate(state, world, pos, block, fromPos, notify);
  }

  protected void update(World world, BlockPos pos) {
    if (this.absorbLava(world, pos)) {
      if (!world.isClient) {
        world.setBlockState(pos, BlockInit.FULLLAVASPONGEBLOCK.getDefaultState(), 3);
      }
      world.syncWorldEvent(2001, pos, Block.getRawIdFromState(Blocks.LAVA.getDefaultState()));
    }

  }

  private boolean absorbLava(World world, BlockPos pos) {
    Queue<Pair<BlockPos, Integer>> queue = Lists.newLinkedList();
    queue.add(new Pair<>(pos, 0));
    int i = 0;

    while (!queue.isEmpty()) {
      Pair<BlockPos, Integer> pair = (Pair<BlockPos, Integer>) queue.poll();
      BlockPos blockPos = (BlockPos) pair.getLeft();
      int j = (Integer) pair.getRight();
      Direction[] var8 = Direction.values();
      int var9 = var8.length;

      for (int var10 = 0; var10 < var9; ++var10) {
        Direction direction = var8[var10];
        BlockPos blockPos2 = blockPos.offset(direction);
        BlockState blockState = world.getBlockState(blockPos2);
        FluidState fluidState = world.getFluidState(blockPos2);
        if (fluidState.isIn(FluidTags.LAVA)) {
          if (blockState.getBlock() instanceof FluidDrainable
              && ((FluidDrainable) blockState.getBlock()).tryDrainFluid(world, blockPos2, blockState) != Fluids.EMPTY) {
            ++i;
            if (j < 6) {
              queue.add(new Pair<>(blockPos2, j + 1));
            }
          } else if (blockState.getBlock() instanceof FluidBlock) {
            world.setBlockState(blockPos2, Blocks.AIR.getDefaultState(), 3);
            ++i;
            if (j < 6) {
              queue.add(new Pair<>(blockPos2, j + 1));
            }
          }
        }
      }

      if (i > 64) {
        break;
      }
    }

    return i > 0;
  }

}