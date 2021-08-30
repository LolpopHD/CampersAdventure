package com.lolpop.campersdelight.items;

import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class BackpackItem extends BlockItem implements Wearable {
    public BackpackItem(Block block, Settings settings) {
        super(block, settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }

    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA;
    }

    public boolean canBeNested() {
        return false;
    }
}
