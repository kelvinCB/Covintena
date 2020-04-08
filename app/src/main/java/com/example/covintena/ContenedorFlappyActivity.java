package com.example.covintena;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ContenedorFlappyActivity extends AppCompatActivity {


    private FlappyVirusActivity flappyVirusActivity;
    private GameOverActivity gameOverActivity;
    private Handler handler = new Handler();
    private final static long TIMER_INTERVAL = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_game_over);
        flappyVirusActivity = new FlappyVirusActivity(this);
        gameOverActivity = new GameOverActivity();

        setContentView(flappyVirusActivity);

        // Start the timer.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        flappyVirusActivity.invalidate();
                    }
                });

            }
        }, 0, TIMER_INTERVAL);

    }


}
