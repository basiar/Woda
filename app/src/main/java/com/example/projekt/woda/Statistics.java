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

import java.text.SimpleDateFormat;
import java.util.Date;

public class Statistics extends AppCompatActivity
{
    Cursor cursor;
    GraphView graph;
    LineGraphSeries<DataPoint> series;
    PointsGraphSeries<DataPoint> series2;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistic);

        series = new LineGraphSeries<DataPoint>();
        series2 = new PointsGraphSeries<DataPoint>();
        cursor = GlobalDataBase.getDb().getWeightData();

        graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);

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
                Toast.makeText(context, String.valueOf(dataPoint.getY()), Toast.LENGTH_SHORT).show();
            }
        });

        if(cursor.getCount() != 0){
            graph.getViewport().setXAxisBoundsManual(true);
            cursor.moveToFirst();
            graph.getViewport().setMinX(cursor.getLong(1));
            cursor.moveToLast();
            graph.getViewport().setMaxX(cursor.getLong(1));
        }



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
