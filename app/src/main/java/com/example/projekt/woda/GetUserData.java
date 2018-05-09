package com.example.projekt.woda;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class GetUserData extends AppCompatActivity {

    Spinner spinner_gender;
    ArrayAdapter<CharSequence> adapter_gender;
    String gender_choice;
    Spinner spinner_activity;
    ArrayAdapter<CharSequence> adapter_activity;
    String activity_choice;
    EditText age_field;
    EditText weight_field;
    Button ok;
    CheckBox box;
    CheckBox box2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_user_data);

        age_field = findViewById(R.id.age_field);
        weight_field = findViewById(R.id.weight_field);
        age_field.setText(String.valueOf(User.getAge()));
        weight_field.setText(String.valueOf(User.getWeight()));
        box = findViewById(R.id.is_pregnant);
        box2 = findViewById(R.id.is_nursing);
        spinner_gender = findViewById(R.id.gender_choice);
        adapter_gender = ArrayAdapter.createFromResource(this,R.array.gender_list, android.R.layout.simple_spinner_item);
        adapter_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_gender.setAdapter(adapter_gender);
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender_choice = adapterView.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spinner_activity = findViewById(R.id.activity_choice);
        adapter_activity = ArrayAdapter.createFromResource(this,R.array.activity_list, android.R.layout.simple_spinner_item);
        adapter_activity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_activity.setAdapter(adapter_activity);
        spinner_activity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activity_choice = adapterView.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        //Set User fields from DB if exits
        Cursor cursor = GlobalDataBase.getDb().getUserData();
        if(cursor.getCount() != 0){
            cursor.moveToLast();
            age_field.setText(String.valueOf(cursor.getInt(3)));
            weight_field.setText(String.valueOf(cursor.getInt(2)));
        }

        ok = findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.setAge(Integer.parseInt(age_field.getText().toString()));
                User.setWeight(Integer.parseInt(weight_field.getText().toString()));
                if(gender_choice.length() == 9) {
                    User.setGender("M");
                    box.setChecked(false);
                    box2.setChecked(false);
                }
                else {
                    User.setGender("K");
                }
                User.setIf_pregnant(box.isChecked());
                User.setIf_nursing(box2.isChecked());
                if(activity_choice=="Średnia"){
                    User.setActivity("S");
                }
                else if (activity_choice=="Wysoka"){
                    User.setActivity("W");
                }
                Hydration.setHyd();

                GlobalDataBase.getDb().insert_Weight();
                GlobalDataBase.getDb().insert_UserData();

                finish();
            }
        });
    }
}
