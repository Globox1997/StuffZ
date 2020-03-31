package net.stuffz.block;

import net.minecraft.block.RedstoneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Random;

public class enderblock extends RedstoneBlock {

    public enderblock(Settings settings) {
        super(settings);
    }

    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        Random random1 = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        double z1 = (random1.nextInt() % 10);
        double z2 = z1 / 10;
        if (z2 < 0) {
            z2 = z2 * (-1);
        }
        double z3 = random2.nextInt() % 10;
        double z4 = z3 / 100;
        if (z4 < 0) {
            z4 = z4 * (-1);
        }
        double z5 = random3.nextInt() % 10;
        double z6 = z5 / 10;
        if (z6 < 0) {
            z6 = z6 * (-1);
        }
        world.addParticle(ParticleTypes.DRAGON_BREATH, pos.getX() + z2, pos.getY() + z4 + 1D, pos.getZ() + z6, 0.0D, 0.0D, 0.0D);
    }

}
