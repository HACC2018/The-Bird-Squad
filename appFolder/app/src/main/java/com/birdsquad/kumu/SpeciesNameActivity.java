package com.birdsquad.kumu;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import java.util.Calendar;


public class SpeciesNameActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_name);
        super.onCreateDrawer();

        String[] taxaNames = getResources().getStringArray(R.array.autocomplete_taxaname);
        ((AutoCompleteTextView)findViewById(R.id.editText2)).setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taxaNames));
    }

    /**
     * Creates a new form with the entered species name which will be added to the app storage and set as current form.
     * Then starts a new activity to prompt user for plant images.
     * @param view
     */
    public void goToNext(View view) {
        Intent intent = new Intent(this, ImagesActivity.class);
        AutoCompleteTextView editText = (AutoCompleteTextView) findViewById(R.id.editText2);
        String speciesName = editText.getText().toString();
        Form newForm = new Form(speciesName);
        newForm.setDate(Calendar.getInstance().getTime());
        KumuApp.getAppStorage().insertForm(newForm);
        KumuApp.getAppStorage().saveForms();
        startActivity(intent);
    }

}
