package com.birdsquad.kumu;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Date;

public class ImagesActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private FusedLocationProviderClient mFusedLocationClient;

    private ArrayList<Photo> images;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private static boolean locationEnabled;

    private static Location thisLocation;

    public boolean checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    locationEnabled = true;

                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        this.images = new ArrayList<Photo>();
        // set first image to + icon
        Bitmap addIcon = BitmapFactory.decodeResource(getResources(), R.drawable.add_photo_icon);
        Photo addIconPhoto = new Photo(addIcon);
        images.add(addIconPhoto);

        // For location services
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Check if the person has enabled location permissions
        locationEnabled = checkLocationPermission();

        final GridView gridview = (GridView) findViewById(R.id.imageGridView);

        gridview.setAdapter(new PhotoAdapter(this, R.layout.grid_item_layout, images));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if (position == images.size() - 1) {
                    if (images.size() >= 11) {
                        Toast.makeText(ImagesActivity.this, "You already have 10 images.",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        dispatchTakePictureIntent();
                    }
                }

            }
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            final Photo newPhoto = new Photo(imageBitmap);

            if (locationEnabled) {
                mFusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                Log.d("MapDemoActivity", "No error");
                                // Got last known location. In some rare situations this can be null.
                                if (location != null) {
                                    Log.d("MapDemoActivity", location.getLatitude() + " " + location.getLongitude());
                                    thisLocation = location;
                                    newPhoto.setLocation(thisLocation);
                                    
                                    images.add(0, newPhoto);
                                    final GridView gridview = (GridView) findViewById(R.id.imageGridView);
                                    ((ArrayAdapter<Photo>)gridview.getAdapter()).notifyDataSetChanged();
                                } else {
                                    Log.d("MapDemoActivity", "Location turned null");
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("MapDemoActivity", "Error trying to get last GPS location");
                                e.printStackTrace();
                            }
                        });
            } else {
                Snackbar errorMessage = Snackbar.make(findViewById(R.id.mainLoginConstraintLayout), "Please enable location services", Snackbar.LENGTH_LONG);
                errorMessage.show();
            }
        }
    }

    public void goToForm(View view) {
        images.remove(images.size() - 1);
        Intent intent = new Intent(this, FormActivity.class);
        KumuApp.getAppStorage().getCurrentForm().addImages(images);
        KumuApp.getAppStorage().getCurrentForm().setDate(new Date());

        /* If location or images are null, it means things stay broke so uncomment this to debug
        Log.d("testtest", "" + KumuApp.getAppStorage().getCurrentForm().images.size());
        Log.d("testtest", KumuApp.getAppStorage().getForms().size() + " forms");
        for(Photo p : KumuApp.getAppStorage().getCurrentForm().images){
            Log.d("testtest", p.getLocation().getLatitude() + " " + p.getLocation().getLongitude());
        }

        for(Form f : KumuApp.getAppStorage().getForms()){
            if(f.equals(KumuApp.getAppStorage().getCurrentForm())){
                Log.d("testtest", "reee");
            }
            for(Photo p : f.images){
                Log.d("testtest2", p.getLocation().getLatitude() + " " + p.getLocation().getLongitude());
            }
        }*/

        KumuApp.getAppStorage().saveForms();
        startActivity(intent);
    }

}
