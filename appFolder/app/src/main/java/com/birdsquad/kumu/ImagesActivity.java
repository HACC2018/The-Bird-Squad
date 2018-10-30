package com.birdsquad.kumu;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImagesActivity extends AppCompatActivity {

    private Bitmap[] images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.images = new Bitmap[10];
        // set first image to + icon

        final GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new PhotoAdapter(this, android.R.layout.simple_gallery_item, images));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (position == gridview.getAdapter().getCount() - 1) {
                    // launch camera
                }
            }
        });

        setContentView(R.layout.activity_images);
    }


}
