package com.ikeyler.MLMod;

import java.util.ArrayList;
import java.util.List;

public class Friends {
    public List<String> friendList = new ArrayList<>();
    public boolean isFriend(String name) {
        return friendList.contains(name);
    }
    public void addFriend(String name) {
        friendList.add(name);
    }
    public void removeFriend(String name) {
        friendList.remove(name);
    }
    public void toggleFriend(String name) {
        if (isFriend(name)) {
            removeFriend(name);
        }
        else {
            addFriend(name);
        }
    }
}
