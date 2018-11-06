package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class FormActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4);

        Switch enterPopulation = (Switch) findViewById(R.id.population_data_switch);
        final LinearLayout innerFields = (LinearLayout) findViewById(R.id.population_data_layout);

        enterPopulation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    innerFields.setVisibility(View.VISIBLE);
                }
                else {
                    innerFields.setVisibility(View.GONE);
                }
            }
        });

    }

    public void goToSection5(View view) {
        // check all fields and submit to current form
        // this method is mostly unimplemented
        Intent intent = new Intent(this, FormActivity5.class);
        startActivity(intent);
    }

    public void finishLaterSection4 (View view) {
        Intent intent = new Intent(this, SpeciesNameActivity.class);
        Toast.makeText(FormActivity4.this, "Form saved for later.",
                Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
