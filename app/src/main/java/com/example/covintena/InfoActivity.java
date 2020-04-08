package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InfoActivity extends AppCompatActivity {

    Button btnDato1;
    Button btnDato2;
    Button btnDato3;
    Button btnDato4;
    Button btnDato5;
    Button btnDato6;
    Button btnAudio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

       // getSupportActionBar().hide();

        btnDato1 = (Button)findViewById(R.id.DATO1);
        btnDato2 = (Button)findViewById(R.id.DATO2);
        btnDato3 = (Button)findViewById(R.id.DATO3);
        btnDato4 = (Button)findViewById(R.id.DATO4);
        btnDato5 = (Button)findViewById(R.id.DATO5);
        btnDato6 = (Button)findViewById(R.id.DATO6);

        btnAudio = (Button)findViewById(R.id.btnaudio);



        btnDato1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(InfoActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(InfoActivity.this, Dato1Activity.class);
                startActivity(intent);
            }
        });

        btnDato2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(InfoActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(InfoActivity.this, Dato2Activity.class);
                startActivity(intent);
            }
        });

        btnDato3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(InfoActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(InfoActivity.this, Dato3Activity.class);
                startActivity(intent);
            }
        });

        btnDato4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(InfoActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(InfoActivity.this, Dato4Activity.class);
                startActivity(intent);
            }
        });

        btnDato5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(InfoActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(InfoActivity.this, Dato5Activityy.class);
                startActivity(intent);
            }
        });

        btnDato6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(InfoActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(InfoActivity.this, Dato6Activity.class);
                startActivity(intent);
            }
        });

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(InfoActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(InfoActivity.this, AudiosActivity.class);
                startActivity(intent);
            }
        });



    }
}
