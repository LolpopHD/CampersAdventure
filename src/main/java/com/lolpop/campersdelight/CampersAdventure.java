package com.lolpop.campersdelight;

import com.lolpop.campersdelight.blocks.blockentity.BackpackBlockEntity;
import com.lolpop.campersdelight.screenhandler.BackpackScreenHandler;
import com.lolpop.campersdelight.blocks.BlockInit;
import com.lolpop.campersdelight.items.BackpackItem;
import com.lolpop.campersdelight.items.ItemInit;
import com.lolpop.campersdelight.recipes.MarshmallowRecipe;
import com.lolpop.campersdelight.utils.BetterUsageContext;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
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

        UseBlockCallback.EVENT.register(((player, world, hand, hitResult) -> {
            if(player.getEquippedStack(EquipmentSlot.CHEST).getItem() instanceof BackpackItem && player.isSneaking() && player.getMainHandStack().isEmpty() && hitResult.getSide() == Direction.UP){
                ItemStack stack = player.getEquippedStack(EquipmentSlot.CHEST);
                ItemUsageContext context = new BetterUsageContext(world,   player, hand, stack, hitResult);
                BlockPos pos = context.getBlockPos();
                BlockState state = context.getWorld().getBlockState(pos);
                if(state.getBlock().isShapeFullCube(state, context.getWorld(), pos)){
                    if(stack.useOnBlock(context) == ActionResult.CONSUME){
                        player.equipStack(EquipmentSlot.CHEST, ItemStack.EMPTY);
                        player.swingHand(Hand.MAIN_HAND, true);
                    }
                }
            }
            return ActionResult.PASS;
        }));

        BACKPACK_SCREEN_HANLDER = ScreenHandlerRegistry.registerSimple(new Identifier(MODID, "backpack"), BackpackScreenHandler::new);
        BACKPACK_ENITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, MODID+":backpack", FabricBlockEntityTypeBuilder.create(BackpackBlockEntity::new, BlockInit.BACKPACK).build(null));
    }
}
