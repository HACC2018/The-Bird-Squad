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

    // Section 2: Population Structure
    public boolean reportPopulationStructure;
    public Number numMaturePlants;
    public Number numImmaturePlants;
    public Number numSeedlings;
    public boolean mostCurrentCensus;

    // Section 3: Individual Plant data
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

    // Section 4: Population Data
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

    // Section 5: Habitat
    public String[] observatoryClosure;
    public String[] observatoryHeight;
    public String[] understoryClosure;
    public String[] soilDrainage;
    public String[] topography;
    public String[] aspect;
    public String assocObsSpecies;
    public String assocUndSpecies;
    public String[] substrate;

    // Section 6: Threats
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

    /**
     * Add section 1 fields to the form
     *
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
     * Add Section 2 fields to the form.
     * Population Structure
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
     * Individual Plant data
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

    /**
     * Add Section 4 fields of the form
     * Population Data
     */
    public void addFieldsSection4(
            boolean reportPopulationData,
            // Phenology
            Number percentVegative,
            Number percentBuds,
            Number percentFlower,
            Number percentImmatureFruit,
            Number percentMatureFruit,
            // Health condition
            Number percentHealthy,
            Number percentModerate,
            Number percentPoor,
            Number percentDead,
            // Light level
            Number percentFullSun,
            Number percentPartialSun,
            Number percentPartialShade,
            Number percentDeepShade    ) {

        this.reportPopulationData = reportPopulationData;
        this.percentVegative = percentVegative;
        this.percentBuds = percentBuds;
        this.percentFlower = percentFlower;
        this.percentImmatureFruit = percentImmatureFruit;
        this.percentMatureFruit = percentMatureFruit;
        this.percentHealthy = percentHealthy;
        this.percentModerate = percentModerate;
        this.percentPoor = percentPoor;
        this.percentDead = percentDead;
        this.percentFullSun = percentFullSun;
        this.percentPartialSun = percentPartialSun;
        this.percentPartialShade = percentPartialShade;
        this.percentDeepShade = percentDeepShade;

    }


    /**
     * Add Section 5 fields of the form
     * Habitat Data
     */
    public void addFieldsSection5(
            String[] observatoryClosure,
            String[] observatoryHeight,
            String[] understoryClosure,
            String[] soilDrainage,
            String[] topography,
            String[] aspect,
            String assocObsSpecies,
            String assocUndSpecies,
            String[] substrate
    ) {

        this.observatoryClosure = observatoryClosure;
        this.observatoryHeight = observatoryHeight;
        this.understoryClosure = understoryClosure;
        this.soilDrainage = soilDrainage;
        this.topography = topography;
        this.aspect = aspect;
        this.assocObsSpecies = assocObsSpecies;
        this.assocUndSpecies = assocUndSpecies;
        this.substrate = substrate;

    }


    /**
     * Add Section 6 fields of the form
     * Threats Data
     */
    public void addFieldsSection6(
            boolean reportThreats,
            String threats,
            String threatManagementNotes
    ) {

        this.reportThreats = reportThreats;
        this.threats = threats;
        this.threatManagementNotes = threatManagementNotes;

    }

}
