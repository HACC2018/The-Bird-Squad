package com.birdsquad.kumu;

import android.graphics.Bitmap;
import android.location.Location;

import java.io.File;

public class Photo {

    private String absPath;

    private Bitmap image;

    private Location location;

    public Photo(Bitmap image) {
        this.image = image;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Bitmap getPhoto() {
        return this.image;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getFileAbsPath(){return this.absPath;}

    public void setFileAbsPath(String f){
        this.absPath = f;
    }
}
