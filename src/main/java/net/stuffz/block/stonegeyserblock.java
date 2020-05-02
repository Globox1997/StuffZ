package net.stuffz.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
    int z7;

    public stonegeyserblock(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {

        Random random1 = new Random();
        Random random3 = new Random();
        Random random5 = new Random();
        int z1 = (random1.nextInt() % 10);
        float z2 = z1 / 70;
        int z5 = random3.nextInt() % 10;
        float z6 = z5 / 70;
        z7 = (random5.nextInt() % 30);

        if (z6 % 2 == 0) {
            world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.5F + z2, pos.getY() + 1F, pos.getZ() + 0.5F + z6,
                    0.0D, 0.0D, 0.0D);
        }

        if (z7 == 1) {
            world.playSound(pos.getX(), pos.getY(), pos.getZ(), main.GEYSEREVENT, SoundCategory.AMBIENT, 1F, 1F, true);
            while (count4 < 50) {
                count5 = count4 / 10D;

                world.addParticle(ParticleTypes.CLOUD, pos.getX() + 0.5D, pos.getY() + count5 + 1D, pos.getZ() + 0.5D,
                        0.0D, 0.0D, 0.0D);
                count4++;
            }
        }

        if (count4 >= 50) {
            count4 = 0;
        }

    }

    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        if (z7 == 1 && world.isClient) {
            entity.setVelocity(0, 1.1D, 0);
        }

    }

    public void onStacksDropped(BlockState state, World world, BlockPos pos, ItemStack stack) {
        super.onStacksDropped(state, world, pos, stack);
        ItemStack geyserdrop = new ItemStack(main.STONEGEYSERBLOCK);
        ItemStack netherrackdrop = new ItemStack(Items.STONE);
        if (!world.isClient) {
            if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 1) {
                Block.dropStack(world, pos, geyserdrop);
            } else {
                Block.dropStack(world, pos, netherrackdrop);
            }
        }
    }
}
