package net.stuffz.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.stuffz.init.BlockInit;
import net.stuffz.init.SoundInit;

import java.util.Random;

public class nethergeyserblock extends Block {
    int count = 0;
    int count2 = 0;
    double count3 = 0;

    public nethergeyserblock(Settings settings) {
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

        if (z6 % 2 == 0 && world.isClient) {
            world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5D + z2, pos.getY() + 1D, pos.getZ() + 0.5D + z6,
                    0.0D, 0.0D, 0.0D);
        }

    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        if (entity.isAttackable()) {
            if (world.isClient) {
                entity.setVelocity(0, 1.2D, 0);
                world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundInit.GEYSER_EVENT, SoundCategory.AMBIENT, 1F,
                        1F, true);
                while (count2 < 60) {
                    Random random1 = new Random();
                    Random random2 = new Random();
                    Random random3 = new Random();
                    Random random4 = new Random();
                    double z1 = (random1.nextInt() % 21);
                    double z2 = z1 / 100;
                    if (z2 < 0) {
                        z2 = z2 * (-1);
                    }
                    double z3 = random2.nextInt() % 21;
                    double z4 = z3 / 100;
                    if (z4 < 0) {
                        z4 = z4 * (-1);
                    }
                    double z5 = random3.nextInt() % 21;
                    double z6 = z5 / 100;
                    if (z6 < 0) {
                        z6 = z6 * (-1);
                    }
                    double z7 = random4.nextInt() % 21;
                    double z8 = z7 / 100;
                    if (z8 < 0) {
                        z8 = z8 * (-1);
                    }

                    count3 = count2 / 10D;
                    world.addParticle(ParticleTypes.CLOUD, pos.getX() + 0.5D, pos.getY() + count3 + 1D,
                            pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
                    if (count2 < 10) {
                        world.addParticle(ParticleTypes.FLAME, pos.getX() + 0.3D + z2, pos.getY() + 1D,
                                pos.getZ() + 0.3D + z4, 0.0D, 0.0D, 0.0D);
                    }
                    if (count2 < 25) {
                        world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.3D + z8, pos.getY() + count3 + 1D,
                                pos.getZ() + 0.3D + z6, 0.0D, 0.0D, 0.0D);
                    }
                    count2++;
                }
                if (count2 >= 60) {
                    count2 = 0;
                }
            }
        }
    }

    @Override
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        ItemStack geyserdrop = new ItemStack(BlockInit.NETHERGEYSERBLOCK);
        ItemStack netherrackdrop = new ItemStack(Items.NETHERRACK);
        if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 1) {
            Block.dropStack(world, pos, geyserdrop);
        } else {
            Block.dropStack(world, pos, netherrackdrop);
        }
    }
}
