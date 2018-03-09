package com.example.dawid.woda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.Serializable;

public class Main2Activity extends AppCompatActivity {

    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        DaneUzytkownika daneUzytkownika = (DaneUzytkownika) intent.getSerializableExtra("dane");
        Log.v("waga", String.valueOf(daneUzytkownika.getWaga()));
        Log.v("wiek", String.valueOf(13));

        textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setText(String.valueOf(daneUzytkownika.getWaga())+" "+ String.valueOf(daneUzytkownika.getWiek()));

    }
}
