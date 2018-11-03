package com.birdsquad.kumu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FormActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form2);
    }

    public void goToSection3(View view) {
        // check all fields and submit to current form if the user has selected yes
        Intent intent = new Intent(this, FormActivity3.class);
        startActivity(intent);
    }
}
