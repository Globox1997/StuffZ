package net.stuffz.block;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;
import net.stuffz.init.BlockInit;
import net.stuffz.init.ItemInit;
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

  public void update() {

    if (!isEmpty()) {
      if (this.isuncrafthit() && this instanceof UncraftBlockEntity) {
        ItemStack itemStack = this.getStack(0);
        unCraft(itemStack);
        this.unCraftHit = 0;
        markDirty();
      }
    }
  }

  public boolean isuncrafthit() {
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

  private void unCraft(ItemStack itemStack) {
    // Chain
    if (itemStack.isItemEqual(new ItemStack(Items.CHAINMAIL_CHESTPLATE))) {
      this.clear();
      this.setStack(0, new ItemStack(ItemInit.CHAINMAILPLATE, 8));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.CHAINMAIL_LEGGINGS))) {
      this.clear();
      this.setStack(0, new ItemStack(ItemInit.CHAINMAILPLATE, 7));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.CHAINMAIL_HELMET))) {
      this.clear();
      this.setStack(0, new ItemStack(ItemInit.CHAINMAILPLATE, 5));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.CHAINMAIL_BOOTS))) {
      this.clear();
      this.setStack(0, new ItemStack(ItemInit.CHAINMAILPLATE, 4));
    }
    // Diamond
    if (itemStack.isItemEqual(new ItemStack(Items.DIAMOND_CHESTPLATE))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.DIAMOND, 8));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.DIAMOND_LEGGINGS))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.DIAMOND, 7));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.DIAMOND_HORSE_ARMOR))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.DIAMOND, 6));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.DIAMOND_HELMET))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.DIAMOND, 5));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.DIAMOND_BOOTS))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.DIAMOND, 4));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.DIAMOND_AXE))
        || itemStack.isItemEqual(new ItemStack(Items.DIAMOND_PICKAXE))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.DIAMOND, 3));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.DIAMOND_HOE))
        || itemStack.isItemEqual(new ItemStack(Items.DIAMOND_SWORD))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.DIAMOND, 2));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.DIAMOND_SHOVEL))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.DIAMOND, 1));
    }
    // Gold
    if (itemStack.isItemEqual(new ItemStack(Items.GOLDEN_CHESTPLATE))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.GOLD_INGOT, 8));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.GOLDEN_LEGGINGS))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.GOLD_INGOT, 7));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.GOLDEN_HORSE_ARMOR))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.GOLD_INGOT, 6));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.GOLDEN_HELMET))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.GOLD_INGOT, 5));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.GOLDEN_BOOTS)) || itemStack.isItemEqual(new ItemStack(Items.CLOCK))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.GOLD_INGOT, 4));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.GOLDEN_AXE))
        || itemStack.isItemEqual(new ItemStack(Items.GOLDEN_PICKAXE))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.GOLD_INGOT, 3));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.GOLDEN_HOE))
        || itemStack.isItemEqual(new ItemStack(Items.GOLDEN_SWORD))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.GOLD_INGOT, 2));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.GOLDEN_SHOVEL))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.GOLD_INGOT, 1));
    }
    // Iron
    if (itemStack.isItemEqual(new ItemStack(Items.IRON_CHESTPLATE))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.IRON_INGOT, 8));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.IRON_LEGGINGS))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.IRON_INGOT, 7));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.IRON_HORSE_ARMOR))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.IRON_INGOT, 6));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.IRON_HELMET))
        || itemStack.isItemEqual(new ItemStack(Items.MINECART))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.IRON_INGOT, 5));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.IRON_BOOTS)) || itemStack.isItemEqual(new ItemStack(Items.COMPASS))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.IRON_INGOT, 4));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.IRON_AXE))
        || itemStack.isItemEqual(new ItemStack(Items.IRON_PICKAXE))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.IRON_INGOT, 3));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.IRON_HOE)) || itemStack.isItemEqual(new ItemStack(Items.IRON_SWORD))
        || itemStack.isItemEqual(new ItemStack(Items.BUCKET)) || itemStack.isItemEqual(new ItemStack(Items.SHEARS))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.IRON_INGOT, 2));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.IRON_SHOVEL))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.IRON_INGOT, 1));
    }
    // Leather
    if (itemStack.isItemEqual(new ItemStack(Items.LEATHER_CHESTPLATE))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.LEATHER, 8));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.LEATHER_LEGGINGS))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.LEATHER, 7));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.LEATHER_HELMET))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.LEATHER, 5));
    }
    if (itemStack.isItemEqual(new ItemStack(Items.LEATHER_BOOTS))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.LEATHER, 4));
    }
    // Netherite
    if (itemStack.isItemEqual(new ItemStack(Items.NETHERITE_CHESTPLATE))
        || itemStack.isItemEqual(new ItemStack(Items.NETHERITE_LEGGINGS))
        || itemStack.isItemEqual(new ItemStack(Items.NETHERITE_HELMET))
        || itemStack.isItemEqual(new ItemStack(Items.NETHERITE_BOOTS))
        || itemStack.isItemEqual(new ItemStack(Items.NETHERITE_AXE))
        || itemStack.isItemEqual(new ItemStack(Items.NETHERITE_PICKAXE))
        || itemStack.isItemEqual(new ItemStack(Items.NETHERITE_HOE))
        || itemStack.isItemEqual(new ItemStack(Items.NETHERITE_SWORD))
        || itemStack.isItemEqual(new ItemStack(Items.NETHERITE_SHOVEL))) {
      this.clear();
      this.setStack(0, new ItemStack(Items.NETHERITE_INGOT, 1));
    }
    // Trident
    if (itemStack.isItemEqual(new ItemStack(Items.TRIDENT))) {
      this.clear();
      this.setStack(0, new ItemStack(ItemInit.SHINYDIAMOND, 1));
    }
  }
}