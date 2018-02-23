package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
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

        // Load all of the new values into the view object
        magView.setText(formatMagnitude(earthQuake.getMagnitude()));
        dirView.setText(direction);
        locView.setText(baseLocation);
        datView.setText(earthQuake.getDate());

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthQuake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return quakeView;
    }

    /**
     * Formats the quake magnitude into a consistent 1 decimal place format to allow for
     * consistent presentation in the app
     *
     * @param mag - the magnitude of the quake returned in double form from USGS
     * @return - String representation in correct format
     */
    private String formatMagnitude(double mag) {
        DecimalFormat magFormat = new DecimalFormat("0.0");
        return magFormat.format(mag);
    }

    /**
     * Determines the colour of the circle behind the quake magnitude based on the magnitude
     * (ie a sliding colour scale for severity of quake)
     *
     * @param mag - the magnitude of the quake returned in double form from USGS
     * @return - an integer value representing the matched colour
     */
    private int getMagnitudeColor(double mag) {
        int magColour;
        int floorMag = (int) Math.floor(mag);

        if (floorMag < 0) {
            floorMag = 0;
        }

        switch (floorMag) {
            case 0:
            case 1:
                magColour = R.color.magnitude1;
                break;
            case 2:
                magColour = R.color.magnitude2;
                break;
            case 3:
                magColour = R.color.magnitude3;
                break;
            case 4:
                magColour = R.color.magnitude4;
                break;
            case 5:
                magColour = R.color.magnitude5;
                break;
            case 6:
                magColour = R.color.magnitude6;
                break;
            case 7:
                magColour = R.color.magnitude7;
                break;
            case 8:
                magColour = R.color.magnitude8;
                break;
            case 9:
                magColour = R.color.magnitude9;
                break;
            default:
                magColour = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magColour);
    }
}
