package com.birdsquad.kumu;

import android.graphics.Bitmap;
import android.location.Location;

import java.io.File;

public class Photo {

    public String absPath;

    public Bitmap image;

    public Number latitude;
    public Number longitude;

    public Photo(Bitmap image) {
        this.image = image;
    }

    public void setLocation(Location location) {
        this.latitude = location.getLatitude(); this.longitude = location.getLongitude();
    }

    public Bitmap getPhoto() {
        return this.image;
    }

    public String getFileAbsPath(){return this.absPath;}

    public void setFileAbsPath(String f){
        this.absPath = f;
    }
}
