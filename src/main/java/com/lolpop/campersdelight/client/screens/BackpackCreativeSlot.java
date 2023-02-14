package com.lolpop.campersdelight.client.screens;

import com.lolpop.campersdelight.items.ItemInit;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class BackpackCreativeSlot extends CreativeInventoryScreen.CreativeSlot {
    public BackpackCreativeSlot(Slot slot, int invSlot, int x, int y) {
        super(slot, invSlot, x, y);
    }

    public Pair<Identifier, Identifier> getBackgroundSprite() {
        return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, BackpackSlot.EMPTY_BACKPACK_SLOT_TEXTURE);
    }

    public boolean canInsert(ItemStack stack) {
        return stack.isOf(ItemInit.BACKPACK);
    }
}
