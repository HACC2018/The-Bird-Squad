package com.birdsquad.kumu;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class PhotoAdapter extends ArrayAdapter {

    private Context context;

    private Bitmap[] photos;

    public PhotoAdapter(Context context, int resource, Bitmap[] objects) {
        super(context, resource, objects);
        this.photos = objects;
        this.context = context;
    }

    public int getCount() {
        return photos.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(photos[position]);
        return imageView;
    }


}
