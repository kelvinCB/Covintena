package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverHeadsUpActivity extends AppCompatActivity {

    TextView tvcorrectas;
    TextView tvincorrectas;

    Button btnVJ_hu, btnSalir_hu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_heads_up);

        btnVJ_hu = (Button)findViewById(R.id.volverJugar_hu);
        btnSalir_hu = (Button)findViewById(R.id.salir_hu);

        int correctas = getIntent().getExtras().getInt("correctas");
        int incorrecta = getIntent().getExtras().getInt("incorrectas");

        tvcorrectas = findViewById(R.id.tvCorrectas);
        tvincorrectas = findViewById(R.id.tvIncorrectas);

        tvcorrectas.setText(String.valueOf(correctas));
        tvincorrectas.setText(String.valueOf(incorrecta));

        btnVJ_hu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GameOverHeadsUpActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GameOverHeadsUpActivity.this, HeadsUpActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSalir_hu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer sb = MediaPlayer.create(GameOverHeadsUpActivity.this, R.raw.sonidoboton);
                sb.start();
                Intent intent = new Intent(GameOverHeadsUpActivity.this, GamesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GameOverHeadsUpActivity.this,
                GamesActivity.class);
        startActivity(intent);
        finish();
    }
}
