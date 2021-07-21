package net.stuffz.feature;

import com.mojang.serialization.Codec;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.stuffz.init.BlockInit;
import net.stuffz.init.ConfigInit;
import net.stuffz.plants.IronBush;

public class IronBushFeature extends Feature<DefaultFeatureConfig> {

    public IronBushFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        if (!ConfigInit.CONFIG.generate_bushes) {
            return false;
        }
        BlockPos topPos = context.getWorld().getTopPosition(Heightmap.Type.WORLD_SURFACE, context.getOrigin());
        Boolean isAirOrGrass;
        Boolean isGrassBlock;
        int booleanCount = 0;
        for (int i = -1; i < 2; i++) {
            for (int u = -1; u < 2; u++) {
                isGrassBlock = context.getWorld().getBlockState(topPos.north(i).east(u).down()).getBlock().equals(Blocks.GRASS_BLOCK);
                isAirOrGrass = context.getWorld().getBlockState(topPos.north(i).east(u)).isAir() || context.getWorld().getBlockState(topPos.north(i).east(u)).getBlock().equals(Blocks.GRASS);
                if (isGrassBlock && isAirOrGrass) {
                    booleanCount++;
                }
                if (booleanCount == 9) {
                    int randomInt = context.getWorld().getRandom().nextInt(4) + 1;
                    for (int k = 0; k < randomInt; k++) {
                        int randomIntEast = context.getWorld().getRandom().nextInt(3) - 1;
                        int randomIntSouth = context.getWorld().getRandom().nextInt(3) - 1;
                        context.getWorld().setBlockState(topPos.east(randomIntEast).south(randomIntSouth), BlockInit.IRONBUSH.getDefaultState().with(IronBush.AGE, 3), 3);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
