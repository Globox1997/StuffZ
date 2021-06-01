package net.stuffz.plants;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.stuffz.init.BlockInit;

public class Hop extends CropBlock {

    public Hop(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return BlockInit.HOP;
    }

}