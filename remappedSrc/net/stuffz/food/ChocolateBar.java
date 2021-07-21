package net.stuffz.food;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.FoodComponent;

public class ChocolateBar extends Item {

    public static final FoodComponent FOOD_COMPONENT = (new FoodComponent.Builder()).hunger(4).saturationModifier(1.2F)
            .meat().build();

    public ChocolateBar() {
        super(new Item.Settings().food(FOOD_COMPONENT).group(ItemGroup.FOOD));
    }

}