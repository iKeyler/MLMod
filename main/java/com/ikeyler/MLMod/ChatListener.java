package com.ikeyler.MLMod;

import com.ikeyler.MLMod.util.Message;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

import static com.ikeyler.MLMod.Events.onMessage;
import static com.ikeyler.MLMod.Main.mc;
import static com.ikeyler.MLMod.Main.prefix;
import static com.ikeyler.MLMod.util.Func.*;

public class ChatListener {
    public static List<String> IgnoreList = new ArrayList<>();
    @SubscribeEvent
    public void onClientChat(ClientChatEvent e) {
        String[] split = e.getMessage().split(" ");
        if (e.getMessage().startsWith(".") || e.getMessage().startsWith("/")) {
            if (Configs.Other.wrongLangcmds && wrongLangCmds().containsKey(split[0].substring(1))) {
                split[0] = wrongLangCmds().get(split[0].substring(1));
                e.setMessage(String.join(" ", split));
            }
        }
        if (e.getMessage().equals(".mlmod.importignorelist")) {
            e.setCanceled(true);
            addToIgnoreList(listToArray(IgnoreList, Configs.Filters.IgnoreList));
            mc.player.sendStatusMessage(new TextComponentString(prefix+"Импортировано §6"+IgnoreList.size()+" §fигроков"), true);
        }
        if (e.getMessage().equals("/values")) {
            e.setCanceled(true);
            mc.player.sendMessage(new TextComponentString("\n "+prefix+"Плейсхолдеры:\n §6%player% §8- §7игрок/сущность, исполняющий код\n §6%default% §8- §7игрок/сущность по умолчанию\n §6%selected% §8- §7выбранный игрок/сущность\n §6%selection% §8- §7список выбранных игроков/сущностей\n §6%damager% §8- §7ударивший игрок/сущность\n §6%shooter% §8- §7выстреливший игрок/сущность\n §6%killer% §8- §7убийца\n §6%victim% §8- §7жертва\n §6%random% §8- §7случайный объект из выборки\n §6%entity% §8- §7выбранная сущность\n"));
        }
    }
    @SubscribeEvent
    public void onReceivedChat(ClientChatReceivedEvent e) {
        String message = e.getMessage().getUnformattedText();
        String[] split = message.split(" ");
        Message msg = checkMessage(e.getMessage().getUnformattedText());
        if (msg != null) {
            onMessage(msg, e);
            debug(msg.getMessage());
        }
        //ban word
        if (ArrayUtils.isNotEmpty(Configs.Filters.BanWords)) {
            if (CheckBanWord(e.getMessage().getUnformattedText())) {
                e.setCanceled(true);
                debug("Сообщение с бан-вордом было скрыто");
            }
        }
        //ignore list import
        if (split.length >= 6 && message.split("\n")[0].equals("Игнорирование » Твой список заблокированных игроков:")) {
            List<String> list = arrayToList(message.split("\n"));
            List<String> ignorelist = new ArrayList<>();
            list.remove(0);
            for (String element:list) {
                String[] Element = element.split(" ");
                ignorelist.add(Element[Element.length-1]);
            }
            IgnoreList = ignorelist;
            e.setMessage(new TextComponentString(e.getMessage().getFormattedText()+"\n§a[Импортировать в список заблокированных игроков]").setStyle(new Style().setClickEvent(importIgnoreList())));
        }
    }
}
