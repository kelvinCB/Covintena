package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    Button btnInfo;
    Button btnGames;
    Button btnVagMode;
    Button btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();

        btnInfo = (Button)findViewById(R.id.button);
        btnGames = (Button)findViewById(R.id.button2);
        btnVagMode = (Button)findViewById(R.id.button3);
        btnHelp = (Button)findViewById(R.id.btnhelp);

        //Botón Info
        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayer sb = MediaPlayer.create(MainActivity.this, R.raw.sonidoboton);
                //sb.start();
                Intent intent = new Intent(MainActivity.this, Transicion1Activity.class);
                startActivity(intent);
            }
        });

        //Botón Games
        btnGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayer sb = MediaPlayer.create(MainActivity.this, R.raw.sonidoboton);
                //sb.start();
                Intent intent = new Intent(MainActivity.this, Transicion2Activity.class);
                startActivity(intent);
            }
        });

        //Botón VagMode
        btnVagMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayer sb = MediaPlayer.create(MainActivity.this, R.raw.sonidoboton);
                //sb.start();
                Intent intent = new Intent(MainActivity.this, Transicion3Activity.class);
                startActivity(intent);
            }
        });

        //Botón Help
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //MediaPlayer sb = MediaPlayer.create(MainActivity.this, R.raw.sonidoboton);
                //sb.start();
                Intent intent = new Intent(MainActivity.this, gintrucionesActivity.class);
                startActivity(intent);
            }
        });




    }

}

