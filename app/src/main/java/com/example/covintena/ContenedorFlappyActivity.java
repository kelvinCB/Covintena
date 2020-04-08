package com.example.covintena;

import android.content.Intent;
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
    public static boolean cambio = false;
    private static boolean aux;

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
                        aux = FlappyVirusActivity.cambiar;
                        auxSonidoTos = FlappyVirusActivity.sonidoTos;
                        auxSonidoPuntos = FlappyVirusActivity.sonidoPuntos;
                        if(auxSonidoPuntos == true){
                            FlappyVirusActivity.sonidoPuntos = false;
                            MediaPlayer puntos = MediaPlayer.create(ContenedorFlappyActivity.this, R.raw.puntos);
                            puntos.start();
                        }
                        if(auxSonidoTos == true){
                            FlappyVirusActivity.sonidoTos = false;
                            MediaPlayer sb = MediaPlayer.create(ContenedorFlappyActivity.this, R.raw.tos);
                            sb.start();
                        }
                        System.out.println("El valor de la variable auxiliar es: "+aux);
                        if(aux==true) {
                            llamarGameOver();
                            timer.cancel();
                        }
                    }
                });

            }
        }, 0, TIMER_INTERVAL);


}

    public void llamarGameOver(){
    new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(ContenedorFlappyActivity.this, GameOverActivity.class);
            startActivity(intent);
            finish();
            MediaPlayer sb = MediaPlayer.create(ContenedorFlappyActivity.this, R.raw.coronado1);
            sb.start();
        }
    }, 2000);

    }


}
