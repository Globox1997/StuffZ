package net.stuffz.init;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

public class ProviderInit {

  public static void init() {
    FabricModelPredicateProviderRegistry.register(new Identifier("active"), (stack, world, entity) -> {
      if (stack.hasTag()) {
        if (stack.getTag().getBoolean("activeruby")) {
          return 1F;
        }
      }
      return 0F;
    });
  }

}