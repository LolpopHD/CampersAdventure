package com.lolpop.campersdelight;

import com.lolpop.campersdelight.blocks.BackpackBlockEntity;
import com.lolpop.campersdelight.blocks.BackpackScreenHandler;
import com.lolpop.campersdelight.blocks.BlockInit;
import com.lolpop.campersdelight.items.ItemInit;
import com.lolpop.campersdelight.recipes.MarshmallowRecipe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CampersAdventure implements ModInitializer {

    public static final String MODID = "campersdelight";
    public static RecipeSerializer<MarshmallowRecipe> MARSHMALLOW_RECIPE_SERIALIZER;
    public static BlockEntityType<BackpackBlockEntity> BACKPACK_ENITY;
    public static ScreenHandlerType<BackpackScreenHandler> BACKPACK_SCREEN_HANLDER;

    @Override
    public void onInitialize() {
        ItemInit.Init();
        BlockInit.Init();

        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MODID, "marshmallow"), new CookingRecipeSerializer(MarshmallowRecipe::new, 200));

        BACKPACK_SCREEN_HANLDER = ScreenHandlerRegistry.registerSimple(new Identifier(MODID, "backpack"), BackpackScreenHandler::new);
        BACKPACK_ENITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MODID+":backpack", FabricBlockEntityTypeBuilder.create(BackpackBlockEntity::new, BlockInit.BACKPACK).build(null));
    }
}
