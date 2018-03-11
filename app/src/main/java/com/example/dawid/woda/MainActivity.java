package com.example.dawid.woda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    DaneUzytkownika daneUzytkownika;

    Spinner spinner_plec;
    ArrayAdapter<CharSequence> adapter_plec;
    String plec_wybor;

    EditText wiek_pole;
    EditText waga_pole;

    Button dalej;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_plec = (Spinner) findViewById(R.id.plec_wybor);
        adapter_plec = ArrayAdapter.createFromResource(this,R.array.plec_lista, android.R.layout.simple_spinner_item);
        adapter_plec .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_plec.setAdapter(adapter_plec);
        spinner_plec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                plec_wybor = adapterView.getSelectedItem().toString();
                Toast.makeText(adapterView.getContext(),plec_wybor,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        wiek_pole = (EditText)findViewById(R.id.wiek_pole);
        waga_pole = (EditText)findViewById(R.id.waga_pole);

        wiek_pole.setText(String.valueOf(DaneUzytkownika.wiek));
        waga_pole.setText(String.valueOf(DaneUzytkownika.waga));

        dalej = (Button)findViewById(R.id.dalej);
        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DaneUzytkownika.wiek = Integer.parseInt(wiek_pole.getText().toString());
                DaneUzytkownika.waga = Integer.parseInt(waga_pole.getText().toString());
                DaneUzytkownika.plec = plec_wybor;

                Intent intent = new Intent(MainActivity.this,MainPage.class);
                startActivity(intent);
            }
        });
    }
}
