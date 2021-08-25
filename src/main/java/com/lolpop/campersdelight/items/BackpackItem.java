package com.lolpop.campersdelight.items;

import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.item.*;

public class BackpackItem extends BlockItem implements Wearable {
    public BackpackItem(Block block, Settings settings) {
        super(block, settings);
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
    }
}
