package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class FormActivity6 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form6);
        super.onCreateDrawer();

        Switch enterThreats = (Switch) findViewById(R.id.threat_switch);
        final LinearLayout innerFields = (LinearLayout) findViewById(R.id.threat_layout);

        enterThreats.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    public void submitForm(View view) {
        // submit
        KumuApp.getAppStorage().getCurrentForm().isFinished = true;
        KumuApp.getAppStorage().saveForms();
        Intent intent = new Intent(this, SpeciesNameActivity.class);
        Toast.makeText(FormActivity6.this, "Form submitted. Mahalo!",
                Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void finishLaterSection6 (View view) {
        Intent intent = new Intent(this, SpeciesNameActivity.class);
        Toast.makeText(FormActivity6.this, "Form saved for later.",
                Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
