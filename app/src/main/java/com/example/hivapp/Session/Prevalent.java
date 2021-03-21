package com.example.hivapp.Session;

import com.example.hivapp.Model.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Prevalent {
    public static User currentOnlineUser;

    public static final String UserPhoneKey = "UserPhone";
    public static final String UserPasswordKey = "UserPassword";

    public static float randFloat(float min, float max) {

        Random rand = new Random();

        return rand.nextFloat() * (max - min) + min;

    }
    public static String Date() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        return sdf.format(cal.getTime());
    }
    public static String giveDate() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss ");
        return sdf.format(cal.getTime());
    }
}

