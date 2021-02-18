package com.example.hivapp;

public class Chat_Users {
    private int ImageProfile;
    private String username, userDescription;

    public Chat_Users(int imageProfile, String username, String userDescription) {
        ImageProfile = imageProfile;
        this.username = username;
        this.userDescription = userDescription;
    }

    public int getImageProfile() {
        return ImageProfile;
    }

    public void setImageProfile(int imageProfile) {
        ImageProfile = imageProfile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
}
