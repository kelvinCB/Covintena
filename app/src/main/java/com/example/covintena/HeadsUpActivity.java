package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.example.covintena.interfaces.preguntasApi;
import com.example.covintena.model.Pregunta;

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
    private TextView tvFondo;

    CountDownTimer countDownTimer, countDownTimer1;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    private Boolean aux;
    private int correctas, incorrectas;
    private Boolean crono, crono1, pressBack, sensorStop, sensorStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heads_up);

        crono = crono1 = false;
        pressBack=false;
        sensorStop= sensorStart = false;
        correctas=0;
        incorrectas=0;
        aux=true;

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        tvPregunta = findViewById(R.id.tvPregunta);
        tvRespuesta = findViewById(R.id.tvRespuesta);
        tvCronometro = findViewById(R.id.tvCronometro);
        tvFondo = findViewById(R.id.fondo);

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
                countDownTimer1 = new CountDownTimer(5000,
                        1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        tvPregunta.setText(String.format(Locale.getDefault(),
                                "Póntelo en la frente \n%d",
                                millisUntilFinished / 1000L));
                        crono1=true;
                    }

                    @Override
                    public void onFinish() {
                        crono1=false;
                        countDownTimer = new CountDownTimer(60000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                tvCronometro.setText(String.format(Locale.getDefault(), "%d",
                                        millisUntilFinished / 1000));
                                crono=true;
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

    private void activateSensor(final List<Pregunta> preguntaList) {
        crono = true;
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
                        tvFondo.setBackgroundResource(R.color.verde);
                        aux = false;
                        correctas++;
                        correctSound();
                    }
                    //Evento que se desata al mover el celular hacia arriba
                    if (z > 5 && aux) {
                        tvFondo.setBackgroundResource(R.color.rojo);
                        aux = false;
                        incorrectas++;
                        incorrectSound();
                    }
                    //Reinicio de variables
                    if (!aux && z < 2 && z > -2) {
                        i++;
                        //Poner fondo de pantalla normal
                        tvFondo.setBackgroundResource(R.color.blanco_t);

                        //
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

    private void menu(){
        crono = false;
        stopSensor();
        Intent intent = new Intent(HeadsUpActivity.this,
                GameOverHeadsUpActivity.class);
        intent.putExtra("correctas", correctas);
        intent.putExtra("incorrectas", incorrectas);
        startActivity(intent);
        finish();
    }

    private void startSensor() {
        sensorStop=false;
        sensorStart=true;
        sensorManager.registerListener(sensorEventListener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stopSensor() {
        sensorStop=true;
        sensorStart=false;
        sensorManager.unregisterListener(sensorEventListener);
    }


    private void correctSound(){
        //Sonido cuando es correcto
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.correct);
        mediaPlayer.start();

    }

    private void incorrectSound(){
        //Sonido cuando es incorrecto
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.incorrect);
        mediaPlayer.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (crono1){
            countDownTimer1.cancel();
        }
        if (crono){
            countDownTimer.cancel();
        }
        if(!sensorStop && sensorStart){
            stopSensor();
        }
        if (!pressBack){
            finish();
        }
    }

    /*
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void initializeCrono() {
        Intent intent = new Intent(HeadsUpActivity.this,
                GamesActivity.class);
        startActivity(intent);
        finish();
    }
    */

    @Override
    public void onBackPressed() {
        pressBack=true;
        Intent intent = new Intent(HeadsUpActivity.this,
                GamesActivity.class);
        startActivity(intent);
        finish();
    }
}
