package net.stuffz.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Direction;

public class brewingbarrelrenderer extends BlockEntityRenderer<brewingbarrelentity> {

  public brewingbarrelrenderer(BlockEntityRenderDispatcher dispatcher) {
    super(dispatcher);
  }

  @Override
  public void render(brewingbarrelentity blockEntity, float tickDelta, MatrixStack matrices,
      VertexConsumerProvider vertexConsumers, int light, int overlay) {
    BlockState state = blockEntity.getWorld().getBlockState(blockEntity.getPos());
    Direction blockDirection = state.get(HorizontalFacingBlock.FACING);
    if (blockEntity.hasAxe) {
      if (blockDirection.equals(Direction.NORTH) || blockDirection.equals(Direction.SOUTH)) {
        matrices.push();
        matrices.translate(0.5D, 0.5D, 1D);
        // matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
        MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(0),
            ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
        matrices.pop();
      }
      if (blockDirection.equals(Direction.WEST) || blockDirection.equals(Direction.EAST)) {
        matrices.push();
        matrices.translate(1D, 0.5D, 0.5D);
        // matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90));
        matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90));
        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
        MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(0),
            ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
        matrices.pop();
      }

    }

  }
}