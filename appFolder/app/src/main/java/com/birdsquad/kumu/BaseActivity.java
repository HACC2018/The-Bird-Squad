package com.birdsquad.kumu;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;
    protected BaseActivity context = this;
    protected ActionBarDrawerToggle mToggle;

    protected void onCreateDrawer() {

        drawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        int id = menuItem.getItemId();
                        switch (getResources().getResourceEntryName(id)) {
                            case "nav_current" :
                                Intent currentFormIntent = new Intent(context, SpeciesNameActivity.class);
                                startActivity(currentFormIntent);
                                break;
                            case "nav_incomplete" :
                                Intent incompleteFormIntent = new Intent(context, IncompleteFormsActivity.class);
                                startActivity(incompleteFormIntent);
                                break;
                            case "nav_historical" :
                                Intent historicalFormIntent = new Intent(context, HistoricalFormsActivity.class);
                                startActivity(historicalFormIntent);
                                break;
                            case "nav_map" :
                                Intent mapIntent = new Intent(context, MapActivity.class);
                                startActivity(mapIntent);
                                break;
                            default:
                                break;
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
