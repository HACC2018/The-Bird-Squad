package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FormActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form5);
    }

    public void goToSection6(View view) {
        // check all fields and submit to current form if user selected yes
        Intent intent = new Intent(this, FormActivity6.class);
        startActivity(intent);
    }
}
