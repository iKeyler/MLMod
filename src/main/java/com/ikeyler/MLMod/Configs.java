package com.ikeyler.MLMod;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.ikeyler.MLMod.Main.MsgList;

@Config(modid=Reference.MOD_ID)
@Mod.EventBusSubscriber(modid=Reference.MOD_ID)
public class Configs {
    @Config.LangKey("mlmod.general.Messages")
    public static Messages Messages = new Messages();
    @Config.LangKey("mlmod.general.Other")
    public static Other Other = new Other();
    @Config.LangKey("mlmod.general.Creative")
    public static Creative Creative = new Creative();
    @Config.LangKey("mlmod.general.Filters")
    public static Filters Filters = new Filters();
    @Config.LangKey("mlmod.general.MessageFormatting")
    public static MessageFormatting MessageFormatting = new MessageFormatting();
    public static class Messages {
        @Config.LangKey("mlmod.general.Messages.GeneralMessages")
        public Messages.GeneralMessages generalMessages = new Messages.GeneralMessages();
        @Config.LangKey("mlmod.general.Messages.CreativeMessages")
        public Messages.CreativeMessages creativeMessages = new Messages.CreativeMessages();
        @Config.LangKey("mlmod.general.Messages.BedwarsMessages")
        public Messages.BedwarsMessages bedwarsMessages = new Messages.BedwarsMessages();
        @Config.LangKey("mlmod.general.Messages.SkywarsMessages")
        public Messages.SkywarsMessages skywarsMessages = new Messages.SkywarsMessages();
        public static class GeneralMessages {
            @Config.Name("Новый вопрос")
            public boolean NewAsk = true;
            @Config.Name("Неотвеченные вопросы")
            public boolean UnansweredAsks = true;
            @Config.Name("Непрочитанные письма")
            public boolean UnreadMail = true;
            @Config.Name("Приглашение в мир")
            public boolean WorldInvite = true;
            @Config.Name("Новое видео")
            public boolean NewVideo = true;
            @Config.Name("'Вот ты и дома, на Mineland Network'")
            public boolean WelcomeMessage = true;
            @Config.Name("Сообщение при входе в /dev")
            public boolean DevJoin = true;
            @Config.Name("Открытие кейсов")
            public boolean CaseOpen = true;
            @Config.Name("Игрок получил ... за голосование")
            public boolean Reward = true;
            @Config.Name("Сообщение о покупке")
            public boolean BuyMessage = true;
            @Config.Name("(SW & BW) 'Выбран последний кит'")
            public boolean kitGiven = true;
            @Config.Name("(SW & BW) 'Сегодня это твоя первая игра на ...'")
            public boolean firstGame = true;
            @Config.Name("'Ты зашел с устаревшего IP'")
            public boolean old_ip = false;
        }
        public static class CreativeMessages {
            @Config.Name("'Эта территория недоступна'")
            public boolean TerritoryUnavailable = true;
            @Config.Name("Критическая ошибка§c*")
            @Config.Comment({"Эта настройка не полностью скрывает сообщение:", "остаётся звук и пустые сообщения"})
            public boolean CriticalError = true;
        }
        public static class BedwarsMessages {
            @Config.Name("Игрок зашел в игру")
            public boolean playerJoin = true;
            @Config.Name("Недостаточно денег для покупки")
            public boolean notEnoughMoney = true;
            @Config.Name("'Ты получил блоки'")
            public boolean blocksGiven = true;
        }
        public static class SkywarsMessages {
            @Config.Name("Игрок зашел в игру")
            public boolean playerJoin = true;
        }
    }
    public static class Filters {
        @Config.LangKey("mlmod.general.Filters.IgnoreList")
        @Config.Comment("§7Игнорирует игроков из ЛС, донат и креатив чата")
        public String[] IgnoreList = {};
        @Config.LangKey("mlmod.general.Filters.BanWords")
        @Config.Comment("§7Значения, начинающиеся на §f!!REGEX!! §7поддерживают регулярные выражения")
        public String[] BanWords = {};
        @Config.LangKey("mlmod.general.Filters.IgnoredWorlds")
        @Config.Comment("§7Реклама миров с указанными ID будет исключена из чата")
        public int[] IgnoredWorlds = {};
    }
    public static class Other {
        @Config.Name("§aПроигрывать звук при личном сообщении")
        public boolean NotificationSound = true;
        @Config.Name("Команды на русской раскладке§c*")
        @Config.Comment("§7Команды §6/hub§7, §6/dev§7, §6/play§7, §6/run§7, §6/build§7, §6/party§7, §6/msg§7, §6/dc§7, §6/ad§7, §6/games§7, §6/asks §7можно писать без смены раскладки")
        public boolean wrongLangcmds = false;
        @Config.Name("Отладка мода")
        public boolean debug = false;
        @Config.Name("Включить улучшенный чат")
        public boolean BetterChat = false;
        @Config.Name("(Skyblock) Тип отображения поднятия ставки")
        public SkyblockBidType BidType = SkyblockBidType.CHAT;
        public enum SkyblockBidType {
            CHAT("В чате"),
            HOTBAR("Над хотбаром");
            private final String name;
            SkyblockBidType(String name) {
                this.name = name;
            }
            @Override
            public String toString() {
                return this.name;
            }
        }
    }
    public static class MessageFormatting {
        @Config.Name("Форматирование креатив-чата")
        public String creativeChat = "";
        @Config.Name("Форматирование донат-чата")
        public String donateChat = "";
        @Config.Name("Значок при упоминании")
        public String pingMark = "&c&l!";
        @Config.Name("Значок взаимодействия с игроком")
        public String editMark = "&e✎";
    }
    public static class Creative {
        @Config.Name("§bПоказывать вводимый текст")
        public boolean ShowChatInput = false;
        @Config.Name("§bПроигрывать звук при нажатии ПКМ")
        public boolean PlaySound = false;
        @Config.Name("§bShift+ПКМ для вывода текста книги в чат")
        public boolean ShiftToGetText = false;
    }
    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equals(Reference.MOD_ID)) {
            ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
            MsgList.setMessages();
        }
    }
}