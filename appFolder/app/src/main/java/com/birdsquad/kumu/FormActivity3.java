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

        boolean setVisible = setFieldsSection3();
        if (setVisible) {
            innerFields.setVisibility(View.VISIBLE);
        }
        else {
            innerFields.setVisibility(View.GONE);
        }

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

    /**
     * Submit all section 3 data to current form, go on to section 4
     * @param view
     */
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
                KumuApp.getAppStorage().saveForms();
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
            KumuApp.getAppStorage().saveForms();
            Intent intent = new Intent(this, FormActivity4.class);
            startActivity(intent);
        }
    }

    /**
     * Submit existing section 3 data to current form to be finished later
     * @param view
     */
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
        KumuApp.getAppStorage().saveForms();
        Intent intent = new Intent(this, SpeciesNameActivity.class);
        startActivity(intent);
        Toast.makeText(FormActivity3.this, "Form saved for later.",
                Toast.LENGTH_SHORT).show();

    }

    /**
     * Initialize all fields
     * @return
     */
    public boolean setFieldsSection3() {
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

        boolean reportIndividualPlant = KumuApp.getAppStorage().getCurrentForm().reportIndividualPlant;
        enterIndividual.setChecked(reportIndividualPlant);

        if (reportIndividualPlant) {
            plantNoBox.setText(KumuApp.getAppStorage().getCurrentForm().plantNumber.toString());
            plantHeightBox.setText(KumuApp.getAppStorage().getCurrentForm().plantHeight.toString());
            plantDiameterBox.setText(KumuApp.getAppStorage().getCurrentForm().plantBaseDiameter.toString());
            amtImmatureBox.setText(KumuApp.getAppStorage().getCurrentForm().amountImmatureFruit.toString());
            amtMatureBox.setText(KumuApp.getAppStorage().getCurrentForm().amountMatureFruit.toString());
            amtCuttingBox.setText(KumuApp.getAppStorage().getCurrentForm().amountCuttings.toString());
            amtAirLayerBox.setText(KumuApp.getAppStorage().getCurrentForm().amountAirLayers.toString());
            amtFlowersBox.setText(KumuApp.getAppStorage().getCurrentForm().amountFlowers.toString());
            plantTaggedCheck.setChecked(KumuApp.getAppStorage().getCurrentForm().plantTagged);

            int plantGenderIndex;
            if (KumuApp.getAppStorage().getCurrentForm().plantGender != null) {
                switch (KumuApp.getAppStorage().getCurrentForm().plantGender) {
                    case "Unknown":
                        plantGenderIndex = 0;
                        break;
                    case "Male":
                        plantGenderIndex = 1;
                        break;
                    case "Female":
                        plantGenderIndex = 2;
                        break;
                    case "Both Male and Female":
                        plantGenderIndex = 3;
                        break;
                    case "Perfect Flowers (Hermaphrodite)":
                        plantGenderIndex = 4;
                        break;
                    case "N/A":
                        plantGenderIndex = 5;
                        break;
                    case "Male?":
                        plantGenderIndex = 6;
                        break;
                    case "Female?":
                        plantGenderIndex = 7;
                        break;
                    default:
                        plantGenderIndex = 0;
                        break;
                }
            }
            else {
                plantGenderIndex = 0;
            }

            plantGenderDropdown.setSelection(plantGenderIndex);

            int plantAgeIndex;
            if (KumuApp.getAppStorage().getCurrentForm().plantAge != null) {
                switch (KumuApp.getAppStorage().getCurrentForm().plantAge) {
                    case "Mature":
                        plantAgeIndex = 0;
                        break;
                    case "Immature":
                        plantAgeIndex = 1;
                        break;
                    case "Seedling (With Cotyledons)":
                        plantAgeIndex = 2;
                        break;
                    default:
                        plantAgeIndex = 0;
                        break;
                }
            }
            else {
                plantAgeIndex = 0;
            }

            plantAgeDropdown.setSelection(plantAgeIndex);

            int plantRepIndex;
            if (KumuApp.getAppStorage().getCurrentForm().plantReproductive != null) {
                switch (KumuApp.getAppStorage().getCurrentForm().plantReproductive) {
                    case "Vegetative (No flowers, buds, or fruit)":
                        plantRepIndex = 0;
                        break;
                    case "Flower Buds":
                        plantRepIndex = 1;
                        break;
                    case "Immature Fruit":
                        plantRepIndex = 2;
                        break;
                    case "Mature Fruit":
                        plantRepIndex = 3;
                        break;
                    case "Dormant":
                        plantRepIndex = 4;
                        break;
                    default:
                        plantRepIndex = 0;
                        break;
                }
            }
            else {
                plantRepIndex = 0;
            }

            plantRepDropdown.setSelection(plantRepIndex);

            int plantHealthIndex;
            if (KumuApp.getAppStorage().getCurrentForm().plantVigor != null) {
                switch (KumuApp.getAppStorage().getCurrentForm().plantVigor) {
                    case "Healthy":
                        plantHealthIndex = 0;
                        break;
                    case "Moderate":
                        plantHealthIndex = 1;
                        break;
                    case "Poor":
                        plantHealthIndex = 2;
                        break;
                    case "Dead":
                        plantHealthIndex = 3;
                        break;
                    default:
                        plantHealthIndex = 0;
                        break;
                }
            }
            else {
                plantHealthIndex = 0;
            }

            plantHealthDropdown.setSelection(plantHealthIndex);

        }

        return reportIndividualPlant;

    }

}
