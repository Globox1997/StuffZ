package net.stuffz.plants;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.stuffz.init.BlockInit;

public class flax extends CropBlock {

    public flax(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return BlockInit.FLAX;
    }

}