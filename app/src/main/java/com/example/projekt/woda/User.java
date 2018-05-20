package com.example.projekt.woda;

import android.app.Application;

import java.io.Serializable;

public class User extends Application implements Serializable {

    private static String gender = "M";
    private static int age = 0;
    private static int weight = 0;
    private static boolean if_pregnant = false;
    private static boolean if_nursing = false;
    private static String activity = "S";

    public static int getAge()
    {
        return age;
    }
    public static int getWeight()
    {
        return weight;
    }
    public static String getGender()
    {
        return gender;
    }
    public static boolean getIf_nursing()
    {
        return if_nursing;
    }
    public static boolean getIf_pregnant()
    {
        return if_pregnant;
    }
    public static String getActivity() {
        return activity;
    }

    public static void setAge(int age)
    {
        User.age = age;
    }
    public static void setGender(String gender)
    {
        User.gender = gender;
    }
    public static void setIf_nursing(boolean if_nursing)
    {
        User.if_nursing = if_nursing;
    }
    public static void setIf_pregnant(boolean if_pregnant)
    {
        User.if_pregnant = if_pregnant;
    }
    public static void setWeight(int weight)
    {
        User.weight = weight;
    }
    public static void setActivity(String activity) {
        User.activity = activity;
    }
}
