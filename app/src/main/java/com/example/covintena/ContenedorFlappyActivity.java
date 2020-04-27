package com.example.covintena;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ContenedorFlappyActivity extends AppCompatActivity {


    private FlappyVirusActivity flappyVirusActivity;
    private Handler handler = new Handler();
    private final static long TIMER_INTERVAL = 30;

    //Sonidos
    public static boolean sonidoTos = false;
    private static boolean auxSonidoTos;

    public static boolean sonidoPuntos = false;
    private static boolean auxSonidoPuntos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        flappyVirusActivity = new FlappyVirusActivity(this);

        setContentView(flappyVirusActivity);

        // Start the timer.
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        flappyVirusActivity.invalidate();
                        auxSonidoTos = FlappyVirusActivity.sonidoTos;
                        auxSonidoPuntos = FlappyVirusActivity.sonidoPuntos;
                        if (auxSonidoPuntos == true) {
                            FlappyVirusActivity.sonidoPuntos = false;
                            MediaPlayer puntos = MediaPlayer.create(ContenedorFlappyActivity.this, R.raw.puntos);
                            puntos.start();
                        }
                        if (auxSonidoTos == true) {
                            FlappyVirusActivity.sonidoTos = false;
                            MediaPlayer sb = MediaPlayer.create(ContenedorFlappyActivity.this, R.raw.tos);
                            sb.start();
                        }
                    }
                });

            }
        }, 0, TIMER_INTERVAL);

    }

}
