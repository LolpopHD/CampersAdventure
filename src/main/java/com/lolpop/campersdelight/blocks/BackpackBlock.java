package com.lolpop.campersdelight.blocks;

import com.lolpop.campersdelight.items.ItemInit;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.block.enums.ChestType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class BackpackBlock extends BlockWithEntity implements Waterloggable{

    public static final DirectionProperty FACING;
    public static final BooleanProperty WATERLOGGED;
    public static final Identifier CONTENTS;

    protected BackpackBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BackpackBlockEntity(pos, state);
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction direction = ctx.getPlayerFacing().getOpposite();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        return this.getDefaultState().with(FACING, direction).with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomName()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BackpackBlockEntity) {
                ((BackpackBlockEntity)blockEntity).setCustomName(itemStack.getName());
            }
        }

    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BackpackBlockEntity) {
            BackpackBlockEntity entity = (BackpackBlockEntity)blockEntity;
            if (!world.isClient && player.isCreative() && !entity.isEmpty()) {
                ItemStack itemStack = new ItemStack(BlockInit.BACKPACK);
                NbtCompound nbtCompound = entity.writeInventoryNbt(new NbtCompound());
                if (!nbtCompound.isEmpty()) {
                    itemStack.setSubNbt("BlockEntityTag", nbtCompound);
                }

                if (entity.hasCustomName()) {
                    itemStack.setCustomName(entity.getCustomName());
                }

                ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, itemStack);
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            } else {
                entity.checkLootInteraction(player);
            }
        }

        super.onBreak(world, pos, state, player);
    }

    public ItemStack getItem(BlockPos pos, World world){
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BackpackBlockEntity) {
            BackpackBlockEntity entity = (BackpackBlockEntity)blockEntity;
            if (!world.isClient) {
                ItemStack itemStack = ItemInit.BACKPACK.getDefaultStack();
                NbtCompound nbtCompound = entity.writeInventoryNbt(new NbtCompound());
                if (!nbtCompound.isEmpty()) {
                    itemStack.setSubNbt("BlockEntityTag", nbtCompound);
                }

                if (entity.hasCustomName()) {
                    itemStack.setCustomName(entity.getCustomName());
                }
                return itemStack;
            }
        }
        return ItemStack.EMPTY;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else if (player.isSpectator()) {
            return ActionResult.CONSUME;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(player.isSneaking()){
                ItemStack itemStack2 = player.getEquippedStack(EquipmentSlot.CHEST);
                if (itemStack2.isEmpty()) {
                    player.equipStack(EquipmentSlot.CHEST, getItem(pos, world));
                    world.removeBlock(pos, false);
                    return ActionResult.CONSUME;
                }
            }
            if (blockEntity instanceof BackpackBlockEntity) {
                BackpackBlockEntity entity = (BackpackBlockEntity)blockEntity;
                player.openHandledScreen(entity);

                return ActionResult.CONSUME;
            } else {
                return ActionResult.PASS;
            }
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        WATERLOGGED = Properties.WATERLOGGED;
        CONTENTS = new Identifier("contents");
    }
}
