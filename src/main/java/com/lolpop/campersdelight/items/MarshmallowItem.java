package com.lolpop.campersdelight.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class MarshmallowItem extends Item implements DyeableItem {

    protected boolean giveStick;

    public MarshmallowItem(Settings settings, int hunger) {
        super(settings.food(new FoodComponent.Builder().hunger(hunger).build()));
    }
    public int getColor(ItemStack stack) {
        NbtCompound nbtCompound = stack.getSubNbt("display");
        return nbtCompound != null && nbtCompound.contains("color", 99) ? nbtCompound.getInt("color") : 0xffffff;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack item = super.finishUsing(stack, world, user);
        if(!world.isClient && giveStick && !((PlayerEntity)user).getAbilities().creativeMode){
            item = Items.STICK.getDefaultStack();
        }
        return item;
    }

    public MarshmallowItem giveStick(){
        giveStick = true;
        return this;
    }
}
