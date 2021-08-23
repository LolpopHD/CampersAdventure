package com.lolpop.campersdelight.blocks;

import com.lolpop.campersdelight.CampersAdventure;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class BackpackBlockEntity extends LootableContainerBlockEntity {


    private DefaultedList<ItemStack> invstack;

    public BackpackBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(CampersAdventure.BACKPACK_ENITY, blockPos, blockState);
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.invstack;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.invstack = list;
    }

    @Override
    protected Text getContainerName() {
        return null;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return null;
    }

    @Override
    public int size() {
        return 27;
    }
}
