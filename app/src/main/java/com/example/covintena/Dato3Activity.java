package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dato3Activity extends AppCompatActivity {

    Button audiodato3;
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato3);

        audiodato3 = (Button)findViewById(R.id.btnescuchar6);
        mp = MediaPlayer.create(Dato3Activity.this, R.raw.origen_covid19);

        audiodato3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.origen_covid19);

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
            mp = MediaPlayer.create(Dato3Activity.this, sonido);
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
