package com.birdsquad.kumu;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;

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
            this.forms = new Gson().fromJson(jsonForms, ArrayList.class);
        }
    }

    /**
     * Adds a new offline form to offline storage
     * @param form
     */
    public void insertForm(Form form) {
        forms.add(form);
        currentForm = form; // The most recent inserted form will always be the current form
    }

    public ArrayList<Form> getForms() {
        return forms;
    }

    public Form getCurrentForm() {
        return currentForm;
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

}
