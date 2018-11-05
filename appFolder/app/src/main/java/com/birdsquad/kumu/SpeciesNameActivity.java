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
import android.widget.EditText;


public class SpeciesNameActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private SpeciesNameActivity context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_name);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        int id = menuItem.getItemId();
                        switch (getResources().getResourceEntryName(id)) {
                            case "nav_incomplete" :
                                Intent incompleteFormIntent = new Intent(context, IncompleteFormsActivity.class);
                                startActivity(incompleteFormIntent);
                                break;
                            case "nav_historical" :
                                Intent historicalFormIntent = new Intent(context, HistoricalFormsActivity.class);
                                startActivity(historicalFormIntent);
                                break;
                            case "nav_map" :
                                /*Intent mapIntent = new Intent(context, MapActivity.class);
                                startActivity(mapIntent);*/
                                break;
                            default:
                                break;
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });

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

    public void openDrawer(View view) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
}
