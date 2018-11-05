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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            connected = true;
        else
            connected = false;

        //If we are connected, get JSON payload and send to server
        if(connected){
            if(KumuApp.getAppStorage() == null){
                Log.d("StorageDebug", "Storage is null");
            }

            final ArrayList<Form> forms = KumuApp.getAppStorage().getFormsToSync();

            if(forms.size() > 0){
                Log.d("PostToServer", "There exists unsychronized forms");
                final String formsJson = new Gson().toJson(forms);

                RequestQueue queue = Volley.newRequestQueue(this);
                String url = KumuApp.URLToServerPostForms;

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                Log.d("PostToServer", "SUCCESS: " + response);

                                try{
                                    int ind = Integer.parseInt(response);
                                    forms.get(ind).isSynced = true;
                                } catch(NumberFormatException e){
                                    Log.d("PostToServer", "FAILED TO PARSE RESPONSE! Returned response should be a number corresponding to the index of the json array that was successful");
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("PostToServer", "Error posting json to server: " + error.getMessage());
                    }
                }){
                    @Override
                    protected Map<String, String> getParams(){
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("json", formsJson);
                        return params;

                    }
                };
                // Add the request to the RequestQueue.
                queue.add(stringRequest);

            } else {
                Log.d("PostToServer", "No forms to sync");
            }
        }
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
