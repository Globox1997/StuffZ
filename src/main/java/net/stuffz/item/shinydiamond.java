package net.stuffz.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class shinydiamond extends Item {

    public shinydiamond(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasEnchantmentGlint(ItemStack stack) {
        return true;
    }
}