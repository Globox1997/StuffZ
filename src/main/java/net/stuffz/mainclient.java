
package net.stuffz;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)

public class mainclient implements ClientModInitializer {

    @Override

    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(main.SPELT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(main.HOP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(main.MALT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(main.NUTSBUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(main.FLAX, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(main.ENDERBLOCK, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(main.ADENDERBLOCK, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(main.HEALBLOCK, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(main.NETHERGEYSERBLOCK, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(main.STONEGEYSERBLOCK, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(main.SULFURBLOCK, RenderLayer.getSolid());
        BlockRenderLayerMap.INSTANCE.putBlock(main.IRONBUSH, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(main.GOLDBUSH, RenderLayer.getCutout());

    }

}