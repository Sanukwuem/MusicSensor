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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    @Override
    public void onSensorChanged(SensorEvent event) {


        if (event.values[0] < -3){
            harppart.start();
        }

        
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    MediaPlayer harppart;
    Sensor accelerometer;
    SensorManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm= (SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        harppart = MediaPlayer.create(this, R.raw.harppart);

        setupMessageButton();
    }

    private void setupMessageButton() {
        Button messageButton = (Button) findViewById(R.id.button);
        messageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this, "You clicked it!", Toast.LENGTH_LONG).show();

                startActivity(new Intent(MainActivity.this, FluteActivity.class));
                finish();

            }
        });
    }
}
