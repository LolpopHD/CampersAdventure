package com.lolpop.campersdelight.duck;

import net.minecraft.item.ItemStack;

public interface BackpackSlotDuckProvider {
    ItemStack getBackpackSlot();

    void setBackpackSlot(ItemStack stack);
}
