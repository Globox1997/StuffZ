package net.stuffz.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
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
import net.stuffz.init.SoundInit;
import net.stuffz.init.TagInit;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;

public class brewingbarrel extends Block implements BlockEntityProvider {
  public static final DirectionProperty FACING;
  private static final VoxelShape X_AXIS_SHAPE;
  private static final VoxelShape Z_AXIS_SHAPE;

  public brewingbarrel(Settings settings) {
    super(settings);
    this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
  }

  @Override
  public BlockEntity createBlockEntity(BlockView view) {
    return new brewingbarrelentity();
  }

  @Override
  @Environment(EnvType.CLIENT)
  public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
    tooltip.add(new TranslatableText("item.stuffz.moreinfo.tooltip"));
    if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340)) {
      tooltip.remove(new TranslatableText("item.stuffz.moreinfo.tooltip"));
      tooltip.add(new TranslatableText("block.stuffz.brewingbarrel.tooltip"));
      tooltip.add(new TranslatableText("block.stuffz.brewingbarrel.tooltip2"));
      tooltip.add(new TranslatableText("block.stuffz.brewingbarrel.tooltip3"));
    }
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext ctx) {
    return (BlockState) this.getDefaultState().with(FACING, ctx.getPlayerFacing().rotateYClockwise());
  }

  @Override
  public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    Direction direction = (Direction) state.get(FACING);
    return direction.getAxis() == Direction.Axis.X ? X_AXIS_SHAPE : Z_AXIS_SHAPE;
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
  public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
      BlockHitResult hit) {
    Inventory blockEntity = (Inventory) world.getBlockEntity(pos);
    ItemStack stackaxe = blockEntity.getStack(0);
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
        }
      }
      return ActionResult.PASS;
    } else {
      ItemStack heldItem = player.getMainHandStack();
      if (heldItem.getItem().isIn(TagInit.AXE_ITEMS) && blockEntity.getStack(0).isEmpty()) {
        if (!world.isClient) {
          if (!player.isCreative()) {
            blockEntity.setStack(0, heldItem.split(1));
          } else {
            blockEntity.setStack(0, heldItem.copy().split(1));
          }
          world.playSound(null, pos, SoundInit.BARRELHIT_EVENT, SoundCategory.BLOCKS, 0.3F, 1F);
          return ActionResult.SUCCESS;
        }
      } else if ((heldItem.getItem().equals(ItemInit.DARKMALT) || heldItem.isItemEqual(new ItemStack(BlockInit.MALT)))
          && blockEntity.getStack(2).isEmpty()) {
        if (!world.isClient) {
          if (!player.isCreative()) {
            blockEntity.setStack(2, heldItem.split(1));
          } else {
            blockEntity.setStack(2, heldItem.copy().split(1));
          }
          return ActionResult.SUCCESS;
        }
      } else if (heldItem.getItem().equals(ItemInit.HOPS) && blockEntity.getStack(3).isEmpty()) {
        if (!world.isClient) {
          if (!player.isCreative()) {
            blockEntity.setStack(3, heldItem.split(1));
          } else {
            blockEntity.setStack(3, heldItem.copy().split(1));
          }
          return ActionResult.SUCCESS;
        }
      } else if (heldItem.getItem().equals(Items.GLASS_BOTTLE) && blockEntity.getStack(1).isEmpty()) {
        if (!world.isClient) {
          if (!player.isCreative()) {
            blockEntity.setStack(1, heldItem.split(1));
          } else {
            blockEntity.setStack(1, heldItem.copy().split(1));
          }
          return ActionResult.SUCCESS;
        }
      }
      return ActionResult.PASS;
    }
  }

  @Override
  public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
      @Nullable BlockEntity blockEntity, ItemStack stack) {
    player.incrementStat(Stats.MINED.getOrCreateStat(this));
    player.addExhaustion(0.005F);
    if (blockEntity instanceof Inventory) {
      ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
      world.updateComparators(pos, this);
    }
    dropStacks(state, world, pos, blockEntity, player, stack);
  }

  static {
    FACING = HorizontalFacingBlock.FACING;
    X_AXIS_SHAPE = VoxelShapes.union(createCuboidShape(5D, 0, 1D, 11D, 1D, 15D),
        createCuboidShape(5D, 0D, 0D, 11D, 1D, 1D), createCuboidShape(5D, 0D, 15D, 11D, 1D, 16D),
        createCuboidShape(3D, 1D, 1D, 13D, 3D, 15D), createCuboidShape(3D, 1D, 0D, 4D, 3D, 1D),
        createCuboidShape(3D, 1D, 15D, 4D, 3D, 16D), createCuboidShape(12D, 1D, 0D, 13D, 3D, 1D),
        createCuboidShape(12D, 1D, 15D, 13D, 3D, 1D), createCuboidShape(2D, 3D, 1D, 14D, 9D, 15D),
        createCuboidShape(2D, 3D, 0D, 3D, 9D, 1D), createCuboidShape(2D, 3D, 15D, 3D, 9D, 16D),
        createCuboidShape(13D, 3D, 0D, 14D, 9D, 1D), createCuboidShape(13D, 3D, 15D, 14D, 9D, 16D),
        createCuboidShape(3D, 9D, 1D, 13D, 11D, 15D), createCuboidShape(3D, 9D, 0D, 4D, 11D, 1D),
        createCuboidShape(3D, 9D, 15D, 4D, 11D, 16D), createCuboidShape(12D, 9D, 0D, 13D, 11D, 1D),
        createCuboidShape(12D, 9D, 15D, 13D, 11D, 16D), createCuboidShape(11D, 10D, 0D, 12D, 11D, 1D),
        createCuboidShape(11D, 10D, 15D, 12D, 11D, 16D), createCuboidShape(11D, 1D, 0D, 12D, 2D, 1D),
        createCuboidShape(11D, 1D, 15D, 12D, 2D, 16D), createCuboidShape(4D, 10D, 0D, 5D, 11D, 1D),
        createCuboidShape(4D, 10D, 15D, 5D, 11D, 16D), createCuboidShape(4D, 1D, 0D, 5D, 2D, 1D),
        createCuboidShape(4D, 1D, 15D, 5D, 2D, 16D), createCuboidShape(5D, 11D, 1D, 11D, 12D, 15D),
        createCuboidShape(5D, 11D, 0D, 11D, 12D, 1D), createCuboidShape(5D, 11D, 15D, 11D, 12D, 16D),
        createCuboidShape(14D, 3D, 3D, 15D, 9D, 13D), createCuboidShape(1D, 3D, 3D, 2D, 9D, 13D),
        createCuboidShape(2D, 9D, 3D, 3D, 11D, 13D), createCuboidShape(13D, 9D, 3D, 14D, 11D, 13D),
        createCuboidShape(13D, 1D, 3D, 14D, 3D, 13D), createCuboidShape(2D, 1D, 3D, 3D, 3D, 13D),
        createCuboidShape(11D, 0D, 3D, 13D, 1D, 13D), createCuboidShape(3D, 0D, 3D, 5D, 1D, 13D),
        createCuboidShape(3D, 11D, 3D, 5D, 12D, 13D), createCuboidShape(11D, 11D, 3D, 13D, 12D, 13D),
        createCuboidShape(5D, 12D, 3D, 11D, 13D, 13D), createCuboidShape(6D, 2D, 0D, 10D, 3D, 1D));
    Z_AXIS_SHAPE = VoxelShapes.union(createCuboidShape(1D, 0D, 5D, 15D, 1D, 11D),
        createCuboidShape(15D, 0D, 5D, 16D, 1D, 11D), createCuboidShape(0D, 0D, 5D, 1D, 1D, 11D),
        createCuboidShape(1D, 1D, 3D, 15D, 3D, 13D), createCuboidShape(15D, 1D, 3D, 16D, 3D, 4D),
        createCuboidShape(0D, 1D, 3D, 1D, 3D, 4D), createCuboidShape(15D, 1D, 12D, 16D, 3D, 13D),
        createCuboidShape(0D, 1D, 12D, 1D, 3D, 13D), createCuboidShape(1D, 3D, 2D, 15D, 9D, 14D),
        createCuboidShape(15D, 3D, 2D, 16D, 9D, 3D), createCuboidShape(0D, 3D, 2D, 1D, 9D, 3D),
        createCuboidShape(15D, 3D, 13D, 16D, 9D, 14D), createCuboidShape(0D, 3D, 13D, 1D, 9D, 14D),
        createCuboidShape(1D, 9D, 3D, 15D, 11D, 13D), createCuboidShape(15D, 9D, 3D, 16D, 11D, 4D),
        createCuboidShape(0D, 9D, 3D, 1D, 11D, 4D), createCuboidShape(15D, 9D, 12D, 16D, 11D, 13D),
        createCuboidShape(0D, 9D, 12D, 1D, 11D, 13D), createCuboidShape(15D, 10D, 11D, 16D, 11D, 12D),
        createCuboidShape(0D, 10D, 11D, 1D, 11D, 12D), createCuboidShape(15D, 1D, 11D, 16D, 2D, 12D),
        createCuboidShape(0D, 1D, 11D, 1D, 2D, 12D), createCuboidShape(15D, 10D, 4D, 16D, 11D, 5D),
        createCuboidShape(0D, 10D, 4D, 1D, 11D, 5D), createCuboidShape(15D, 1D, 4D, 16D, 2D, 5D),
        createCuboidShape(0D, 1D, 4D, 1D, 2D, 5D), createCuboidShape(1D, 11D, 5D, 15D, 12D, 11D),
        createCuboidShape(15D, 11D, 5D, 16D, 12D, 11D), createCuboidShape(0D, 11D, 5D, 1D, 12D, 11D),
        createCuboidShape(3D, 3D, 14D, 13D, 9D, 15D), createCuboidShape(3D, 3D, 1D, 13D, 9D, 2D),
        createCuboidShape(3D, 9D, 2D, 13D, 11D, 3D), createCuboidShape(3D, 9D, 13D, 13D, 11D, 14D),
        createCuboidShape(3D, 1D, 13D, 13D, 3D, 14D), createCuboidShape(3D, 1D, 2D, 13D, 3D, 3D),
        createCuboidShape(3D, 0D, 11D, 13D, 1D, 13D), createCuboidShape(3D, 0D, 3D, 13D, 1D, 5D),
        createCuboidShape(3D, 11D, 3D, 13D, 12D, 5D), createCuboidShape(3D, 11D, 11D, 13D, 12D, 13D),
        createCuboidShape(3D, 12D, 5D, 13D, 13D, 11D), createCuboidShape(15D, 2D, 6D, 16D, 3D, 10D));
  }
}
