package com.birdsquad.kumu;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Storage {

    private ArrayList<Form> forms;

    public ArrayList<Form> unfinishedForms;

    public ArrayList<Form> historicalForms;

    private Form currentForm;

    public Storage() {
        this.forms = new ArrayList<Form>();
        this.currentForm = null;
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
    public String syncForms() {
        // makes a json serializable object of the forms
        // delete all offline forms here
        // returns this object
        Gson gson = new Gson();
        String json = gson.toJson(forms);
        return json;
    }

}
