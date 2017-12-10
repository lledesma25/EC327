package com.example.lewis.helloworld;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class LocationActivity extends Fragment implements LocationListener {


    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate( R.layout.activity_location, container, false );

        return layout;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocationManager mLocation = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        mLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    @Override
    public void onLocationChanged(Location location)
    {
        TextView txt = (TextView) getActivity().findViewById(R.id.textView);

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
