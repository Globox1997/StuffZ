package net.stuffz.block;

import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.stuffz.init.TagInit;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AnvilBlock;

public class UncraftBlock extends AnvilBlock implements BlockEntityProvider {

  public UncraftBlock(Settings settings) {
    super(settings);
  }

  @Override
  public BlockEntity createBlockEntity(BlockView view) {
    return new UncraftBlockEntity();
  }

  @Override
  @Environment(EnvType.CLIENT)
  public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
    tooltip.add(new TranslatableText("item.stuffz.moreinfo.tooltip"));
    if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340)) {
      tooltip.remove(new TranslatableText("item.stuffz.moreinfo.tooltip"));
      tooltip.add(new TranslatableText("block.stuffz.uncraftblock.tooltip"));
      tooltip.add(new TranslatableText("block.stuffz.uncraftblock.tooltip2"));
    }
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
          if (!player.isCreative()) {
            blockEntity.setStack(0, heldItem.split(1));
          } else {
            blockEntity.setStack(0, heldItem.copy());
          }
          return ActionResult.SUCCESS;
        } else {
          return ActionResult.SUCCESS;
        }
      } else
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

}
