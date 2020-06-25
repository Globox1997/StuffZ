package net.stuffz.block;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;

public class uncraftblockrenderer extends BlockEntityRenderer<uncraftblockentity> {

  public uncraftblockrenderer(BlockEntityRenderDispatcher dispatcher) {
    super(dispatcher);
  }

  @Override
  public void render(uncraftblockentity blockEntity, float tickDelta, MatrixStack matrices,
      VertexConsumerProvider vertexConsumers, int light, int overlay) {
    if (!blockEntity.isEmpty()) {
      matrices.push();
      matrices.translate(0.5D, 1.4D, 0.5D);
      matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((blockEntity.getWorld().getTime() + tickDelta) * 4F));
      int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
      MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(0),
          ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
      matrices.pop();
    }
  }
}