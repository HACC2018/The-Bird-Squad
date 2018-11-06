package com.birdsquad.kumu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FormAdapter extends ArrayAdapter<Form> {

    private Activity activity;
    private ArrayList<Form> forms;
    private static LayoutInflater inflater = null;

    public FormAdapter (Activity activity, int textViewResourceId,ArrayList<Form> forms) {
        super(activity, textViewResourceId, forms);
        try {
            this.activity = activity;
            this.forms = forms;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.incomplete_forms_listitem_layout, null);
                holder = new ViewHolder();

                holder.display_speciesname = (TextView) vi.findViewById(R.id.list_speciesName);
                holder.display_datetime = (TextView) vi.findViewById(R.id.list_dateTime);
                if(holder.display_datetime.getText().toString().equals("")){
                    holder.display_datetime.setText("No datetime");
                }
                if(holder.display_speciesname.getText().toString().equals("")){
                    holder.display_speciesname.setText("No species name specified");
                }

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }



            holder.display_speciesname.setText(forms.get(position).taxonName);
            holder.display_datetime.setText(forms.get(position).dateCreated.toString());


        } catch (Exception e) {


        }
        return vi;
    }

    public int getCount() {
        return forms.size();
    }

    public Form getItem(int position) {
        return forms.get(position);
    }

    public static class ViewHolder {
        public TextView display_speciesname;
        public TextView display_datetime;

    }



}
