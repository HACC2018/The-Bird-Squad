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

public class FormActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);

        Switch enterIndividual = (Switch) findViewById(R.id.enterIndividualSwitch);
        final LinearLayout innerFields = (LinearLayout) findViewById(R.id.individual_plant);

        enterIndividual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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

    public void goToSection4(View view) {
        Switch enterIndividual = (Switch) findViewById(R.id.enterIndividualSwitch);
        EditText plantNoBox = (EditText) findViewById(R.id.plantNoBox);
        CheckBox plantTaggedCheck = (CheckBox) findViewById(R.id.plantTaggedCheckbox);
        Spinner plantGenderDropdown = (Spinner) findViewById(R.id.plantGenderDropdown);
        EditText plantHeightBox = (EditText) findViewById(R.id.plantHeightBox);
        EditText plantDiameterBox = (EditText) findViewById(R.id.plantDiameterBox);
        Spinner plantAgeDropdown = (Spinner) findViewById(R.id.plantAgeDropdown);
        Spinner plantRepDropdown = (Spinner) findViewById(R.id.plantReproductiveDropdown);
        Spinner plantHealthDropdown = (Spinner) findViewById(R.id.plantHealthDropdown);
        EditText amtImmatureBox = (EditText) findViewById(R.id.amtImmatureFruitBox);
        EditText amtMatureBox = (EditText) findViewById(R.id.amtMatureFruitBox);
        EditText amtCuttingBox  = (EditText) findViewById(R.id.amtCuttingsBox);
        EditText amtAirLayerBox = (EditText) findViewById(R.id.amtAirLayersBox);
        EditText amtFlowersBox = (EditText) findViewById(R.id.amtFlowersBox);

        boolean reportIndividual = enterIndividual.isChecked();

        if (reportIndividual) {

            String plantNo = plantNoBox.getText().toString();
            String plantHeight = plantHeightBox.getText().toString();
            String plantDiam = plantDiameterBox.getText().toString();
            String amtImmature = amtImmatureBox.getText().toString();
            String amtMature = amtMatureBox.getText().toString();
            String amtCutting = amtCuttingBox.getText().toString();
            String amtAirLayer = amtAirLayerBox.getText().toString();
            String amtFlowers = amtFlowersBox.getText().toString();

            boolean anyFieldsEmpty = TextUtils.isEmpty(plantNo) ||
                    TextUtils.isEmpty(plantHeight) ||
                    TextUtils.isEmpty(plantDiam) ||
                    TextUtils.isEmpty(amtImmature) ||
                    TextUtils.isEmpty(amtMature) ||
                    TextUtils.isEmpty(amtCutting) ||
                    TextUtils.isEmpty(amtAirLayer) ||
                    TextUtils.isEmpty(amtFlowers);

            String plantGender = plantGenderDropdown.getSelectedItem().toString();
            String plantAge = plantAgeDropdown.getSelectedItem().toString();
            String plantRep = plantRepDropdown.getSelectedItem().toString();
            String plantHealth = plantHealthDropdown.getSelectedItem().toString();
            boolean plantTagged = plantTaggedCheck.isChecked();

            if (!anyFieldsEmpty) {
                Number plantNoNum = Integer.parseInt(plantNo);
                Number plantHeightNum = Double.parseDouble(plantHeight);
                Number plantDiamNum = Double.parseDouble(plantDiam);
                Number amtImmNum = Integer.parseInt(amtImmature);
                Number amtMatureNum = Integer.parseInt(amtMature);
                Number amtCuttingNum = Integer.parseInt(amtCutting);
                Number amtAirNum = Integer.parseInt(amtAirLayer);
                Number amtFlowersNum = Integer.parseInt(amtFlowers);

                KumuApp.getAppStorage().getCurrentForm().addFieldsSection3(
                        reportIndividual,
                        plantNoNum,
                        plantTagged,
                        plantGender,
                        plantHeightNum,
                        plantDiamNum,
                        plantAge,
                        plantRep,
                        plantHealth,
                        amtImmNum,
                        amtMatureNum,
                        amtCuttingNum,
                        amtAirNum,
                        amtFlowersNum
                );
                Intent intent = new Intent(this, FormActivity4.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(FormActivity3.this, "You must complete all fields.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            KumuApp.getAppStorage().getCurrentForm().setReportIndividualPlant(false);
            Intent intent = new Intent(this, FormActivity4.class);
            startActivity(intent);
        }
    }

    public void finishLaterSection3(View view) {
        Switch enterIndividual = (Switch) findViewById(R.id.enterIndividualSwitch);
        EditText plantNoBox = (EditText) findViewById(R.id.plantNoBox);
        CheckBox plantTaggedCheck = (CheckBox) findViewById(R.id.plantTaggedCheckbox);
        Spinner plantGenderDropdown = (Spinner) findViewById(R.id.plantGenderDropdown);
        EditText plantHeightBox = (EditText) findViewById(R.id.plantHeightBox);
        EditText plantDiameterBox = (EditText) findViewById(R.id.plantDiameterBox);
        Spinner plantAgeDropdown = (Spinner) findViewById(R.id.plantAgeDropdown);
        Spinner plantRepDropdown = (Spinner) findViewById(R.id.plantReproductiveDropdown);
        Spinner plantHealthDropdown = (Spinner) findViewById(R.id.plantHealthDropdown);
        EditText amtImmatureBox = (EditText) findViewById(R.id.amtImmatureFruitBox);
        EditText amtMatureBox = (EditText) findViewById(R.id.amtMatureFruitBox);
        EditText amtCuttingBox  = (EditText) findViewById(R.id.amtCuttingsBox);
        EditText amtAirLayerBox = (EditText) findViewById(R.id.amtAirLayersBox);
        EditText amtFlowersBox = (EditText) findViewById(R.id.amtFlowersBox);

        boolean reportIndividual = enterIndividual.isChecked();

        if (reportIndividual) {
            String plantNo = plantNoBox.getText().toString();
            String plantHeight = plantHeightBox.getText().toString();
            String plantDiam = plantDiameterBox.getText().toString();
            String amtImmature = amtImmatureBox.getText().toString();
            String amtMature = amtMatureBox.getText().toString();
            String amtCutting = amtCuttingBox.getText().toString();
            String amtAirLayer = amtAirLayerBox.getText().toString();
            String amtFlowers = amtFlowersBox.getText().toString();

            Number plantNoNum = (TextUtils.isEmpty(plantNo)) ? 0 : Integer.parseInt(plantNo);
            Number plantHeightNum = (TextUtils.isEmpty(plantHeight)) ? 0 : Double.parseDouble(plantHeight);
            Number plantDiamNum = (TextUtils.isEmpty(plantDiam)) ? 0 : Double.parseDouble(plantDiam);
            Number amtImmNum = (TextUtils.isEmpty(amtImmature)) ? 0 : Integer.parseInt(amtImmature);
            Number amtMatureNum = (TextUtils.isEmpty(amtMature)) ? 0 : Integer.parseInt(amtMature);
            Number amtCuttingNum = (TextUtils.isEmpty(amtCutting)) ? 0 : Integer.parseInt(amtCutting);
            Number amtAirNum = (TextUtils.isEmpty(amtAirLayer)) ? 0 : Integer.parseInt(amtAirLayer);
            Number amtFlowersNum = (TextUtils.isEmpty(amtFlowers)) ? 0 : Integer.parseInt(amtFlowers);

            String plantGender = plantGenderDropdown.getSelectedItem().toString();
            String plantAge = plantAgeDropdown.getSelectedItem().toString();
            String plantRep = plantRepDropdown.getSelectedItem().toString();
            String plantHealth = plantHealthDropdown.getSelectedItem().toString();
            boolean plantTagged = plantTaggedCheck.isChecked();

            KumuApp.getAppStorage().getCurrentForm().addFieldsSection3(
                    reportIndividual,
                    plantNoNum,
                    plantTagged,
                    plantGender,
                    plantHeightNum,
                    plantDiamNum,
                    plantAge,
                    plantRep,
                    plantHealth,
                    amtImmNum,
                    amtMatureNum,
                    amtCuttingNum,
                    amtAirNum,
                    amtFlowersNum
            );

        }
        else {
            KumuApp.getAppStorage().getCurrentForm().setReportIndividualPlant(false);
        }
        Intent intent = new Intent(this, SpeciesNameActivity.class);
        startActivity(intent);
        Toast.makeText(FormActivity3.this, "Form saved for later.",
                Toast.LENGTH_SHORT).show();

    }

}
