package com.example.lewis.helloworld;


import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permission_track = 1;
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION};

        if(!hasPermissions(this, permission))
        {
            ActivityCompat.requestPermissions(this, permission, permission_track);
        }
    }

    public void viewMapActivity(View v) {
        Intent MapActivity = new Intent(this, MapsActivity.class);
        startActivity(MapActivity);
    }

    public void viewLocationActivity(View v) {
        Intent LocationActivity = new Intent(this, LocationActivity.class);
        startActivity(LocationActivity);
    }

    public static boolean hasPermissions(Context context, String[] permission)
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && context != null && permission != null)
        {
            for(String permissions:permission) {
                if (ActivityCompat.checkSelfPermission(context, permissions) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}