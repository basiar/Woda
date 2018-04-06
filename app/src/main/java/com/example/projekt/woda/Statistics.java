package com.example.projekt.woda;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Statistics extends AppCompatActivity
{
    Cursor cursor;
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series2;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic);

        graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        cursor = GlobalDataBase.getDb().getWeightData();
        int x = 0;
        while (cursor.moveToNext()){
            series.appendData( new DataPoint( cursor.getLong(1), cursor.getInt(2) ) ,false,cursor.getCount());
            x++;
            Log.v("cursor: ", String.valueOf(cursor.getString(1)));
            Log.v("cursor: ", String.valueOf(cursor.getInt(2)));
        }

        graph.addSeries(series);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return simpleDateFormat.format(new Date((long)value));
                } else {
                    return super.formatLabel(value, isValueX) + " kg";
                }
            }
        });


        /*GraphView graph2 = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        Cursor cursor2 = GlobalDataBase.getDb().getWeightData();
        x = 0;
        while (cursor.moveToNext()){
            series2.appendData( new DataPoint( x , cursor.getInt(2) ) ,true,100);
            x++;
        }
        graph.addSeries(series2);*/

    }

}
