package com.ikeyler.MLMod.message;

import com.ikeyler.MLMod.Configs;
import com.ikeyler.MLMod.util.Func;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;

import static com.ikeyler.MLMod.Main.*;

public class MessageManager {
    private final List<Message> messages;
    public MessageManager() {
        messages = new ArrayList<>();
        addMessages(MsgList.messages);
    }
    public void addMessages(Message[] messageList) {
        messages.addAll(Arrays.asList(messageList));
    }
    public Message getMessage(String message) {
        return messages.stream()
                .filter(msg -> msg.matches(message)).findFirst().orElse(null);
    }
    public void processMessages(Message m, ClientChatReceivedEvent event) {
        if (m == null) {
            return;
        }
        Messages ml = MsgList;
        ITextComponent message = event.getMessage();
        Matcher matcher = m.getMatcher();
        String[] split;
        try {
            split = matcher.group(1).split(" ");
        } catch (Exception ignore) {
            split = new String[]{};
        }
        if (!m.isActive()) {
            event.setCanceled(true);
            return;
        }
        if (m == ml.PM || m == ml.DONATE_CHAT || m == ml.CREATIVE_CHAT) {
            filter.addMessage(m, event.getMessage().getFormattedText());
            if (Func.CheckIgnore(Func.removeColors(matcher.group(1)))) {
                event.setCanceled(true);
                logger.info("Скрыто сообщение: "+message.getUnformattedText());
                Func.Wait(() -> mc.player.sendStatusMessage(new TextComponentString("§fСкрыто сообщение от "+matcher.group(1)), true), 500);
                return;
            }
            String chatMessage = matcher.group(2);

            if (m == ml.CREATIVE_CHAT && !(Configs.MessageFormatting.creativeChat.equals(""))) {
                String newMessage = Configs.MessageFormatting.creativeChat.replace("%player%", matcher.group(1)).replace("\\n", "\n");
                newMessage = split.length != 1 ? newMessage.replace("%message%", chatMessage.replace(" §r§3[Перевести]", "").replace(" §r§3[Перевести]§r", "")) : newMessage.replace("%message%", Func.removeColors(chatMessage).replace(" [Перевести]", ""));
                newMessage = Func.replaceColors(newMessage.replace("%message2%", Func.removeColors(chatMessage.replace(" §r§3[Перевести]", "").replace(" §r§3[Перевести]§r", "")))).trim();
                event.setMessage(new TextComponentString(newMessage));
            }

            if (m == ml.DONATE_CHAT && !(Configs.MessageFormatting.donateChat.equals(""))) {
                String newMessage = Func.replaceColors(Configs.MessageFormatting.donateChat
                        .replace("%player%", matcher.group(1))
                        .replace("%message%", chatMessage.replace(" §r§3[Перевести]", "").replace(" §r§3[Перевести]§r", "")));
                event.setMessage(new TextComponentString(newMessage));
            }

            if (Configs.Other.BetterChat) {
                if (!Func.getLastElement(Func.removeColors(matcher.group(1)).split(" ")).equals(mc.player.getName())) {
                    if (!Configs.MessageFormatting.editMark.equals("")) {
                        event.getMessage().appendSibling(new TextComponentString(" "+Func.replaceColors(Configs.MessageFormatting.editMark)).setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, ".mlmodplayerinteract " + Func.getLastElement(Func.removeColors(matcher.group(1)).split(" ")) + " " + Func.removeColors(chatMessage).replace("[Перевести]", ""))).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§eВзаимодействие с игроком §7[ЛКМ]")))));
                    }
                }
            }

            if (!Func.getLastElement(Func.removeColors(matcher.group(1)).split(" ")).equals(mc.player.getName())) {
                if (!Configs.MessageFormatting.pingMark.equals("")) {
                    if (chatMessage.contains(mc.player.getName())) {
                        String reply;
                        if (m==ml.CREATIVE_CHAT) {reply = "/cc "+Func.getLastElement(Func.removeColors(matcher.group(1)).split(" "))+", ";}
                        else if (m==ml.DONATE_CHAT) {reply = "/dc "+Func.getLastElement(Func.removeColors(matcher.group(1)).split(" "))+", ";}
                        else {reply = "/r ";}
                        event.getMessage().appendSibling(new TextComponentString(" "+Func.replaceColors(Configs.MessageFormatting.pingMark)).setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentString("§aВас упомянули в этом сообщении\n§7ЛКМ, чтобы ответить"))).setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, reply))));
                    }
                }
            }

            if (m==ml.PM) {
                Func.Wait(() -> mc.player.sendStatusMessage(new TextComponentString("§fСообщение от §b" + matcher.group(1) + "§f. Ответить §8- §b/r"), true), 500);
                Func.notificationSound();
            }
            return;
        }

        if (m==ml.DEV_JOIN) {
            ITextComponent component = new TextComponentString("         §6§l[Плейсхолдеры]\n").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/values")));
            message.appendSibling(component);
            return;
        }
        if (m==ml.WORLD_INVITE) {
            try {
                String value = Objects.requireNonNull(message.getSiblings().get(0).getStyle().getClickEvent()).getValue();
                String id = Func.getLastElement(value.split(" ")).trim();
                if (Func.CheckWorld(Integer.parseInt(id))) {
                    event.setCanceled(true);
                    return;
                }
                Func.debug("Последний айди мира: "+ id);
                ITextComponent newMsg = new TextComponentString(message.getFormattedText().replaceFirst("\\s*\\[Присоединиться]", "[Присоединиться] §8(ID: " + id + ")")).setStyle(message.getSiblings().get(0).getStyle());
                event.setMessage(newMsg);
            }
            catch (Exception ignore) {}
            return;
        }
        if (m==ml.BEDWARS_UPGRADE_BOUGHT) {
            mc.player.sendStatusMessage(new TextComponentString("§a+ §f"+matcher.group(2)), true);
            return;
        }
        if (m==ml.VERIFICATION) {
            event.setCanceled(true);
            mc.player.sendStatusMessage(new TextComponentString("§fПодожди немного. Идет проверка..."), true);
            return;
        }
        if (m==ml.VERIFICATION2) {
            event.setCanceled(true);
            mc.player.sendStatusMessage(new TextComponentString("§fПодожди немного. Идет проверка..."), true);
            return;
        }
        if (m==ml.SKYBLOCK_BID) {
            if (Configs.Other.BidType.name().equals("HOTBAR")) {
                event.setCanceled(true);
                String SKYBLOCK_LAST_BID_PLAYER = matcher.group(1);
                String SKYBLOCK_LAST_BID_PRICE = matcher.group(2);
                String text = "§6" + SKYBLOCK_LAST_BID_PLAYER + " §fподнял ставку до §6" + SKYBLOCK_LAST_BID_PRICE +"$";
                mc.player.sendStatusMessage(new TextComponentString(text), true);
            }
            return;
        }
        if (m==ml.NEW_ASK || m==ml.UNANSWERED_ASKS) {
            message.appendSibling(new TextComponentString(" §a[Посмотреть вопросы]").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/q"))));
            return;
        }
        if (m==ml.UNREAD_MAIL) {
            message.appendSibling(new TextComponentString(" §a[Открыть почту]").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/mailgui"))));
            return;
        }
        if (m==ml.REGISTER) {
            message.appendSibling(new TextComponentString(" §a[Сгенерировать пароль]").setStyle(new Style().setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/genpwd"))));
        }
    }
}

