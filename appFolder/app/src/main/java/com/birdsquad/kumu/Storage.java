package com.birdsquad.kumu;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
            this.forms = new Gson().fromJson(jsonForms, new TypeToken<ArrayList<Form>>(){}.getType());
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
        Log.d("StorageDebug", "Set current form " + currentForm.speciesName);
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

}
