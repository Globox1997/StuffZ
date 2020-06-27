package net.stuffz.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.stuffz.init.BlockInit;
import net.stuffz.init.ItemInit;
import net.stuffz.init.TagInit;
import net.minecraft.block.Block;

public class brewingbarrel extends Block implements BlockEntityProvider {
  public static final DirectionProperty FACING;
  private static final VoxelShape X_SHAPE1;
  private static final VoxelShape X_SHAPE2;
  private static final VoxelShape X_SHAPE3;
  private static final VoxelShape X_SHAPE4;
  private static final VoxelShape X_SHAPE5;
  private static final VoxelShape X_SHAPE6;
  private static final VoxelShape Z_SHAPE1;
  private static final VoxelShape Z_SHAPE2;
  private static final VoxelShape Z_SHAPE3;
  private static final VoxelShape Z_SHAPE4;
  private static final VoxelShape Z_SHAPE5;
  private static final VoxelShape Z_SHAPE6;
  private static final VoxelShape X_AXIS_SHAPE;
  private static final VoxelShape Z_AXIS_SHAPE;

  public brewingbarrel(Settings settings) {
    super(settings);
    this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return (BlockState) this.getDefaultState().with(FACING, ctx.getPlayerFacing().rotateYClockwise());
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    Direction direction = (Direction) state.get(FACING);
    return direction.getAxis() == Direction.Axis.X ? Z_AXIS_SHAPE : X_AXIS_SHAPE;
  }

  @Override
  public BlockState rotate(BlockState state, BlockRotation rotation) {
    return (BlockState) state.with(FACING, rotation.rotate((Direction) state.get(FACING)));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  @Override
  public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
    return false;
  }

  @Override
  public BlockEntity createBlockEntity(BlockView view) {
    return new brewingbarrelentity();
  }

  @Override
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
      BlockHitResult hit) {
    Inventory blockEntity = (Inventory) world.getBlockEntity(pos);
    ItemStack stackaxe = blockEntity.getStack(0);
    ItemStack stackwater = blockEntity.getStack(1);
    ItemStack stackmalt = blockEntity.getStack(2);
    ItemStack stackhops = blockEntity.getStack(3);
    ItemStack stackbeer = blockEntity.getStack(4);

    if (!blockEntity.isEmpty()) {
      if (player.isSneaking()) {
        if (!stackbeer.isEmpty()) {
          if (!world.isClient) {
            player.giveItemStack(stackbeer);
          }
          blockEntity.getStack(4).decrement(1);
          return ActionResult.SUCCESS;
        } else if (!stackaxe.isEmpty()) {
          if (!world.isClient) {
            player.giveItemStack(stackaxe);
          }
          blockEntity.getStack(0).decrement(1);
          return ActionResult.SUCCESS;
        } else if (!stackmalt.isEmpty() || !stackhops.isEmpty()) {
          if (!world.isClient) {
            player.giveItemStack(stackmalt);
            player.giveItemStack(stackhops);
          }
          blockEntity.getStack(2).decrement(1);
          blockEntity.getStack(3).decrement(1);
          return ActionResult.SUCCESS;
        } else if (!stackwater.isEmpty()) {
          if (!world.isClient) {
            player.giveItemStack(stackwater); // Only Water remove
          }
          blockEntity.getStack(1).decrement(1);
          return ActionResult.SUCCESS;
        }
      }
      return ActionResult.PASS;
    } else {
      ItemStack heldItem = player.getMainHandStack();
      if (heldItem.getItem().isIn(TagInit.AXE_ITEMS)) {
        if (!world.isClient) {
          if (!player.isCreative()) {
            blockEntity.setStack(0, heldItem.split(1));
          } else {
            blockEntity.setStack(0, heldItem.copy());
          }
          return ActionResult.SUCCESS;
        }
      } else if (heldItem.getItem().equals(ItemInit.DARKMALT) || heldItem.isItemEqual(new ItemStack(BlockInit.MALT))) {
        if (!world.isClient) {
          if (!player.isCreative()) {
            blockEntity.setStack(2, heldItem.split(1));
          } else {
            blockEntity.setStack(2, heldItem.copy());
          }
          return ActionResult.SUCCESS;
        }
      } else if (heldItem.getItem().equals(ItemInit.HOPS)) {
        if (!world.isClient) {
          if (!player.isCreative()) {
            blockEntity.setStack(3, heldItem.split(1));
          } else {
            blockEntity.setStack(3, heldItem.copy());
          }
          return ActionResult.SUCCESS;
        }
      } else if (heldItem.getItem().equals(Items.WATER_BUCKET)) {
        if (!world.isClient) {
          if (!player.isCreative()) {
            blockEntity.setStack(1, heldItem.split(1));
          } else {
            blockEntity.setStack(1, heldItem.copy());
          }
          return ActionResult.SUCCESS;
        }
      }
      return ActionResult.PASS;
    }
  }

  @Override
  public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
    if (!state.isOf(newState.getBlock())) {
      BlockEntity blockEntity = world.getBlockEntity(pos);
      if (blockEntity instanceof Inventory) {
        ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
        world.updateComparators(pos, this);
      }

      super.onStateReplaced(state, world, pos, newState, moved);
    }
  }

  static {
    FACING = HorizontalFacingBlock.FACING;
    X_SHAPE1 = Block.createCuboidShape(5D, 12D, 0D, 11D, 13D, 16D);
    X_SHAPE2 = Block.createCuboidShape(3D, 11D, 0D, 13D, 12D, 16D);
    X_SHAPE3 = Block.createCuboidShape(2D, 9D, 0D, 14D, 11D, 16D);
    X_SHAPE4 = Block.createCuboidShape(1D, 3D, 0D, 15D, 9D, 16D);
    X_SHAPE5 = Block.createCuboidShape(2D, 1D, 0D, 14D, 3D, 16D);
    X_SHAPE6 = Block.createCuboidShape(3D, 0D, 0D, 13D, 1D, 16D);
    Z_SHAPE1 = Block.createCuboidShape(0D, 12D, 5D, 16D, 13D, 11D);
    Z_SHAPE2 = Block.createCuboidShape(0D, 11D, 3D, 16D, 12D, 13D);
    Z_SHAPE3 = Block.createCuboidShape(0D, 9D, 2D, 16D, 11D, 14D);
    Z_SHAPE4 = Block.createCuboidShape(0D, 3D, 1D, 16D, 9D, 15D);
    Z_SHAPE5 = Block.createCuboidShape(0D, 1D, 2D, 16D, 3D, 14D);
    Z_SHAPE6 = Block.createCuboidShape(0D, 0D, 3D, 16D, 1D, 13D);
    X_AXIS_SHAPE = VoxelShapes.union(X_SHAPE1, X_SHAPE2, X_SHAPE3, X_SHAPE4, X_SHAPE5, X_SHAPE6);
    Z_AXIS_SHAPE = VoxelShapes.union(Z_SHAPE1, Z_SHAPE2, Z_SHAPE3, Z_SHAPE4, Z_SHAPE5, Z_SHAPE6);
  }
}
