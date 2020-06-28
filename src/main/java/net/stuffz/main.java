package net.stuffz;

import net.stuffz.init.*;
import net.fabricmc.api.ModInitializer;

public class main implements ModInitializer {

      @Override
      public void onInitialize() {
            BlockInit.init();
            ItemInit.init();
            LootInit.init();
            SoundInit.init();
            TagInit.init();
            FeatureInit features = new FeatureInit();
            features.init();

      }

}

// System.out.println("hastag" + stack.hasTag());

// You are LOVED!!!
// Jesus loves you unconditionaly!