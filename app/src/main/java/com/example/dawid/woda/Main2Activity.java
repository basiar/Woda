package com.example.dawid.woda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;

public class Main2Activity extends AppCompatActivity {

    TextView textView;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        DaneUzytkownika daneUzytkownika = (DaneUzytkownika) intent.getSerializableExtra("dane");

        textView = (TextView)findViewById(R.id.textView);
        textView.setText(String.valueOf("Wiek:  "+ DaneUzytkownika.wiek));

        textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setText(String.valueOf("Waga: "+DaneUzytkownika.waga));

        textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText(String.valueOf("Płeć: "+DaneUzytkownika.plec));


    }
}
