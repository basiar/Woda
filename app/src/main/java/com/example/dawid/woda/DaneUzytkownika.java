package com.example.dawid.woda;

import android.util.Log;
import android.widget.EditText;

import java.io.Serializable;

/**
 * Created by Dawid on 05.03.2018.
 */

public class DaneUzytkownika implements Serializable {

    private String plec;
    private int wiek;
    private int waga;

    public DaneUzytkownika(){
        this.plec = "Mężczyzna";
        this.wiek = 1;
        this.waga = 1;
    }

    public String getPlec() {
        return plec;
    }

    public int getWiek() {
        return wiek;
    }
    public int getWaga() {
        return waga;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
        Log.v("sds", String.valueOf(wiek));
    }

    public void setWaga(int waga) {
        this.waga = waga;
        Log.v("sds", String.valueOf(waga));
    }

}
