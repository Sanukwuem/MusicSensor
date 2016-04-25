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

public class FluteActivity extends AppCompatActivity implements SensorEventListener {

    MediaPlayer flutepart;
    Sensor accelerometer;
    SensorManager sm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flute);
        sm= (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        flutepart = MediaPlayer.create(this, R.raw.flutepart);

        setupMessageButton();
        setupMessageButton2();
    }


    private void setupMessageButton() {
        Button messageButton = (Button) findViewById(R.id.button2);
        messageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(FluteActivity.this, "You clicked it!", Toast.LENGTH_LONG).show();

                startActivity(new Intent(FluteActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void setupMessageButton2() {
        Button messageButton = (Button) findViewById(R.id.button3);
        messageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(FluteActivity.this, "You clicked it!", Toast.LENGTH_LONG).show();

                startActivity(new Intent(FluteActivity.this, XylophoneActivity.class));

            }
        });
    }

    public void onStop() {
        super.onStop();
        flutepart.stop();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.values[0] < -3){
            flutepart.start();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
