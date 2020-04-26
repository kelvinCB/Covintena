package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dato5Activityy extends AppCompatActivity {

    Button audiodato5;
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato5_activityy);

        audiodato5 = (Button)findViewById(R.id.btnescuchar8);
        mp = MediaPlayer.create(Dato5Activityy.this, R.raw.detalles_covid19);

        audiodato5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.detalles_covid19);

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
            mp = MediaPlayer.create(Dato5Activityy.this, sonido);
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
