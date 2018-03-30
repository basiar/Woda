package com.example.projekt.woda;

import android.content.Intent;
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

        //DODAC SPINNER DO POZIOMU AKTYWNOSCI

        age_field = (EditText)findViewById(R.id.age_field);
        weight_field = (EditText)findViewById(R.id.weight_field);
        age_field.setText(String.valueOf(User.getAge()));
        weight_field.setText(String.valueOf(User.getWeight()));
        box=(CheckBox)findViewById(R.id.is_pregnant);
        box2=(CheckBox)findViewById(R.id.is_nursing);
        spinner_gender = (Spinner) findViewById(R.id.gender_choice);
        adapter_gender = ArrayAdapter.createFromResource(this,R.array.gender_list, android.R.layout.simple_spinner_item);
        adapter_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
        spinner_activity = (Spinner) findViewById(R.id.activity_choice);
        adapter_activity = ArrayAdapter.createFromResource(this,R.array.activity_list, android.R.layout.simple_spinner_item);
        adapter_activity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_activity.setAdapter(adapter_activity);
        spinner_activity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                activity_choice = adapterView.getSelectedItem().toString();
                Toast.makeText(adapterView.getContext(),gender_choice,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        ok = (Button)findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User.setAge(Integer.parseInt(age_field.getText().toString()));
                User.setWeight(Integer.parseInt(weight_field.getText().toString()));
                User.setGender(gender_choice);
                if(gender_choice=="M")
                {
                    box.setChecked(false);
                    box2.setChecked(false);
                }
                User.setIf_pregnant(box.isChecked());
                User.setIf_nursing(box2.isChecked());
                if(activity_choice=="Średni"){User.setActivity("S");}
                else {User.setActivity("W");}
                User.setActivity(activity_choice);
                boolean b = GlobalDataBase.getDb().insert_UserData();
                Log.v("b:", String.valueOf(b));
                boolean bb = GlobalDataBase.getDb().insert_Weight();
                Log.v("bb:", String.valueOf(bb));

                Intent intent = new Intent(GetUserData.this,MainPage.class);
                startActivity(intent);
            }
        });
    }
}
