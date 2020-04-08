package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameOverActivity extends AppCompatActivity {

    Button btnvj, btnsalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over2);

        btnvj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GameOverActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GameOverActivity.this, ContenedorFlappyActivity.class);
                startActivity(intent);
            }
        });

        btnsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GameOverActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GameOverActivity.this, GamesActivity.class);
                startActivity(intent);
            }
        });

    }
}
