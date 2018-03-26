package com.example.projekt.woda;

import android.app.Application;

/**
 * Created by Basia on 24.03.2018.
 */

public class Hydration extends Application
{
    private static int hyd;
    public static int getHyd() {
        return hyd;
    }

    public static void setHyd()
    {
        hyd=0;
        if(User.getGender()=="K") {
            if (User.getAge() < 1)
            {
                hyd=125*User.getWeight();
            }
            else if(User.getAge()>=1 && User.getAge()<6)
            {
                hyd=90*User.getWeight();
            }
            else if(User.getAge()>=6 && User.getAge()<10)
            {
                hyd=70*User.getWeight();
            }
            else if(User.getAge()>=10 && User.getAge()<18)
            {
                hyd=40*User.getWeight();
            }
            else if(User.getAge()>=18 && User.getAge()<50)
            {
                hyd=30*User.getWeight();

            }
            else if(User.getAge()>=50)
            {
                hyd=1500+15*(User.getWeight()-20);
            }


            if (User.getIf_pregnant()) {
                hyd = hyd + 300;
            }
            if (User.getIf_nursing()) {
                hyd = hyd + 800;
            }
        }
        else if(User.getGender()=="M")
        {
            if (User.getAge() < 1)
            {
                hyd=150*User.getWeight();
            }
            else if(User.getAge()>=1 && User.getAge()<6)
            {
                hyd=100*User.getWeight();
            }
            else if(User.getAge()>=6 && User.getAge()<10)
            {
                hyd=85*User.getWeight();
            }
            else if(User.getAge()>=10 && User.getAge()<18)
            {
                hyd=50*User.getWeight();
            }
            else if(User.getAge()>=18 && User.getAge()<50)
            {
                hyd=35*User.getWeight();

            }
            else if(User.getAge()>=50)
            {
                hyd=1500+15*(User.getWeight()-20);
            }

            //wliczone juz nawodnienie w trakcie treningu przed i po
            //dla aktywnosci sredniej
            if(User.getActivity()=="S")
            {
                hyd=hyd+15*User.getWeight();
            }
            //dla aktywnosci wysokiej
            else if(User.getActivity()=="W")
            {
                hyd=hyd+20*User.getWeight();
            }
        }
    }
}
