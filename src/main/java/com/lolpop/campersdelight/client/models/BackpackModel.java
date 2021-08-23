package com.lolpop.campersdelight.client.models;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;

@Environment(EnvType.CLIENT)
public class BackpackModel<T extends LivingEntity> extends AnimalModel<T> {

    private final ModelPart backpack;
    private static final String BACKPACK = "backpack";

    public BackpackModel(ModelPart root){
        this.backpack = root.getChild(BACKPACK);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData partData = data.getRoot();
        partData.addChild(BACKPACK, ModelPartBuilder.create()
                .uv(0, 0).cuboid(-4.0F, -10.0F, 0.0F, 8.0F, 10.0F, 4.0F)
                .uv(0, 14).cuboid(-4.0F, -10.0F, -1.0F, 8.0F, 4.0F, 1.0F)
                .uv(6, 19).cuboid(-1.0F, -6.0F, -1.0F, 2.0F, 3.0F, 1.0F)
                .uv(18, 14).cuboid(-4.0F, -2.0F, -1.0F, 8.0F, 2.0F, 1.0F)
                .uv(0, 19).cuboid(-5.0F, -3.0F, 0.0F, 1.0F, 3.0F, 4.0F)
                .uv(14, 17).cuboid(4.0F, -3.0F, 0.0F, 1.0F, 3.0F, 4.0F), ModelTransform.of(0.0F, 10.0F, 4.0F, 0.0F, 3.14F , 0.0F));
        return TexturedModelData.of(data, 64, 64);
    }

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        if(entity.isInSneakingPose()){
            this.backpack.pitch = -0.5F;
            this.backpack.pivotZ = 8.04F;
            this.backpack.pivotY = 9.05F;
        }
        else {
            this.backpack.pitch = 0F;
            this.backpack.pivotZ = 4.0F;
            this.backpack.pivotY = 10.0F;
        }
    }

    protected Iterable<ModelPart> getHeadParts() { return ImmutableList.of(); }

    protected Iterable<ModelPart> getBodyParts() { return ImmutableList.of(this.backpack); }
}
