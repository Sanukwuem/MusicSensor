package com.example.sean.musicsensor;

import android.annotation.SuppressLint;
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

public class MainActivity extends AppCompatActivity implements SensorEventListener {



    @Override //controls what happens when the accelerometer is moved(y values)
    public void onSensorChanged(SensorEvent event) {


            //start the instruments with the phone moving

            //harpone.start();
            //harptwo.start();
            //harpthree.start();
            //harpfour.start();
            //harpfive.start();
            //harpsix.start();


            //event values[0] is x; event values[1] is y; event values[2] is z
            //this particular sequence only uses the y axis

            if (event.values[1] > 6){
            harpone.setVolume(1,1);
             } else {
                harpone.setVolume(0,0);
            }

            if (event.values[1] > 3 && event.values[1] <= 6){
                harptwo.setVolume(1,1);
            } else {
                harptwo.setVolume(0,0);
            }

        if (event.values[1] > 0 && event.values[1] <= 3){
            harpthree.setVolume(1,1);
        } else {
            harpthree.setVolume(0,0);
        }

        if (event.values[1] > -3 && event.values[1] <= 0){
            harpfour.setVolume(1,1);
        } else {
            harpfour.setVolume(0,0);
        }

        if (event.values[1] > -6 && event.values[1] <= -3){
            harpfive.setVolume(1,1);
        } else {
            harpfive.setVolume(0,0);
        }

        if (event.values[1] <= -6){
            harpsix.setVolume(1,1);
        } else {
            harpsix.setVolume(0,0);
        }





    }

    @Override //not used but need for Sensor Event Listener
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //Sets up all of the Media Player objects all found in the "raw" file under "res"
    MediaPlayer harppart;

    MediaPlayer harpone;
    MediaPlayer harptwo;
    MediaPlayer harpthree;
    MediaPlayer harpfour;
    MediaPlayer harpfive;
    MediaPlayer harpsix;

    //sensor events
    Sensor accelerometer;
    SensorManager sm;

    @Override //when the activity(screen) starts up this tells it what to do
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm= (SensorManager)getSystemService(SENSOR_SERVICE); //setting up sensor
        accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //setting up accelerometer
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        //creating each of the media objects
        harppart = MediaPlayer.create(this, R.raw.harppart);

        harpone = MediaPlayer.create(this, R.raw.harpone);
        harptwo = MediaPlayer.create(this, R.raw.harptwo);
        harpthree = MediaPlayer.create(this, R.raw.harpthree);
        harpfour = MediaPlayer.create(this, R.raw.harpfour);
        harpfive = MediaPlayer.create(this, R.raw.harpfive);
        harpsix = MediaPlayer.create(this, R.raw.harpsix);

        //using functions that are below. sets up the play button and instrument button
        setupMessageButton();
        setupPlayButton();
    }

    //when activity switches or screen shuts off the instruments stops playing
    public void onStop() {
        super.onStop();
        harppart.stop();

        harpone.stop();
        harptwo.stop();
        harpthree.stop();
        harpfour.stop();
        harpfive.stop();
        harpsix.stop();
    }


    //modeled button online.. should actually be called instrument button
    private void setupMessageButton() {
        Button messageButton = (Button) findViewById(R.id.button); //button id in xml file

        //setting a listener on the button and telling it what to do
        messageButton.setOnClickListener(new View.OnClickListener() {

            @Override //when you click on the button it starts a new activity and ends the old activity
                      //this prevents the stack from being built on top of
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked it!", Toast.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this, FluteActivity.class));
                finish();

            }
        });
    }

    //play button that when pressed starts each of the different instrument parts
    //set looping makes the media player run again after the instrumental is complete
    private void setupPlayButton(){
        Button playButton = (Button) findViewById(R.id.button5);
        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                harpone.start();
                harptwo.start();
                harpthree.start();
                harpfour.start();
                harpfive.start();
                harpsix.start();
                harpone.setLooping(true);
                harptwo.setLooping(true);
                harpthree.setLooping(true);
                harpfour.setLooping(true);
                harpfive.setLooping(true);
                harpsix.setLooping(true);


            }
        });
    }
}
