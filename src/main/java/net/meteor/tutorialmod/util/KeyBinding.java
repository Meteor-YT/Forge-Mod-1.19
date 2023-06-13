package net.meteor.tutorialmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_O;

public class KeyBinding {
    public static final String KEY_CATEGORY_TUTORIAL = "key.category.tutorialmod.tutorial";
    public static final String KEY_DRINK_WATER = "key.tutorialmod.drink_water";
    public static final KeyMapping DRINKING_KEY = new KeyMapping(KEY_DRINK_WATER, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW_KEY_O, KEY_CATEGORY_TUTORIAL);
}
