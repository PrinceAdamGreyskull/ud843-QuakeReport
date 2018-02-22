package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Andrew on 21/02/2018.
 */

public class QuakeAdapter extends ArrayAdapter {

    public QuakeAdapter(Context context, ArrayList<EarthQuake> aList){
        super(context,0,aList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //If a new view is required, inflate the new view
        View quakeView = convertView;
        if (quakeView == null){
            quakeView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quake_view, parent, false);
        }

        // Retrieve the data for the EarthQuake and display
        EarthQuake earthQuake = (EarthQuake) getItem(position);

        TextView magView = (TextView) quakeView.findViewById(R.id.vw_magnitude);
        TextView locView = (TextView) quakeView.findViewById(R.id.vw_location);
        TextView datView = (TextView) quakeView.findViewById(R.id.vw_date);

        magView.setText(String.format("%1.1f",earthQuake.getMagnitude()));
        locView.setText(earthQuake.getLocation());
        //datView.setText("" + earthQuake.getTime_in_ms());
        datView.setText(earthQuake.getDate());

        return quakeView;
    }
}
