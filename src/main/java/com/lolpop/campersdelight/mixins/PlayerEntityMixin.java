package com.lolpop.campersdelight.mixins;

import com.lolpop.campersdelight.duck.BackpackSlotDuckProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin implements BackpackSlotDuckProvider {
    @Shadow
    @Final
    private PlayerInventory inventory;

    @Override
    public ItemStack getBackpackSlot() {
        return inventory instanceof BackpackSlotDuckProvider duckProvider ? duckProvider.getBackpackSlot() : ItemStack.EMPTY;
    }

    @Override
    public void setBackpackSlot(ItemStack stack) {
        if (inventory instanceof BackpackSlotDuckProvider duckProvider)
            duckProvider.setBackpackSlot(stack);
    }
}
