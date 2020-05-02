package net.stuffz.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.stuffz.main;

public class sulfurblock extends Block {
  public int a = 0;

  public sulfurblock(Settings settings) {
    super(settings);
  }

  protected int getExperienceWhenMined(Random random) {
    return MathHelper.nextInt(random, 2, 5);
  }

  public void onStacksDropped(BlockState state, World world, BlockPos pos, ItemStack stack) {
    super.onStacksDropped(state, world, pos, stack);
    ItemStack sulfurdrop = new ItemStack(main.SULFUR, 2);
    ItemStack sulfurblockdrop = new ItemStack(main.SULFURBLOCK);
    if (!world.isClient) {
      if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
        int i = this.getExperienceWhenMined(world.random);
        if (i > 0) {
          this.dropExperience(world, pos, i);
          Block.dropStack(world, pos, sulfurdrop);

          if (EnchantmentHelper.getLevel(Enchantments.FORTUNE, stack) != 0) {
            Block.dropStack(world, pos, sulfurdrop);
          }
        }
      } else {
        Block.dropStack(world, pos, sulfurblockdrop);
      }
    }
  }
}
