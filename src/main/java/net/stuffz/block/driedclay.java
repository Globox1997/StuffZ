package net.stuffz.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class driedclay extends Block {

  public driedclay(Settings settings) {
    super(settings);
  }

  @Override
  public void onSteppedOn(World world, BlockPos pos, Entity entity) {
    BlockPos water = new BlockPos(pos.getX(), pos.getY(), pos.getZ());
    if (world.getBlockState(water.north()).getBlock().equals(Blocks.WATER)
        || world.getBlockState(water.east()).getBlock().equals(Blocks.WATER)
        || world.getBlockState(water.south()).getBlock().equals(Blocks.WATER)
        || world.getBlockState(water.west()).getBlock().equals(Blocks.WATER)
        || world.getBlockState(water.north().east()).getBlock().equals(Blocks.WATER)
        || world.getBlockState(water.north().west()).getBlock().equals(Blocks.WATER)
        || world.getBlockState(water.south().east()).getBlock().equals(Blocks.WATER)
        || world.getBlockState(water.south().west()).getBlock().equals(Blocks.WATER) && !world.isClient) {
      world.removeBlock(pos, true);
      world.setBlockState(pos, Blocks.CLAY.getDefaultState(), 3);
    }
  }
}
