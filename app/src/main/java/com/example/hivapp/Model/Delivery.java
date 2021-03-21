package com.example.hivapp.Model;

public class Delivery {
    String name;
    String image;
    String location;
    String owner;
    String date;
    String price;
    String username;
    String uid;
    String mylocation;

    public Delivery(){

    }

    public Delivery(String name, String image, String location, String owner, String date, String price, String username, String uid, String mylocation) {
        this.name = name;
        this.image = image;
        this.location = location;
        this.owner = owner;
        this.date = date;
        this.price = price;
        this.username = username;
        this.uid = uid;
        this.mylocation = mylocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMylocation() {
        return mylocation;
    }

    public void setMylocation(String mylocation) {
        this.mylocation = mylocation;
    }
}
