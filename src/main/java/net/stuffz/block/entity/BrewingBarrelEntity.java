package net.stuffz.block.entity;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.stuffz.init.BlockInit;
import net.stuffz.init.ItemInit;

import java.util.Set;

public class BrewingBarrelEntity extends BlockEntity implements Inventory, BlockEntityClientSerializable {
    private DefaultedList<ItemStack> inventory;
    private int brewing = 120;
    private int waterdrip = 0;

    public BrewingBarrelEntity(BlockPos pos, BlockState state) {
        super(BlockInit.BREWINGBARRELENTITY, pos, state);
        this.inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
    }


    // slot 0 = axeslot
    // slot 1 = bottleslot
    // slot 2 = maltslot
    // slot 3 = hopsslot
    // slot 4 = beerslot

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        inventory.clear();
        Inventories.readNbt(nbt, inventory);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        return nbt;
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, BrewingBarrelEntity blockEntity) {
        blockEntity.update();
    }

    public static void serverTick(World world, BlockPos pos, BlockState state, BrewingBarrelEntity blockEntity) {
        blockEntity.update();
    }

    public void update() {
        if (!this.getStack(0).isEmpty() && !this.getStack(1).isEmpty() && !this.getStack(2).isEmpty()
                && !this.getStack(3).isEmpty() && this.getStack(4).isEmpty()) {
            if (this.brewing > 0) {
                this.brewing--;
            }
        }
        if (!world.isClient && this.brewing == 0) {
            this.getStack(1).decrement(1);
            this.getStack(2).decrement(1);
            this.getStack(3).decrement(1);
            world.playSound(null, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 0.4F, 1F);
            if (this.getStack(2).equals(new ItemStack(ItemInit.DARKMALT))) {
                this.setStack(4, new ItemStack(ItemInit.DARKBEER));
            } else {
                this.setStack(4, new ItemStack(ItemInit.BEER));
            }
            this.brewing = 60;
            markDirty();
        }
        BlockState state = this.getWorld().getBlockState(this.getPos());
        Direction blockDirection = state.get(HorizontalFacingBlock.FACING);
        if (!getStack(0).isEmpty() && getStack(1).isEmpty() && getStack(4).isEmpty()) {
            this.waterdrip++;
            if (waterdrip == 20 && world.isClient) {
                if (blockDirection.equals(Direction.NORTH)) {
                    world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + 0.98D, pos.getY() + 0.37D, pos.getZ() + 0.5D,
                            0.0D, 0.0D, 0.0D);
                    waterdrip = 0;
                }
                if (blockDirection.equals(Direction.SOUTH)) {
                    world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + 0.02D, pos.getY() + 0.37D, pos.getZ() + 0.5D,
                            0.0D, 0.0D, 0.0D);
                    waterdrip = 0;
                }
                if (blockDirection.equals(Direction.EAST)) {
                    world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + 0.5D, pos.getY() + 0.37D, pos.getZ() + 0.98D,
                            0.0D, 0.0D, 0.0D);
                    waterdrip = 0;
                }
                if (blockDirection.equals(Direction.WEST)) {
                    world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + 0.5D, pos.getY() + +0.37D, pos.getZ() + 0.02D,
                            0.0D, 0.0D, 0.0D);
                    waterdrip = 0;
                }
            }

        }
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
        this.markDirty();
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
        return this.inventory.get(slot);
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
        this.inventory.set(slot, stack);
        this.markDirty();
    }

    @Override
    public int getMaxCountPerStack() {
        return Inventory.super.getMaxCountPerStack();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onOpen(PlayerEntity player) {
        Inventory.super.onOpen(player);
    }

    @Override
    public void onClose(PlayerEntity player) {
        Inventory.super.onClose(player);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return Inventory.super.isValid(slot, stack);
    }

    @Override
    public int count(Item item) {
        return Inventory.super.count(item);
    }

    @Override
    public boolean containsAny(Set<Item> items) {
        return Inventory.super.containsAny(items);
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        inventory.clear();
        Inventories.readNbt(tag, inventory);
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        Inventories.writeNbt(tag, inventory);
        return tag;
    }

    @Override
    public void sync() {
        BlockEntityClientSerializable.super.sync();
    }

}