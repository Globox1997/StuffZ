package net.stuffz.block;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
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
import net.stuffz.block.entity.BrewingBarrelEntity;
import net.stuffz.init.BlockInit;
import net.stuffz.init.ItemInit;
import net.stuffz.init.SoundInit;
import net.stuffz.init.TagInit;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class BrewingBarrel extends Block implements BlockEntityProvider {
    public static final DirectionProperty FACING;
    private static final VoxelShape X_AXIS_SHAPE;
    private static final VoxelShape Z_AXIS_SHAPE;

    public BrewingBarrel(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState) ((BlockState) this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BrewingBarrelEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, BlockInit.BREWINGBARRELENTITY, world.isClient ? BrewingBarrelEntity::clientTick : BrewingBarrelEntity::serverTick);
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
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
            if (heldItem.isIn(TagInit.AXE_ITEMS) && blockEntity.getStack(0).isEmpty()) {
                if (!world.isClient) {
                    if (!player.isCreative()) {
                        blockEntity.setStack(0, heldItem.split(1));
                    } else {
                        blockEntity.setStack(0, heldItem.copy().split(1));
                    }
                    world.playSound(null, pos, SoundInit.BARRELHIT_EVENT, SoundCategory.BLOCKS, 0.3F, 1F);
                    return ActionResult.SUCCESS;
                }
            } else if ((heldItem.getItem().equals(ItemInit.DARKMALT) || heldItem.isItemEqual(new ItemStack(ItemInit.MALT))) && blockEntity.getStack(2).isEmpty()) {
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
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005F);
        if (blockEntity instanceof Inventory) {
            ItemScatterer.spawn(world, pos, (Inventory) blockEntity);
            world.updateComparators(pos, this);
        }
        dropStacks(state, world, pos, blockEntity, player, stack);
    }

    protected static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> checkType(BlockEntityType<A> givenType, BlockEntityType<E> expectedType,
            BlockEntityTicker<? super E> ticker) {
        return expectedType == givenType ? (BlockEntityTicker<A>) ticker : null;
    }

    static {
        FACING = HorizontalFacingBlock.FACING;

        X_AXIS_SHAPE = VoxelShapes.union(createCuboidShape(5, 0, 1, 11, 1, 15), createCuboidShape(5, 0, 0, 11, 1, 1), createCuboidShape(5, 0, 15, 11, 1, 16), createCuboidShape(3, 1, 1, 13, 3, 15),
                createCuboidShape(3, 1, 0, 4, 3, 1), createCuboidShape(3, 1, 15, 4, 3, 16), createCuboidShape(12, 1, 0, 13, 3, 1), createCuboidShape(12, 1, 15, 13, 3, 16),
                createCuboidShape(2, 3, 1, 14, 9, 15), createCuboidShape(2, 3, 0, 3, 9, 1), createCuboidShape(2, 3, 15, 3, 9, 16), createCuboidShape(13, 3, 0, 14, 9, 1),
                createCuboidShape(13, 3, 15, 14, 9, 16), createCuboidShape(3, 9, 1, 13, 11, 15), createCuboidShape(3, 9, 0, 4, 11, 1), createCuboidShape(3, 9, 15, 4, 11, 16),
                createCuboidShape(12, 9, 0, 13, 11, 1), createCuboidShape(12, 9, 15, 13, 11, 16), createCuboidShape(11, 10, 0, 12, 11, 1), createCuboidShape(11, 10, 15, 12, 11, 16),
                createCuboidShape(11, 1, 0, 12, 2, 1), createCuboidShape(11, 1, 15, 12, 2, 16), createCuboidShape(4, 10, 0, 5, 11, 1), createCuboidShape(4, 10, 15, 5, 11, 16),
                createCuboidShape(4, 1, 0, 5, 2, 1), createCuboidShape(4, 1, 15, 5, 2, 16), createCuboidShape(5, 11, 1, 11, 12, 15), createCuboidShape(5, 11, 0, 11, 12, 1),
                createCuboidShape(5, 11, 15, 11, 12, 16), createCuboidShape(14, 3, 3, 15, 9, 13), createCuboidShape(1, 3, 3, 2, 9, 13), createCuboidShape(2, 9, 3, 3, 11, 13),
                createCuboidShape(13, 9, 3, 14, 11, 13), createCuboidShape(13, 1, 3, 14, 3, 13), createCuboidShape(2, 1, 3, 3, 3, 13), createCuboidShape(11, 0, 3, 13, 1, 13),
                createCuboidShape(3, 0, 3, 5, 1, 13), createCuboidShape(3, 11, 3, 5, 12, 13), createCuboidShape(11, 11, 3, 13, 12, 13), createCuboidShape(5, 12, 3, 11, 13, 13));
        Z_AXIS_SHAPE = VoxelShapes.union(createCuboidShape(1, 0, 5, 15, 1, 11), createCuboidShape(15, 0, 5, 16, 1, 11), createCuboidShape(0, 0, 5, 1, 1, 11), createCuboidShape(1, 1, 3, 15, 3, 13),
                createCuboidShape(15, 1, 3, 16, 3, 4), createCuboidShape(0, 1, 3, 1, 3, 4), createCuboidShape(15, 1, 12, 16, 3, 13), createCuboidShape(0, 1, 12, 1, 3, 13),
                createCuboidShape(1, 3, 2, 15, 9, 14), createCuboidShape(15, 3, 2, 16, 9, 3), createCuboidShape(0, 3, 2, 1, 9, 3), createCuboidShape(15, 3, 13, 16, 9, 14),
                createCuboidShape(0, 3, 13, 1, 9, 14), createCuboidShape(1, 9, 3, 15, 11, 13), createCuboidShape(15, 9, 3, 16, 11, 4), createCuboidShape(0, 9, 3, 1, 11, 4),
                createCuboidShape(15, 9, 12, 16, 11, 13), createCuboidShape(0, 9, 12, 1, 11, 13), createCuboidShape(15, 10, 11, 16, 11, 12), createCuboidShape(0, 10, 11, 1, 11, 12),
                createCuboidShape(15, 1, 11, 16, 2, 12), createCuboidShape(0, 1, 11, 1, 2, 12), createCuboidShape(15, 10, 4, 16, 11, 5), createCuboidShape(0, 10, 4, 1, 11, 5),
                createCuboidShape(15, 1, 4, 16, 2, 5), createCuboidShape(0, 1, 4, 1, 2, 5), createCuboidShape(1, 11, 5, 15, 12, 11), createCuboidShape(15, 11, 5, 16, 12, 11),
                createCuboidShape(0, 11, 5, 1, 12, 11), createCuboidShape(3, 3, 14, 13, 9, 15), createCuboidShape(3, 3, 1, 13, 9, 2), createCuboidShape(3, 9, 2, 13, 11, 3),
                createCuboidShape(3, 9, 13, 13, 11, 14), createCuboidShape(3, 1, 13, 13, 3, 14), createCuboidShape(3, 1, 2, 13, 3, 3), createCuboidShape(3, 0, 11, 13, 1, 13),
                createCuboidShape(3, 0, 3, 13, 1, 5), createCuboidShape(3, 11, 3, 13, 12, 5), createCuboidShape(3, 11, 11, 13, 12, 13), createCuboidShape(3, 12, 5, 13, 13, 11));
    }
}
