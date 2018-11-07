package com.birdsquad.kumu;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

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
        mMap = googleMap;
        ArrayList<Form> myForms = KumuApp.getAppStorage().getForms(); // will change to completed forms later
        for (Form form : myForms) {
            String formName = form.taxonName;
            Date formDate = form.dateCreated;
            if (form.images != null) {
                Location formLocation = form.images.get(0).getLocation();
                if (formLocation != null) {
                    Log.d("MappingForms", "The location was found as " + formLocation.getLatitude() + ", " + formLocation.getLongitude());
                    LatLng coordinates = new LatLng(formLocation.getLatitude(), formLocation.getLongitude());
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
