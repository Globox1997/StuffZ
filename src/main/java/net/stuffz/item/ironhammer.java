package net.stuffz.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stuffz.block.uncraftblock;
import net.stuffz.block.uncraftblockentity;

public class ironhammer extends Item {

  public ironhammer(Settings settings) {
    super(settings);
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    World world = context.getWorld();
    BlockPos pos = context.getBlockPos();
    PlayerEntity player = context.getPlayer();
    BlockState state = context.getWorld().getBlockState(pos);
    uncraftblockentity uncraftBlockEntity = (uncraftblockentity) world.getBlockEntity(pos);

    if (state.getBlock() instanceof uncraftblock && uncraftBlockEntity instanceof uncraftblockentity
        && !uncraftBlockEntity.isEmpty()) {
      if (!player.isCreative()) {
        context.getStack().damage(1, player, (p) -> p.sendToolBreakStatus(p.getActiveHand()));
      }
      world.playSound(player, pos, SoundEvents.BLOCK_ENDER_CHEST_CLOSE, SoundCategory.BLOCKS, 1F, 1F);
      uncraftBlockEntity.setuncrafthit(uncraftBlockEntity.getuncrafthit() + 1);
      return ActionResult.SUCCESS;
    } else {
      return ActionResult.PASS;
    }
  }
}