package net.stuffz.plants;

import javax.swing.text.html.BlockView;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.stuffz.init.BlockInit;

public class IronBush extends SweetBerryBushBlock {

  public IronBush(Settings settings) {
    super(settings);
  }

  @Environment(EnvType.CLIENT)
  public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
    return new ItemStack(BlockInit.IRONBUSH);
  }

  @Override
  public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
    if (entity instanceof LivingEntity) {
      entity.slowMovement(state, new Vec3d(0.800000011920929D, 0.75D, 0.800000011920929D));
      if (!world.isClient && (Integer) state.get(AGE) > 0
          && (entity.lastRenderX != entity.getX() || entity.lastRenderZ != entity.getZ())
          && entity instanceof HostileEntity) {
        double d = Math.abs(entity.getX() - entity.lastRenderX);
        double e = Math.abs(entity.getZ() - entity.lastRenderZ);
        if (d >= 0.003000000026077032D || e >= 0.003000000026077032D) {
          entity.damage(DamageSource.SWEET_BERRY_BUSH, 1.0F);
        }
      }

    }
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
      BlockHitResult hit) {
    int i = (Integer) state.get(AGE);
    boolean bl = i == 3;
    if (!bl && player.getStackInHand(hand).getItem() == Items.BONE_MEAL) {
      return ActionResult.PASS;
    } else if (i > 1) {
      int j = 1 + world.random.nextInt(2);
      dropStack(world, pos, new ItemStack(Items.IRON_NUGGET, j + (bl ? 1 : 0)));
      world.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS,
          1.0F, 0.8F + world.random.nextFloat() * 0.4F);
      world.setBlockState(pos, (BlockState) state.with(AGE, 1), 2);
      return ActionResult.SUCCESS;
    } else {
      return super.onUse(state, world, pos, player, hand, hit);
    }
  }

  @Override
  public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
    super.onStacksDropped(state, world, pos, stack);
    ItemStack bush = new ItemStack(BlockInit.IRONBUSH);
    if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 1
        || stack.isItemEqualIgnoreDamage(new ItemStack(Items.SHEARS))) {
      Block.dropStack(world, pos, bush);
    }

  }

}