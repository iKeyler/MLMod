package com.ikeyler.MLMod;

import com.ikeyler.MLMod.message.Message;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.ikeyler.MLMod.Main.MsgList;

public class ChatFilter {
    // TODO: 23.01.2024 NEGR
    public List<String> donateChat = new ArrayList<>();
    public List<String> creativeChat = new ArrayList<>();
    public List<String> PM = new ArrayList<>();
    public enum FilterType {
        DONATE_CHAT,
        CREATIVE_CHAT,
        PM
    }
    public void addMessage(Message message, String original) {
        if (message == MsgList.CREATIVE_CHAT) {
            creativeChat.add(original);
        }
        else if (message == MsgList.DONATE_CHAT) {
            donateChat.add(original);
        }
        else {
            PM.add(original);
        }
    }
    public boolean writeToFile(Message message) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("mlmodlogs.txt")) {
            writer.append("----------\n");
            if (message == MsgList.CREATIVE_CHAT) {
                creativeChat.forEach(str -> writer.println(str+"\n"));
            }
            else if (message == MsgList.DONATE_CHAT) {
                donateChat.forEach(str -> writer.append(str).append("\n"));
            }
            else {
                PM.forEach(str -> writer.append(str).append("\n"));
            }
        }
        return true;
    }
}
