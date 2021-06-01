package net.stuffz.food;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.FoodComponent;

public class CactusFruit extends Item {

    public static final FoodComponent FOOD_COMPONENT = (new FoodComponent.Builder()).hunger(3).saturationModifier(0.6F)
            .meat().build();

    public CactusFruit() {
        super(new Item.Settings().food(FOOD_COMPONENT).group(ItemGroup.FOOD));
    }

}