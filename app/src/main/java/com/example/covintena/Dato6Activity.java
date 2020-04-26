package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dato6Activity extends AppCompatActivity {

    Button audiodato6,audiodato7 ;
    MediaPlayer mp = new MediaPlayer();
    MediaPlayer mp2 = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato6);

        audiodato6 = (Button)findViewById(R.id.btnescuchar9);
        mp = MediaPlayer.create(Dato6Activity.this, R.raw.tips_covid19);

        audiodato7 = (Button)findViewById(R.id.btnescuchar11);
        mp = MediaPlayer.create(Dato6Activity.this, R.raw.tips_covid19);

        audiodato6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.tips_covid19);

            }
        });

        audiodato7.setOnClickListener(new View.OnClickListener() {
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
            mp = MediaPlayer.create(Dato6Activity.this, sonido);
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
