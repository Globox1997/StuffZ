package net.stuffz.drink;

import java.util.Random;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class DarkBeer extends Item {
  public DarkBeer(Settings settings) {
    super(settings);
  }

  @Override
  public ItemStack finishUsing(ItemStack stack, World world, LivingEntity entity) {
    StatusEffectInstance nausea = new StatusEffectInstance(StatusEffect.byRawId(9), 400, 0, true, false);
    StatusEffectInstance slowness = new StatusEffectInstance(StatusEffect.byRawId(2), 400, 0, true, false);
    StatusEffectInstance blindness = new StatusEffectInstance(StatusEffect.byRawId(15), 160, 0, true, false);
    StatusEffectInstance luck = new StatusEffectInstance(StatusEffect.byRawId(26), 400, 1, true, false);
    StatusEffectInstance badluck = new StatusEffectInstance(StatusEffect.byRawId(27), 800, 0, true, false);
    StatusEffectInstance badomen = new StatusEffectInstance(StatusEffect.byRawId(31), 800, 0, true, false);
    StatusEffectInstance hero = new StatusEffectInstance(StatusEffect.byRawId(32), 1200, 0, true, false);
    Random random = new Random();
    int randomNumber = random.nextInt() % 7;
    if (randomNumber < 0) {
      randomNumber = randomNumber * (-1);
    }
    if (!world.isClient) {
      switch (randomNumber) {
        case 0:
          entity.addStatusEffect(nausea);
          entity.addStatusEffect(slowness);
        case 1:
          entity.addStatusEffect(slowness);
          entity.addStatusEffect(blindness);
        case 2:
          entity.addStatusEffect(luck);
        case 3:
          entity.addStatusEffect(blindness);
        case 4:
          entity.addStatusEffect(nausea);
          entity.addStatusEffect(badluck);
        case 5:
          entity.addStatusEffect(nausea);
          entity.addStatusEffect(badomen);
        case 6:
          entity.addStatusEffect(hero);
          entity.addStatusEffect(luck);
        default:
          entity.addStatusEffect(badluck);
      }
    }
    return new ItemStack(Items.GLASS_BOTTLE);
  }

  @Override
  public int getMaxUseTime(ItemStack itemStack_1) {
    return 32;
  }

  @Override
  public UseAction getUseAction(ItemStack itemStack_1) {
    return UseAction.DRINK;
  }

  @Override
  public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
    playerEntity_1.setCurrentHand(hand_1);
    return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
  }
}