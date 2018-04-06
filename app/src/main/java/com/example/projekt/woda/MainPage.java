package com.example.projekt.woda;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

public class MainPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    User user = new User();
    GlobalDataBase globalDataBase = new GlobalDataBase(this);
    Hydration hydration = new Hydration();
    private static double dailyHydration = 0;
    private static double percent;
    ProgressBar hydrationBar;

    final ArrayList<String> drinks = new ArrayList<String>();
    final ArrayList<String> desc = new ArrayList<String>();
    final ArrayList<Integer> img = new ArrayList<Integer>();

    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hydrationBar = (ProgressBar) findViewById(R.id.HydrationBar);

        //Wczytanie z BD dzienny stan nawodnienia
        cursor = GlobalDataBase.getDb().getDailyData();
        while (cursor.moveToNext()){
            hydrationBar.setProgress(cursor.getInt(1));
            drinks.add(cursor.getString(3));
            desc.add(cursor.getString(4));
            img.add(cursor.getInt(2));
        }

        //Rozwijanie/zwijanie menu
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Wyswietlenie listy wypitych napojow
        final ListView listView = (ListView) findViewById(R.id.status_ListView);
        StatusListView statusListView = new StatusListView(this,drinks,desc,img);
        listView.setAdapter(statusListView);

        //Wyswietlanie porad
        ArrayAdapter<String> porady_adapter = new ArrayAdapter<String>(this, R.layout.items, R.id.tip ,Tips.advice);
        ListView listView2 = (ListView) findViewById(R.id.tips_ListView);
        listView2.setAdapter(porady_adapter);

        //Wyswietlanie daty na gorze ekranu
        TextView date1;
        date1 = (TextView) findViewById(R.id.date);
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM");
        Date date = new Date();
        date1.setText(dateFormat.format(date));

        //Dodawanie nowego napoju
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainPage.this);
                View view1 = getLayoutInflater().inflate(R.layout.items3,null);
                builder.setView(view1);
                final AlertDialog dialog = builder.create();
                dialog.show();

                Button _200 = (Button) view1.findViewById(R.id.b200);
                Button _250 = (Button) view1.findViewById(R.id.b250);
                Button _500 = (Button) view1.findViewById(R.id.b500);
                Button _1000 = (Button) view1.findViewById(R.id.b1000);
                Button _1500 = (Button) view1.findViewById(R.id.b1500);

                _200.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("200 ml");
                        img.add(R.drawable.water2);
                        dailyHydration+=200;
                        percent = (dailyHydration / hydration.getHyd())*100;
                        hydrationBar.setProgress((int) percent);
                        listView.invalidateViews();
                        dialog.dismiss();
                        Log.v("hydration", String.valueOf(dailyHydration));
                        Log.v("hyd precent", String.valueOf((int)percent));
                    }
                });
                _250.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("250 ml");
                        img.add(R.drawable.water2);
                        dailyHydration+=250;
                        percent = (dailyHydration / hydration.getHyd())*100;
                        hydrationBar.setProgress((int) percent);
                        Log.v("hydration", String.valueOf(dailyHydration));
                        Log.v("hyd precent", String.valueOf((int)percent));
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
                        percent = (dailyHydration / hydration.getHyd())*100;
                        hydrationBar.setProgress((int) percent);
                        listView.invalidateViews();
                        dialog.dismiss();
                        Log.v("hydration", String.valueOf(dailyHydration));
                        Log.v("hyd precent", String.valueOf((int)percent));
                    }
                });
                _1000.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("1000ml (1l)");
                        img.add(R.drawable.water2);
                        dailyHydration+=1000;
                        percent = (dailyHydration / hydration.getHyd())*100;
                        hydrationBar.setProgress((int) percent);
                        listView.invalidateViews();
                        dialog.dismiss();
                    }
                });
                _1500.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        drinks.add("Woda");
                        desc.add("1500 ml (1,5l)");
                        img.add(R.drawable.water2);
                        dailyHydration+=1500;
                        percent = (dailyHydration / hydration.getHyd())*100;
                        hydrationBar.setProgress((int) percent);
                        listView.invalidateViews();
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (int i=0; i< drinks.size(); i++ ){
            globalDataBase.getDb().insert_Daily_Data((int)percent,drinks.get(i),desc.get(i),img.get(i));
        }
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
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
            Intent intent = new Intent(MainPage.this,Calendar.class);
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
