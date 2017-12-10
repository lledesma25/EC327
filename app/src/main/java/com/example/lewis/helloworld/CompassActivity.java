package com.example.lewis.helloworld;

import android.app.Fragment;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;

import android.hardware.SensorEvent;

import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import static android.content.Context.SENSOR_SERVICE;

    public class CompassActivity extends Fragment implements SensorEventListener {
    // device sensor manager
    private SensorManager mSensor;
    private ImageView image;
    private float currentDegree = 0f;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View layout = inflater.inflate( R.layout.activity_compass, container, false );

        return layout;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensor = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        image = getActivity().findViewById(R.id.imageViewCompass);
    }
    @Override
    public void onPause() {
        super.onPause();
        // to stop the listener and save battery
        mSensor.unregisterListener(this);
    }
    @Override
    public void onResume() {
        super.onResume();
        // code for system's orientation sensor registered listeners
        mSensor.registerListener(this, mSensor.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        // get angle around the z-axis rotated
        float degree = Math.round(event.values[0]);

        RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        // how long the animation will take place
        ra.setDuration(210);

        // set the animation after the end of the reservation status
        ra.setFillAfter(true);

        // Start the animation
        image.startAnimation(ra);
        currentDegree = -degree;

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not in use
    }
}