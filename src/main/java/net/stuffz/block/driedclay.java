package net.stuffz.block;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class DriedClay extends Block {

  public DriedClay(Settings settings) {
    super(settings);
  }

  @Override
  @Environment(EnvType.CLIENT)
  public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
    tooltip.add(new TranslatableText("item.stuffz.moreinfo.tooltip"));
    if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340)) {
      tooltip.remove(new TranslatableText("item.stuffz.moreinfo.tooltip"));
      tooltip.add(new TranslatableText("block.stuffz.driedclay.tooltip"));
    }
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
