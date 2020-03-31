package net.stuffz.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.stuffz.main;

public class shinydiamond extends Item {

    public shinydiamond(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasEnchantmentGlint(ItemStack stack) {
        return stack.getItem() == main.SHINYDIAMOND;
    }
}