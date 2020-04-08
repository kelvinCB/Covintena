package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GamesActivity extends AppCompatActivity {

    Button btnHU;
    Button btnTV;
    Button btnFV;
    Button btnjugarhu, btnjugartv, btnjugarfv;
    Button btnayuda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

     //  getSupportActionBar().hide();

        btnHU = (Button)findViewById(R.id.buttonHU);
        btnTV = (Button)findViewById(R.id.buttonTV);
        btnFV = (Button)findViewById(R.id.buttonFV);


        btnjugarhu = (Button)findViewById(R.id.jugarhu);
        btnjugartv = (Button)findViewById(R.id.jugartv);
        btnjugarfv = (Button)findViewById(R.id.jugarfv);
        btnayuda = (Button)findViewById(R.id.ayuda);




        btnHU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GamesActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GamesActivity.this, HeadsUpActivity.class);
                startActivity(intent);
            }
        });

        btnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GamesActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GamesActivity.this, TriviaActivity.class);
                startActivity(intent);
            }
        });

        btnFV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GamesActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GamesActivity.this, ContenedorFlappyActivity.class);
                startActivity(intent);
            }
        });

        btnjugarhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GamesActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GamesActivity.this, HeadsUpActivity.class);
                startActivity(intent);
            }
        });

        btnjugartv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GamesActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GamesActivity.this, TriviaActivity.class);
                startActivity(intent);
            }
        });

        btnjugarfv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GamesActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GamesActivity.this, ContenedorFlappyActivity
                        .class);
                startActivity(intent);
            }
        });


        btnayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GamesActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GamesActivity.this, InstruccionesActitvity.class);
                startActivity(intent);
            }
        });

    }
}
