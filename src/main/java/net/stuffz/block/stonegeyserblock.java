package net.stuffz.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stuffz.main;

import java.util.Random;

public class stonegeyserblock extends Block {
    int count = 0;
    int count2 = 0;
    double count3 = 0;
    int count4 = 0;
    double count5 = 0;
    double z7;

    public stonegeyserblock(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        
        Random random1 = new Random();
        Random random3 = new Random();
        Random random5 = new Random();
        double z1 = (random1.nextInt() % 10);
        double z2 = z1 / 70;
        double z5 = random3.nextInt() % 10;
        double z6 = z5 / 70;
        z7 = (random5.nextInt() % 10);
        if (z6 % 2 == 0) {
            world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5D + z2, pos.getY() + 1D, pos.getZ() + 0.5D + z6,
                    0.0D, 0.0D, 0.0D);
        }
        if(z7 == 1)
        {
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), main.GEYSEREVENT, SoundCategory.AMBIENT, 0.5F, 0.5F, false);
            while (count4 < 50) {
                count5 = count4 / 10D;
               
                world.addParticle(ParticleTypes.CLOUD, pos.getX() + 0.5D, pos.getY() + count5 + 1D, pos.getZ() + 0.5D, 0.0D,
                    0.0D, 0.0D);
                    count4++;
            }
        }
        if (count4 >= 50) {
            count4 = 0;
        }

    }

    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        if(z7 == 1)
        {
            entity.setVelocity(0, 1.1D, 0);
        }
      

    } 
}
