package com.birdsquad.kumu;

import android.graphics.Bitmap;
import android.location.Location;

public class Photo {

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

}
