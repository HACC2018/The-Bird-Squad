package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class SpeciesNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_name);
    }

    /**
     * Creates a new form with the entered species name which will be added to the app storage and set as current form.
     * Then starts a new activity to prompt user for plant images.
     * @param view
     */
    public void goToNext(View view) {
        Intent intent = new Intent(this, ImagesActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText2);
        String speciesName = editText.getText().toString();
        Form newForm = new Form(speciesName);
        KumuApp.getAppStorage().insertForm(newForm);
        startActivity(intent);
    }
}
