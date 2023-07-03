package com.ikeyler.MLMod;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;
public class Keybinds {
    private static final String CATEGORY = "MLMod";
    public static final KeyBinding HUB_KEY = new KeyBinding("Выйти в хаб", Keyboard.KEY_NONE, CATEGORY);
    public static final KeyBinding DEV_KEY = new KeyBinding("Режим кодинга", Keyboard.KEY_NONE, CATEGORY);
    public static final KeyBinding BUILD_KEY = new KeyBinding("Режим строительства", Keyboard.KEY_NONE, CATEGORY);
    public static final KeyBinding PLAY_KEY = new KeyBinding("Режим игры", Keyboard.KEY_NONE, CATEGORY);
    public static void register() {
        ClientRegistry.registerKeyBinding(HUB_KEY);
        ClientRegistry.registerKeyBinding(DEV_KEY);
        ClientRegistry.registerKeyBinding(BUILD_KEY);
        ClientRegistry.registerKeyBinding(PLAY_KEY);
    }
}