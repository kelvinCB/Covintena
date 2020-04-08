package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameOverHeadsUpActivity extends AppCompatActivity {

    TextView tvcorrectas;
    TextView tvincorrectas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_heads_up);

        int correctas = getIntent().getExtras().getInt("correctas");
        int incorrecta = getIntent().getExtras().getInt("incorrectas");

        tvcorrectas = findViewById(R.id.tvCorrectas);
        tvincorrectas = findViewById(R.id.tvIncorrectas);

        tvcorrectas.setText(String.valueOf(correctas));
        tvincorrectas.setText(String.valueOf(incorrecta));

    }
}
