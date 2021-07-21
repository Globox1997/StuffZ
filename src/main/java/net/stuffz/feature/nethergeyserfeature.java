package net.stuffz.feature;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.stuffz.init.BlockInit;
import net.stuffz.init.ConfigInit;

public class NetherGeyserFeature extends Feature<DefaultFeatureConfig> {

    public NetherGeyserFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        if (!ConfigInit.CONFIG.generate_geysers) {
            return false;
        }
        Boolean isNetherrack;
        Boolean isAir;
        BlockPos pos = context.getOrigin();
        for (int k = 0; k < 254; k++) {
            isNetherrack = context.getWorld().getBlockState(pos.up(k)).getBlock().equals(Blocks.NETHERRACK);
            isAir = context.getWorld().getBlockState(pos.up(k + 1)).isAir();
            if (isNetherrack && isAir) {
                int booleanCount = 0;
                for (int i = -1; i < 2; i++) {
                    for (int u = -1; u < 2; u++) {
                        isNetherrack = context.getWorld().getBlockState(pos.north(i).east(u).up(k)).getBlock().equals(Blocks.NETHERRACK);
                        isAir = context.getWorld().getBlockState(pos.north(i).east(u).up(k + 1)).isAir();
                        if (isNetherrack && isAir) {
                            booleanCount++;
                        }
                        if (booleanCount == 9) {
                            context.getWorld().setBlockState(pos.up(k), BlockInit.NETHERGEYSERBLOCK.getDefaultState(), 3);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
