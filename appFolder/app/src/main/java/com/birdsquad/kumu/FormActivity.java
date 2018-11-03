package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }

    /**
     * Submits section 1 form data to the current form, goes to next section of the form.
     * @param view
     */
    public void goToSection2(View view) {
        // check all fields and submit to current form
        Spinner woSpinner = (Spinner) findViewById(R.id.wildOutplantedDropdown);
        EditText tNameBox = (EditText) findViewById(R.id.taxonNameBox);
        EditText obsBox = (EditText) findViewById(R.id.observerNameBox);
        EditText orgBox = (EditText) findViewById(R.id.orgNameBox);
        Spinner islSpinner = (Spinner) findViewById(R.id.islandDropdown);
        EditText aCodeBox = (EditText) findViewById(R.id.areaCodeBox);
        EditText rSiteBox = (EditText) findViewById(R.id.refSiteBox);
        EditText locNoteBox = (EditText) findViewById(R.id.locationNoteBox);

        String wildOutplanted = woSpinner.getSelectedItem().toString();
        String tName = tNameBox.getText().toString();
        String obs = obsBox.getText().toString();
        String orgName = orgBox.getText().toString();
        String island = islSpinner.getSelectedItem().toString();
        String aCode = aCodeBox.getText().toString();
        String rSite = rSiteBox.getText().toString();
        String locNotes = locNoteBox.getText().toString();

        KumuApp.getAppStorage().getCurrentForm().addFieldsSection1(
                wildOutplanted,
                tName,
                obs,
                orgName,
                island,
                aCode,
                rSite,
                locNotes
        );

        Intent intent = new Intent(this, FormActivity2.class);
        startActivity(intent);
    }

    public void finishLaterSection1(View view) {

        Spinner woSpinner = (Spinner) findViewById(R.id.wildOutplantedDropdown);
        EditText tNameBox = (EditText) findViewById(R.id.taxonNameBox);
        EditText obsBox = (EditText) findViewById(R.id.observerNameBox);
        EditText orgBox = (EditText) findViewById(R.id.orgNameBox);
        Spinner islSpinner = (Spinner) findViewById(R.id.islandDropdown);
        EditText aCodeBox = (EditText) findViewById(R.id.areaCodeBox);
        EditText rSiteBox = (EditText) findViewById(R.id.refSiteBox);
        EditText locNoteBox = (EditText) findViewById(R.id.locationNoteBox);

        String wildOutplanted = woSpinner.getSelectedItem().toString();
        String tName = tNameBox.getText().toString();
        String obs = obsBox.getText().toString();
        String orgName = orgBox.getText().toString();
        String island = islSpinner.getSelectedItem().toString();
        String aCode = aCodeBox.getText().toString();
        String rSite = rSiteBox.getText().toString();
        String locNotes = locNoteBox.getText().toString();

        KumuApp.getAppStorage().getCurrentForm().addFieldsSection1(
                wildOutplanted,
                tName,
                obs,
                orgName,
                island,
                aCode,
                rSite,
                locNotes
        );

        Toast.makeText(FormActivity.this, "Form saved for later.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, SpeciesNameActivity.class);
        startActivity(intent);
    }
}
