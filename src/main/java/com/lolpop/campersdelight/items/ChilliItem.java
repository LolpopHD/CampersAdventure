package com.lolpop.campersdelight.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ChilliItem extends Item {

    public ChilliItem(Settings settings) {
        super(settings);
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity player){
        ItemStack item = super.finishUsing(stack, world, player);
        if(!world.isClient){
            player.setOnFireFor(2);
        }
        return item;
    }
}
