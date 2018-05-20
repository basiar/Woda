package com.example.projekt.woda;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Basia on 24.03.2018.
 */


public class GlobalDataBase extends Application
{
    private static DataBase db;

    GlobalDataBase(Context context){
        db = new DataBase(context);
    }

    public static DataBase getDb()
    {
        return db;
    }
}
