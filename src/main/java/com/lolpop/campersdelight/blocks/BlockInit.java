package com.lolpop.campersdelight.blocks;

import com.lolpop.campersdelight.CampersAdventure;
import com.lolpop.campersdelight.items.ItemInit;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {

    public static final Block CHILLICROP = new ChilliCropBlock(AbstractBlock.Settings.of(Material.PLANT).nonOpaque().noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final Block BACKPACK = new BackpackBlock(AbstractBlock.Settings.of(Material.WOOL).nonOpaque().sounds(BlockSoundGroup.WOOL));
    public static final Block SITTING_LOG_OAK = new SittingLogTemplateBlock(AbstractBlock.Settings.of(Material.WOOD).nonOpaque().sounds(BlockSoundGroup.WOOD), () -> ItemInit.SITTING_LOG_OAK);
    public static final Block SITTING_LOG_SPRUCE = new SittingLogTemplateBlock(AbstractBlock.Settings.of(Material.WOOD).nonOpaque().sounds(BlockSoundGroup.WOOD), () -> ItemInit.SITTING_LOG_SPRUCE);
    public static final Block SITTING_LOG_BIRCH = new SittingLogTemplateBlock(AbstractBlock.Settings.of(Material.WOOD).nonOpaque().sounds(BlockSoundGroup.WOOD), () -> ItemInit.SITTING_LOG_BIRCH);
    public static final Block SITTING_LOG_ACACIA = new SittingLogTemplateBlock(AbstractBlock.Settings.of(Material.WOOD).nonOpaque().sounds(BlockSoundGroup.WOOD), () -> ItemInit.SITTING_LOG_ACACIA);
    public static final Block SITTING_LOG_JUNGLE = new SittingLogTemplateBlock(AbstractBlock.Settings.of(Material.WOOD).nonOpaque().sounds(BlockSoundGroup.WOOD), () -> ItemInit.SITTING_LOG_JUNGLE);
    public static final Block SITTING_LOG_DARK_OAK = new SittingLogTemplateBlock(AbstractBlock.Settings.of(Material.WOOD).nonOpaque().sounds(BlockSoundGroup.WOOD), () -> ItemInit.SITTING_LOG_DARK_OAK);

    public static void register(String path, Block block) {
        Registry.register(Registry.BLOCK, new Identifier(CampersAdventure.MODID, path), block);
    }

    public static void Init() {
        register("chilli", CHILLICROP);
        register("backpack", BACKPACK);
        register("sitting_log_oak", SITTING_LOG_OAK);
        register("sitting_log_spruce", SITTING_LOG_SPRUCE);
        register("sitting_log_birch", SITTING_LOG_BIRCH);
        register("sitting_log_acacia", SITTING_LOG_ACACIA);
        register("sitting_log_jungle", SITTING_LOG_JUNGLE);
        register("sitting_log_dark_oak", SITTING_LOG_DARK_OAK);
    }
}
