package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FormActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form4);
    }

    public void goToSection5(View view) {
        // check all fields and submit to current form
        Intent intent = new Intent(this, FormActivity5.class);
        startActivity(intent);
    }
}
