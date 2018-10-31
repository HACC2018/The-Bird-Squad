package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class SpeciesNameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_species_name);
    }

    public void goToNext(View view) {
        Intent intent = new Intent(this, ImagesActivity.class);
        startActivity(intent);
    }
}
