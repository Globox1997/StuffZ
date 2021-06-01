package net.stuffz.block.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import net.stuffz.init.BlockInit;
import net.stuffz.init.RecipeInit;
import net.minecraft.util.Tickable;

public class UncraftBlockEntity extends BlockEntity implements Tickable, Inventory, BlockEntityClientSerializable {
  private DefaultedList<ItemStack> inventory;
  public int unCraftHit;

  public UncraftBlockEntity() {
    super(BlockInit.UNCRAFTBLOCKENTITY);
    this.inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
  }

  @Override
  public void fromTag(BlockState state, CompoundTag tag) {
    super.fromTag(state, tag);
    inventory.clear();
    Inventories.fromTag(tag, inventory);
  }

  @Override
  public CompoundTag toTag(CompoundTag tag) {
    super.toTag(tag);
    Inventories.toTag(tag, inventory);
    return tag;
  }

  @Override
  public void tick() {
    this.update();
  }

  private void update() {
    if (!isEmpty()) {
      if (this.isuncrafthit()) {
        unCraftRecipe(this.getStack(0));
        this.unCraftHit = 0;
        markDirty();
      }
    }
  }

  private boolean isuncrafthit() {
    return getuncrafthit() > 2;
  }

  public int getuncrafthit() {
    return unCraftHit;
  }

  public void setuncrafthit(int hit) {
    this.unCraftHit = hit;
  }

  @Override
  public void markDirty() {
    super.markDirty();
    sendUpdate();
  }

  private void sendUpdate() {
    if (this.world != null) {
      BlockState state = this.world.getBlockState(this.pos);
      (this.world).updateListeners(this.pos, state, state, 3);
    }
  }

  @Override
  public void clear() {
    this.inventory.clear();
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean isEmpty() {
    return this.getStack(0).isEmpty();
  }

  @Override
  public ItemStack getStack(int slot) {
    return this.inventory.get(0);
  }

  @Override
  public ItemStack removeStack(int slot, int amount) {
    ItemStack result = Inventories.splitStack(this.inventory, slot, 1);
    if (!result.isEmpty()) {
      markDirty();
    }
    return result;
  }

  @Override
  public ItemStack removeStack(int slot) {
    this.markDirty();
    return Inventories.removeStack(this.inventory, slot);
  }

  @Override
  public void setStack(int slot, ItemStack stack) {
    this.inventory.set(0, stack);
    this.markDirty();
  }

  @Override
  public boolean canPlayerUse(PlayerEntity player) {
    return true;
  }

  @Override
  public void fromClientTag(CompoundTag tag) {
    inventory.clear();
    Inventories.fromTag(tag, inventory);
  }

  @Override
  public CompoundTag toClientTag(CompoundTag tag) {
    super.toTag(tag);
    Inventories.toTag(tag, inventory);
    return tag;
  }

  private void unCraftRecipe(ItemStack itemStack) {
    int index = RecipeInit.UNCRAFT_ITEM_LIST.indexOf(itemStack.getItem());
    this.clear();
    this.setStack(0,
        new ItemStack(RecipeInit.UNCRAFT_RESULT_ITEM_LIST.get(index), RecipeInit.UNCRAFT_RESULT_COUNT_LIST.get(index)));
  }

}