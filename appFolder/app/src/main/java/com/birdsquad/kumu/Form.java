package com.birdsquad.kumu;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

public class Form {

    private String speciesName;
    // Section 1
    private String wildOrOutplanted;
    private String taxonName;
    private ArrayList<Bitmap> images;
    private Date observationDate;
    private String observerName;
    private String organizationName;
    private String island;
    private String areaCode;
    private String refSite;
    private String locationNotes;
    // needs location gps point
    // needs elevation
    // Section 2
    private boolean reportPopulationStructure;
    private Number numMaturePlants;
    private Number numImmaturePlants;
    private Number numSeedlings;
    private boolean mostCurrentCensus;
    // Section 3
    private boolean reportIndividualPlant;
    private Number plantNumber;
    private boolean plantTagged;
    private String plantGender;
    private Number plantHeight;
    private Number plantBaseDiameter;
    private Number plantAge;
    private String plantReproductive;
    private String plantVigor;
    private Number amountImmatureFruit;
    private Number amountMatureFruit;
    private Number amountCuttings;
    private Number amountAirLayers;
    private Number amonuntFlowers;
    // Section 4
    private boolean reportPopulationData;
    // Phenology
    private Number percentVegative;
    private Number percentBuds;
    private Number percentFlower;
    private Number percentImmatureFruit;
    private Number percentMatureFruit;
    // Health condition
    private Number percentHealthy;
    private Number percentModerate;
    private Number percentPoor;
    private Number percentDead;
    // Light level
    private Number percentFullSun;
    private Number percentPartialSun;
    private Number percentPartialShade;
    private Number percentDeepShade;
    // section 5
    private String[] observatoryClosure;
    private String[] observatoryHeight;
    private String[] understoryClosure;
    private String[] soilDrainage;
    private String[] topography;
    private String[] aspect;
    private String assocObsSpecies;
    private String assocUndSpecies;
    private String[] substrate;
    // section 6
    private boolean reportThreats;
    private String threats;
    private String threatManagementNotes;


    /**
     * Creates a new Form object with just a speciesName (used for SpeciesNameActivity)
     * Other activities will update the form with other fields.
     * @param speciesName the rare plant species name input by the user on SpeciesNameActivity
     */
    public Form(String speciesName) {
        this.speciesName = speciesName;
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
