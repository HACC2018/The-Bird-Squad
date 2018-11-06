package com.birdsquad.kumu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


public class SpeciesNameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_name);
        super.onCreateDrawer();
    }

    /**
     * Creates a new form with the entered species name which will be added to the app storage and set as current form.
     * Then starts a new activity to prompt user for plant images.
     * @param view
     */
    public void goToNext(View view) {
        Intent intent = new Intent(this, ImagesActivity.class);
        EditText editText = (EditText) findViewById(R.id.speciesNameBox);
        String speciesName = editText.getText().toString();
        if (TextUtils.isEmpty(speciesName)) {
            Toast.makeText(SpeciesNameActivity.this, "You must enter a species name.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Form newForm = new Form(speciesName);
            newForm.setDate(Calendar.getInstance().getTime());
            KumuApp.getAppStorage().insertForm(newForm);
            KumuApp.getAppStorage().saveForms();
            startActivity(intent);
        }

    }

}
