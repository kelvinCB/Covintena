package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class GameOverTriviaActivity extends AppCompatActivity {

    TextView tvcorrectas;
    TextView tvincorrectas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_trivia);

        int correctas = getIntent().getExtras().getInt("correctas");
        int incorrecta = getIntent().getExtras().getInt("incorrectas");

        tvcorrectas = findViewById(R.id.tvCorrectasTriv);
        tvincorrectas = findViewById(R.id.tvIncorrectasTriv);

        tvcorrectas.setText(String.valueOf(correctas));
        tvincorrectas.setText(String.valueOf(incorrecta));
    }
}
