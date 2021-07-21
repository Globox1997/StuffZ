package net.stuffz.block.renderer;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import net.stuffz.block.entity.UncraftBlockEntity;

public class UncraftBlockRenderer implements BlockEntityRenderer<UncraftBlockEntity> {

  public UncraftBlockRenderer(BlockEntityRenderDispatcher dispatcher) {
  }

  @Override
  public void render(UncraftBlockEntity blockEntity, float tickDelta, MatrixStack matrices,
      VertexConsumerProvider vertexConsumers, int light, int overlay) {
    BlockState state = blockEntity.getWorld().getBlockState(blockEntity.getPos());
    if (!state.isAir()) {
      Direction blockDirection = state.get(HorizontalFacingBlock.FACING);
      if (!blockEntity.isEmpty()) {
        int count = blockEntity.getStack(0).getCount() / 3;
        matrices.push();
        int lightAbove = WorldRenderer.getLightmapCoordinates(blockEntity.getWorld(), blockEntity.getPos().up());
        if (blockDirection.equals(Direction.NORTH) || blockDirection.equals(Direction.SOUTH)) {
          matrices.translate(0.5D, 1.02D, 0.4D);
          matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90F));

        } else if (blockDirection.equals(Direction.WEST) || blockDirection.equals(Direction.EAST)) {
          matrices.translate(0.6D, 1.02D, 0.5D);
          matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(90F));
          matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(90F));
        }
        ItemStack stack = blockEntity.getStack(0);
        //ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model
//        MinecraftClient.getInstance().getItemRenderer().renderItem();
//        ItemStack stack, ModelTransformation.Mode transformationType, int light, int overlay, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int seed
        MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove,
            overlay, matrices, vertexConsumers,(int)blockEntity.getPos().asLong());
        if (count > 0) {
          matrices.translate(0.0D, 0.12D, -0.05D);
          matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(10));
          matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(15));
          MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND, lightAbove,
              overlay, matrices, vertexConsumers,(int)blockEntity.getPos().asLong());
          if (count > 1) {
            matrices.translate(-0.1D, -0.32D, 0.08D);
            matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-20F));
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-20F));
            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Mode.GROUND,
                lightAbove, overlay, matrices, vertexConsumers,(int)blockEntity.getPos().asLong());
          }
        }
        matrices.pop();
      }
    }
  }
}