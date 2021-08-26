package com.lolpop.campersdelight.blocks;

import com.lolpop.campersdelight.CampersAdventure;
import com.lolpop.campersdelight.items.BackpackItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

public class BackpackBlockEntity extends LootableContainerBlockEntity implements SidedInventory {


    private DefaultedList<ItemStack> inventory;
    private static final int[] AVAILABLE_SLOTS = IntStream.range(0, 18).toArray();

    public BackpackBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(CampersAdventure.BACKPACK_ENITY, blockPos, blockState);
        this.inventory = DefaultedList.ofSize(18, ItemStack.EMPTY);
    }


    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inventory;
    }

    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inventory = list;
    }

    protected Text getContainerName() {
        return new TranslatableText("container.campersdelight.backpack");
    }

    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new BackpackScreenHandler(syncId, playerInventory, this);
    }

    public int size() {
        return this.inventory.size();
    }

    public int[] getAvailableSlots(Direction side) {
        return AVAILABLE_SLOTS;
    }

    public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
        return !(stack.getItem() instanceof BackpackItem);
    }

    public boolean canExtract(int slot, ItemStack stack, Direction dir) {
        return true;
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        this.readInventoryNbt(nbt);
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        return this.writeInventoryNbt(nbt);
    }

    public void readInventoryNbt(NbtCompound nbt) {
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(nbt) && nbt.contains("Items", 9)) {
            Inventories.readNbt(nbt, this.inventory);
        }

    }

    public NbtCompound writeInventoryNbt(NbtCompound nbt) {
        if (!this.serializeLootTable(nbt)) {
            Inventories.writeNbt(nbt, this.inventory, false);
        }

        return nbt;
    }
}
