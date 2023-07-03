package com.ikeyler.MLMod.util;

import java.util.regex.Matcher;

public class Message {
    private final Messages msg;
    private final Matcher matcher;
    public Message(Messages msg, Matcher matcher) {
        this.msg = msg;
        this.matcher = matcher;
    }
    public Messages getMessage() {
        return this.msg;
    }
    public Matcher getMatcher() {
        return this.matcher;
    }
}
