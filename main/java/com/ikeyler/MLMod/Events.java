package com.ikeyler.MLMod;

import com.ikeyler.MLMod.util.Message;
import com.ikeyler.MLMod.util.Messages;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

import static com.ikeyler.MLMod.Keybinds.*;
import static com.ikeyler.MLMod.Main.mc;
import static com.ikeyler.MLMod.util.Func.*;
import static com.ikeyler.MLMod.util.Messages.messagesToHashMap;

public class Events {
    public static void onMessage(Message msg, ClientChatReceivedEvent event) {
        Messages type = msg.getMessage();
        if (!isMsgAllowed(type)) {
            event.setCanceled(true);
            return;
        }
        switch (type) {
            case PM:
            case CREATIVE_CHAT:
            case DONATE_CHAT:
                if (CheckIgnore(msg.getMatcher().group(1))) {
                    event.setCanceled(true);
                    Wait(() -> mc.player.sendStatusMessage(new TextComponentString("§fСкрыто сообщение от §b"+msg.getMatcher().group(1)), true), 500);
                    return;
                }
                if (Configs.Other.BetterChat) {
                    if (!getLastElement(msg.getMatcher().group(1).split(" ")).equals(mc.player.getName())) {
                        ITextComponent report = new TextComponentString(" §c[R] ").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/report " + getLastElement(msg.getMatcher().group(1).split(" ")))).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§cПожаловаться"))));
                        ITextComponent ignore = new TextComponentString("§e[I]").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/ignore add " + getLastElement(msg.getMatcher().group(1).split(" ")))).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§eИгнорировать игрока"))));
                        event.getMessage().appendSibling(report);
                        event.getMessage().appendSibling(ignore);
                    }
                }
                if (type == Messages.PM) {
                    Wait(() -> mc.player.sendStatusMessage(new TextComponentString("§fСообщение от §b" + msg.getMatcher().group(1) + "§f. Ответить §8- §b/r"), true), 500);
                    notificationSound();
                }
                break;
            case DEV_JOIN:
                ITextComponent component = new TextComponentString("         §6§l[Плейсхолдеры]\n").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/values")));
                event.getMessage().appendSibling(component);
                break;
            case WORLD_INVITE:
                try {
                    String value = Objects.requireNonNull(event.getMessage().getSiblings().get(0).getStyle().getClickEvent()).getValue();
                    if (CheckWorld(value)) {event.setCanceled(true); return;}
                    ITextComponent newMsg = new TextComponentString(event.getMessage().getFormattedText().replaceFirst("\\s*\\[Присоединиться]", "[Присоединиться] §8(ID: " + getLastElement(value.split(" ")) + ")")).setStyle(event.getMessage().getSiblings().get(0).getStyle());
                    event.setMessage(newMsg);
                }
                catch (Exception ignore) {}
                break;
            case BEDWARS_UPGRADE_BOUGHT:
                mc.player.sendStatusMessage(new TextComponentString("§a+ §f"+msg.getMatcher().group(2)), true);
                break;
            case VERIFICATION:
                event.setCanceled(true);
                mc.player.sendStatusMessage(new TextComponentString("§fВведи §bкод с картинки §fдля продолжения"), true);
                break;
            case VERIFICATION2:
                event.setCanceled(true);
                mc.player.sendStatusMessage(new TextComponentString("§fПодожди немного. Идет проверка..."), true);
            default:
                break;
        }
    }
    private static boolean isMsgAllowed(Messages type) {
        HashMap<Messages, Boolean> messages = messagesToHashMap();
        return messages.get(type);
    }
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
                Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString(text.replace("&", "§")), true);
            }
            catch (Exception ignore) {}
        }
    }
    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem e) {
        if (Configs.Creative.PlaySound && e.getEntityPlayer().getName().equals(mc.player.getName())) {
            if (!mc.player.isSneaking() && e.getItemStack().isItemEqual(new ItemStack(Item.getItemById(340)))) {
                mc.world.playSound(mc.player.getPosition(), new SoundEvent(new ResourceLocation(removeColors(e.getItemStack().getDisplayName()))), SoundCategory.MASTER, 1.0F, 1.0F, false);
            }
        }
        if (Configs.Creative.ShiftToGetText && e.getEntityPlayer().getName().equals(mc.player.getName())) {
            if (mc.player.isSneaking() && e.getItemStack().isItemEqual(new ItemStack(Item.getItemById(340)))) {
                mc.player.sendMessage(new TextComponentString(e.getItemStack().getDisplayName()).setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, e.getItemStack().getDisplayName().replace("§", "&")))));
            }
        }
    }
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (Configs.Other.PortalTrapNotify && event.getEntity() instanceof EntityPlayer) {
            if (HUB_KEY.getKeyCode() != Keyboard.KEY_NONE) {
                try {
                    EntityPlayer player = (EntityPlayer) event.getEntity();
                    int x = MathHelper.floor(player.posX);
                    int y = MathHelper.floor(player.posY);
                    int z = MathHelper.floor(player.posZ);
                    IBlockState state = mc.world.getBlockState(new BlockPos(x, y, z));
                    Block block = state.getBlock();
                    if (Block.isEqualTo(block, Blocks.PORTAL)) {
                        mc.player.sendStatusMessage(new TextComponentString("§fПортал-ловушка? Нажми §6" + Keyboard.getKeyName(HUB_KEY.getKeyCode()) + " §fдля выхода"), true);
                    }
                }
                catch (Exception ignore) {
                }
            }
        }
    }
}
