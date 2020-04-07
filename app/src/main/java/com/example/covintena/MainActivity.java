package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    private FlappyVirusActivity gameView;
    private Handler handler = new Handler();
    private final static long TIMER_INTERVAL = 30;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, ContenedorFlappyActivity.class);
        startActivity(intent);
    }

}

