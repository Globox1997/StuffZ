package net.stuffz;

import net.stuffz.init.*;
import net.fabricmc.api.ModInitializer;

public class StuffMain implements ModInitializer {

    @Override
    public void onInitialize() {
        ConfigInit.init();
        BlockInit.init();
        ItemInit.init();
        LootInit.init();
        RecipeInit.init();
        SoundInit.init();
        TagInit.init();
        FeatureInit.init();

    }

}

// You are LOVED!!!
// Jesus loves you unconditionaly!