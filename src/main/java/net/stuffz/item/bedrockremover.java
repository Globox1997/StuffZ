package net.stuffz.item;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.stuffz.init.ItemInit;

public class bedrockremover extends Item {
  // public boolean rubyactive = false;

  public bedrockremover(Settings settings) {
    super(settings);

    FabricModelPredicateProviderRegistry.register(new Identifier("active"), (stack, world, entity) -> {
      CompoundTag tags = stack.getTag();
      // if (tags.getBoolean("active")) {
      if (tags.contains("active")) {
        if (tags.getBoolean("active")) {
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
      if (state.getBlock().equals(Blocks.BEDROCK)) {
        if (!world.isClient) {
          world.breakBlock(context.getBlockPos(), false);
          tags.putBoolean("active", false);
        }
        return ActionResult.SUCCESS;
      }
    }
    return ActionResult.PASS;
  }

  @Override
  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    ItemStack stack = user.getActiveItem();
    CompoundTag tags = stack.getTag();
    ItemStack ruby = new ItemStack(ItemInit.YELLOWRUBY);
    int rubyslot = user.inventory.getSlotWithStack(ruby);

    if (tags == null) {
      stack.setTag(new CompoundTag());
      tags = stack.getTag();
    }
    if (user.inventory.contains(ruby)) {
      tags.putBoolean("active", true);
      user.inventory.removeStack(rubyslot, 1);
      // if (!world.isClient) {
      return TypedActionResult.success(user.getStackInHand(hand));
      // }
    } else {
      // TypedActionResult.fail(stack);
      return TypedActionResult.pass(user.getStackInHand(hand));
    }
  }
}
