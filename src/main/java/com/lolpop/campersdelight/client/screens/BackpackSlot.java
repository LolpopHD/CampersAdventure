package com.lolpop.campersdelight.client.screens;

import com.lolpop.campersdelight.CampersAdventure;
import com.lolpop.campersdelight.items.ItemInit;
import com.mojang.datafixers.util.Pair;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Identifier;

public class BackpackSlot extends Slot {
    public static final Identifier EMPTY_SLOT_BACKGROUND_TEXTURE = new Identifier(CampersAdventure.MODID, "textures/gui/container/empty_slot.png");
    public static final Identifier EMPTY_BACKPACK_SLOT_TEXTURE = new Identifier(CampersAdventure.MODID, "item/empty_backpack_slot");
    public static final int BACKPACK_SLOT_ID = 240;

    public BackpackSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public Pair<Identifier, Identifier> getBackgroundSprite() {
        return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, EMPTY_BACKPACK_SLOT_TEXTURE);
    }

    public boolean canInsert(ItemStack stack) {
        return stack.isOf(ItemInit.BACKPACK);
    }
}
