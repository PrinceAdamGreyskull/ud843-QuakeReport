package com.example.android.quakereport;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Andrew on 3/01/2018.
 */

public class QuakeAdapter extends ArrayAdapter {

    public QuakeAdapter(Context context, ArrayList<QuakeDetail> arrayList) {
        super(context, 0, arrayList);
    }

}
