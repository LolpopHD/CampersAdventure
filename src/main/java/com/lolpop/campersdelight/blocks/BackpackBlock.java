package com.lolpop.campersdelight.blocks;

import com.lolpop.campersdelight.blocks.blockentity.BackpackBlockEntity;
import com.lolpop.campersdelight.duck.BackpackSlotDuckProvider;
import com.lolpop.campersdelight.items.ItemInit;
import com.lolpop.campersdelight.utils.VoxelShapeStuff;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.*;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BackpackBlock extends BlockWithEntity implements Waterloggable {

    public static final DirectionProperty FACING;
    public static final BooleanProperty WATERLOGGED;
    public static final Identifier CONTENTS;

    public static final VoxelShape[] SHAPE0 = VoxelShapeStuff.rotationsOf(VoxelShapeStuff.betterBox(4, 0, 7, 12, 10, 11));
    public static final VoxelShape[] SHAPE1 = VoxelShapeStuff.rotationsOf(VoxelShapeStuff.betterBox(4, 6, 6, 12, 10, 7));
    public static final VoxelShape[] SHAPE2 = VoxelShapeStuff.rotationsOf(VoxelShapeStuff.betterBox(7, 3, 6, 9, 6, 7));
    public static final VoxelShape[] SHAPE3 = VoxelShapeStuff.rotationsOf(VoxelShapeStuff.betterBox(4, 0, 6, 12, 2, 7));
    public static final VoxelShape[] SHAPE4 = VoxelShapeStuff.rotationsOf(VoxelShapeStuff.betterBox(12, 0, 7, 13, 3, 11));
    public static final VoxelShape[] SHAPE5 = VoxelShapeStuff.rotationsOf(VoxelShapeStuff.betterBox(3, 0, 7, 4, 3, 11));

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
                ((BackpackBlockEntity) blockEntity).setCustomName(itemStack.getName());
            }
        }

    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BackpackBlockEntity) {
            if (!world.isClient) {
                ItemEntity itemEntity = new ItemEntity(world, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, getItem(pos, world));
                itemEntity.setToDefaultPickupDelay();
                world.spawnEntity(itemEntity);
            }
        }

        super.onBreak(world, pos, state, player);
    }

    public ItemStack getItem(BlockPos pos, World world) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof BackpackBlockEntity entity) {
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

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else if (player.isSpectator()) {
            return ActionResult.CONSUME;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (player.isSneaking()) {
                if (player instanceof BackpackSlotDuckProvider duckProvider) {
                    ItemStack itemStack2 = duckProvider.getBackpackSlot();
                    if (itemStack2.isEmpty()) {
                        duckProvider.setBackpackSlot(getItem(pos, world));
                        world.removeBlock(pos, false);
                        return ActionResult.CONSUME;
                    }
                }
            } else {
                if (blockEntity instanceof BackpackBlockEntity entity) {
                    player.openHandledScreen(entity);
                    return ActionResult.CONSUME;
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        super.appendTooltip(stack, world, tooltip, options);
        NbtCompound nbtCompound = stack.getSubNbt("BlockEntityTag");
        if (nbtCompound != null) {
            if (nbtCompound.contains("LootTable", 8)) {
                tooltip.add(new LiteralText("???????"));
            }

            if (nbtCompound.contains("Items", 9)) {
                DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(27, ItemStack.EMPTY);
                Inventories.readNbt(nbtCompound, defaultedList);
                int i = 0;
                int j = 0;

                for (ItemStack itemStack : defaultedList)
                    if (!itemStack.isEmpty()) {
                        ++j;
                        if (i <= 4) {
                            ++i;
                            MutableText mutableText = itemStack.getName().shallowCopy();
                            mutableText.append(" x").append(String.valueOf(itemStack.getCount()));
                            tooltip.add(mutableText);
                        }
                    }

                if (j - i > 0)
                    tooltip.add((new TranslatableText("container.campersdelight.backpack.more", j - i)).formatted(Formatting.ITALIC));
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

    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        int dir = state.get(Properties.HORIZONTAL_FACING).getOpposite().ordinal() - 2;
        return VoxelShapeStuff.combineVoxelShapes(SHAPE0[dir], SHAPE1[dir], SHAPE2[dir], SHAPE3[dir], SHAPE4[dir], SHAPE5[dir]);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int dir = state.get(Properties.HORIZONTAL_FACING).getOpposite().ordinal() - 2;
        return VoxelShapeStuff.combineVoxelShapes(SHAPE0[dir], SHAPE1[dir], SHAPE2[dir], SHAPE3[dir], SHAPE4[dir], SHAPE5[dir]);
    }

    static {
        FACING = HorizontalFacingBlock.FACING;
        WATERLOGGED = Properties.WATERLOGGED;
        CONTENTS = new Identifier("contents");
    }


}
