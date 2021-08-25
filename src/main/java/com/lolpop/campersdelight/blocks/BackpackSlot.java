package com.lolpop.campersdelight.blocks;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class BackpackSlot extends Slot {
    public BackpackSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public boolean canInsert(ItemStack stack) {
        return stack.getItem().canBeNested();
    }
}
