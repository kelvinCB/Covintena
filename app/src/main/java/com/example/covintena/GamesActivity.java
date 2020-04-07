package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GamesActivity extends AppCompatActivity {

    Button btnHU;
    Button btnTV;
    Button btnFV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        getSupportActionBar().hide();

        btnHU = (Button)findViewById(R.id.buttonHU);
        btnTV = (Button)findViewById(R.id.buttonTV);
        btnFV = (Button)findViewById(R.id.buttonFV);


        btnHU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamesActivity.this, HeadsUpActivity.class);
                startActivity(intent);
            }
        });

        btnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamesActivity.this, TriviaActivity.class);
                startActivity(intent);
            }
        });

        btnFV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GamesActivity.this, FlappyVirusActivity.class);
                startActivity(intent);
            }
        });


    }
}
