package com.lolpop.campersdelight.client.models;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;

public class SittingLogOak<T extends LivingEntity> extends AnimalModel<T> {
    private final ModelPart sittingLogOak;
    private static final String OAK_SITTING_LOG = "oak_sitting_log";

    public SittingLogOak(ModelPart root) {
        this.sittingLogOak = root.getChild(OAK_SITTING_LOG);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData partData = data.getRoot();
        partData.addChild(OAK_SITTING_LOG, ModelPartBuilder.create().uv(0, 0).cuboid(-20.0F, -10.0F, 0.0F, 38.0F, 10.0F, 10.0F), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
        return TexturedModelData.of(data, 128, 128);
    }

    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        if (entity.isInSneakingPose()) {
            this.sittingLogOak.pitch = -0.5F;
            this.sittingLogOak.pivotZ = 8.04F;
            this.sittingLogOak.pivotY = 9.05F;
        } else {
            this.sittingLogOak.pitch = 0F;
            this.sittingLogOak.pivotZ = 4.0F;
            this.sittingLogOak.pivotY = 10.0F;
        }
    }

    @Override
    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of();
    }
}