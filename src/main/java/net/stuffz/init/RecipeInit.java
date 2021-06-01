package net.stuffz.init;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceType;
import net.stuffz.util.UncraftRecipeLoader;

public class RecipeInit {
    public static final List<Item> UNCRAFT_ITEM_LIST = new ArrayList<>();
    public static final List<Item> UNCRAFT_RESULT_ITEM_LIST = new ArrayList<>();
    public static final List<Integer> UNCRAFT_RESULT_COUNT_LIST = new ArrayList<>();

    public static void init() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new UncraftRecipeLoader());
    }
}