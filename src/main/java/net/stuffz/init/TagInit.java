package net.stuffz.init;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagInit {

    public static final Tag<Item> AXE_ITEMS = TagFactory.ITEM.create(new Identifier("stuffz", "axe_items"));

    public static void init() {
    }

}