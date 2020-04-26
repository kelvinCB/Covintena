package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dato2Activity extends AppCompatActivity {

    Button audiodato2;
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato2);

        audiodato2 = (Button)findViewById(R.id.btnescuchar2);
        mp = MediaPlayer.create(Dato2Activity.this, R.raw.sintomas);

        audiodato2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.sintomas);

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
            mp = MediaPlayer.create(Dato2Activity.this, sonido);
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
