package com.example.covintena;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ContenedorFlappyActivity extends AppCompatActivity {


    private FlappyVirusActivity gameView;
    private Handler handler = new Handler();
    private final static long TIMER_INTERVAL = 30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_game_over);

        gameView = new FlappyVirusActivity(this);
        setContentView(gameView);

        // Start the timer.
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gameView.invalidate();
                    }
                });
            }
        }, 0, TIMER_INTERVAL);


    }


}
