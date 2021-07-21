package net.stuffz.item;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.stuffz.init.ItemInit;

public class BedrockRemover extends Item {

    public BedrockRemover(Settings settings) {
        super(settings);

    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockState state = world.getBlockState(context.getBlockPos());
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();
        NbtCompound tags = stack.getNbt();
        if (player.getBlockPos().getY() > 120) {
            if (state.getBlock().equals(Blocks.BEDROCK) && stack.hasNbt() && tags.getBoolean("activeruby")) {
                if (!world.isClient) {
                    world.breakBlock(context.getBlockPos(), false);
                }
                tags.putBoolean("activeruby", false);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.stuffz.bedrockremover.tooltip"));
        tooltip.add(new TranslatableText("item.stuffz.moreinfo.tooltip"));
        if (InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 340)) {
            tooltip.remove(new TranslatableText("item.stuffz.moreinfo.tooltip"));
            tooltip.add(new TranslatableText("item.stuffz.bedrockremover.tooltip2"));
        }
    }

    @Override
    public void onCraft(ItemStack stack, World world, PlayerEntity player) {
        NbtCompound tags = stack.getNbt();
        if (tags == null) {
            stack.setNbt(new NbtCompound());
            tags = stack.getNbt();
        }
        tags.putBoolean("activeruby", false);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        NbtCompound tags = stack.getNbt();
        ItemStack ruby = new ItemStack(ItemInit.YELLOWRUBY);
        if (user.getInventory().contains(ruby)) {
            if (stack.hasNbt() && tags.getBoolean("activeruby") == false) {
                tags.putBoolean("activeruby", true);
                if (!world.isClient && !user.isCreative()) {
                    int rubyslot = user.getInventory().getSlotWithStack(ruby);
                    user.getInventory().removeStack(rubyslot, 1);
                }
            } else {
                tags.putBoolean("activeruby", true);
            }
            return TypedActionResult.success(user.getStackInHand(hand));
        }
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

}