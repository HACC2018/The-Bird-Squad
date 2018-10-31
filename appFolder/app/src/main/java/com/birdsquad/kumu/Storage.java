package com.birdsquad.kumu;

import java.util.ArrayList;

public class Storage {

    private ArrayList<Form> forms;

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
        return this.forms;
    }

    public Form getCurrentForm() {
        return this.currentForm;
    }

    /**
     * Syncs all offline forms to the server.
     */
    public void syncForms() {
        // makes a json serializable object of the forms
        // delete all offline forms here
        // returns this object
    }

}
