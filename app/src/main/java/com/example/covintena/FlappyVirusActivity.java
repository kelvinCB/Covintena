package com.example.covintena;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.content.Intent;
public class FlappyVirusActivity extends View {


    // Canvas
    private int canvasWidth;
    private int canvasHeight;

    // Bird
    private Bitmap bird[] = new Bitmap[2];
    private int birdX = 10;
    private int birdY;
    private int birdSpeed;

    //private Bitmap images;
    private Bitmap virus;
    private Bitmap jabon;

    private Bitmap raya_calle;

    //Position and speed of jabon Bitmap
    private int jabonLeftX;
    private int jabonLeftY;
    private int jabonLeftSpeed = 15;

    //Position and speed of Virus Bitmap
    private int virusLeftX;
    private int virusLeftY;
    private int virusLeftSpeed = 20;

    // Background
    private Bitmap fondoImage;

    // Edificios
    private Bitmap edificio1;
    private Bitmap edificio2;

    // Score
    private Paint scorePaint = new Paint();
    private int score;

    // Level
    private Paint levelPaint = new Paint();

    // Life
    private Bitmap life[] = new Bitmap[2];
    private int life_count;

    // Status Check
    private boolean touch_flg = false;

    public FlappyVirusActivity(Context context) {
        super(context);

        //Imagenes del muchacho con cohete y sin cohete
        bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.nuevo_no_volando);
        bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.nuevo_volando);

        //Imagenes del jabon y del virus
        jabon = BitmapFactory.decodeResource(getResources(), R.drawable.jabon);
        virus = BitmapFactory.decodeResource(getResources(), R.drawable.coronavirs);

       // Imagen de la raya de la calle
        raya_calle = BitmapFactory.decodeResource(getResources(), R.drawable.raya_calle);

        //Imagen del fondo principal donde vuela el muchacho
        fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.fondoflappy);

        //Imagen de los edificios
          edificio1 = BitmapFactory.decodeResource(getResources(),R.drawable.edificio1);
          edificio2 = BitmapFactory.decodeResource(getResources(),R.drawable.edificio2);

        //Imagenes de los corazones
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);

        //Tamano, color y fuente del score
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(36);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        //Tamaño, color y fuente del nivle
        levelPaint.setColor(Color.BLACK);
        levelPaint.setTextSize(36);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        // First position.
        birdY = 500;
        score = 0;
        life_count = 3;

    }



    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(fondoImage, 0, 0, null); // Hasta donde llega drawImage de fondo

        canvas.drawBitmap(edificio2, -350,720,null); // Posicion del edificio 2
        canvas.drawBitmap(edificio1, 160,720,null); // Posicion del edificio 1
        canvas.drawBitmap(edificio2, 630,720,null); // Posicion del edificio 2

        // Bird
        int minBirdY = bird[0].getHeight(); // Altura donde inicia el pajarito
        int maxBirdY = canvasHeight - bird[0].getHeight() * 2; //Hasta donde llega a caer el pajarito //5 en mi cel

        birdY += birdSpeed;
        if (birdY < minBirdY) birdY = minBirdY;
        if (birdY > maxBirdY) birdY = maxBirdY;
        birdSpeed += 2;

        if (touch_flg) {
            // Flap wings.
            canvas.drawBitmap(bird[1], birdX, birdY, null);
            touch_flg = false;
        } else {
            canvas.drawBitmap(bird[0], birdX, birdY, null);
        }


        // Jabon toca muchacho
        jabonLeftX -= jabonLeftSpeed;
        if (hitCheck(jabonLeftX, jabonLeftY)) {
            score += 10;
            jabonLeftX = -100;
        }
        if (jabonLeftX < 0) {
            jabonLeftX = canvasWidth + 20;
            jabonLeftY = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }

        //posicion del jabon
        canvas.drawBitmap(jabon, jabonLeftX, jabonLeftY, null);


        // Virus toca muchacho
        virusLeftX -= virusLeftSpeed;
        if (hitCheck(virusLeftX, virusLeftY)) {
            virusLeftX = -100;
            life_count--;
            if (life_count == 0) {//Cuando no quedan vidas
                // Game Over

                //Log.v("Message", "Game Over"); //Aqui llamaria una vista game over con un sonido de gameover
            }
        }

        // Cuando no hay virus genera uno
        if (virusLeftX < 0) { // 0 es la posicion en x que hace desaparecer la bolita
            virusLeftX = canvasWidth + 200;
            virusLeftY = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }
        //Posicion del virus
        canvas.drawBitmap(virus, virusLeftX, virusLeftY, null);


        // Score
        canvas.drawText("Puntos : " + score, 20, 60, scorePaint);//Dibuja el score casi al inicio

        // Level
        canvas.drawText("Nivel.1", (canvasWidth / 2) , 60, levelPaint);// dibuja el nivel en el centro

        // Life
        for (int i = 0; i < 3; i++) {
            int x = (int) ((canvasWidth-300) + life[0].getWidth() * 1.5 * i);// canvasWidth es donde comienza el primer corazon de vida
            int y = 30;

            //Donde dibujar las vidas
            if (i < life_count) {
                canvas.drawBitmap(life[0], x, y-5, null); // el 70 posicion de las vidas
            } else {
                canvas.drawBitmap(life[1], x, y-5, null);
            }
        }
    }

    public boolean hitCheck(int x, int y) {
        if (birdX < x && x < (birdX + bird[0].getWidth()) &&
                birdY < y && y < (birdY + bird[0].getHeight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch_flg = true;
            birdSpeed = -25; //Gravedad del pajarito
        }
        return true;
    }

}
