package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.covintena.interfaces.categoriasApi;
import com.example.covintena.interfaces.preguntasApi;
import com.example.covintena.model.Categoria;
import com.example.covintena.model.Pregunta;
import com.example.covintena.model._respuesta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeadsUpActivity extends AppCompatActivity {

    private TextView tvPregunta;
    private TextView tvRespuesta;
    private TextView tvCronometro;

    CountDownTimer countDownTimer;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    private Boolean aux=true;
    private int correctas = 0, incorrectas = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heads_up);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        tvPregunta = findViewById(R.id.tvPregunta);
        tvRespuesta = findViewById(R.id.tvRespuesta);
        tvCronometro = findViewById(R.id.tvCronometro);

        if (sensor == null){
            finish();
        }

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
                                "Póntelo en la frente \n%d",
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
                        activateSensor(preguntaList);
                    }
                }.start();
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {

            }
        });

    }

    private void menu(){
        stopSensor();
        Intent intent = new Intent(HeadsUpActivity.this,
                GameOverHeadsUpActivity.class);
        intent.putExtra("correctas", correctas);
        intent.putExtra("incorrectas", incorrectas);
        startActivity(intent);
    }

    private void activateSensor(final List<Pregunta> preguntaList) {
        if (preguntaList != null) {
            //Randomizar el listado de palabras
            Collections.shuffle(preguntaList, new Random());
            tvPregunta.setText(preguntaList.get(0).getPregunta());

            for (int i = 0; i < 4; i++){
                if (preguntaList.get(0).getRespuesta().get(i).getValor()){
                    tvRespuesta.setText(preguntaList.get(0).getRespuesta().get(i).getTexto());
                    break;
                }
            }

            //Evento de sensor
            sensorEventListener = new SensorEventListener() {
                int i = 0;

                @Override
                public void onSensorChanged(SensorEvent event) {
                    float z = event.values[2];
                    //Evento que se desata al mover el celular hacia abajo
                    if (z < -5 && aux) {
                        aux = false;
                        correctas++;
                        //correctSound();
                    }
                    //Evento que se desata al mover el celular hacia arriba
                    if (z > 5 && aux) {
                        aux = false;
                        incorrectas++;
                        //incorrectSound();
                    }
                    //Reinicio de variables
                    if (!aux && z < 2 && z > -2) {
                        i++;
                        if (i < preguntaList.size()) {
                            tvPregunta.setText(preguntaList.get(i).getPregunta());
                            for (int j = 0; j < 4; j++) {
                                if (preguntaList.get(i).getRespuesta().get(j).getValor()) {
                                    tvRespuesta.setText(preguntaList.get(i).getRespuesta().get(j)
                                            .getTexto());
                                }
                                aux = true;
                            }
                        }else{
                            countDownTimer.onFinish();
                        }
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
            startSensor();
        }
    }

    private void startSensor() {
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stopSensor() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        countDownTimer.onFinish();
        finish();
    }
}
