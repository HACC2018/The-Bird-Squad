package com.birdsquad.kumu;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Storage {

    private ArrayList<Form> forms;

    private Form currentForm;

    public Storage() {
        this.forms = new ArrayList<Form>();
        this.currentForm = null;
        //Load forms from saved json
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(KumuApp.getInstance());
        String jsonForms = preferences.getString("json", null);

        //If json exists, load it
        if(jsonForms != null){
            Log.d("StorageDebug", "Not null, it is: " + jsonForms);
            this.forms = new Gson().fromJson(jsonForms, new TypeToken<ArrayList<Form>>(){}.getType());
            //Let's see if images exist on any of these
            for(Form f : forms){
                if(f.images != null){
                    Log.d("StorageDebug", "Not null image: " + f.images.size());
                }
            }
        }
    }

    /**
     * Adds a new offline form to offline storage
     * @param form
     */
    public void insertForm(Form form) {
        forms.add(form);
        currentForm = form; // The most recent inserted form will always be the current form
        saveForms();
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

    public Form getCurrentForm() {
        return currentForm;
    }

    public void setCurrentForm(Form f){
        currentForm = f;
        Log.d("StorageDebug", "Set current form " + currentForm.taxonName);
    }

    /**
     *  Makes a json of forms array list
     */
    public ArrayList<Form> getFormsToSync() {
        // makes a json serializable object of the forms
        // delete all offline forms here
        // returns this object

        ArrayList<Form> unsyncedCompletedForms = new ArrayList<Form>();
        for(Form f : forms){
            if(f.isFinished && !f.isSynced){
                unsyncedCompletedForms.add(f);
            }
        }
        return unsyncedCompletedForms;
    }

    public ArrayList<Form> getHistoryCompletedForms(){
        ArrayList<Form> comp = new ArrayList<Form>();
        for(Form f : forms){
            if(f.isFinished){
                comp.add(f);
            }
        }
        return comp;
    }

    public ArrayList<Form> getUnfinishedForms(){
        ArrayList<Form> unfin = new ArrayList<Form>();
        Log.d("StorageDebug", forms.size() + " there are this many forms");
        for(Form f : forms){
            if(!f.isFinished) {
                unfin.add(f);
            }
        }
        return unfin;
    }

    public ArrayList<Form> getCompletedForms(){
        ArrayList<Form> comp = new ArrayList<Form>();
        for(Form f : forms){
            if(f.isFinished){
                comp.add(f);
            }
        }
        return comp;
    }

    public void saveForms(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(KumuApp.getInstance());
        SharedPreferences.Editor ed = preferences.edit();
        ed.putString("json", new Gson().toJson(forms));
        ed.commit();
    }

    public void syncForms(final Context context){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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
            //Wait for init
            while(KumuApp.getAppStorage() == null){}

            final ArrayList<Form> forms = KumuApp.getAppStorage().getFormsToSync();

            if(forms.size() > 0){
                Log.d("PostToServer", "There exists unsychronized forms");

                for(int i = 0; i<forms.size(); i++){
                    final Form f = forms.get(i);
                    final int tempI = i;
                    final ArrayList<Photo> imagesCache = f.images;
                    f.images = null;
                    final String formsJson = new Gson().toJson(f);
                    Log.d("PostToServer", formsJson);
                    RequestQueue queue = Volley.newRequestQueue(context);
                    String url = KumuApp.URLToServerPostForms;

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                                    Log.d("PostToServer", "SUCCESS: " + response);

                                    try{
                                        int formID = Integer.parseInt(response);
                                        for(int i = 0; i<imagesCache.size(); i++){
                                            new UploadFile(imagesCache.get(i), imagesCache.get(i).latitude, imagesCache.get(i).longitude, formID, context.getApplicationContext()).execute();
                                        }

                                        f.isSynced = true;
                                        f.images = imagesCache;
                                        saveForms();
                                        Log.d("PostToServer", "Synced");
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
                    stringRequest.setShouldCache(false);
                    queue.add(stringRequest);
                }

            } else {
                Log.d("PostToServer", "No forms to sync");
            }
        }
    }

}
