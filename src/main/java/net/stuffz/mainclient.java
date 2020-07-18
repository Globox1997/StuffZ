
package net.stuffz;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.stuffz.init.ProviderInit;
import net.stuffz.init.RenderInit;

@Environment(EnvType.CLIENT)

public class mainclient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ProviderInit.init();
        RenderInit.init();
    }

}