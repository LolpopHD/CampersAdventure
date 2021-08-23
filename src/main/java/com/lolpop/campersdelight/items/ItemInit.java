package com.lolpop.campersdelight.items;

import com.lolpop.campersdelight.CampersAdventure;
import com.lolpop.campersdelight.blocks.BlockInit;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {

    public static final Item CHILLI = new ChilliItem(new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).build()).group(ItemGroup.FOOD));
    public static final Item CHILLI_SEEDS = new AliasedBlockItem(BlockInit.CHILLICROP, new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item MARSHMALLOW = new MarshmallowItem(new FabricItemSettings().group(ItemGroup.FOOD), 1);
    public static final Item MARSHMALLOW_STICK = new MarshmallowItem(new FabricItemSettings().group(ItemGroup.FOOD), 1).giveStick();
    public static final Item MARSHMALLOW_MELTY = new MarshmallowItem(new FabricItemSettings().group(ItemGroup.FOOD), 2).giveStick();
    public static final Item BACKPACK = new BackpackItem(new FabricItemSettings().group(ItemGroup.MISC));

    public static void register(String path, Item item) {
        Registry.register(Registry.ITEM, new Identifier(CampersAdventure.MODID, path), item);
    }

    public static void Init(){
        register("chilli", CHILLI);
        register("chilli_seeds", CHILLI_SEEDS);
        register("marshmallow", MARSHMALLOW);
        register("marshmallow_stick", MARSHMALLOW_STICK);
        register("marshmallow_melty", MARSHMALLOW_MELTY);
        register("backpack", BACKPACK);
    }
}
