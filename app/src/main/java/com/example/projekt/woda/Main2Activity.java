package com.example.projekt.woda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView textView;
    TextView textView1;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = (TextView)findViewById(R.id.textView);
        textView.setText(String.valueOf("Wiek:  "+User.age));

        textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setText(String.valueOf("Waga: "+User.weight));

        textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setText(String.valueOf("Płeć: "+User.gender));
    }
}
