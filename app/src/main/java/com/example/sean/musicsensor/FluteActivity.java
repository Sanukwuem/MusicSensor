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

public class FluteActivity extends AppCompatActivity implements SensorEventListener {

    MediaPlayer flutepart;
    MediaPlayer fluteone;
    MediaPlayer flutetwo;
    MediaPlayer flutethree;
    MediaPlayer flutefour;
    MediaPlayer flutefive;
    MediaPlayer flutesix;

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

        fluteone = MediaPlayer.create(this, R.raw.fluteone);
        flutetwo = MediaPlayer.create(this, R.raw.flutetwo);
        flutethree = MediaPlayer.create(this, R.raw.flutethree);
        flutefour = MediaPlayer.create(this, R.raw.flutefour);
        flutefive = MediaPlayer.create(this, R.raw.flutefive);
        flutesix = MediaPlayer.create(this, R.raw.flutesix);

        setupMessageButton();
        setupMessageButton2();
        setupPlayButton();
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

        fluteone.stop();
        flutetwo.stop();
        flutethree.stop();
        flutefour.stop();
        flutefive.stop();
        flutesix.stop();
    }


    @Override
    public void onSensorChanged(SensorEvent event) {



        //fluteone.start();
        //flutetwo.start();
        //flutethree.start();
        //flutefour.start();
        //flutefive.start();
        //flutesix.start();

        if (event.values[1] > 6){
            fluteone.setVolume(1, 1);
        } else {
            fluteone.setVolume(0, 0);
        }

        if (event.values[1] > 3 && event.values[1] <= 6){
            flutetwo.setVolume(1, 1);
        } else {
            flutetwo.setVolume(0, 0);
        }

        if (event.values[1] > 0 && event.values[1] <= 3){
            flutethree.setVolume(1, 1);
        } else {
            flutethree.setVolume(0, 0);
        }

        if (event.values[1] > -3 && event.values[1] <= 0){
            flutefour.setVolume(1, 1);
        } else {
            flutefour.setVolume(0, 0);
        }

        if (event.values[1] > -6 && event.values[1] <= -3){
            flutefive.setVolume(1, 1);
        } else {
            flutefive.setVolume(0, 0);
        }

        if (event.values[1] <= -6){
            flutesix.setVolume(1, 1);
        } else {
            flutesix.setVolume(0, 0);
        }



    }

    private void setupPlayButton(){
        Button playButton = (Button) findViewById(R.id.button6);
        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                fluteone.start();
                flutetwo.start();
                flutethree.start();
                flutefour.start();
                flutefive.start();
                flutesix.start();
                fluteone.setLooping(true);
                flutetwo.setLooping(true);
                flutethree.setLooping(true);
                flutefour.setLooping(true);
                flutefive.setLooping(true);
                flutesix.setLooping(true);


            }
        });
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
