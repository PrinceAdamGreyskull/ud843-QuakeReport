package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Andrew on 21/02/2018.
 */

public class EarthQuake {

    private double magnitude = 0.0;
    private String location = "";
    private long time_in_ms = 0;
    private String qUrl = "";

    public EarthQuake(double mag, String loc, long dat){
        magnitude = mag;
        location = loc;
        time_in_ms = dat;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getTime_in_ms() {
        return time_in_ms;
    }

    public String getDate(){
        Date quakeDate = new Date(time_in_ms);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, YYYY\nH:mm a");

        return dateFormatter.format(quakeDate);
    }

    public String getqUrl() {
        return qUrl;
    }

    /**
     * Set and get the URL on the USGS page for visiting the detailed quake data
     *
     * @param qUrl - the URL supplied in the USGS JSON file
     */
    public void setqUrl(String qUrl) {
        this.qUrl = qUrl;
    }
}
