package net.stuffz.plants;

import javax.swing.text.html.BlockView;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stuffz.main;

public class goldbush extends SweetBerryBushBlock {

  public goldbush(Settings settings) {
    super(settings);
  }

  @Environment(EnvType.CLIENT)
  public ItemStack getPickStack(BlockView blockView_1, BlockPos blockPos_1, BlockState blockState_1) {
    return new ItemStack(main.GOLDBUSH);
  }

  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
      BlockHitResult hit) {
    int i = (Integer) state.get(AGE);
    boolean bl = i == 3;
    if (!bl && player.getStackInHand(hand).getItem() == Items.BONE_MEAL) {
      return ActionResult.PASS;
    } else if (i > 1) {
      int j = 1 + world.random.nextInt(2);
      dropStack(world, pos, new ItemStack(Items.GOLD_NUGGET, j + (bl ? 1 : 0)));
      world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS,
          1.0F, 0.8F + world.random.nextFloat() * 0.4F);
      world.setBlockState(pos, (BlockState) state.with(AGE, 1), 2);
      return ActionResult.SUCCESS;
    } else {
      return super.onUse(state, world, pos, player, hand, hit);
    }
  }

  public void onStacksDropped(BlockState state, World world, BlockPos pos, ItemStack stack) {
    ItemStack bush = new ItemStack(main.GOLDBUSH);
    super.onStacksDropped(state, world, pos, stack);
    if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 1) {
      SweetBerryBushBlock.dropStack(world, pos, bush);
    }

  }

}