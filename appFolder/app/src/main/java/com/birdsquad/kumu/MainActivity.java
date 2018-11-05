package com.birdsquad.kumu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void login(View view) {
        EditText user = (EditText) findViewById(R.id.editText);
        EditText userpass = (EditText) findViewById(R.id.editText3);
        String errorString = "";
        if(user.getText().toString().trim().equals(""))
            errorString += "Username is required to login";
        if(userpass.getText().toString().equals("")) {
            if(!errorString.equals("")){
                errorString += " || ";
            }
            errorString += "Password is required to login";
        }
        if(errorString.equals("")){
            //save login
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(KumuApp.getInstance());
            SharedPreferences.Editor ed = preferences.edit();
            ed.putString("login", user.getText().toString() + " " + userpass.getText().toString());
            ed.commit();

            Intent intent = new Intent(this, SpeciesNameActivity.class);
            startActivity(intent);
        } else {
            //Display error
            Snackbar errorMessage = Snackbar.make(findViewById(R.id.mainLoginConstraintLayout), errorString, Snackbar.LENGTH_LONG);
            errorMessage.show();
        }
    }

}
