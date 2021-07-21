package net.stuffz.plants;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.stuffz.init.BlockInit;

public class Flax extends CropBlock {

    public Flax(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return BlockInit.FLAX;
    }

}