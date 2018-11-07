package com.birdsquad.kumu;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.Manifest;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LatLngBounds HAWAII = new LatLngBounds(
            new LatLng(19, -160), new LatLng(23, -154));

    private boolean locationEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // For testing

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    locationEnabled = true;

                }
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("GoogleMapActivity", "The map was found to be ready");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission
                    (this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    &&
                    ActivityCompat.checkSelfPermission
                            (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 1);
                //return;
            }
            else {
                locationEnabled = true;
            }
        }
        else {
            locationEnabled = true;
        }

        if (locationEnabled) {
            mMap = googleMap;
            ArrayList<Form> myForms = KumuApp.getAppStorage().getForms(); // will change to completed forms later
            for (Form form : myForms) {
                String formName = form.taxonName;
                Date formDate = form.dateCreated;
                if (form.images != null) {
                    if (form.images.get(0).latitude != null && form.images.get(0).longitude != null) {
                        Log.d("MappingForms", "The location was found as " + form.images.get(0).latitude + ", " + form.images.get(0).longitude);
                        LatLng coordinates = new LatLng(form.images.get(0).latitude.doubleValue(), form.images.get(0).longitude.doubleValue());
                        mMap.addMarker(new MarkerOptions()
                                .position(coordinates)
                                .title(formName + " " + formDate.toString())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.kumu_map_icon)));
                    }
                }
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(HAWAII, 0));

        }

    }
}
