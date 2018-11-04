package com.birdsquad.kumu;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

public class Form {

    private String speciesName;
    private boolean isFinished = false;
    private Date dateCreated;
    // Section 1
    private String wildOrOutplanted;
    private String taxonName;
    private ArrayList<Bitmap> images;
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
    private String plantAge;
    private String plantReproductive;
    private String plantVigor;
    private Number amountImmatureFruit;
    private Number amountMatureFruit;
    private Number amountCuttings;
    private Number amountAirLayers;
    private Number amountFlowers;
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

    /**
     * Add section 1 fields to the form
     * Missing: location and elevation
     * @param wildOrOutplanted
     * @param taxonName
     * @param observerName
     * @param organizationName
     * @param island
     * @param areaCode
     * @param refSite
     * @param locationNotes
     */
    public void addFieldsSection1(
            String wildOrOutplanted,
            String taxonName,
            String observerName,
            String organizationName,
            String island,
            String areaCode,
            String refSite,
            String locationNotes
    ) {
        this.wildOrOutplanted = wildOrOutplanted;
        this.taxonName = taxonName;
        this.observerName = observerName;
        this.organizationName = organizationName;
        this.island = island;
        this.areaCode = areaCode;
        this.refSite = refSite;
        this.locationNotes =  locationNotes;
    }

    /**
     * Add section 2 fields to the form.
     * @param numMaturePlants
     * @param numImmaturePlants
     * @param numSeedlings
     * @param mostCurrentCensus
     */
    public void addFieldsSection2(
            Number numMaturePlants,
            Number numImmaturePlants,
            Number numSeedlings,
            boolean mostCurrentCensus
    ) {
        this.numMaturePlants = numMaturePlants;
        this.numImmaturePlants = numImmaturePlants;
        this.numSeedlings = numSeedlings;
        this.mostCurrentCensus = mostCurrentCensus;
    }

    /**
     * Add Section 3 fields of the form
     * @param plantNumber
     * @param plantTagged
     * @param plantGender
     * @param plantHeight
     * @param plantBaseDiameter
     * @param plantAge
     * @param plantReproductive
     * @param plantVigor
     * @param amountImmatureFruit
     * @param amountMatureFruit
     * @param amountCuttings
     * @param amountAirLayers
     * @param amountFlowers
     */
    public void addFieldsSection3(
            Number plantNumber,
            boolean plantTagged,
            String plantGender,
            Number plantHeight,
            Number plantBaseDiameter,
            String plantAge,
            String plantReproductive,
            String plantVigor,
            Number amountImmatureFruit,
            Number amountMatureFruit,
            Number amountCuttings,
            Number amountAirLayers,
            Number amountFlowers
    ) {
        this.plantNumber = plantNumber;
        this.plantTagged = plantTagged;
        this.plantGender = plantGender;
        this.plantHeight = plantHeight;
        this.plantBaseDiameter = plantBaseDiameter;
        this.plantAge = plantAge;
        this.plantReproductive = plantReproductive;
        this.plantVigor = plantVigor;
        this.amountImmatureFruit = amountImmatureFruit;
        this.amountMatureFruit = amountMatureFruit;
        this.amountCuttings = amountCuttings;
        this.amountAirLayers = amountAirLayers;
        this.amountFlowers = amountFlowers;
    }

}
