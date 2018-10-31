package com.birdsquad.kumu;

import java.util.List;

public class Storage {

    private List<Form> forms;

    private Form currentForm;

    /**
     * Adds a new offline form to offline storage
     * @param form
     */
    public void insertForm(Form form) {
        forms.add(form);
        currentForm = form; // The most recent inserted form will always be the current form
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
