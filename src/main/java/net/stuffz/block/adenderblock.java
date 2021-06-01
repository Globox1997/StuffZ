package net.stuffz.block;

import net.minecraft.block.RedstoneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Random;

public class AdenderBlock extends RedstoneBlock {

    public AdenderBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        StatusEffectInstance spd = new StatusEffectInstance(StatusEffect.byRawId(1), 15, 0, false, false);
        LivingEntity bob = (LivingEntity) entity;
        if (!world.isClient) {
            bob.addStatusEffect(spd);
        } else {
            Random random1 = new Random();
            Random random2 = new Random();
            Random random3 = new Random();
            double z1 = (random1.nextInt() % 4);
            double z2 = z1 / 10;
            double z3 = random2.nextInt() % 10;
            double z4 = z3 / 100;
            double z5 = random3.nextInt() % 4;
            double z6 = z5 / 10;
            world.addParticle(ParticleTypes.DRAGON_BREATH, entity.getX() + z2, entity.getY() + z4, entity.getZ() + z6,
                    0.0D, 0.0D, 0.0D);
        }
    }

}
