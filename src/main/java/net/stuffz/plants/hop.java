package net.stuffz.plants;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.stuffz.main;

public class hop extends CropBlock {

    public hop(Settings settings) {
        super(settings);

    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return main.HOP;
    }

}