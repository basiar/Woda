package com.example.projekt.woda;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Wyświetlanie tabeli 1
        Cursor cursor = GlobalDataBase.getDb().getUserData();
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()){
            stringBuffer.append("ID: "+cursor.getString(0)+"\n");
            stringBuffer.append("Gender: "+cursor.getString(1)+"\n");
            stringBuffer.append("Weight: "+cursor.getString(2)+"\n");
            stringBuffer.append("Age: "+cursor.getString(3)+"\n");
            stringBuffer.append("Nursing: "+cursor.getString(4)+"\n");
            stringBuffer.append("Pregnant: "+cursor.getString(5)+"\n");
            stringBuffer.append("Activity: "+cursor.getString(6)+"\n");
            stringBuffer.append("--------------------------------------\n");
        }

        // Wyświetlanie tabeli 3
       /* Cursor cursor = GlobalDataBase.getDb().getWeightData();
        StringBuffer stringBuffer = new StringBuffer();
        while(cursor.moveToNext()){
            stringBuffer.append("ID: "+cursor.getString(0)+"\n");
            stringBuffer.append("Date: "+cursor.getString(1)+"\n");
            stringBuffer.append("Weight: "+cursor.getString(2)+"\n");
        }*/

        // Wyświetlanie tabeli 4
        /*Cursor cursor = GlobalDataBase.getDb().getDailyData();
        StringBuffer stringBuffer = new StringBuffer();
        while(cursor.moveToNext()){
            stringBuffer.append("ID: "+cursor.getString(0)+"\n");
            stringBuffer.append("Progress: "+cursor.getString(1)+"\n");
            stringBuffer.append("Img: "+cursor.getString(2)+"\n");
            stringBuffer.append("Drinks: "+cursor.getString(3)+"\n");
            stringBuffer.append("Description: "+cursor.getString(4)+"\n");
        }*/

        textView = (TextView)findViewById(R.id.textView2);
        textView.setText(stringBuffer.toString());

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalDataBase.getDb().deleteAll();
                textView.setText("");
            }
        });
    }
}
