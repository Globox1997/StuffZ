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
    if (!state.isAir()) {
      Direction blockDirection = state.get(HorizontalFacingBlock.FACING);
      int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
      if (blockDirection.equals(Direction.EAST)) {
        if (!blockEntity.getStack(0).isEmpty()) {
          matrices.push();
          matrices.translate(0.5D, 0.55D, 1.2D);
          matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(-90F));
          matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(0),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
        if (!blockEntity.getStack(1).isEmpty()) {
          matrices.push();
          matrices.translate(0.5D, 0.26D, 0.96D);
          matrices.scale(0.8F, 0.8F, 0.8F);
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(1),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
        if (!blockEntity.getStack(4).isEmpty()) {
          matrices.push();
          matrices.translate(0.5D, 0.26D, 0.96D);
          matrices.scale(0.8F, 0.8F, 0.8F);
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(4),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
      }
      if (blockDirection.equals(Direction.WEST)) {
        if (!blockEntity.getStack(0).isEmpty()) {
          matrices.push();
          matrices.translate(0.5D, 0.55D, -0.2D);
          matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90F));
          matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(0),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
        if (!blockEntity.getStack(1).isEmpty()) {
          matrices.push();
          matrices.translate(0.5D, 0.26D, 0.04D);
          matrices.scale(0.8F, 0.8F, 0.8F);
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(1),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
        if (!blockEntity.getStack(4).isEmpty()) {
          matrices.push();
          matrices.translate(0.5D, 0.26D, 0.04D);
          matrices.scale(0.8F, 0.8F, 0.8F);
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(4),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
      }
      if (blockDirection.equals(Direction.NORTH)) {
        if (!blockEntity.getStack(0).isEmpty()) {
          matrices.push();
          matrices.translate(1.2D, 0.55D, 0.5D);
          matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(0),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
        if (!blockEntity.getStack(1).isEmpty()) {
          matrices.push();
          matrices.translate(0.96D, 0.26D, 0.5D);
          matrices.scale(0.8F, 0.8F, 0.8F);
          matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90F));
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(1),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
        if (!blockEntity.getStack(4).isEmpty()) {
          matrices.push();
          matrices.translate(0.96D, 0.26D, 0.5D);
          matrices.scale(0.8F, 0.8F, 0.8F);
          matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90F));
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(4),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
      }
      if (blockDirection.equals(Direction.SOUTH)) {
        if (!blockEntity.getStack(0).isEmpty()) {
          matrices.push();
          matrices.translate(-0.2D, 0.5D, 0.5D);
          matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180F));
          matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(90F));
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(0),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
        if (!blockEntity.getStack(1).isEmpty()) {
          matrices.push();
          matrices.translate(0.04D, 0.26D, 0.5D);
          matrices.scale(0.8F, 0.8F, 0.8F);
          matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90F));
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(1),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
        if (!blockEntity.getStack(4).isEmpty()) {
          matrices.push();
          matrices.translate(0.04D, 0.26D, 0.5D);
          matrices.scale(0.8F, 0.8F, 0.8F);
          matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(90F));
          MinecraftClient.getInstance().getItemRenderer().renderItem(blockEntity.getStack(4),
              ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
          matrices.pop();
        }
      }
    }
  }
}