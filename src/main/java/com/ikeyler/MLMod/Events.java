package com.ikeyler.MLMod;

import com.ikeyler.MLMod.util.Func;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import java.lang.reflect.Field;

import static com.ikeyler.MLMod.Keybinds.*;
import static com.ikeyler.MLMod.Main.mc;

public class Events {
    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e) {
        if (HUB_KEY.isPressed()) {
            mc.player.sendChatMessage("/hub");
        }
        else if (DEV_KEY.isPressed()) {
            mc.player.sendChatMessage("/dev");
        }
        else if (BUILD_KEY.isPressed()) {
            mc.player.sendChatMessage("/build");
        }
        else if (PLAY_KEY.isPressed()) {
            mc.player.sendChatMessage("/play");
        }
    }
    @SubscribeEvent
    public void onKeyboardInput(GuiScreenEvent.KeyboardInputEvent.Pre event) {
        if (event.getGui() instanceof GuiChat && mc.player.getHeldItemMainhand().isItemEqual(new ItemStack(Item.getItemById(340))) && Configs.Creative.ShowChatInput) {
            try {
                GuiChat chatGui = (GuiChat) event.getGui();
                Field inputField = GuiChat.class.getDeclaredField("inputField");
                inputField.setAccessible(true);
                GuiTextField chatTextField = (GuiTextField) inputField.get(chatGui);
                String text = chatTextField.getText();
                mc.player.sendStatusMessage(new TextComponentString(text.replace("&", "§")), true);
            }
            catch (Exception ignore) {}
        }
    }
    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem e) {
        if (Configs.Creative.PlaySound && e.getEntityPlayer().getName().equals(mc.player.getName())) {
            if (!mc.player.isSneaking() && e.getItemStack().isItemEqual(new ItemStack(Item.getItemById(340)))) {
                mc.world.playSound(mc.player.getPosition(), new SoundEvent(new ResourceLocation(Func.removeColors(e.getItemStack().getDisplayName()))), SoundCategory.MASTER, 1.0F, 1.0F, false);
            }
        }
        if (Configs.Creative.ShiftToGetText && e.getEntityPlayer().getName().equals(mc.player.getName())) {
            if (mc.player.isSneaking() && e.getItemStack().isItemEqual(new ItemStack(Item.getItemById(340)))) {
                mc.player.sendMessage(new TextComponentString(e.getItemStack().getDisplayName()).setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, e.getItemStack().getDisplayName().replace("§", "&")))));
            }
        }
    }
}
