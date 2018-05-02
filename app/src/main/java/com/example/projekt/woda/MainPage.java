package com.example.projekt.woda;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.icu.text.UnicodeSet;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    GlobalDataBase globalDataBase = new GlobalDataBase(this);
    static double dailyHydration;
    static double dailyNeedHydration;
    static double percent;
    private int dayInYear;

    ArrayList<String> drinks = new ArrayList<String>();
    ArrayList<String> desc = new ArrayList<String>();
    ArrayList<Integer> img = new ArrayList<Integer>();
    ArrayList<Integer> lastWaterAdded = new ArrayList<Integer>();

    Cursor cursor;
    ListView listView;
    ProgressBar hydrationBar;

    Button _200;
    Button _250;
    Button _500;
    Button _1000;
    Button _1500;

    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Intent intent;
    Boolean is_notification_on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.v("user onCreate","OnCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get daily data hydration get drom DB
        cursor = GlobalDataBase.getDb().getDailyData();
        while (cursor.moveToNext()){
            drinks.add(cursor.getString(3));
            desc.add(cursor.getString(4));
            img.add(cursor.getInt(2));
            dailyHydration = cursor.getInt(1);
            dailyNeedHydration = (cursor.getInt(5));
            dayInYear = (cursor.getInt(6));
            lastWaterAdded.add(cursor.getInt(7));
        }
        if(cursor.getCount() == 0){
            dailyHydration = 0;
        }
        cursor = GlobalDataBase.getDb().getUserData();
        if(cursor.getCount() != 0){
            cursor.moveToLast();
            Log.v("user UD DNH", String.valueOf(cursor.getInt(7)));
            dailyNeedHydration = cursor.getInt(7);
        }

        //Check if is new day
        Calendar calendar = Calendar.getInstance();
        if( calendar.get(Calendar.DAY_OF_YEAR) != dayInYear){
            if(cursor.getCount()!=0){
                GlobalDataBase.getDb().insert_Hydration((int)dailyHydration, (int)dailyNeedHydration);
                dailyHydration = 0;
                GlobalDataBase.getDb().deleteDailyData();
            }
        }

        //Hydration Bar set onCreate
        hydrationBar = findViewById(R.id.HydrationBar);
        hydrationBar.setScaleY(3f);
        hydrationBar.getProgressDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
        if(cursor.getCount() == 0) {
            hydrationBar.setProgress(0);
            cursor = GlobalDataBase.getDb().getHydration();
            if(cursor.getCount() != 0){
                cursor.moveToLast();
                dailyNeedHydration = cursor.getInt(3);
            }
        }
        else {
            hydrationBar.setProgress((int)((dailyHydration/dailyNeedHydration)*100));
        }

        Log.v("user DH", String.valueOf(dailyNeedHydration));

        //Rozwijanie/zwijanie menu
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                      this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Wyswietlenie listy wypitych napojow
        listView = (ListView) findViewById(R.id.status_ListView);
        StatusListView statusListView = new StatusListView(this,drinks,desc,img);
        listView.setAdapter(statusListView);

        //Wyswietlanie porad
        /*ArrayAdapter<String> porady_adapter = new ArrayAdapter<String>(this, R.layout.items, R.id.tip ,Tips.advice);
        ListView listView2 = findViewById(R.id.tips_ListView);
        listView2.setAdapter(porady_adapter);*/

        //Wyswietlanie daty na gorze ekranu
        TextView date1;
        date1 = findViewById(R.id.date);
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM");
        Date date = new Date();
        date1.setText(dateFormat.format(date));

        //Dodawanie nowego napoju
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
                View view1 = getLayoutInflater().inflate(R.layout.items3,null);
                builder.setView(view1);
                final AlertDialog dialog = builder.create();
                dialog.show();

                _200 = (Button) view1.findViewById(R.id.b200);
                _250 = (Button) view1.findViewById(R.id.b250);
                _500 = (Button) view1.findViewById(R.id.b500);
                _1000 = (Button) view1.findViewById(R.id.b1000);
                _1500 = (Button) view1.findViewById(R.id.b1500);

                _200.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("200 ml");
                        img.add(R.drawable.water2);
                        dailyHydration+=200;
                        percent = (dailyHydration / dailyNeedHydration)*100;
                        hydrationBar.setProgress((int) percent);
                        listView.invalidateViews();
                        dialog.dismiss();
                        lastWaterAdded.add(200);

                    }
                });
                _250.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("250 ml");
                        img.add(R.drawable.water2);
                        dailyHydration+=250;
                        percent = (dailyHydration / dailyNeedHydration)*100;
                        hydrationBar.setProgress((int) percent);
                        lastWaterAdded.add(250);
                        listView.invalidateViews();
                        dialog.dismiss();
                    }
                });
                _500.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("500 ml (0,5l)");
                        img.add(R.drawable.water2);
                        dailyHydration+=500;
                        percent = (dailyHydration / dailyNeedHydration)*100;
                        hydrationBar.setProgress((int) percent);
                        listView.invalidateViews();
                        dialog.dismiss();
                        lastWaterAdded.add(500);
                    }
                });
                _1000.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("1000ml (1l)");
                        img.add(R.drawable.water2);
                        dailyHydration+=1000;
                        percent = (dailyHydration /dailyNeedHydration)*100;
                        hydrationBar.setProgress((int) percent);
                        listView.invalidateViews();
                        dialog.dismiss();
                        lastWaterAdded.add(1000);
                    }
                });
                _1500.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("1500 ml (1,5l)");
                        img.add(R.drawable.water2);
                        dailyHydration+=1500;
                        lastWaterAdded.add(1500);
                        percent = (dailyHydration / dailyNeedHydration)*100;
                        hydrationBar.setProgress((int) percent);
                        listView.invalidateViews();
                        dialog.dismiss();
                    }
                });
            }
        });

        //Usuwanie ostatnio dodanego napoju
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lastWaterAdded.size() != 0){
                    dailyHydration -= lastWaterAdded.get(lastWaterAdded.size()-1);
                    lastWaterAdded.remove(lastWaterAdded.size()-1);
                    drinks.remove(drinks.size()-1);
                    img.remove(img.size()-1);
                    desc.remove(desc.size()-1);
                    hydrationBar = findViewById(R.id.HydrationBar);
                    hydrationBar.setProgress((int)((dailyHydration/dailyNeedHydration)*100));
                    listView.invalidateViews();
                    GlobalDataBase.getDb().deleteLastDailyData();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("user onPause","=== OnPause ===");
        //Save daily data hydration to DB onPause
        cursor = GlobalDataBase.getDb().getDailyData();
        for (int i=cursor.getCount(); i< drinks.size(); i++ ){
            globalDataBase.getDb().insert_Daily_Data((int)dailyHydration,drinks.get(i),desc.get(i),img.get(i),(int)dailyNeedHydration,lastWaterAdded.get(i));
        }
        drinks.removeAll(drinks);
        desc.removeAll(desc);
        img.removeAll(img);
        lastWaterAdded.removeAll(lastWaterAdded);
        dailyHydration = 0;
    }

    @Override
    protected void onResume() {
        Log.v("log user onResume","=== OnResume ===");
        //Get daily data hydration from DB onResume
        super.onResume();
        drinks.removeAll(drinks);
        desc.removeAll(desc);
        img.removeAll(img);
        lastWaterAdded.removeAll(lastWaterAdded);
        cursor = GlobalDataBase.getDb().getDailyData();
        while (cursor.moveToNext()){
            dailyHydration = cursor.getInt(1);
            drinks.add(cursor.getString(3));
            desc.add(cursor.getString(4));
            img.add(cursor.getInt(2));
            lastWaterAdded.add(cursor.getInt(7));
        }
        if(cursor.getCount() == 0){
            dailyHydration = 0;
        }
        cursor = GlobalDataBase.getDb().getUserData();
        if(cursor.getCount() != 0){
            cursor.moveToLast();
            dailyNeedHydration = cursor.getInt(7);
        }

        hydrationBar = findViewById(R.id.HydrationBar);
        hydrationBar.setProgress((int)((dailyHydration/dailyNeedHydration)*100));

        listView = (ListView) findViewById(R.id.status_ListView);
        StatusListView statusListView = new StatusListView(this,drinks,desc,img);
        listView.setAdapter(statusListView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("log user onDestroy","=== OnDestroy ====");
        //Save daily data hydration to DB onDestroy
        cursor = GlobalDataBase.getDb().getDailyData();
        for (int i=cursor.getCount(); i< drinks.size(); i++ ){
            globalDataBase.getDb().insert_Daily_Data((int)dailyHydration,drinks.get(i),desc.get(i),img.get(i), (int) dailyNeedHydration,lastWaterAdded.get(i));
        }
        drinks.removeAll(drinks);
        desc.removeAll(desc);
        img.removeAll(img);
        lastWaterAdded.removeAll(lastWaterAdded);
        dailyHydration = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Notyfications on
        if (id == R.id.notifications) {
            intent = new Intent(this, AlarmReceivier.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 3000 , pendingIntent);
            is_notification_on = true;
        }
        //Notyfications off
        if (id == R.id.notifications_off) {
            if(is_notification_on){
                alarmManager.cancel(pendingIntent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Powiadomienia nie są włączone", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.date)
        {
            Intent intent = new Intent(MainPage.this,GetUserData.class);
            startActivity(intent);
        }
        else if (id == R.id.statistic)
        {
            Intent intent = new Intent(MainPage.this,Statistics.class);
            startActivity(intent);
        }
        else if (id == R.id.calendar)
        {
            Intent intent = new Intent(MainPage.this,MyCalendar.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_share)
        {
        }
        else if (id == R.id.nav_send)
        {
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
