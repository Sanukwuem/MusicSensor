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
import android.widget.Toast;

public class XylophoneActivity extends AppCompatActivity implements SensorEventListener {

    MediaPlayer xylophonepart;

    MediaPlayer xylophoneone;
    MediaPlayer xylophonetwo;
    MediaPlayer xylophonethree;
    MediaPlayer xylophonefour;
    MediaPlayer xylophonefive;
    MediaPlayer xylophonesix;


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

        xylophoneone = MediaPlayer.create(this, R.raw.pianoone);
        xylophonetwo = MediaPlayer.create(this, R.raw.pianotwo);
        xylophonethree = MediaPlayer.create(this, R.raw.pianothree);
        xylophonefour = MediaPlayer.create(this, R.raw.pianofour);
        xylophonefive = MediaPlayer.create(this, R.raw.pianofive);
        xylophonesix = MediaPlayer.create(this, R.raw.pianosix);

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

        xylophoneone.stop();
        xylophonetwo.stop();
        xylophonethree.stop();
        xylophonefour.stop();
        xylophonefive.stop();
        xylophonesix.stop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        xylophoneone.start();
        xylophonetwo.start();
        xylophonethree.start();
        xylophonefour.start();
        xylophonefive.start();
        xylophonesix.start();

        if (event.values[1] > 6){
            xylophoneone.setVolume(1, 1);
        } else {
            xylophoneone.setVolume(0, 0);
        }

        if (event.values[1] > 3 && event.values[1] <= 6){
            xylophonetwo.setVolume(1, 1);
        } else {
            xylophonetwo.setVolume(0, 0);
        }

        if (event.values[1] > 0 && event.values[1] <= 3){
            xylophonethree.setVolume(1, 1);
        } else {
            xylophonethree.setVolume(0, 0);
        }

        if (event.values[1] > -3 && event.values[1] <= 0){
            xylophonefour.setVolume(1, 1);
        } else {
            xylophonefour.setVolume(0, 0);
        }

        if (event.values[1] > -6 && event.values[1] <= -3){
            xylophonefive.setVolume(1, 1);
        } else {
            xylophonefive.setVolume(0, 0);
        }

        if (event.values[1] <= -6){
            xylophonesix.setVolume(1, 1);
        } else {
            xylophonesix.setVolume(0, 0);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}