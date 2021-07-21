package net.stuffz.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ShinyDiamond extends Item {

    public ShinyDiamond(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}