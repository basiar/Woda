package com.example.projekt.woda;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Basia on 24.03.2018.
 */

public class DataBase extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Nawodnienie";
    public static final String TABLE_NAME1="USER_DATA";
    public static final String COL1="ID";
    public static final String COL2="GENDER";
    public static final String COL3="WEIGHT";
    public static final String COL4="AGE";
    public static final String COL5="NURSING";
    public static final String COL6="PREGNANT";
    public static final String TABLE_NAME2="DAILY_HYD";
    public static final String COL7="DATE";
    public static final String COL8="HYDRATION";
    public static final String COL9="HYDRATION_NEEDED";
    public static final String TABLE_NAME3="WEIGHT_DATA";

    public DataBase(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+TABLE_NAME1+" ("+COL1+" PRIMARY KEY AUTOINCREMENT, "+COL2+" TEXT NOT NULL, "+COL3+" INTEGER NOT NULL, "+COL4+ " INTEGER NOT NULL, "+COL5+" INTEGER NOT NULL, "+COL6+" INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE "+TABLE_NAME2+" ("+COL1+" PRIMARY KEY AUTOINCREMENT, "+COL7+" TEXT NOT NULL, "+COL8+" INTEGER NOT NULL, "+COL9+" INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE "+TABLE_NAME3+" ("+COL1+" PRIMARY KEY AUTOINCREMENT, "+COL7+" TEXT NOT NULL, "+COL3+" INTEGER NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME3);
    }

    public boolean insert_UserData()
    {
        long result;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues con=new ContentValues();
        con.put(COL2, User.getGender());
        con.put(COL3, User.getWeight());
        con.put(COL4, User.getAge());
        con.put(COL5, User.getIf_nursing());
        con.put(COL6, User.getIf_pregnant());
        result =db.insert(TABLE_NAME1, null, con);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean insert_Weight(int weight)
    {
        long result;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues con=new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM");
        Date date = new Date();
        con.put(COL7,(dateFormat.format(date)));
        con.put(COL3, weight);
        result=db.insert(TABLE_NAME3, null, con);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    //ogarnac gdzie to zrobic zeby codziennie to sie samo wykonywalo
    public boolean insert_Hydration()
    {
        long result;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues con=new ContentValues();
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM");
        Date date = new Date();
        con.put(COL7,(dateFormat.format(date)));
        con.put(COL3, Hydration.getHyd());
        //dodac nawodnienie z danego dnia-w sensie ile komus sie udalo wypic
        result=db.insert(TABLE_NAME2, null, con);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
