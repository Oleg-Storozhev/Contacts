package com.example.contacts;

public class Person {
    private final int imageID;
    private final String title;
    private final boolean online;
    private final String gender;
    private final String email;

    public Person(int imageID, String title, boolean online, String gender, String email) {
        this.imageID = imageID;
        this.title = title;
        this.online = online;
        this.gender = gender;
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }
}
