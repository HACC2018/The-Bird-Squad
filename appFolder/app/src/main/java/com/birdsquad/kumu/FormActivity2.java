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
import android.widget.Switch;
import android.widget.Toast;

import org.w3c.dom.Text;

public class FormActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);

        Switch enterPopulationStructure = (Switch) findViewById(R.id.enterPopStrucSwitch);
        final LinearLayout innerFields = (LinearLayout) findViewById(R.id.population_structure_layout);

        boolean setVisible = setFields();
        if (setVisible) {
            innerFields.setVisibility(View.VISIBLE);
        }
        else {
            innerFields.setVisibility(View.GONE);
        }

        enterPopulationStructure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    /**
     * Submits field values to current form only if the person has selected to fill out population structure data
     * Does not let user leave fields empty, if they have chosen to fill out this form.
     * @param view
     */
    public void goToSection3(View view) {
        Switch enterPopulationStructure = (Switch) findViewById(R.id.enterPopStrucSwitch);
        EditText numMatureBox = (EditText) findViewById(R.id.numMaturePlantsBox);
        EditText numImmatureBox = (EditText) findViewById(R.id.numImmaturePlantsBox);
        EditText numSeedlingBox = (EditText) findViewById(R.id.numSeedlingsBox);
        CheckBox currentCensusCheck = (CheckBox) findViewById(R.id.currentCensusCheckbox);

        boolean enterPopStruc = enterPopulationStructure.isChecked();

        if (enterPopStruc) {
            String numMatureString = numMatureBox.getText().toString();
            String numImmatureString = numImmatureBox.getText().toString();
            String numSeedlingsString = numSeedlingBox.getText().toString();

            boolean anyFieldsEmpty = TextUtils.isEmpty(numMatureString) ||
                    TextUtils.isEmpty(numImmatureString) ||
                    TextUtils.isEmpty(numSeedlingsString);

            if (!anyFieldsEmpty) {
                Number numMature = Integer.parseInt(numMatureString);
                Number numImmature = Integer.parseInt(numImmatureString);
                Number numSeedlings = Integer.parseInt(numSeedlingsString);
                boolean currentCensus = currentCensusCheck.isChecked();
                KumuApp.getAppStorage().getCurrentForm().addFieldsSection2(
                        enterPopStruc,
                        numMature,
                        numImmature,
                        numSeedlings,
                        currentCensus
                );
                Intent intent = new Intent(this, FormActivity3.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(FormActivity2.this, "You must complete all fields.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            KumuApp.getAppStorage().getCurrentForm().setReportPopulationStructure(false);
            Intent intent = new Intent(this, FormActivity3.class);
            startActivity(intent);
        }
    }

    /**
     * Submit unfinished form to be completed later, go back to home screen
     * @param view
     */
    public void finishLaterSection2(View view) {
        Switch enterPopulationStructure = (Switch) findViewById(R.id.enterPopStrucSwitch);
        EditText numMatureBox = (EditText) findViewById(R.id.numMaturePlantsBox);
        EditText numImmatureBox = (EditText) findViewById(R.id.numImmaturePlantsBox);
        EditText numSeedlingBox = (EditText) findViewById(R.id.numSeedlingsBox);
        CheckBox currentCensusCheck = (CheckBox) findViewById(R.id.currentCensusCheckbox);

        boolean enterPopStruc = enterPopulationStructure.isChecked();

        if (enterPopStruc) {
            String numMatureString = numMatureBox.getText().toString();
            String numImmatureString = numImmatureBox.getText().toString();
            String numSeedlingsString = numSeedlingBox.getText().toString();

            Number numMature = (TextUtils.isEmpty(numMatureString)) ? 0 : Integer.parseInt(numMatureString);
            Number numImmature = (TextUtils.isEmpty(numImmatureString)) ? 0 : Integer.parseInt(numImmatureString);
            Number numSeedlings = (TextUtils.isEmpty(numSeedlingsString)) ? 0 : Integer.parseInt(numSeedlingsString);
            boolean currentCensus = currentCensusCheck.isChecked();

            KumuApp.getAppStorage().getCurrentForm().addFieldsSection2(
                    enterPopStruc,
                    numMature,
                    numImmature,
                    numSeedlings,
                    currentCensus
            );
        }
        else {
            KumuApp.getAppStorage().getCurrentForm().setReportPopulationStructure(false);
        }
        Intent intent = new Intent(this, SpeciesNameActivity.class);
        startActivity(intent);
        Toast.makeText(FormActivity2.this, "Form saved for later.",
                Toast.LENGTH_SHORT).show();

    }

    public boolean setFields() {
        Switch enterPopulationStructure = (Switch) findViewById(R.id.enterPopStrucSwitch);
        EditText numMatureBox = (EditText) findViewById(R.id.numMaturePlantsBox);
        EditText numImmatureBox = (EditText) findViewById(R.id.numImmaturePlantsBox);
        EditText numSeedlingBox = (EditText) findViewById(R.id.numSeedlingsBox);
        CheckBox currentCensusCheck = (CheckBox) findViewById(R.id.currentCensusCheckbox);

        boolean reportPopStruct = KumuApp.getAppStorage().getCurrentForm().isReportPopulationStructure();
        enterPopulationStructure.setChecked(reportPopStruct);

        if (reportPopStruct) {
            numMatureBox.setText(KumuApp.getAppStorage().getCurrentForm().getNumMaturePlants().toString());
            numImmatureBox.setText(KumuApp.getAppStorage().getCurrentForm().getNumMaturePlants().toString());
            numSeedlingBox.setText(KumuApp.getAppStorage().getCurrentForm().getNumSeedlings().toString());
            currentCensusCheck.setChecked(KumuApp.getAppStorage().getCurrentForm().isMostCurrentCensus());
        }
        return reportPopStruct;
    }
}
