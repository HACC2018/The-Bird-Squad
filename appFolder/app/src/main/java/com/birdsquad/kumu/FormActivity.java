package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        setFields();
        super.onCreateDrawer();
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

        KumuApp.getAppStorage().saveForms();

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

        KumuApp.getAppStorage().saveForms();

        Toast.makeText(FormActivity.this, "Form saved for later.",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, SpeciesNameActivity.class);
        startActivity(intent);
    }

    /**
     * Initialize fields with current form data.
     */
    public void setFields() {
        Spinner woSpinner = (Spinner) findViewById(R.id.wildOutplantedDropdown);
        EditText tNameBox = (EditText) findViewById(R.id.taxonNameBox);
        EditText obsBox = (EditText) findViewById(R.id.observerNameBox);
        EditText orgBox = (EditText) findViewById(R.id.orgNameBox);
        Spinner islSpinner = (Spinner) findViewById(R.id.islandDropdown);
        EditText aCodeBox = (EditText) findViewById(R.id.areaCodeBox);
        EditText rSiteBox = (EditText) findViewById(R.id.refSiteBox);
        EditText locNoteBox = (EditText) findViewById(R.id.locationNoteBox);

        int woSpinnerIndex;
        if (KumuApp.getAppStorage().getCurrentForm().wildOrOutplanted != null) {
            switch(KumuApp.getAppStorage().getCurrentForm().wildOrOutplanted) {
                case "Wild" : woSpinnerIndex = 0;
                    break;
                case "Outplanted" : woSpinnerIndex = 1;
                    break;
                case "InterSitu (i.e. Garden, Seed, Orchard)" : woSpinnerIndex = 2;
                    break;
                default : woSpinnerIndex = 0;
                    break;
            }
        }
        else {
            woSpinnerIndex = 0;
        }

        woSpinner.setSelection(woSpinnerIndex);

        int islandSpinnerIndex;
        if (KumuApp.getAppStorage().getCurrentForm().island != null) {
            switch(KumuApp.getAppStorage().getCurrentForm().island) {
                case "Hawaii" : islandSpinnerIndex = 0;
                    break;
                case "Maui" : islandSpinnerIndex = 1;
                    break;
                case "Oahu" : islandSpinnerIndex = 2;
                    break;
                case "Molokai" : islandSpinnerIndex = 3;
                    break;
                case "Kauai" : islandSpinnerIndex = 4;
                    break;
                case "Niihau" : islandSpinnerIndex = 5;
                    break;
                case "Lanai" : islandSpinnerIndex = 6;
                    break;
                case "Kahoolawe" : islandSpinnerIndex = 7;
                    break;
                default : islandSpinnerIndex = 0;
                    break;
            }
        }
        else {
            islandSpinnerIndex = 0;
        }

        islSpinner.setSelection(islandSpinnerIndex);

        tNameBox.setText(KumuApp.getAppStorage().getCurrentForm().taxonName);
        obsBox.setText(KumuApp.getAppStorage().getCurrentForm().observerName);
        orgBox.setText(KumuApp.getAppStorage().getCurrentForm().organizationName);
        aCodeBox.setText(KumuApp.getAppStorage().getCurrentForm().areaCode);
        rSiteBox.setText(KumuApp.getAppStorage().getCurrentForm().refSite);
        locNoteBox.setText(KumuApp.getAppStorage().getCurrentForm().locationNotes);
    }
}
