package com.example.lewis.helloworld;


import android.Manifest;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.app.Dialog;
import android.widget.Toast;
import android.text.Html;
import android.widget.TextView;
import android.graphics.Typeface;
import com.example.lewis.helloworld.WeatherActivity;
import com.example.lewis.helloworld.CompassActivity;
import com.example.lewis.helloworld.LocationActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.function.Function;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        int permission_track = 1;
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};

        if(!hasPermissions(this, permissions))
        {
            ActivityCompat.requestPermissions(this, permissions, permission_track);
        }

        if(isServicesOK()){
            init();
        }

        final TextView cityField = findViewById(R.id.city_field);
        final TextView updatedField = findViewById(R.id.updated_field);
        final TextView detailsField = findViewById(R.id.details_field);
        final TextView currentTemperatureField = findViewById(R.id.current_temperature_field);
        final TextView humidity_field = findViewById(R.id.humidity_field);
        final TextView pressure_field = findViewById(R.id.pressure_field);
//        weatherIcon = (TextView)findViewById(R.id.weather_icon);
//        weatherIcon.setTypeface(weatherFont);


        WeatherActivity.placeIdTask asyncTask =new WeatherActivity.placeIdTask(new WeatherActivity.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(weather_city);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: "+weather_humidity);
                pressure_field.setText("Pressure: "+weather_pressure);
//                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        asyncTask.execute("42.3497", "-71.1037"); //  asyncTask.execute("Latitude", "Longitude")


    LocationActivity speed = new LocationActivity();



    }

    @Override
    public void onResume() {
        super.onResume();

        final TextView cityField = findViewById(R.id.city_field);
        final TextView updatedField = findViewById(R.id.updated_field);
        final TextView detailsField = findViewById(R.id.details_field);
        final TextView currentTemperatureField = findViewById(R.id.current_temperature_field);
        final TextView humidity_field = findViewById(R.id.humidity_field);
        final TextView pressure_field = findViewById(R.id.pressure_field);
//        weatherIcon = (TextView)findViewById(R.id.weather_icon);
//        weatherIcon.setTypeface(weatherFont);


        WeatherActivity.placeIdTask asyncTask =new WeatherActivity.placeIdTask(new WeatherActivity.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(weather_city);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: "+weather_humidity);
                pressure_field.setText("Pressure: "+weather_pressure);
//                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });
        asyncTask.execute("42.3497", "-71.1037"); //  asyncTask.execute("Latitude", "Longitude")



    }



    private void init(){

    }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public static boolean hasPermissions(Context context, String[] permissions)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null)
        {
            for(String permission:permissions)
            {
                if(ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }
        return true;
    }

}