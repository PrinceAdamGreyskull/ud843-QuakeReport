package com.example.android.quakereport;

/**
 * Created by andre on 3/01/2018.
 */

public class QuakeDetail {

    //TODO Convert Magnitude to float and work out how to display
    private String qkMagnitude;
    private String qkLocation;
    //TODO Convert Date to date format and work out how to display
    private String qkDate;

    public QuakeDetail(String mag, String loc, String date) {
        qkMagnitude = mag;
        qkLocation = loc;
        qkDate = date;
    }

    public String getQkMagnitude() {
        return qkMagnitude;
    }

    public String getQkLocation() {
        return qkLocation;
    }

    public String getQkDate() {
        return qkDate;
    }
}
