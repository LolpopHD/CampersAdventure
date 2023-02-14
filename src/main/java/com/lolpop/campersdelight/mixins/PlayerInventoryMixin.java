package com.lolpop.campersdelight.mixins;

import com.lolpop.campersdelight.client.screens.BackpackSlot;
import com.lolpop.campersdelight.duck.BackpackSlotDuckProvider;
import com.lolpop.campersdelight.items.ItemInit;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin implements BackpackSlotDuckProvider {
    private ItemStack backpackStack = ItemStack.EMPTY;

    @Inject(method = "getStack", at = @At("HEAD"), cancellable = true)
    private void injectedGetStack(int slot, CallbackInfoReturnable<ItemStack> cir) {
        if (slot == BackpackSlot.BACKPACK_SLOT_ID) {
            cir.setReturnValue(backpackStack);
            cir.cancel();
        }
    }

    @Inject(method = "setStack", at = @At("HEAD"), cancellable = true)
    private void injectedSetStack(int slot, ItemStack stack, CallbackInfo ci) {
        if (slot == BackpackSlot.BACKPACK_SLOT_ID) {
            if (stack.isOf(ItemInit.BACKPACK))
                backpackStack = stack;
            ci.cancel();
        }
    }

    @Inject(method = "removeStack(I)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void injectedRemoveStack(int slot, CallbackInfoReturnable<ItemStack> cir) {
        if (slot == BackpackSlot.BACKPACK_SLOT_ID) {
            ItemStack a = backpackStack;
            backpackStack = ItemStack.EMPTY;
            cir.setReturnValue(a);
            cir.cancel();
        }
    }

    @Inject(method = "removeStack(II)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void injectedRemoveStackAmount(int slot, int amount, CallbackInfoReturnable<ItemStack> cir) {
        if (slot == BackpackSlot.BACKPACK_SLOT_ID) {
            ItemStack a = backpackStack;
            if (amount < 1) a = ItemStack.EMPTY;
            else backpackStack = ItemStack.EMPTY;
            cir.setReturnValue(a);
            cir.cancel();
        }
    }

    @Inject(method = "addStack(ILnet/minecraft/item/ItemStack;)I", at = @At("HEAD"), cancellable = true)
    private void injectedAddStack(int slot, ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (slot == BackpackSlot.BACKPACK_SLOT_ID) {
            if (backpackStack.isEmpty() && stack.isOf(ItemInit.BACKPACK)) {
                backpackStack = stack;
                cir.setReturnValue(0);
            } else {
                cir.setReturnValue(stack.getCount());
            }
            cir.cancel();
        }
    }

    @Inject(method = "writeNbt", at = @At("TAIL"))
    private void injectedWriteNbt(NbtList nbtList, CallbackInfoReturnable<NbtList> cir) {
        NbtCompound nbtCompound3;
        if (!backpackStack.isEmpty()) {
            nbtCompound3 = new NbtCompound();
            nbtCompound3.putByte("Slot", (byte) BackpackSlot.BACKPACK_SLOT_ID);
            backpackStack.writeNbt(nbtCompound3);
            nbtList.add(nbtCompound3);
        }
    }

    @Inject(method = "readNbt", at = @At("TAIL"))
    private void injectedReadNbt(NbtList nbtList, CallbackInfo ci) {
        backpackStack = ItemStack.EMPTY;

        for (int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            int j = nbtCompound.getByte("Slot") & 255;
            if (j == BackpackSlot.BACKPACK_SLOT_ID) {
                ItemStack itemStack = ItemStack.fromNbt(nbtCompound);
                if (!itemStack.isEmpty())
                    backpackStack = itemStack;
            }
        }
    }

    @Override
    public ItemStack getBackpackSlot() {
        return backpackStack;
    }

    @Override
    public void setBackpackSlot(ItemStack stack) {
        backpackStack = stack;
    }
}
