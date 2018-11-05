package com.birdsquad.kumu;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;

public class Form {

    public String speciesName;
    public boolean isFinished = false;
    public Date dateCreated;
    // Section 1
    public String wildOrOutplanted;
    public String taxonName;
    public ArrayList<Photo> images;
    public String observerName;
    public String organizationName;
    public String island;
    public String areaCode;
    public String refSite;
    public String locationNotes;
    // needs location gps point
    // needs elevation
    // Section 2
    public boolean reportPopulationStructure;
    public Number numMaturePlants;
    public Number numImmaturePlants;
    public Number numSeedlings;
    public boolean mostCurrentCensus;
    // Section 3
    public boolean reportIndividualPlant;
    public Number plantNumber;
    public boolean plantTagged;
    public String plantGender;
    public Number plantHeight;
    public Number plantBaseDiameter;
    public String plantAge;
    public String plantReproductive;
    public String plantVigor;
    public Number amountImmatureFruit;
    public Number amountMatureFruit;
    public Number amountCuttings;
    public Number amountAirLayers;
    public Number amountFlowers;
    // Section 4
    public boolean reportPopulationData;
    // Phenology
    public Number percentVegative;
    public Number percentBuds;
    public Number percentFlower;
    public Number percentImmatureFruit;
    public Number percentMatureFruit;
    // Health condition
    public Number percentHealthy;
    public Number percentModerate;
    public Number percentPoor;
    public Number percentDead;
    // Light level
    public Number percentFullSun;
    public Number percentPartialSun;
    public Number percentPartialShade;
    public Number percentDeepShade;
    // section 5
    public String[] observatoryClosure;
    public String[] observatoryHeight;
    public String[] understoryClosure;
    public String[] soilDrainage;
    public String[] topography;
    public String[] aspect;
    public String assocObsSpecies;
    public String assocUndSpecies;
    public String[] substrate;
    // section 6
    public boolean reportThreats;
    public String threats;
    public String threatManagementNotes;

    public void setReportPopulationStructure(boolean reportPopulationStructure) {
        this.reportPopulationStructure = reportPopulationStructure;
    }

    public void setReportIndividualPlant(boolean reportIndividualPlant) {
        this.reportIndividualPlant = reportIndividualPlant;
    }

    public void setReportPopulationData(boolean reportPopulationData) {
        this.reportPopulationData = reportPopulationData;
    }


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
    public void addImages(ArrayList<Photo> images) {
        this.images = images;
    }

    public void setDate(Date date) {
        this.dateCreated = date;
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
            boolean reportPopulationStructure,
            Number numMaturePlants,
            Number numImmaturePlants,
            Number numSeedlings,
            boolean mostCurrentCensus
    ) {
        this.reportPopulationStructure = reportPopulationStructure;
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
            boolean reportIndividualPlant,
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
        this.reportIndividualPlant = reportIndividualPlant;
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
