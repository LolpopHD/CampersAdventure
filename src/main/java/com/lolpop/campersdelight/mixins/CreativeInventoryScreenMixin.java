package com.lolpop.campersdelight.mixins;

import com.lolpop.campersdelight.client.screens.BackpackSlot;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreativeInventoryScreen.class)
public abstract class CreativeInventoryScreenMixin extends AbstractInventoryScreen<CreativeInventoryScreen.CreativeScreenHandler> {
    private CreativeInventoryScreen.CreativeSlot backpackSlot;

    public CreativeInventoryScreenMixin(CreativeInventoryScreen.CreativeScreenHandler screenHandler, PlayerInventory playerInventory, Text text) {
        super(screenHandler, playerInventory, text);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void injectedInit(PlayerEntity player, CallbackInfo ci) {
        BackpackSlot slot = new BackpackSlot(player.getInventory(), BackpackSlot.BACKPACK_SLOT_ID, 127, 20);
        this.backpackSlot = slot.getCreativeVariant();
    }

    @Redirect(at = @At(value = "INVOKE", target = "net/minecraft/util/collection/DefaultedList.size()I"), method = "setSelectedTab")
    private int size(DefaultedList<ItemStack> list) {
        return 46;
    }

    @Inject(method = "setSelectedTab", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/DefaultedList;clear()V", shift = At.Shift.AFTER))
    private void injectedBackpackSlot(ItemGroup group, CallbackInfo ci) {
        if (group == ItemGroup.INVENTORY) this.handler.slots.add(this.backpackSlot);
        else this.handler.slots.remove(this.backpackSlot);
    }

    @Inject(method = "drawBackground", at = @At("TAIL"))
    private void injectedDrawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY, CallbackInfo ci) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, BackpackSlot.EMPTY_SLOT_BACKGROUND_TEXTURE);
        DrawableHelper.drawTexture(matrices, x + 126, y + 19, 18, 18, 0, 0, 18, 18, 32, 32);
    }
}
