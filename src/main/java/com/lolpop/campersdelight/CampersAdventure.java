package com.lolpop.campersdelight;

import com.lolpop.campersdelight.blocks.BackpackBlockEntity;
import com.lolpop.campersdelight.blocks.BlockInit;
import com.lolpop.campersdelight.items.ItemInit;
import com.lolpop.campersdelight.recipes.MarshmallowRecipe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CampersAdventure implements ModInitializer {

    public static final String MODID = "campersadventure";
    public static RecipeSerializer<MarshmallowRecipe> MARSHMALLOW_RECIPE_SERIALIZER;
    public static BlockEntityType<BackpackBlockEntity> BACKPACK_ENITY;

    @Override
    public void onInitialize() {
        BACKPACK_ENITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MODID+":backpack", FabricBlockEntityTypeBuilder.create(BackpackBlockEntity::new, BlockInit.BACKPACK).build(null));
        ItemInit.Init();
        BlockInit.Init();
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MODID, "marshmallow"), new CookingRecipeSerializer(MarshmallowRecipe::new, 200));
    }
}
