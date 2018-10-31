package com.birdsquad.kumu;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class Form {

    private String speciesName;

    private ArrayList<Bitmap> images;

    /**
     * Creates a new Form object with just a speciesName (used for SpeciesNameActivity)
     * Inserts the new form into the Storage object where it will be set as the currentForm
     * Other activities will update the form with other fields.
     * @param speciesName the rare plant species name input by the user on SpeciesNameActivity
     */
    public Form(String speciesName) {
        this.speciesName = speciesName;
        // insert into global Storage variable form list
    }

    /**
     * Sets the form's list of images to the images taken by the user in ImagesActivity
     * @param images
     */
    public void addImages(ArrayList<Bitmap> images) {
        this.images = images;
    }

    public void addAllOtherFields() {
        // We don't know what fields yet
        // Implement this later
    }

}
