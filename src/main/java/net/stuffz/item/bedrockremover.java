package net.stuffz.item;

import java.util.List;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.stuffz.init.ItemInit;

public class bedrockremover extends Item {

  public bedrockremover(Settings settings) {
    super(settings);

    FabricModelPredicateProviderRegistry.register(new Identifier("active"), (stack, world, entity) -> {
      if (stack.hasTag()) {
        if (stack.getTag().getBoolean("activeruby")) {
          return 1F;
        }
      }
      return 0F;
    });
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    World world = context.getWorld();
    BlockState state = world.getBlockState(context.getBlockPos());
    PlayerEntity player = context.getPlayer();
    ItemStack stack = context.getStack();
    CompoundTag tags = stack.getTag();
    if (player.getBlockPos().getY() > 120) {
      if (state.getBlock().equals(Blocks.BEDROCK) && stack.hasTag() && tags.getBoolean("activeruby")) {
        if (!world.isClient) {
          world.breakBlock(context.getBlockPos(), false);
          tags.putBoolean("activeruby", false);
        }
        return ActionResult.SUCCESS;
      }
    }
    return ActionResult.PASS;
  }

  @Override
  public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
    tooltip.add(new TranslatableText("item.stuffz.bedrockremover.tooltip"));
  }

  @Override
  public void onCraft(ItemStack stack, World world, PlayerEntity player) {
    CompoundTag tags = stack.getTag();
    if (tags == null) {
      stack.setTag(new CompoundTag());
      tags = stack.getTag();
    }
    tags.putBoolean("activeruby", true);
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    ItemStack stack = user.getStackInHand(hand);
    CompoundTag tags = stack.getTag();
    ItemStack ruby = new ItemStack(ItemInit.YELLOWRUBY);
    int rubyslot = user.inventory.getSlotWithStack(ruby);

    if (user.inventory.contains(ruby) && stack.hasTag() && tags.getBoolean("activeruby") == false) {
      tags.putBoolean("activeruby", true);
      if (!world.isClient) {
        user.inventory.removeStack(rubyslot, 1);
      }
      return TypedActionResult.success(user.getStackInHand(hand));
    } else {
      return TypedActionResult.pass(user.getStackInHand(hand));
    }
  }
}