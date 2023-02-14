package com.lolpop.campersdelight.items;

import com.lolpop.campersdelight.CampersAdventure;
import com.lolpop.campersdelight.blocks.BlockInit;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {

    public static final Item CHILLI = new ChilliItem(new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).build()).group(ItemGroup.FOOD));
    public static final Item CHILLI_SEEDS = new AliasedBlockItem(BlockInit.CHILLICROP, new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item MARSHMALLOW = new MarshmallowItem(new FabricItemSettings().group(ItemGroup.FOOD), 1);
    public static final Item MARSHMALLOW_STICK = new MarshmallowItem(new FabricItemSettings().group(ItemGroup.FOOD), 1).giveStick();
    public static final Item MARSHMALLOW_MELTY = new MarshmallowItem(new FabricItemSettings().group(ItemGroup.FOOD), 2).giveStick();
    public static final Item BACKPACK = new BackpackItem(BlockInit.BACKPACK, new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item SITTING_LOG_OAK = new BlockItem(BlockInit.SITTING_LOG_OAK, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item SITTING_LOG_SPRUCE = new BlockItem(BlockInit.SITTING_LOG_SPRUCE, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item SITTING_LOG_BIRCH = new BlockItem(BlockInit.SITTING_LOG_BIRCH, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item SITTING_LOG_ACACIA = new BlockItem(BlockInit.SITTING_LOG_ACACIA, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item SITTING_LOG_JUNGLE = new BlockItem(BlockInit.SITTING_LOG_JUNGLE, new FabricItemSettings().group(ItemGroup.DECORATIONS));
    public static final Item SITTING_LOG_DARK_OAK = new BlockItem(BlockInit.SITTING_LOG_DARK_OAK, new FabricItemSettings().group(ItemGroup.DECORATIONS));

    public static void register(String path, Item item) {
        Registry.register(Registry.ITEM, new Identifier(CampersAdventure.MODID, path), item);
    }

    public static void Init() {
        register("chilli", CHILLI);
        register("chilli_seeds", CHILLI_SEEDS);
        register("marshmallow", MARSHMALLOW);
        register("marshmallow_stick", MARSHMALLOW_STICK);
        register("marshmallow_melty", MARSHMALLOW_MELTY);
        register("backpack", BACKPACK);
        register("sitting_log_oak", SITTING_LOG_OAK);
        register("sitting_log_spruce", SITTING_LOG_SPRUCE);
        register("sitting_log_birch", SITTING_LOG_BIRCH);
        register("sitting_log_acacia", SITTING_LOG_ACACIA);
        register("sitting_log_jungle", SITTING_LOG_JUNGLE);
        register("sitting_log_dark_oak", SITTING_LOG_DARK_OAK);
    }
}
