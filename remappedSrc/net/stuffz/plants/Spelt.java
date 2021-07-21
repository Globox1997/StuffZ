package net.stuffz.plants;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.stuffz.init.BlockInit;

public class Spelt extends CropBlock {
    public Spelt(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return BlockInit.SPELT;
    }

}