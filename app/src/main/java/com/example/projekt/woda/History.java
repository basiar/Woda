package com.example.projekt.woda;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class History extends AppCompatActivity {

    TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM");
        Calendar calendar = Calendar.getInstance();

      /*Cursor cursor = GlobalDataBase.getDb().getUserData();
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
        }*/

        Cursor cursor = GlobalDataBase.getDb().getHydration();
        StringBuffer stringBuffer2 = new StringBuffer();
        while (cursor.moveToNext()){
           // stringBuffer2.append("ID: "+cursor.getString(0)+"\n");
            calendar.setTimeInMillis(Long.parseLong(cursor.getString(1)));
            stringBuffer2.append("Data: "+dateFormat.format(calendar.getTime())+"\n");
            stringBuffer2.append("Dzienne nawodnienie: "+cursor.getString(2)+"\n");
            stringBuffer2.append("Dzienne zapotrzebowanie: "+cursor.getString(3)+"\n");
            stringBuffer2.append("--------------------------------------\n");
        }

       /* Cursor cursor = GlobalDataBase.getDb().getWeightData();
        StringBuffer stringBuffer = new StringBuffer();
        while(cursor.moveToNext()){
            stringBuffer.append("ID: "+cursor.getString(0)+"\n");
            stringBuffer.append("Date: "+cursor.getString(1)+"\n");
            stringBuffer.append("Weight: "+cursor.getString(2)+"\n");
        }*/

        cursor = GlobalDataBase.getDb().getDailyData();
        StringBuffer stringBuffer = new StringBuffer();
        while(cursor.moveToNext()){
            //stringBuffer.append("ID: "+cursor.getString(0)+"\n");
            stringBuffer.append("Dzienne nawodnienie: "+cursor.getString(1)+"\n");
            //stringBuffer.append("Img: "+cursor.getString(2)+"\n");
            stringBuffer.append("Napój: "+cursor.getString(3)+"\n");
            stringBuffer.append("Pojemność: "+cursor.getString(4)+"\n");
            //stringBuffer.append("Data: "+cursor.getString(5)+"\n");
            //stringBuffer.append("Last added: "+cursor.getString(6)+"\n");
            stringBuffer.append("--------------------------------------\n");
        }

        textView = findViewById(R.id.textView);
        textView.setText(stringBuffer.toString());

        textView2 = findViewById(R.id.textView2);
        textView2.setText(stringBuffer2.toString());

        Button button = findViewById(R.id.reset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalDataBase.getDb().deleteAll();
                textView2.setText("");
                textView.setText("");
            }
        });
    }
}
