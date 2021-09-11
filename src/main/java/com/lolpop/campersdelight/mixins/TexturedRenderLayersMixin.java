package com.lolpop.campersdelight.mixins;

import com.lolpop.campersdelight.client.screens.BackpackSlot;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {
    @Inject(method = "addDefaultTextures", at = @At("TAIL"))
    private static void injectedDefaultTextures(Consumer<SpriteIdentifier> adder, CallbackInfo ci) {
        adder.accept(new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, BackpackSlot.EMPTY_BACKPACK_SLOT_TEXTURE));
    }
}
