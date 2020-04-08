package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstruccionesActitvity extends AppCompatActivity {

    Button btnescuchar1, btnescuchar2, btnescuchar3;
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones_actitvity);

        btnescuchar1 = (Button)findViewById(R.id.btnescuchar3);
        btnescuchar2 = (Button)findViewById(R.id.btnescuchar4);
        btnescuchar3 = (Button)findViewById(R.id.btnescuchar5);

        btnescuchar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.heads_up);

            }
        });

        btnescuchar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.trivia);

            }
        });

        btnescuchar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.flappy_virus);

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
            mp = MediaPlayer.create(InstruccionesActitvity.this, sonido);
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
