package com.ikeyler.MLMod.message;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Message {
    private String template;
    private Pattern pattern;
    private boolean active;
    private Matcher matcher;
    public Message(String template) {
        this.template = template;
        this.active = true;
        this.pattern = Pattern.compile(getTemplate());
    }
    public String getTemplate() {
        return this.template;
    }
    public Matcher getMatcher() {
        return this.matcher;
    }
    public void setTemplate(String template) {
        this.template = template;
    }
    public void setActive(boolean active) {
        this.active = active;
        System.out.println("Message "+getTemplate()+" is now "+this.active);
    }
    public boolean isActive() {
        return this.active;
    }
    public boolean matches(String message) {
        this.pattern = Pattern.compile(getTemplate());
        Matcher matcher = pattern.matcher(message);
        if (matcher.matches()) {
            this.matcher = matcher;
            return true;
        }
        return false;
    }
}
