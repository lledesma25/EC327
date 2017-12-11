package com.example.lewis.helloworld;


import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.content.Context.LOCATION_SERVICE;


public class LocationActivity extends Fragment implements LocationListener {

    TextView txt;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate( R.layout.activity_location, container, false );
        txt = layout.findViewById(R.id.Speed_Value);
        txt.setText(R.string.null_speed);
        return layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LocationManager mLocation = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        mLocation.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);




    }


    @Override
    public void onLocationChanged(Location location)
    {

            if(location != null)
            {

                float speed = (float) (location.getSpeed()*2.23694);
                String out = Float.toString(speed) + " MPH";
                txt.setText(out);
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
