package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.Lifecycle;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.covintena.interfaces.preguntasApi;
import com.example.covintena.model.Pregunta;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TriviaActivity extends AppCompatActivity {

    TextView tvPregunta;
    TextView tvCronometro;
    Button btnResp1;
    Button btnResp2;
    Button btnResp3;
    Button btnResp4;
    CountDownTimer countDownTimer, countDownTimer1;
    private int correctas, incorrectas, indexPregunta;
    private Boolean crono, crono1, pressBack;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        pressBack = false;
        crono = crono1 = false;
        correctas = 0;
        incorrectas = 0;
        indexPregunta = 0;
        mediaPlayer = MediaPlayer.create(this, R.raw.correct);

        tvPregunta = findViewById(R.id.tvPregunta);
        tvCronometro = findViewById(R.id.tvCronometro);
        btnResp1 = findViewById(R.id.btnResp1);
        btnResp2 = findViewById(R.id.btnResp2);
        btnResp3 = findViewById(R.id.btnResp3);
        btnResp4 = findViewById(R.id.btnResp4);

        btnResp1.setVisibility(View.INVISIBLE);
        btnResp2.setVisibility(View.INVISIBLE);
        btnResp3.setVisibility(View.INVISIBLE);
        btnResp4.setVisibility(View.INVISIBLE);

        btnResp1.setEnabled(true);
        btnResp2.setEnabled(true);
        btnResp3.setEnabled(true);
        btnResp4.setEnabled(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-hackathon.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        preguntasApi pregApi = retrofit.create(preguntasApi.class);
        Call<List<Pregunta>> call = pregApi.getPreguntas();
        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                final List<Pregunta> preguntaList = response.body();
                countDownTimer1 = new CountDownTimer(5000,
                        1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvPregunta.setText(String.format(Locale.getDefault(),
                                "¡Prepárate!\n%d",
                                millisUntilFinished / 1000L));
                        crono1 = true;
                    }

                    @Override
                    public void onFinish() {
                        crono1 = false;
                        btnResp1.setVisibility(View.VISIBLE);
                        btnResp2.setVisibility(View.VISIBLE);
                        btnResp3.setVisibility(View.VISIBLE);
                        btnResp4.setVisibility(View.VISIBLE);

                        countDownTimer = new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                tvCronometro.setText(String.format(Locale.getDefault(), "%d",
                                        millisUntilFinished / 1000));
                                crono = true;
                            }

                            @Override
                            public void onFinish() {
                                menu();
                            }
                        }.start();
                        Collections.shuffle(preguntaList, new Random());
                        juega(preguntaList, 0);
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {

            }
        });

    }

    private void juega(final List<Pregunta> preguntaList, final int index) {
        crono = true;
        if (preguntaList.size() > index) {
            tvPregunta.setText(preguntaList.get(index).getPregunta());
            btnResp1.setText(preguntaList.get(index).getRespuesta().get(0).getTexto());
            btnResp1.setBackgroundResource(R.drawable.botonestrivia);
            btnResp1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnResp1.setEnabled(false);
                    btnResp2.setEnabled(false);
                    btnResp3.setEnabled(false);
                    btnResp4.setEnabled(false);
                    if (preguntaList.get(index).getRespuesta().get(0).getValor()) {
                        correctas++;
                        correctIncorrectSound(R.raw.correct);
                        btnResp1.setBackgroundColor(Color.GREEN);
                    } else {
                        incorrectas++;
                        correctIncorrectSound(R.raw.incorrect);
                        btnResp1.setBackgroundColor(Color.RED);
                    }
                    indexPregunta++;
                    CountDownTimer count = new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long l) {
                        }

                        @Override
                        public void onFinish() {
                            btnResp1.setEnabled(true);
                            btnResp2.setEnabled(true);
                            btnResp3.setEnabled(true);
                            btnResp4.setEnabled(true);
                            juega(preguntaList, indexPregunta);
                        }
                    }.start();
                }
            });
            btnResp2.setText(preguntaList.get(index).getRespuesta().get(1).getTexto());
            btnResp2.setBackgroundResource(R.drawable.botonestrivia);
            btnResp2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnResp1.setEnabled(false);
                    btnResp2.setEnabled(false);
                    btnResp3.setEnabled(false);
                    btnResp4.setEnabled(false);
                    if (preguntaList.get(index).getRespuesta().get(1).getValor()) {
                        correctas++;
                        correctIncorrectSound(R.raw.correct);
                        btnResp2.setBackgroundColor(Color.GREEN);
                    } else {
                        incorrectas++;
                        correctIncorrectSound(R.raw.incorrect);
                        btnResp2.setBackgroundColor(Color.RED);
                    }
                    indexPregunta++;
                    CountDownTimer count = new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            btnResp1.setEnabled(true);
                            btnResp2.setEnabled(true);
                            btnResp3.setEnabled(true);
                            btnResp4.setEnabled(true);
                            juega(preguntaList, indexPregunta);
                        }
                    }.start();
                }
            });
            btnResp3.setText(preguntaList.get(index).getRespuesta().get(2).getTexto());
            btnResp3.setBackgroundResource(R.drawable.botonestrivia);
            btnResp3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnResp1.setEnabled(false);
                    btnResp2.setEnabled(false);
                    btnResp3.setEnabled(false);
                    btnResp4.setEnabled(false);
                    if (preguntaList.get(index).getRespuesta().get(2).getValor()) {
                        correctas++;
                        correctIncorrectSound(R.raw.correct);
                        btnResp3.setBackgroundColor(Color.GREEN);
                    } else {
                        incorrectas++;
                        correctIncorrectSound(R.raw.incorrect);
                        btnResp3.setBackgroundColor(Color.RED);
                    }
                    indexPregunta++;
                    CountDownTimer count = new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            btnResp1.setEnabled(true);
                            btnResp2.setEnabled(true);
                            btnResp3.setEnabled(true);
                            btnResp4.setEnabled(true);
                            juega(preguntaList, indexPregunta);
                        }
                    }.start();
                }
            });
            btnResp4.setText(preguntaList.get(index).getRespuesta().get(3).getTexto());
            btnResp4.setBackgroundResource(R.drawable.botonestrivia);
            btnResp4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btnResp1.setEnabled(false);
                    btnResp2.setEnabled(false);
                    btnResp3.setEnabled(false);
                    btnResp4.setEnabled(false);
                    if (preguntaList.get(index).getRespuesta().get(3).getValor()) {
                        correctas++;
                        correctIncorrectSound(R.raw.correct);
                        btnResp4.setBackgroundColor(Color.GREEN);
                    } else {
                        incorrectas++;
                        correctIncorrectSound(R.raw.incorrect);
                        btnResp4.setBackgroundColor(Color.RED);
                    }
                    indexPregunta++;
                    CountDownTimer count = new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long l) {

                        }

                        @Override
                        public void onFinish() {
                            btnResp1.setEnabled(true);
                            btnResp2.setEnabled(true);
                            btnResp3.setEnabled(true);
                            btnResp4.setEnabled(true);
                            juega(preguntaList, indexPregunta);
                        }
                    }.start();
                }
            });
        } else {
            menu();
        }
    }

    private void menu() {
        crono = false;
        Intent intent = new Intent(TriviaActivity.this,
                GameOverTriviaActivity.class);
        intent.putExtra("correctas", correctas);
        intent.putExtra("incorrectas", incorrectas);
        startActivity(intent);
        finish();
    }

    private void correctIncorrectSound(int sound) {
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(this, sound);
        mediaPlayer.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (crono1) {
            countDownTimer1.cancel();
        }
        if (crono) {
            countDownTimer.cancel();
        }
        if (!pressBack) {
            finish();
        }
    }

    /*
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void initializeCrono() {
        Intent intent = new Intent(TriviaActivity.this,
                GamesActivity.class);
        startActivity(intent);
        finish();
    }
    */

    @Override
    public void onBackPressed() {
        pressBack = true;
        Intent intent = new Intent(TriviaActivity.this,
                GamesActivity.class);
        startActivity(intent);
        finish();
    }
}