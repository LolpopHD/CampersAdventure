package com.lolpop.campersdelight.recipes;

import com.lolpop.campersdelight.CampersAdventure;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MarshmallowRecipe extends CampfireCookingRecipe {

    protected ItemStack item;

    public MarshmallowRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(id, group, input, output, experience, cookTime);
    }

    public RecipeSerializer<?> getSerializer() {
        return CampersAdventure.MARSHMALLOW_RECIPE_SERIALIZER;
    }

    public boolean matches(Inventory inventory, World world){
        this.item = inventory.getStack(0);
        if(inventory.getStack(0).getItem() instanceof DyeableItem && output.getItem() instanceof DyeableItem){
            return this.input.test(inventory.getStack(0));
        }
        return false;
    }

    public ItemStack craft(Inventory inventory) {
        ItemStack output = this.output.copy();
        ItemStack input = inventory.getStack(0);
        ((DyeableItem) output.getItem()).setColor(output, ((DyeableItem)input.getItem()).getColor(input)    );
        return output.copy();
    }
}
