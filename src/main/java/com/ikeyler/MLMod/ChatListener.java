package com.ikeyler.MLMod;

import com.ikeyler.MLMod.util.Func;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

import static com.ikeyler.MLMod.Main.*;

public class ChatListener {
    private final List<String> ignoreList = new ArrayList<>();
    @SubscribeEvent
    public void onClientChat(ClientChatEvent event) {
        String message = event.getMessage();
        String[] split = message.split(" ");
        if (message.startsWith(".") || message.startsWith("/")) {
            if (Configs.Other.wrongLangcmds && Func.wrongLangCmds().containsKey(split[0].substring(1))) {
                split[0] = Func.wrongLangCmds().get(split[0].substring(1));
                event.setMessage(String.join(" ", split));
                return;
            }
        }
        if (message.startsWith(".edit")) {
            event.setCanceled(true);
            String prevName = mc.player.getHeldItemMainhand().getDisplayName();
            mc.player.sendMessage(new TextComponentString(prefix+"Старое название: §7["+prevName+"§7]").setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§7ЛКМ§a, чтобы вывести текст в чат"))).setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ".edit "+prevName.replace("§", "&")))));
            mc.player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, mc.player.getHeldItemMainhand().setStackDisplayName(Func.replaceColors(message.replaceFirst("\\.edit", "")).trim()));
            mc.playerController.sendSlotPacket(mc.player.getHeldItemMainhand(), 36+mc.player.inventory.currentItem);
            mc.player.sendMessage(new TextComponentString(prefix+"Новое название: §7["+Func.replaceColors(message.replaceFirst("\\.edit", "")).trim()+"§7]").setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§7ЛКМ§a, чтобы вывести текст в чат"))).setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, ".edit "+Func.replaceColors(message.replaceFirst("\\.edit", "")).trim().replace("§", "&")))));
        }
        if (message.startsWith(".mlmodplayerinteract")) {
            event.setCanceled(true);
            if (split.length < 3) {
                return;
            }
            String player = split[1].trim();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < split.length; i++) {
                sb.append(split[i]).append(" ");
            }
            ITextComponent r = new TextComponentString("§c[Пожаловаться] ").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/report " + player)).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§cПожаловаться"))));
            ITextComponent i = new TextComponentString("§e[Игнорировать] ").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/ignore add " + player)).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§eИгнорировать игрока"))));
            ITextComponent c = new TextComponentString("§a[Копировать сообщение] ").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, sb.toString())).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§aКопировать сообщение. Выделите текст из чата и скопируйте §7(CTRL+A + CTRL+C)"))));
            ITextComponent w = new TextComponentString("§2[Написать сообщение]").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg "+player+" ")).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§2Написать сообщение"))));
            mc.player.sendMessage(new TextComponentString(prefix+"Игрок §7"+player+"§f: §e").appendSibling(r).appendSibling(i).appendSibling(c).appendSibling(w));
        }
        switch (message) {
            case ".mlmod.importignorelist":
                event.setCanceled(true);
                Func.addToIgnoreList(Func.listToArray(ignoreList, Configs.Filters.IgnoreList));
                mc.player.sendStatusMessage(new TextComponentString(Main.prefix+"Импортировано §6"+ignoreList.size()+" §fигроков"), true);
                break;
            case "/values":
                event.setCanceled(true);
                mc.player.sendMessage(new TextComponentString("\n "+Main.prefix+"Плейсхолдеры:\n §6%player% §8- §7игрок/сущность, исполняющий код\n §6%default% §8- §7игрок/сущность по умолчанию\n §6%selected% §8- §7выбранный игрок/сущность\n §6%selection% §8- §7список выбранных игроков/сущностей\n §6%damager% §8- §7ударивший игрок/сущность\n §6%shooter% §8- §7выстреливший игрок/сущность\n §6%killer% §8- §7убийца\n §6%victim% §8- §7жертва\n §6%random% §8- §7случайный объект из выборки\n §6%entity% §8- §7выбранная сущность\n"));
                break;
            case "/genpwd":
                event.setCanceled(true);
                String pwd = Func.genPWD(25);
                mc.player.sendMessage(new TextComponentString(Main.prefix+"Наведите, чтобы посмотреть сгенерированный пароль.\n"+Main.prefix+"Нажмите, чтобы вставить его в чат.").setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString(pwd))).setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/reg "+pwd))));
                break;
            default:
                break;
        }
    }
    @SubscribeEvent
    public void onReceivedChat(ClientChatReceivedEvent event) {
        String message = event.getMessage().getUnformattedText();
        String[] split = message.split(" ");
        messageManager.processMessages(messageManager.getMessage(event.getMessage().getUnformattedText()), event);
        messageManager.processMessages(messageManager.getMessage(event.getMessage().getFormattedText()), event);
        System.out.println(message);
        if (Configs.Filters.BanWords != null && Configs.Filters.BanWords.length > 0) {
            if (Func.checkBanWord(message)) {
                event.setCanceled(true);
                logger.info("Скрыто сообщение: "+message);
            }
        }
        if (split.length >= 6 && message.split("\n")[0].equals("Игнорирование » Твой список заблокированных игроков:")) {
            List<String> list = Func.arrayToList(message.split("\n"));
            List<String> importedList = new ArrayList<>();
            list.remove(0);
            for (String element:list) {
                String[] elementParts = element.split(" ");
                importedList.add(elementParts[elementParts.length-1]);
            }
            ignoreList.addAll(importedList);
            event.setMessage(event.getMessage().createCopy().appendSibling(new TextComponentString("\n§a[Импортировать в список заблокированных игроков]").setStyle(new Style().setClickEvent(Func.importIgnoreList()))));
        }
    }
}