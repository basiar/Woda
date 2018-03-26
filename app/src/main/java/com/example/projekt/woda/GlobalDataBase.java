package com.example.projekt.woda;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

/**
 * Created by Basia on 24.03.2018.
 */

//do udostepnienia obslugi bazy danych wszystkim activities
public class GlobalDataBase extends Application
{
    private DataBase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db=new DataBase(getApplicationContext());
    }

    public DataBase getDb()
    {
        return db;
    }
}
