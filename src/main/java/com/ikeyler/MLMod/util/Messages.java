package com.ikeyler.MLMod.util;

import com.ikeyler.MLMod.Configs;

import java.util.HashMap;

public enum Messages {
    UNREAD_MAIL("Почта » У тебя есть (.*?) непрочитанных писем - /mail read"),
    UNANSWERED_ASKS("Вопросы » Всего (.*?) неотвеченных вопросов - /asks"),
    PM("✉ » (.*?) -> тебе \\| (.*?)"),
    PERSONAL_JOIN("Персонал » (.*?) зашёл на сервер\\."),
    NEW_ASK("Вопросы » Вопрос от (.*?)\\. Сервер: (.*?)\\."),
    WORLD_INVITE("Игры » Приглашение:\n" +
            " (.*?) приглашает тебя посетить свою игру: (.*?) \n" +
            "    \n" +
            "                       \\[Присоединиться\\]\n" +
            "    "),
    NEW_VIDEO(" \n" +
            " \\| Новое видео на канале игрока (.*?)\n" +
            " \\| Кликни, чтобы посмотреть: \\[YouTube\\]\n" +
            " \\| Название: (.*?)\n" +
            " \\| Просмотры: (.*?)\n" +
            " \\| Комментарий: (.*?)\n"),
    WELCOME_MESSAGE("-----------------------------------------------------\n" +
            "     Вот ты и дома, на Mineland Network\n" +
            "\n" +
            " ❖ Беги вперёд и выбери режим для игры!\n" +
            " ❖ Кликни по компасу, чтобы узнать больше\\.\n" +
            " ❖ Играй с другом, вместе веселее - /party\n" +
            " \n" +
            " \\[ВКонтакте\\] - \\[Дискорд\\] - \\[Форум\\]\n" +
            "-----------------------------------------------------"),
    DEV_JOIN(" Ты снова в режиме разработчика\\. С возвращением!\n" +
            "  \n" +
            "  \n" +
            " \\| Что-то забыл\\? Открой плейлист с примерами!\n" +
            "  \n" +
            "         \\[Кликни для просмотра плейлиста\\]\n" +
            "  \n" +
            "  "),
    REWARD("Награды » Игрок (.*?) получил (.*?) за голосование!\n" +
            "\n" +
            "                  \\[Голосовать\\]\n"),
    BUY_MESSAGE("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" +
            "            ПОЗДРАВЛЯЕМ!\n" +
            "Игрок (.*?) купил (.*?)\n" +
            "Поздравляем его с покупкой!\n" +
            "Хочешь также\\? ➜ mineland\\.net\n" +
            "   \n" +
            "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀"),
    CREATIVE_CHAT("Креатив-чат » (.*?): (.*?)"),
    DONATE_CHAT("Донат-чат » (.*?): (.*?)"),
    //creative
    CREATIVE_TERRITORY_UNAVAILABLE("Регион » Эта территория для тебя недоступна\\."),
    CREATIVE_CRITICAL_ERROR1("Система » При выполнении действия (.*?) произошла критическая ошибка!"),
    CREATIVE_CRITICAL_ERROR2(" Перепроверь свой код на наличие ошибок, возможно, дело именно в этом\\."),
    CREATIVE_CRITICAL_ERROR3(" Если же ты уверен, что дело не в твоем коде, то немедленно сообщи о том, что ты накодил и со скриншотом ошибки!"),
    CREATIVE_CRITICAL_ERROR4(" Сообщить об этом в группу ВК: vk\\.com/mineland_dev"),
    //bedwars
    BEDWARS_PLAYER_JOIN("(?i)b(.*?) » (.*?) зашёл в игру \\((\\d{1,2})/(\\d{1,2})\\)$"),
    FIRST_GAME("Система » Поздравляем! Сегодня это твоя первая игра на (?i)b(.*?)"),
    BEDWARS_NOT_ENOUGH_MONEY("Система » Недостаточно денег для покупки\\."),
    BEDWARS_UPGRADE_BOUGHT("BedWars » (.*?) купил командное улучшение (.*?)"),
    BEDWARS_BLOCKS_GIVEN("Ты получил блоки\\."),
    KIT_GIVEN("Система » Выбран последний кит, который ты использовал\\."),
    //skywars
    SKYWARS_CASE_FOUND1("Игрок (.*?) отыскал тайник\\."),
    SKYWARS_CASE_FOUND2("Посмотри внимательно и ты тоже найдёшь его!"),
    SKYWARS_PLAYER_JOIN("(?i)s(.*?) » (.*?) зашёл в игру \\((\\d{1,2})/(\\d{1,2})\\)$"),
    VERIFICATION("Guard » To continue, enter the number from the image in chat\\."),
    VERIFICATION2("Guard » Wait awhile\\. Verification is in progress\\.\\.\\."),
    CASE_OPEN1("╔ (.*?) открыл (.*?)"),
    CASE_OPEN2("╠ Ему выпал предмет (.*?)"),
    CASE_OPEN3("╚ Покупай тайники на сайте ➜ mineland\\.net");
    private final String message;
    public String getMessage() {
        return this.message;
    }
    Messages(String message) {
        this.message = message;
    }
    public static HashMap<Messages, Boolean> messagesToHashMap() {
        HashMap<Messages, Boolean> Msgs = new HashMap<>();
        Msgs.put(Messages.UNANSWERED_ASKS, Configs.Messages.generalMessages.UnansweredAsks);
        Msgs.put(Messages.UNREAD_MAIL, Configs.Messages.generalMessages.UnreadMail);
        Msgs.put(Messages.NEW_ASK, Configs.Messages.generalMessages.NewAsk);
        Msgs.put(Messages.NEW_VIDEO, Configs.Messages.generalMessages.NewVideo);
        Msgs.put(Messages.PERSONAL_JOIN, Configs.Messages.generalMessages.PersonalJoin);
        Msgs.put(Messages.PM, true);
        Msgs.put(Messages.WELCOME_MESSAGE, Configs.Messages.generalMessages.WelcomeMessage);
        Msgs.put(Messages.DEV_JOIN, Configs.Messages.generalMessages.DevJoin);
        Msgs.put(Messages.WORLD_INVITE, Configs.Messages.generalMessages.WorldInvite);
        Msgs.put(Messages.REWARD, Configs.Messages.generalMessages.Reward);
        Msgs.put(Messages.BUY_MESSAGE, Configs.Messages.generalMessages.BuyMessage);
        Msgs.put(Messages.CREATIVE_CHAT, true);
        Msgs.put(Messages.DONATE_CHAT, true);
        Msgs.put(Messages.BEDWARS_PLAYER_JOIN, Configs.Messages.bedwarsMessages.playerJoin);
        Msgs.put(Messages.FIRST_GAME, Configs.Messages.generalMessages.firstGame);
        Msgs.put(Messages.BEDWARS_BLOCKS_GIVEN, Configs.Messages.bedwarsMessages.blocksGiven);
        Msgs.put(Messages.KIT_GIVEN, Configs.Messages.generalMessages.kitGiven);
        Msgs.put(Messages.BEDWARS_NOT_ENOUGH_MONEY, Configs.Messages.bedwarsMessages.notEnoughMoney);
        Msgs.put(Messages.BEDWARS_UPGRADE_BOUGHT, true);
        Msgs.put(Messages.SKYWARS_CASE_FOUND1, Configs.Messages.skywarsMessages.caseFound);
        Msgs.put(Messages.SKYWARS_CASE_FOUND2, Configs.Messages.skywarsMessages.caseFound);
        Msgs.put(Messages.SKYWARS_PLAYER_JOIN, Configs.Messages.skywarsMessages.playerJoin);
        Msgs.put(Messages.CREATIVE_CRITICAL_ERROR1, Configs.Messages.creativeMessages.CriticalError);
        Msgs.put(Messages.CREATIVE_CRITICAL_ERROR2, Configs.Messages.creativeMessages.CriticalError);
        Msgs.put(Messages.CREATIVE_CRITICAL_ERROR3, Configs.Messages.creativeMessages.CriticalError);
        Msgs.put(Messages.CREATIVE_CRITICAL_ERROR4, Configs.Messages.creativeMessages.CriticalError);
        Msgs.put(Messages.CREATIVE_TERRITORY_UNAVAILABLE, Configs.Messages.creativeMessages.TerritoryUnavailable);
        Msgs.put(Messages.VERIFICATION, true);
        Msgs.put(Messages.VERIFICATION2, true);
        Msgs.put(Messages.CASE_OPEN1, Configs.Messages.generalMessages.CaseOpen);
        Msgs.put(Messages.CASE_OPEN2, Configs.Messages.generalMessages.CaseOpen);
        Msgs.put(Messages.CASE_OPEN3, Configs.Messages.generalMessages.CaseOpen);
        return Msgs;
    }
}
