package com.example.projekt.woda;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.jjoe64.graphview.series.Series;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Statistics extends AppCompatActivity
{
    Cursor cursor;
    GraphView graph,graph2;
    LineGraphSeries<DataPoint> series;
    PointsGraphSeries<DataPoint> series2;
    LineGraphSeries<DataPoint> series3;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic);

        graph = findViewById(R.id.graph);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

        series = new LineGraphSeries<>();
        series2 = new PointsGraphSeries<>();

        cursor = GlobalDataBase.getDb().getWeightData();
        while (cursor.moveToNext()){
            series.appendData( new DataPoint(cursor.getLong(1), cursor.getInt(2)),false, cursor.getCount());
            series2.appendData( new DataPoint(cursor.getLong(1), cursor.getInt(2)),false, cursor.getCount());
        }

        graph.addSeries(series2);
        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return simpleDateFormat.format(new Date((long)value));
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Context context = getApplicationContext();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis((long) dataPoint.getX());
                String x = formatter.format(calendar.getTime());
                Toast.makeText(context, "Data: "+String.valueOf(x)+
                        "\nWaga: "+String.valueOf(dataPoint.getY()) , Toast.LENGTH_LONG).show();
            }
        });

        if(cursor.getCount() != 0) {
            graph.getViewport().setXAxisBoundsManual(true);
            cursor.moveToFirst();
            graph.getViewport().setMinX(cursor.getLong(1));
            cursor.moveToLast();
            graph.getViewport().setMaxX(cursor.getLong(1));
        }

        //----------------------------------------------------------------------------------------//

        graph2 = findViewById(R.id.graph2);
        graph2.getViewport().setScalable(true);
        graph2.getViewport().setScalableY(true);

        series = new LineGraphSeries<DataPoint>();
        series2 = new PointsGraphSeries<DataPoint>();
        series3 = new LineGraphSeries<DataPoint>();

        cursor = GlobalDataBase.getDb().getHydration();
        while (cursor.moveToNext()){
            series.appendData( new DataPoint(cursor.getLong(1), cursor.getInt(2)),false, cursor.getCount());
            series2.appendData( new DataPoint(cursor.getLong(1), cursor.getInt(2)),false, cursor.getCount());
            Log.v("user statisctic"," data cursor.getLong(1)="+cursor.getLong(1));
            Log.v("user statisctic"," dailyH cursor.getLong(12)="+cursor.getLong(2));
        }

        graph2.addSeries(series2);
        graph2.addSeries(series);
        graph2.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    return simpleDateFormat.format(new Date((long)value));
                } else {
                    return super.formatLabel(value, isValueX);
                }
            }
        });

        series.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Context context = getApplicationContext();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis((long) dataPoint.getX());
                String x = formatter.format(calendar.getTime());
                Toast.makeText(context, "Data: "+String.valueOf(x)+
                        "\nWoda(ml): "+String.valueOf(dataPoint.getY()) , Toast.LENGTH_LONG).show();
            }
        });

        if(cursor.getCount() != 0) {
            graph2.getViewport().setXAxisBoundsManual(true);
            cursor.moveToFirst();
            graph2.getViewport().setMinX(cursor.getLong(1));
            cursor.moveToLast();
            graph2.getViewport().setMaxX(cursor.getLong(1));
        }


    }

}
