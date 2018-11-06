package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class FormActivity5 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form5);
        super.onCreateDrawer();

        Switch enterHabitat = (Switch) findViewById(R.id.habitat_characteristics_switch);
        final LinearLayout innerFields = (LinearLayout) findViewById(R.id.habitat_characteristics_layout);

        enterHabitat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    public void goToSection6(View view) {
        // check all fields and submit to current form if user selected yes
        Intent intent = new Intent(this, FormActivity6.class);
        startActivity(intent);
    }

    public void finishLaterSection5(View view) {
        Intent intent = new Intent(this, SpeciesNameActivity.class);
        Toast.makeText(FormActivity5.this, "Form saved for later.",
                Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
