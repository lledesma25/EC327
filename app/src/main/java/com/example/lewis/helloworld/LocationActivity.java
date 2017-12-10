package com.example.lewis.helloworld;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class LocationActivity extends AppCompatActivity implements LocationListener {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager mLocation = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        mLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.onLocationChanged(null);



    }

    @Override
    public void onLocationChanged(Location location)
    {
        TextView txt = (TextView) this.findViewById(R.id.Speed);

        if(location == null)
        {
            txt.setText("-.- MPH");
        }
        else
        {
            float speed = (float) (location.getSpeed()*2.23694);
            txt.setText(speed + " MPH");
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }



}
