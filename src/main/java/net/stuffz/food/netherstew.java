package net.stuffz.food;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SuspiciousStewItem;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class netherstew extends Item {
    public static final SuspiciousStewItem STEW_ITEM = new SuspiciousStewItem(new Item.Settings());

    public netherstew(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {

        StatusEffectInstance str = new StatusEffectInstance(StatusEffect.byRawId(5), 400, 0, false, false);
        entity.addStatusEffect(str);

        return new ItemStack(Items.BOWL);
    }

    public int getMaxUseTime(ItemStack itemStack_1) {
        return 32;
    }

    public UseAction getUseAction(ItemStack itemStack_1) {
        return UseAction.DRINK;
    }

    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        playerEntity_1.setCurrentHand(hand_1);
        return new TypedActionResult(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
    }
}
