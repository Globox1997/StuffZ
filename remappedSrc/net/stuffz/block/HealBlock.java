package net.stuffz.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Random;

public class HealBlock extends Block {

    public HealBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        Random random1 = new Random();
        Random random3 = new Random();
        double z1 = (random1.nextInt() % 10);
        double z2 = z1 / 70;
        double z5 = random3.nextInt() % 10;
        double z6 = z5 / 70;

        world.addParticle(ParticleTypes.EFFECT, pos.getX() + z2 + 0.5D, pos.getY() + 1D, pos.getZ() + z6 + 0.5D, 0.0D,
                0.0D, 0.0D);

    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        StatusEffectInstance reg = new StatusEffectInstance(StatusEffect.byRawId(10), 20, 0, false, false);
        LivingEntity bob = (LivingEntity) entity;
        if (!world.isClient) {
            bob.addStatusEffect(reg);
        } else {
            Random random1 = new Random();
            Random random2 = new Random();
            Random random3 = new Random();
            double z1 = (random1.nextInt() % 10);
            double z2 = z1 / 10;
            if (z2 < 0) {
                z2 = z2 * (-1);
            }
            double z3 = random2.nextInt() % 180;
            double z4 = z3 / 100;
            if (z4 < 0) {
                z4 = z4 * (-1);
            }
            double z5 = random3.nextInt() % 10;
            double z6 = z5 / 10;
            if (z6 < 0) {
                z6 = z6 * (-1);
            }
            world.addParticle(ParticleTypes.END_ROD, pos.getX() + z2, pos.getY() + z4 + 1D, pos.getZ() + z6, 0.0D, 0.0D,
                    0.0D);
        }
    }

}
