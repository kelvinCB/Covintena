package com.example.covintena;

import android.content.Context;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class Dato1Activity extends AppCompatActivity {

    Button audiodato1;
    MediaPlayer mp = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dato1);

        audiodato1 = (Button)findViewById(R.id.btnescuchar);
        mp = MediaPlayer.create(Dato1Activity.this, R.raw.covid_19);

        audiodato1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reproducir(R.raw.coronavirus);

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
            mp = MediaPlayer.create(Dato1Activity.this, sonido);
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