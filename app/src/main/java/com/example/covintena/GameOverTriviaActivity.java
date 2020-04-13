package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverTriviaActivity extends AppCompatActivity {

    TextView tvcorrectas;
    TextView tvincorrectas;

    Button btnVJ_TV, btnSalir_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_trivia);

        btnVJ_TV = (Button)findViewById(R.id.volverJugar_tv);
        btnSalir_TV = (Button)findViewById(R.id.salir_tv);

        int correctas = getIntent().getExtras().getInt("correctas");
        int incorrecta = getIntent().getExtras().getInt("incorrectas");

        tvcorrectas = findViewById(R.id.tvCorrectasTriv);
        tvincorrectas = findViewById(R.id.tvIncorrectasTriv);

        tvcorrectas.setText(String.valueOf(correctas));
        tvincorrectas.setText(String.valueOf(incorrecta));


        btnVJ_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GameOverTriviaActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GameOverTriviaActivity.this, TriviaActivity.class);
                startActivity(intent);
            }
        });

        btnSalir_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GameOverTriviaActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GameOverTriviaActivity.this, GamesActivity.class);
                startActivity(intent);
            }
        });




    }
}
