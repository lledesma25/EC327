package com.example.lewis.helloworld;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.Context;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager.registerListener(this,compassSensor, SensorManager.SENSOR_DELAY_GAME);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }



    SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    Sensor compassSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
        public void onSensorChanged(SensorEvent sensorEvent)
        {
                float degree = Math.round(sensorEvent.values[0]);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i)
        {

        }

    LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            location.getLatitude();

        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };


}


