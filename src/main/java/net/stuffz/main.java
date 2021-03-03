package net.stuffz;

import net.stuffz.init.*;
import net.fabricmc.api.ModInitializer;

public class main implements ModInitializer {

      @Override
      public void onInitialize() {
            ConfigInit.init();
            BlockInit.init();
            ItemInit.init();
            LootInit.init();
            SoundInit.init();
            TagInit.init();
            FeatureInit.init();

      }

}

// System.out.println("hastag" + stack.hasTag());

// You are LOVED!!!
// Jesus loves you unconditionaly!