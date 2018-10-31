package com.birdsquad.kumu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

public class PhotoAdapter extends ArrayAdapter {

    private Context context;

    private ArrayList<Bitmap> photos;

    private int resource;

    public PhotoAdapter(Context context, int resource, ArrayList<Bitmap> objects) {
        super(context, resource, objects);
        this.photos = objects;
        this.context = context;
        this.resource = resource;
    }

    @Override
   public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Bitmap photo = photos.get(position);
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.image.setImageBitmap(photo);
        return row;

    }

    static class ViewHolder {
        ImageView image;
    }




}
