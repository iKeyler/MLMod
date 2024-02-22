package com.ikeyler.MLMod.message;

import com.ikeyler.MLMod.Configs;

import java.util.HashMap;

public class Messages {
    public final Message UNREAD_MAIL = new Message("Почта » У тебя есть (.*?) непрочитанных писем - /mail read");
    public final Message UNANSWERED_ASKS = new Message("Вопросы » Всего (.*?) неотвеченных вопросов - /asks");
    public final Message PM = new Message("✉ » (.*?) -> тебе \\| (.*?)");
    public final Message NEW_ASK = new Message("Вопросы » Вопрос от (.*?)\\. Сервер: (.*?)\\.");
    public final Message WORLD_INVITE = new Message(" \\| \\[Приглашение в мир\\]\n \\| (.*?) приглашает тебя посетить свою игру:\n \\| (.*?)\n \\| \n \\|            \\[Присоединиться\\]\n \\| \n  ");
    public final Message NEW_VIDEO = new Message(" \n" +
            " \\| Новое видео на канале игрока (.*?)\n" +
            " \\| Кликни, чтобы посмотреть: \\[YouTube\\]\n" +
            " \\| Название: (.*?)\n" +
            " \\| Просмотры: (.*?)\n" +
            " \\| Комментарий: (.*?)\n");
    public final Message WELCOME_MESSAGE = new Message("-----------------------------------------------------\n" +
            "     Вот ты и дома, на Mineland Network\n" +
            "\n" +
            " ❖ Беги вперёд и выбери режим для игры!\n" +
            " ❖ Кликни по компасу, чтобы узнать больше\\.\n" +
            " ❖ Играй с другом, вместе веселее - /party\n" +
            " \n" +
            " \\[ВКонтакте\\] - \\[Дискорд\\] - \\[Форум\\]\n" +
            "-----------------------------------------------------");
    public final Message DEV_JOIN = new Message(" Ты снова в режиме разработчика\\. С возвращением!\n" +
            "  \n" +
            "  \n" +
            " \\| Что-то забыл\\? Открой плейлист с примерами!\n" +
            "  \n" +
            "         \\[Кликни для просмотра плейлиста\\]\n" +
            "  \n" +
            "  ");
    public final Message REWARD = new Message(" \n \\| Игрок (.*?) проголосовал за Mineland!\n \\| ТОП 1 месяца забирает ключ от лицензии Minecraft\\.\n   \n                  \\[Голосовать\\]\n ");
    public final Message BUY_MESSAGE = new Message("▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀\n" +
            "            ПОЗДРАВЛЯЕМ!\n" +
            "Игрок (.*?) купил (.*?)\n" +
            "Поздравляем его с покупкой!\n" +
            "(.*?)" +
            "   \n" +
            "▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀");
    public final Message CREATIVE_CHAT = new Message("§3Креатив-чат §r§8» §r(.*?)§r§7: (.*?)§r");
    public final Message DONATE_CHAT = new Message("§2Донат-чат §r§8» (.*?)§r§7: (.*?)§r");
    public final Message CREATIVE_TERRITORY_UNAVAILABLE = new Message("Регион » Эта территория для тебя недоступна\\.");
    public final Message CREATIVE_CRITICAL_ERROR1 = new Message("Система » При выполнении действия (.*?) произошла критическая ошибка!");
    public final Message CREATIVE_CRITICAL_ERROR2 = new Message(" Перепроверь свой код на наличие ошибок, возможно, дело именно в этом\\.");
    public final Message CREATIVE_CRITICAL_ERROR3 = new Message(" Если же ты уверен, что дело не в твоем коде, то немедленно сообщи о том, что ты накодил и со скриншотом ошибки!");
    public final Message CREATIVE_CRITICAL_ERROR4 = new Message(" Сообщить об этом в группу ВК: vk\\.com/mineland_dev");
    public final Message BEDWARS_PLAYER_JOIN = new Message("(?i)b(.*?) » (.*?) зашёл в игру \\((\\d{1,2})/(\\d{1,2})\\)$");
    public final Message FIRST_GAME = new Message("Система » Поздравляем! Сегодня это твоя первая игра на (?i)b(.*?)");
    public final Message BEDWARS_NOT_ENOUGH_MONEY = new Message("Система » Недостаточно денег для покупки\\.");
    public final Message BEDWARS_UPGRADE_BOUGHT = new Message("BedWars » (.*?) купил командное улучшение (.*?)");
    public final Message BEDWARS_BLOCKS_GIVEN = new Message("Ты получил блоки\\.");
    public final Message KIT_GIVEN = new Message("Система » Выбран последний кит, который ты использовал\\.");
    public final Message SKYWARS_PLAYER_JOIN = new Message("(?i)s(.*?) » (.*?) зашёл в игру \\((\\d{1,2})/(\\d{1,2})\\)$");
    public final Message VERIFICATION = new Message("Guard » Введите код с изображения в чат\\.");
    public final Message VERIFICATION2 = new Message("Guard » Идет проверка, пожалуйста, подождите\\.\\.\\.");
    public final Message SKYBLOCK_BID = new Message("Аукцион » (.*?) поднял ставку до (.*?)\\$\\.");
    public final Message SKYBLOCK_SOLD = new Message("Аукцион » Продано игроку (.*?) за (.*?)\\$\\.");
    public final Message CASE_OPEN1 = new Message("╔ (.*?) открыл (.*?)");
    public final Message CASE_OPEN2 = new Message("╠ Ему выпал предмет (.*?)");
    public final Message CASE_OPEN3 = new Message("╚ Покупай тайники на сайте ➜ mineland\\.net");
    public final Message OLD_IP = new Message("  \n  \n  \n1111111111111111111111111111\n  \n12 \\| \\[ТЫ ЗАШЕЛ С УСТАРЕВШЕГО АЙПИ\\]\n12 \\| Не поменяв IP, ты потеряешь доступ к аккаунту!\n12 \\| Срочно перезайди с IP mc\\.mineland\\.net\n  \n1111111111111111111111111111\n  \n  \n  ");
    public final Message REGISTER = new Message("Авторизация » Зарегистрируйся - \\/reg \\[пароль\\] \\[повтор пароля\\]");
    public final Message[] messages = new Message[] {
            UNREAD_MAIL,
            UNANSWERED_ASKS, NEW_ASK, PM, WORLD_INVITE, NEW_VIDEO, WELCOME_MESSAGE, DEV_JOIN, REWARD, BUY_MESSAGE, CREATIVE_CHAT, DONATE_CHAT,
            CREATIVE_TERRITORY_UNAVAILABLE, CREATIVE_CRITICAL_ERROR1, CREATIVE_CRITICAL_ERROR2, CREATIVE_CRITICAL_ERROR3, CREATIVE_CRITICAL_ERROR4,
            BEDWARS_PLAYER_JOIN, FIRST_GAME, BEDWARS_NOT_ENOUGH_MONEY, BEDWARS_UPGRADE_BOUGHT, BEDWARS_BLOCKS_GIVEN, KIT_GIVEN,
            SKYWARS_PLAYER_JOIN, VERIFICATION, VERIFICATION2, SKYBLOCK_BID, SKYBLOCK_SOLD, CASE_OPEN1, CASE_OPEN2, CASE_OPEN3,
            OLD_IP, REGISTER
    };
    public void setMessages() {
        Configs.Messages m = Configs.Messages;
        UNREAD_MAIL.setActive(m.generalMessages.UnreadMail);
        UNANSWERED_ASKS.setActive(m.generalMessages.UnansweredAsks);
        NEW_ASK.setActive(m.generalMessages.NewAsk);
        WORLD_INVITE.setActive(m.generalMessages.WorldInvite);
        NEW_VIDEO.setActive(m.generalMessages.NewVideo);
        WELCOME_MESSAGE.setActive(m.generalMessages.WelcomeMessage);
        DEV_JOIN.setActive(m.generalMessages.DevJoin);
        REWARD.setActive(m.generalMessages.Reward);
        BUY_MESSAGE.setActive(m.generalMessages.BuyMessage);
        CREATIVE_TERRITORY_UNAVAILABLE.setActive(m.creativeMessages.TerritoryUnavailable);
        CREATIVE_CRITICAL_ERROR1.setActive(m.creativeMessages.CriticalError);
        CREATIVE_CRITICAL_ERROR2.setActive(m.creativeMessages.CriticalError);
        CREATIVE_CRITICAL_ERROR3.setActive(m.creativeMessages.CriticalError);
        CREATIVE_CRITICAL_ERROR4.setActive(m.creativeMessages.CriticalError);
        BEDWARS_PLAYER_JOIN.setActive(m.bedwarsMessages.playerJoin);
        FIRST_GAME.setActive(m.generalMessages.firstGame);
        BEDWARS_NOT_ENOUGH_MONEY.setActive(m.bedwarsMessages.notEnoughMoney);
        BEDWARS_BLOCKS_GIVEN.setActive(m.bedwarsMessages.blocksGiven);
        KIT_GIVEN.setActive(m.generalMessages.kitGiven);
        SKYWARS_PLAYER_JOIN.setActive(m.skywarsMessages.playerJoin);
        CASE_OPEN1.setActive(m.generalMessages.CaseOpen);
        CASE_OPEN2.setActive(m.generalMessages.CaseOpen);
        CASE_OPEN3.setActive(m.generalMessages.CaseOpen);
        OLD_IP.setActive(m.generalMessages.old_ip);
    }
    public HashMap<String, Message> nameToMap() {
        HashMap<String, Message> map = new HashMap<>();
        map.put("UNREAD_MAIL", UNREAD_MAIL);
        map.put("UNANSWERED_ASKS", UNANSWERED_ASKS);
        map.put("PM", PM);
        map.put("NEW_ASK", NEW_ASK);
        map.put("WORLD_INVITE", WORLD_INVITE);
        map.put("NEW_VIDEO", NEW_VIDEO);
        map.put("WELCOME_MESSAGE", WELCOME_MESSAGE);
        map.put("DEV_JOIN", DEV_JOIN);
        map.put("REWARD", REWARD);
        map.put("BUY_MESSAGE", BUY_MESSAGE);
        map.put("CREATIVE_CHAT", CREATIVE_CHAT);
        map.put("DONATE_CHAT", DONATE_CHAT);
        map.put("CREATIVE_TERRITORY_UNAVAILABLE", CREATIVE_TERRITORY_UNAVAILABLE);
        map.put("CREATIVE_CRITICAL_ERROR1", CREATIVE_CRITICAL_ERROR1);
        map.put("CREATIVE_CRITICAL_ERROR2", CREATIVE_CRITICAL_ERROR2);
        map.put("CREATIVE_CRITICAL_ERROR3", CREATIVE_CRITICAL_ERROR3);
        map.put("CREATIVE_CRITICAL_ERROR4", CREATIVE_CRITICAL_ERROR4);
        map.put("BEDWARS_PLAYER_JOIN", BEDWARS_PLAYER_JOIN);
        map.put("FIRST_GAME", FIRST_GAME);
        map.put("BEDWARS_NOT_ENOUGH_MONEY", BEDWARS_NOT_ENOUGH_MONEY);
        map.put("BEDWARS_UPGRADE_BOUGHT", BEDWARS_UPGRADE_BOUGHT);
        map.put("BEDWARS_BLOCKS_GIVEN", BEDWARS_BLOCKS_GIVEN);
        map.put("KIT_GIVEN", KIT_GIVEN);
        map.put("SKYWARS_PLAYER_JOIN", SKYWARS_PLAYER_JOIN);
        map.put("VERIFICATION", VERIFICATION);
        map.put("VERIFICATION2", VERIFICATION2);
        map.put("SKYBLOCK_BID", SKYBLOCK_BID);
        map.put("SKYBLOCK_SOLD", SKYBLOCK_SOLD);
        map.put("CASE_OPEN1", CASE_OPEN1);
        map.put("CASE_OPEN2", CASE_OPEN2);
        map.put("CASE_OPEN3", CASE_OPEN3);
        map.put("OLD_IP", OLD_IP);
        return map;
    }
}
