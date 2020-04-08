package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gintrucionesActivity extends AppCompatActivity {

    Button btnAudio1, btnAudio2, btnAudio3, btnAudio4;
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gintruciones);


        btnAudio1 = (Button)findViewById(R.id.audio_1);
        btnAudio2 = (Button)findViewById(R.id.audio_2);
        btnAudio3 = (Button)findViewById(R.id.audio_3);
        btnAudio4 = (Button)findViewById(R.id.audio_4);
        mp = MediaPlayer.create(gintrucionesActivity.this, R.raw.covid_19);



        btnAudio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.covintena);

            }
        });


        btnAudio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.covid_19);

            }
        });

        btnAudio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.juegos);

            }
        });

        btnAudio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.vago_modo);

            }
        });




    }
    private void reproducir(int sonido){
        if (mp.isPlaying()){
            mp.stop();
            //mp = null;
            //mp = MediaPlayer.create(gintrucionesActivity.this, sonido);
            //mp.start();
        }else{
            mp = MediaPlayer.create(gintrucionesActivity.this, sonido);
            mp.start();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mp.isPlaying()) {
            mp.stop();
        }
    }
}
