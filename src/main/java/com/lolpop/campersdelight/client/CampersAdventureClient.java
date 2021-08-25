package com.lolpop.campersdelight.client;

import com.lolpop.campersdelight.CampersAdventure;
import com.lolpop.campersdelight.blocks.BlockInit;
import com.lolpop.campersdelight.client.models.BackpackModel;
import com.lolpop.campersdelight.client.screens.BackpackScreen;
import com.lolpop.campersdelight.items.ItemInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.item.DyeableItem;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CampersAdventureClient implements ClientModInitializer {

    public static final EntityModelLayer BACKPACK_LAYER = new EntityModelLayer(new Identifier(CampersAdventure.MODID, "backpack"), "main");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), BlockInit.CHILLICROP);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem)stack.getItem()).getColor(stack), ItemInit.MARSHMALLOW);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? ((DyeableItem)stack.getItem()).getColor(stack) : 0xffffff, ItemInit.MARSHMALLOW_STICK);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? ((DyeableItem)stack.getItem()).getColor(stack) : 0xffffff, ItemInit.MARSHMALLOW_MELTY);

        ScreenRegistry.register(CampersAdventure.BACKPACK_SCREEN_HANLDER, BackpackScreen::new);
        EntityModelLayerRegistry.registerModelLayer(BACKPACK_LAYER, BackpackModel::getTexturedModelData);
    }
}
