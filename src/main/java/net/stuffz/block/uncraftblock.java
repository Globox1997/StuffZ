package net.stuffz.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.stuffz.init.TagInit;

public class uncraftblock extends Block implements BlockEntityProvider {
  protected static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

  public uncraftblock(Settings settings) {
    super(settings);
  }

  @Override
  public BlockEntity createBlockEntity(BlockView view) {
    return new uncraftblockentity();
  }

  @Override
  public boolean hasSidedTransparency(BlockState state) {
    return true;
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
      BlockHitResult hit) {
    Inventory blockEntity = (Inventory) world.getBlockEntity(pos);
    ItemStack stack = blockEntity.getStack(0);

    if (!stack.isEmpty()) {
      if (player.isSneaking()) {
        if (!world.isClient) {
          player.giveItemStack(stack);
        }
        blockEntity.clear();
        return ActionResult.SUCCESS;
      }
      return ActionResult.PASS;
    } else {
      ItemStack heldItem = player.getMainHandStack();
      if (heldItem.getItem().isIn(TagInit.UNCRAFT_ITEMS) && !heldItem.isDamaged()) {
        if (!world.isClient) {
          blockEntity.setStack(0, heldItem.split(1));
          return ActionResult.SUCCESS;
        } else {
          return ActionResult.SUCCESS;
        }
      } else
        return ActionResult.PASS;
    }
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

}
