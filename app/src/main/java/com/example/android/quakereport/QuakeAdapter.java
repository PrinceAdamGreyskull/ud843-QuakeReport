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
        TextView dirView = (TextView) quakeView.findViewById(R.id.vw_direction);
        TextView locView = (TextView) quakeView.findViewById(R.id.vw_location);
        TextView datView = (TextView) quakeView.findViewById(R.id.vw_date);

        // Retrieve the full location string
        String fullLocation = earthQuake.getLocation();

        // Split the location into two pieces: the general direction and the base location
        String pattern = " of ";
        String direction;
        String baseLocation;
        if (fullLocation.contains(pattern)) {
            // Handle the normal variant,eg: "200Km NNE of Melbourne, Australia"
            String[] parts = fullLocation.split(pattern);
            direction = parts[0] + " of";
            baseLocation = parts[1];
        } else {
            // Handle the special case, eg: "Pacific-Antarctic Ridge"
            direction = "Near";
            baseLocation = fullLocation;
        }

        magView.setText(String.format("%1.1f",earthQuake.getMagnitude()));
        dirView.setText(direction);
        locView.setText(baseLocation);
        //datView.setText("" + earthQuake.getTime_in_ms());
        datView.setText(earthQuake.getDate());

        return quakeView;
    }
}
