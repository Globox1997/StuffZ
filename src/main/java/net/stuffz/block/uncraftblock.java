package net.stuffz.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.stuffz.tag.itemtags;

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
      // if (heldItem.isItemEqual(new ItemStack(Items.GOLDEN_PICKAXE))) {
      //if (heldItem.getItem().isIn(itemtags.UNCRAFT_ITEMS)) {
      //  if (itemtags.UNCRAFT_ITEMS.contains(heldItem.getItem())) { //heldItem.getItem() == 
        // itemStack.getItem().isIn((Tag)ItemTags.LECTERN_BOOKS)
        System.out.println(itemtags.UNCRAFT_ITEMS);
        if (!world.isClient) {
          blockEntity.setStack(0, heldItem.split(1));
          return ActionResult.SUCCESS;
        } else {
          return ActionResult.SUCCESS;
        }
    //  } else
     //   return ActionResult.PASS;
    }
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return SHAPE;
  }

  // @Override
  // public void afterBreak(World world, PlayerEntity player, BlockPos pos,
  // BlockState state, BlockEntity blockEntity,
  // ItemStack stack) {
  // player.incrementStat(Stats.MINED.getOrCreateStat(this));
  // player.addExhaustion(0.005F);
  // dropStacks(state, world, pos, blockEntity, player, stack);
  // Inventory blockEntityInventory = (Inventory) world.getBlockEntity(pos);
  // ItemStack invStack = blockEntityInventory.getStack(0);
  // dropStacks(state, world, pos, blockEntity, player, invStack);
  // }
  // @Override
  // public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
  //   Inventory blockEntityInventory = (Inventory) world.getBlockEntity(pos);
  //   ItemStack invStack = blockEntityInventory.getStack(0);
  //   Block.dropStack(world, pos, invStack);
  // }

}
