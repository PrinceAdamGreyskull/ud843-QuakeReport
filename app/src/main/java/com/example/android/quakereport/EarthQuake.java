package com.example.android.quakereport;

/**
 * Created by Andrew on 21/02/2018.
 */

public class EarthQuake {

    private double magnitude = 0.0;
    private String location = "";
    private String date = "";

    public EarthQuake(Double mag, String loc, String dat){
        magnitude = mag;
        location = loc;
        date = dat;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }
}
