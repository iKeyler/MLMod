package com.ikeyler.MLMod.util;

import com.ikeyler.MLMod.Configs;
import com.ikeyler.MLMod.Sounds;
import net.minecraft.client.Minecraft;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.ikeyler.MLMod.Main.logger;
import static com.ikeyler.MLMod.Main.mc;

public class Func {
    public static final String[] Colors = {"§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§c", "§e", "§b", "§a", "§d", "§f", "§l", "§r", "§o", "§k", "§n", "§m", "§r"};
    public static boolean CheckIgnore(String player) {
        String[] split = player.split(" ");
        player = split[split.length-1];
        for (String nickname:Configs.Filters.IgnoreList) {
            if (player.equalsIgnoreCase(nickname)) return true;
        }
        return false;
    }
    public static boolean CheckWorld(String id) {
        for (String value:Configs.Filters.IgnoredWorlds) {
            if (value.trim().equals(id.trim())) return true;
        }
        return false;
    }
    public static boolean CheckBanWord(String string) {
        for (String word:Configs.Filters.BanWords) {
            if (!word.startsWith("!!REGEX!! ")) {
                if (string.toLowerCase().contains(word.toLowerCase())) return true;
            }
            else {
                Pattern pattern = Pattern.compile(word.replace("!!REGEX!! ", "").toLowerCase());
                Matcher matcher = pattern.matcher(string.toLowerCase());
                if (matcher.matches()) return true;
            }
        }
        return false;
    }
    public static <T> List<T> arrayToList(T[] array) {
        return Arrays.stream(array).collect(Collectors.toList());
    }
    public static <T> T[] listToArray(List<T> list, T[] array) {
        return list.toArray(array);
    }
    public static void addToIgnoreList(String[] names) {
        List<String> ignorelist = arrayToList(Configs.Filters.IgnoreList);
        for (String name:names) {
            if (!ignorelist.contains(name)) {
                ignorelist.add(name);
            }
        }
        Configs.Filters.IgnoreList = listToArray(ignorelist, Configs.Filters.IgnoreList);
        syncConfig();
    }
    public static void syncConfig() {
        ConfigManager.sync(Reference.MOD_ID, Config.Type.INSTANCE);
    }
    public static ClickEvent importIgnoreList() {
        return new ClickEvent(ClickEvent.Action.RUN_COMMAND, ".mlmod.importignorelist");
    }
    public static Message checkMessage(String text) {
        for (Messages template : Messages.values()) {
            Pattern pattern = Pattern.compile(template.getMessage());
            Matcher matcher = pattern.matcher(text);
            if (matcher.matches()) {
                return new Message(template, matcher);
            }
        }
        return null;
    }
    public static void notificationSound() {
        if (Configs.Other.NotificationSound) mc.world.playSound(Minecraft.getMinecraft().player.getPosition(), Sounds.notification, SoundCategory.MASTER, 1.0F, 1.0F, false);
    }
    public static HashMap<String, String> wrongLangCmds() {
        HashMap<String, String> commands = new HashMap<>();
        commands.put("рги", "/hub");
        commands.put("вум", "/dev");
        commands.put("здфн", "/play");
        commands.put("кгт", "/run");
        commands.put("игшдв", "/build");
        commands.put("зфкен", "/party");
        commands.put("ьып", "/msg");
        commands.put("ь", "/m");
        commands.put("ц", "/w");
        commands.put("вс", "/dc");
        return commands;
    }
    public static String removeColors(String text) {
        for (String color:Colors) {
            text = text.replace(color, "");
        }
        return text;
    }
    public static void debug(Object text) {
        if (Configs.Other.debug)
            logger.debug(text);
    }
    public static void Wait(Runnable task, long delay) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(task, delay, TimeUnit.MILLISECONDS);
    }
    public static <T> T getLastElement(T[] array) {
        return array[array.length-1];
    }
}
