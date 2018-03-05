package com.example.dawid.woda;

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

public class MainActivity extends AppCompatActivity {

    Spinner spinner_plec;
    ArrayAdapter<CharSequence> adapter_plec;

    EditText wiek_pole;
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
                String text = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        wiek_pole = (EditText)findViewById(R.id.wiek_pole);
        dalej = (Button)findViewById(R.id.dalej);



        dalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("tag","kliknięto przycisk dalej "+ "wiek to: "+ wiek_pole.getText());
            }
        });


    }

}
