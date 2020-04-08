package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AudiosActivity extends AppCompatActivity {

    Button btnAud1, btnAud2, btnAud3, btnAud4, btnAud5, btnAud6;
    MediaPlayer mp = new MediaPlayer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audios);


        btnAud1 = (Button)findViewById(R.id.Aud1);
        btnAud2 = (Button)findViewById(R.id.Aud2);
        btnAud3 = (Button)findViewById(R.id.Aud3);
        btnAud4 = (Button)findViewById(R.id.Aud4);
        btnAud5 = (Button)findViewById(R.id.Aud5);
        btnAud6 = (Button)findViewById(R.id.Aud6);

        btnAud1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.coronavirus);

            }
        });

        btnAud2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.sintomas);

            }
        });

        btnAud3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.origen_covid19);

            }
        });

        btnAud4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.medidas_de_salubridad);

            }
        });

        btnAud5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.detalles_covid19);

            }
        });

        btnAud6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.tips_covid19);

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
            mp = MediaPlayer.create(AudiosActivity.this, sonido);
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
