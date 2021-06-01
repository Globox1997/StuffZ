package net.stuffz.util;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.Item;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.stuffz.init.RecipeInit;

public class UncraftRecipeLoader implements SimpleSynchronousResourceReloadListener {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Identifier getFabricId() {
        return new Identifier("stuffz", "uncraft_recipes");
    }

    @Override
    public void apply(ResourceManager manager) {
        for (Identifier id : manager.findResources("uncraft_recipes", path -> path.endsWith(".json"))) {
            try {
                InputStream stream = manager.getResource(id).getInputStream();
                JsonObject data = new JsonParser().parse(new InputStreamReader(stream)).getAsJsonObject();
                RecipeInit.UNCRAFT_ITEM_LIST
                        .add((Item) Registry.ITEM.get(new Identifier(data.get("item").getAsString())));
                RecipeInit.UNCRAFT_RESULT_ITEM_LIST
                        .add((Item) Registry.ITEM.get(new Identifier(data.get("result").getAsString())));
                RecipeInit.UNCRAFT_RESULT_COUNT_LIST.add(data.get("count").getAsInt());

            } catch (Exception e) {
                LOGGER.error("Error occurred while loading resource {}. {}", id.toString(), e.toString());
            }
        }
    }

}