package net.meteor.tutorialmod.fluid;

import com.mojang.math.Vector3f;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidType;

public class BaseFluidType extends FluidType {
    private final ResourceLocation stillTexture;
    private final ResourceLocation flowingTexture;
    private final ResourceLocation overlayTexture;
    private final int tintColour;
    private final Vector3f fogColour;

    public BaseFluidType(final ResourceLocation stillTexture, final ResourceLocation flowingTexture, final ResourceLocation overlayTexture, final int tintColour, final Vector3f fogColour, final Properties properties)
}
