package com.birdsquad.kumu;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pl.droidsonroids.gif.GifImageView;

import static com.birdsquad.kumu.KumuApp.getAppStorage;

public class SplashScreenActivity extends Activity {

    private Intent nextActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.splashLayout);
        layout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(nextActivity != null){
                    startActivity(nextActivity);
                }
            }
        });
    }

    private void blink(){
        TextView tapToContinue = (TextView)findViewById(R.id.splashTapToContinue);
        ObjectAnimator anim = ObjectAnimator.ofFloat(tapToContinue, "alpha", 0F, 1F);
        anim.setDuration(900);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }

    @Override
    protected void onResume(){
        super.onResume();

        //Sync forms
        KumuApp.getAppStorage().syncForms(this);
        GifImageView logo = (GifImageView)findViewById(R.id.kumulogo);
        logo.setImageResource(R.drawable.kumu_animated_2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(KumuApp.getInstance());
        String loginCompleted = preferences.getString("login", null);

        //If user logged in before, skip login activity
        if(loginCompleted != null){
            nextActivity = new Intent(this, SpeciesNameActivity.class);
        } else {
            //User not logged in before, go to login form
            nextActivity = new Intent(this, MainActivity.class);
        }
        blink();
    }

}
