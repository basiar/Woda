package com.example.dawid.woda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner_gender;
    ArrayAdapter<CharSequence> adapter_gender;
    String gender_choice;

    EditText age_field;
    EditText weight_field;
    Button ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner_gender = (Spinner) findViewById(R.id.gender_choice);
        adapter_gender = ArrayAdapter.createFromResource(this,R.array.gender_list, android.R.layout.simple_spinner_item);
        adapter_gender .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapter_gender);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender_choice = adapterView.getSelectedItem().toString();
                Toast.makeText(adapterView.getContext(),gender_choice,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        age_field = (EditText)findViewById(R.id.age_field);
        weight_field = (EditText)findViewById(R.id.weight_field);

        age_field.setText(String.valueOf(User.age));
        weight_field.setText(String.valueOf(User.weight));

        ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User.age = Integer.parseInt(weight_field.getText().toString());
                User.weight = Integer.parseInt(weight_field.getText().toString());
                User.gender = gender_choice;

                Intent intent = new Intent(MainActivity.this,MainPage.class);
                startActivity(intent);
            }
        });
    }
}
