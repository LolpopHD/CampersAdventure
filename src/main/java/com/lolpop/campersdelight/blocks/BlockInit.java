package com.lolpop.campersdelight.blocks;

import com.lolpop.campersdelight.CampersAdventure;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {

    public static final Block CHILLICROP = new ChilliCropBlock(AbstractBlock.Settings.of(Material.PLANT).nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final Block BACKPACK = new BackpackBlock(AbstractBlock.Settings.of(Material.WOOD));

    public static void register(String path, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(CampersAdventure.MODID, path), block);
    }

    public static void Init() {
        register("chilli", CHILLICROP);
        register("backpack", BACKPACK);
    }
}
