package com.example.contacts;

public class Person {
    private final int imageID;
    private final String title;
    private final boolean online;

    public Person(int imageID, String title, boolean online) {
        this.imageID = imageID;
        this.title = title;
        this.online = online;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTitle() {
        return title;
    }

    public boolean isOnline() {
        return online;
    }
}
