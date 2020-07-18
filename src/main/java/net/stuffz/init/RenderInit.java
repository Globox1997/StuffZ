package net.stuffz.init;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.stuffz.block.*;

public class RenderInit {

  public static void init() {
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SPELT, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.HOP, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.MALT, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.NUTSBUSH, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.FLAX, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.ENDERBLOCK, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.ADENDERBLOCK, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.HEALBLOCK, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.NETHERGEYSERBLOCK, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.STONEGEYSERBLOCK, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SULFURBLOCK, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.IRONBUSH, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.GOLDBUSH, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.DRIEDCLAY, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.UNCRAFTBLOCK, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.IRONLADDER, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.BREWINGBARREL, RenderLayer.getCutout());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.YELLOWRUBYORE, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.YELLOWRUBYBLOCK, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.FOSSILBLOCK, RenderLayer.getSolid());
    BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.SULFURBLOCK, RenderLayer.getSolid());
    BlockEntityRendererRegistry.INSTANCE.register(BlockInit.UNCRAFTBLOCKENTITY, uncraftblockrenderer::new);
    BlockEntityRendererRegistry.INSTANCE.register(BlockInit.BREWINGBARRELENTITY, brewingbarrelrenderer::new);
  }

}