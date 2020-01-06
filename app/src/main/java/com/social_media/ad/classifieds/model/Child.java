package com.social_media.ad.classifieds.model;

public class Child {
    String name;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    int icon;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    String key;


    public Child(String name, String key, int icon) {
        this.name = name;
        this.key = key;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }
}
