package com.lolpop.campersdelight.utils;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class VoxelShapeStuff {
    public static VoxelShape combineVoxelShapes(VoxelShape... shapes){
        VoxelShape output = VoxelShapes.empty();
        for(VoxelShape shape : shapes){
            output = VoxelShapes.union(output, shape);
        }
        return output;
    }

    public static VoxelShape betterShape(float minX, float minY, float minZ, float maxX, float maxY, float maxZ){
        return VoxelShapes.cuboid(minX/16, minY/16, minZ/16, maxX/16, maxY/16, maxZ/16);
    }
}
