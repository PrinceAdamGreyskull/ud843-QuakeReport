/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

import static com.example.android.quakereport.QueryUtils.extractEarthquakes;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

/*        // Create a fake list of earthquake locations.
        ArrayList<EarthQuake> earthquakes = new ArrayList<>();
        earthquakes.add(new EarthQuake(1.0,"San Francisco","1 Feb 2018"));
        earthquakes.add(new EarthQuake(2.0,"London","2 Feb 2018"));
        earthquakes.add(new EarthQuake(3.0,"Tokyo","3 Feb 2018"));
        earthquakes.add(new EarthQuake(4.0,"Mexico City","4 Feb 2018"));
        earthquakes.add(new EarthQuake(5.0,"Moscow","5 Feb 2018"));
        earthquakes.add(new EarthQuake(6.0,"Rio de Janeiro","6 Feb 2018"));
        earthquakes.add(new EarthQuake(7.0,"Paris","7 Feb 2018"));*/

        // Generate a list of Earthquakes from JSON data
        final ArrayList<EarthQuake> earthquakes = extractEarthquakes();

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        QuakeAdapter adapter = new QuakeAdapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        // Set up an OnItemClickListener to send clicks on the list items to the relevant URL
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Retrieve and extract the URL from the relevant earthquake
                String qURL = earthquakes.get(position).getqUrl();

                // If the URL is empty, post a toast message. If it's good, go to webpage
                if (qURL != null) {
                    Uri quakePage = Uri.parse(qURL);

                    // Create an intent to send the qURL to a browser
                    Intent webIntent = new Intent(Intent.ACTION_VIEW, quakePage);
                    if (webIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(webIntent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "No URL available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
