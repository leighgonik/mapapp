package com.mapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.location.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * Created by Leigh on 4/6/2018.
 */

public class MainActivity extends AppCompatActivity {
    public final static String LOC_LAT = "com.mapapp.LOC_LAT";
    public final static String LOC_LNG = "com.mapapp.LOC_LNG";
    public final static String ADDR_LINE = "com.mapapp.ADDR_LINE";
    private Geocoder geoCoder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        geoCoder = new Geocoder(this);
    }

    public void sendLoc(View view) throws IOException {
        Intent intent = new Intent(this, MapsActivity.class);
        EditText enterLoc = (EditText) findViewById(R.id.enter_loc);
        String location = enterLoc.getText().toString();
        Address addr = null;
        try {
            while (addr == null) {
                addr = geoCoder.getFromLocationName(location, 1).get(0);
            }
            if (addr == null) {
                System.out.println("addr is null");
                addr.setLatitude(30.307182);
                addr.setLongitude(-97.755996);
                addr.setAddressLine(0, location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        intent.putExtra(LOC_LAT, addr.getLatitude());
        intent.putExtra(LOC_LNG, addr.getLongitude());
        intent.putExtra(ADDR_LINE, addr.getAddressLine(0));
        startActivity(intent);
    }
}