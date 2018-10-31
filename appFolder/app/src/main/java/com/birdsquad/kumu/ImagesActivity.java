package com.birdsquad.kumu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class ImagesActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ArrayList<Bitmap> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        this.images = new ArrayList<Bitmap>();
        // set first image to + icon
        Bitmap addIcon = BitmapFactory.decodeResource(getResources(), R.drawable.add_photo_icon);
        images.add(addIcon);

        final GridView gridview = (GridView) findViewById(R.id.imageGridView);
        
        gridview.setAdapter(new PhotoAdapter(this, R.layout.grid_item_layout, images));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                if (position == images.size() - 1) {
                    if (images.size() >= 11) {
                        Toast.makeText(ImagesActivity.this, "You already have 10 images.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else {
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.d("DebugMePls", "here");
            images.add(0, imageBitmap);
            final GridView gridview = (GridView) findViewById(R.id.imageGridView);
            ((ArrayAdapter<Bitmap>)gridview.getAdapter()).notifyDataSetChanged();
        }
    }

}
