package net.stuffz.item;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stuffz.block.UncraftBlock;
import net.stuffz.block.entity.UncraftBlockEntity;
import net.stuffz.init.BlockInit;
import net.stuffz.init.SoundInit;

public class IronHammer extends MiningToolItem {
  public static final Set<Block> EFFECTIVE_BLOCKS;

  public IronHammer(float attackDamage, float attackSpeed, ToolMaterial material, Set<Block> effectiveBlocks,
      Settings settings) {
    super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
  }

  @Override
  public ActionResult useOnBlock(ItemUsageContext context) {
    World world = context.getWorld();
    BlockPos pos = context.getBlockPos();
    PlayerEntity player = context.getPlayer();
    BlockState state = context.getWorld().getBlockState(pos);
    UncraftBlockEntity uncraftBlockEntity = (UncraftBlockEntity) world.getBlockEntity(pos);

    if (state.getBlock() instanceof UncraftBlock && uncraftBlockEntity instanceof UncraftBlockEntity
        && !uncraftBlockEntity.isEmpty()) {
      if (!player.isCreative()) {
        context.getStack().damage(1, player, (p) -> p.sendToolBreakStatus(p.getActiveHand()));
      }
      world.playSound(player, pos, SoundInit.HAMMERHIT_EVENT, SoundCategory.BLOCKS, 0.4F, 1F);
      uncraftBlockEntity.setuncrafthit(uncraftBlockEntity.getuncrafthit() + 1);
      return ActionResult.SUCCESS;
    } else {
      return ActionResult.PASS;
    }
  }

  static {
    EFFECTIVE_BLOCKS = ImmutableSet.of(Blocks.STONE, BlockInit.UNCRAFTBLOCK);
  }

}