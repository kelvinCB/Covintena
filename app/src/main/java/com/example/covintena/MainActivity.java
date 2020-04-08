package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnInfo;
    Button btnGames;
    Button btnVagMode;
    Button btnAudios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();

        btnInfo = (Button)findViewById(R.id.button);
        btnGames = (Button)findViewById(R.id.button2);
        btnVagMode = (Button)findViewById(R.id.button3);
        btnAudios = (Button)findViewById(R.id.button4);

        //Botón Info
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Transicion1Activity.class);
                startActivity(intent);
            }
        });

        //Botón Games
        btnGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Transicion2Activity.class);
                startActivity(intent);
            }
        });

        //Botón VagMode
        btnVagMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Transicion3Activity.class);
                startActivity(intent);
            }
        });

        //Botón Audio
        btnAudios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AudiosActivity.class);
                startActivity(intent);
            }
        });




    }

}

