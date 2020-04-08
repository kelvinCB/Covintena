package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.params.ColorSpaceTransform;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.covintena.interfaces.preguntasApi;
import com.example.covintena.model.Pregunta;

import java.util.List;
import java.util.Locale;

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
    CountDownTimer countDownTimer;
    private int correctas = 0, incorrectas = 0, indexPregunta = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        tvPregunta = findViewById(R.id.tvPregunta);
        tvCronometro = findViewById(R.id.tvCronometro);
        btnResp1 = findViewById(R.id.btnResp1);
        btnResp2 = findViewById(R.id.btnResp2);
        btnResp3 = findViewById(R.id.btnResp3);
        btnResp4 = findViewById(R.id.btnResp4);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-hackathon.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        preguntasApi pregApi = retrofit.create(preguntasApi.class);
        Call<List<Pregunta>> call = pregApi.getPreguntas();
        call.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                if (!response.isSuccessful()){
                    return;
                }

                final List<Pregunta> preguntaList = response.body();
                CountDownTimer countDownTimer1 = new CountDownTimer(5000,
                        1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvPregunta.setText(String.format(Locale.getDefault(),
                                "Prepárate!\n%d",
                                millisUntilFinished / 1000L));
                    }

                    @Override
                    public void onFinish() {
                        countDownTimer = new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                tvCronometro.setText(String.format(Locale.getDefault(), "%d",
                                        millisUntilFinished / 1000));
                            }

                            @Override
                            public void onFinish() {
                                menu();
                            }
                        }.start();
                        juega(preguntaList, 0);
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {

            }
        });

    }

    private void juega(final List<Pregunta> preguntaList, final int index){
        tvPregunta.setText(preguntaList.get(index).getPregunta());
        btnResp1.setText(preguntaList.get(index).getRespuesta().get(0).getTexto());
        btnResp1.setBackgroundColor(Color.LTGRAY);
        btnResp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preguntaList.get(index).getRespuesta().get(0).getValor()) {
                    correctas++;
                    btnResp1.setBackgroundColor(Color.GREEN);
                } else {
                    incorrectas++;
                    btnResp1.setBackgroundColor(Color.RED);
                }
                indexPregunta++;
                btnResp1.setBackgroundColor(Color.RED);
                CountDownTimer count = new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        juega(preguntaList, indexPregunta);
                    }
                }.start();
            }
        });
        btnResp2.setText(preguntaList.get(index).getRespuesta().get(1).getTexto());
        btnResp2.setBackgroundColor(Color.LTGRAY);
        btnResp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preguntaList.get(index).getRespuesta().get(1).getValor()) {
                    correctas++;
                    btnResp2.setBackgroundColor(Color.GREEN);
                } else {
                    incorrectas++;
                    btnResp2.setBackgroundColor(Color.RED);
                }
                indexPregunta++;
                CountDownTimer count = new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        juega(preguntaList, indexPregunta);
                    }
                }.start();
            }
        });
        btnResp3.setText(preguntaList.get(index).getRespuesta().get(2).getTexto());
        btnResp3.setBackgroundColor(Color.LTGRAY);
        btnResp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preguntaList.get(index).getRespuesta().get(2).getValor()) {
                    correctas++;
                    btnResp3.setBackgroundColor(Color.GREEN);
                } else {
                    incorrectas++;
                    btnResp3.setBackgroundColor(Color.RED);
                }
                indexPregunta++;
                CountDownTimer count = new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        juega(preguntaList, indexPregunta);
                    }
                }.start();
            }
        });
        btnResp4.setText(preguntaList.get(index).getRespuesta().get(3).getTexto());
        btnResp4.setBackgroundColor(Color.LTGRAY);
        btnResp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (preguntaList.get(index).getRespuesta().get(3).getValor()) {
                    correctas++;
                    btnResp4.setBackgroundColor(Color.GREEN);
                } else {
                    incorrectas++;
                    btnResp4.setBackgroundColor(Color.RED);
                }
                indexPregunta++;
                CountDownTimer count = new CountDownTimer(1000, 1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        juega(preguntaList, indexPregunta);
                    }
                }.start();
            }
        });
    }

    private void menu(){
        Intent intent = new Intent(TriviaActivity.this,
                GameOverTriviaActivity.class);
        intent.putExtra("correctas", correctas);
        intent.putExtra("incorrectas", incorrectas);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.onFinish();
        finish();
    }

}
