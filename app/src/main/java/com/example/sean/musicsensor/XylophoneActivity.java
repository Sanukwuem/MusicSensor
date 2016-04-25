package com.example.sean.musicsensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class XylophoneActivity extends AppCompatActivity implements SensorEventListener {

    MediaPlayer xylophonepart;

    Sensor accelerometer;
    SensorManager sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xylophone);
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        xylophonepart = MediaPlayer.create(this, R.raw.xylophonepart);

        setupMessageButton();
    }

    private void setupMessageButton() {
        Button messageButton = (Button) findViewById(R.id.button4);
        messageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(XylophoneActivity.this, "You clicked it!", Toast.LENGTH_LONG).show();

                startActivity(new Intent(XylophoneActivity.this, FluteActivity.class));
                finish();
            }
        });
    }

    public void onStop() {
        super.onStop();
        xylophonepart.stop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0] < -3) {
            xylophonepart.start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}