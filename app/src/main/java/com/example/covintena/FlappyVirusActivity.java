package com.example.covintena;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import android.view.View;

public class FlappyVirusActivity extends View {

    // Canvas
    private int canvasWidth;
    private int canvasHeight;

    // Calle Height & Width

    private double calleHeight;
    private double calleWidth;

    // Bird
    private Bitmap jerry[] = new Bitmap[2];
    private int jerryX = 5; // Distancia horizontal donde inicia Jerry
    private int jerryY;
    private int jerrySpeed;

    //private Bitmap images;
    private Bitmap virus[] = new Bitmap[2];
    private Bitmap jabon[] = new Bitmap[2];
    private Bitmap raya_calle;
    private Bitmap nube;

    //Position and speed of jabon0 Bitmap
    private int jabonLeftX;
    private int jabonLeftY;
    private int jabonLeftSpeed = 10;

    //Position and speed of jabon1 Bitmap
    private int jabonLeftX1;
    private int jabonLeftY1;
    private int jabonLeftSpeed1 = 15;

    //Position and speed of Virus Bitmap
    private int virusLeftX;
    private int virusLeftY;
    private int virusLeftSpeed = 10;

    //Position and speed of Virus Bitmap
    private int virusLeftX1;
    private int virusLeftY1;
    private int virusLeftSpeed1 = 15;

    //Position and speed of raya1 Bitmap
    private int rayaLeftX1;
    private int rayaLeftY1 = -200; // para que la calle no aparezca arriba
    private int rayaLeftSpeed1 = 15;

    //Position and speed of nube Bitmap
    private int nubeLeftX;
    private int nubeLeftY;
    private int nubeLeftSpeed = 3;

    // Background
    private Bitmap fondoImage;
    private Bitmap scaledFondoImagen;

    // Score
    private Paint scorePaint = new Paint();
    private static int score;
    public static int total_score;

    // Level
    private Paint levelPaint = new Paint();
    private static  int level = 1;

    // Life
    private Bitmap life[] = new Bitmap[2];
    private static int life_count;

    // Status Check
    private boolean touch_flg = false;

    //Sonidos
    public static boolean sonidoTos = false;
    public static boolean sonidoPuntos = false;

    public FlappyVirusActivity(Context context) {
        super(context);

        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels;


        //Imagen del fondo principal donde vuela el muchacho

        fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.dia);

        scaledFondoImagen = Bitmap.createScaledBitmap(fondoImage,widthPixels,heightPixels,false);

        //Imagenes del muchacho con cohete y sin cohete
        jerry[0] = BitmapFactory.decodeResource(getResources(), R.drawable.nuevo_no_volando);
        jerry[1] = BitmapFactory.decodeResource(getResources(), R.drawable.nuevo_volando);

        //Imagenes del jabon y del virus
        jabon[0] = BitmapFactory.decodeResource(getResources(), R.drawable.jabon);
        virus[0] = BitmapFactory.decodeResource(getResources(), R.drawable.coronavirs);
        jabon[1] = BitmapFactory.decodeResource(getResources(), R.drawable.jabon);
        virus[1] = BitmapFactory.decodeResource(getResources(), R.drawable.coronavirs);

        // Imagen de la raya de la calle
          raya_calle = BitmapFactory.decodeResource(getResources(), R.drawable.raya_calle);

        // Imagen de las nubes
        nube = BitmapFactory.decodeResource(getResources(), R.drawable.nube);
        nubeLeftX = (widthPixels/2)-(nube.getWidth()/2); // Donde inicia horizontalmente la nube

        //Imagenes de los corazones
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);

        //Tamano, color y fuente del score
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize((life[0].getHeight()/2)+5); // Tamano de los puntos y + 5 para terminar de ajustar
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        scorePaint.setAntiAlias(true);

        //Tamaño, color y fuente del nivle
        levelPaint.setColor(Color.BLACK);
        levelPaint.setTextSize((life[0].getHeight()/2)+5); // Tamano de los niveles y + 5 para terminar de ajustar
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        // First position.
        jerryY = 300; //Altura donde inicia Jerry
        score = 0;
        life_count = 3;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        // Altura calle
        calleHeight  = canvasHeight * 0.18; // donde inicia la la calle es la altura total menos lo que ella ocupa (0.18) el 18% de la pantlla

        // Altura de la nube


        // Jerry
        int minBirdY = jerry[0].getHeight(); // Hasta donde llega Jerry de alto
        int maxJerryY = (int) (canvasHeight - (calleHeight + (calleHeight/2)-40)); //Hasta donde llega a caer  Jerry ()-40 para terminar de ajustar

        canvas.drawBitmap(scaledFondoImagen, 0, 0, null); // Hasta donde llega drawImage de fondo

        jerryY += jerrySpeed;
        if (jerryY < minBirdY) jerryY = minBirdY;
        if (jerryY > maxJerryY) jerryY = maxJerryY;
        jerrySpeed += 2;

        if (touch_flg) {
            // Flap wings.
            canvas.drawBitmap(jerry[1], jerryX, jerryY, null);
            touch_flg = false;
        } else {
            canvas.drawBitmap(jerry[0], jerryX, jerryY, null);
        }


        //Animacion de la raya de la calle

        rayaLeftX1 -= rayaLeftSpeed1;

        //Cuando la raya llega al final de la pantalla
        if (rayaLeftX1 < -raya_calle.getWidth()) { //Hace desaparecer la raya de la calle
            rayaLeftX1 = canvasWidth + 20;
            rayaLeftY1 = (int) (canvasHeight - (calleHeight/2)); //posicion de la raya de la calle (height - calleheigth /2)
        }

        //posicion de la raya de la calle1
        canvas.drawBitmap(raya_calle, rayaLeftX1, rayaLeftY1, null);


        //Animacion de la nube

         nubeLeftX -= nubeLeftSpeed; // Como la nube avanza a la izquierda comiendoce el ancho del canvas a la velocidad de los pixeles de nubeLeftSpeed

        //Cuando la nube0 llega al final de la pantalla
        if (nubeLeftX < -nube.getWidth()) { // Hacer desaparecer y volver aparecer la nube
            nubeLeftX = canvasWidth + 20;
            nubeLeftY =  life[0].getHeight(); //Posicion altura de la nube
        }

        //posicion de la nube0
        canvas.drawBitmap(nube, nubeLeftX, nubeLeftY, null);

        // Jabon0 toca muchacho
        jabonLeftX -= jabonLeftSpeed;
        if (hitCheck(jabonLeftX, jabonLeftY)) {
            score += 10;
            jabonLeftX = -100;
            //Poner Sonido de puntos
            ContenedorFlappyActivity.sonidoPuntos = true;
            sonidoPuntos = ContenedorFlappyActivity.sonidoPuntos;
            ponerImagen();
        }
        if (jabonLeftX < 0) {
            jabonLeftX = canvasWidth + 20;
            jabonLeftY = (int) Math.floor(Math.random() * (maxJerryY - minBirdY)) + minBirdY;
        }

        //posicion del jabon0
        canvas.drawBitmap(jabon[0], jabonLeftX, jabonLeftY, null);

        // Jabon1 toca muchacho
        jabonLeftX1 -= jabonLeftSpeed1;
        if (hitCheck(jabonLeftX1, jabonLeftY1)) {
            score += 10;
            jabonLeftX1 = -100;
            //Poner Sonido de puntos
            ContenedorFlappyActivity.sonidoPuntos = true;
            sonidoPuntos = ContenedorFlappyActivity.sonidoPuntos;
            ponerImagen();
        }
        if (jabonLeftX1 < 0) {
            jabonLeftX1 = canvasWidth + 20;
            jabonLeftY1 = (int) Math.floor(Math.random() * (maxJerryY - minBirdY)) + minBirdY;
        }

        //posicion del jabon1
        canvas.drawBitmap(jabon[1], jabonLeftX1, jabonLeftY1, null);


        // Virus0 toca Jerry
        virusLeftX -= virusLeftSpeed;
        if (hitCheck(virusLeftX, virusLeftY)) {
            virusLeftX = -100;
            life_count--;
            ContenedorFlappyActivity.sonidoTos = true;
            sonidoTos = ContenedorFlappyActivity.sonidoTos;
            if (life_count <= 0) {//Cuando no quedan vidas
                // Game Over
              llamarGameOver();

            }
        }

        // Cuando no hay virus0 genera uno
        if (virusLeftX < 0) { // 0 es la posicion en x que hace desaparecer la imagen
            virusLeftX = canvasWidth + 200;
            virusLeftY = (int) Math.floor(Math.random() * (maxJerryY - minBirdY)) + minBirdY;
        }
        //Posicion del virus0
        canvas.drawBitmap(virus[0], virusLeftX, virusLeftY, null);

        // Virus1 toca Jerry
        virusLeftX1 -= virusLeftSpeed1;
        if (hitCheck(virusLeftX1, virusLeftY1)) {
            virusLeftX1 = -100;
            life_count--;
            ContenedorFlappyActivity.sonidoTos = true;
            sonidoTos = ContenedorFlappyActivity.sonidoTos;
            if (life_count <= 0) {//Cuando no quedan vidas
                // Game Over
                llamarGameOver();

            }
        }

        // Cuando no hay virus1 genera uno
        if (virusLeftX1 <= 0) { // 0 es la posicion en x que hace desaparecer la imagen
            virusLeftX1 = canvasWidth + 200;
            virusLeftY1 = (int) Math.floor(Math.random() * (maxJerryY - minBirdY)) + minBirdY;
        }
        //Posicion del virus
        canvas.drawBitmap(virus[1], virusLeftX1, virusLeftY1, null);

        // Score
        canvas.drawText("Puntos : " + score, 20, 60, scorePaint);//Dibuja el score casi al inicio

        // Level
        canvas.drawText("Nivel."+level, (canvasWidth / 2) , 60, levelPaint);// dibuja el nivel en el centro

        // Life
        for (int i = 0; i < 3; i++) {
            int lifeSize = life[0].getWidth();
            double lifeSpace = 1.5;
            int lifeCant = 3;

            int x = (int) ((canvasWidth-(lifeSize * lifeSpace * lifeCant)) + life[0].getWidth() * lifeSpace * i); // posicion de las vidas
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
        if (jerryX < x && x < (jerryX + jerry[0].getWidth()) &&
                jerryY < y && y < (jerryY + jerry[0].getHeight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch_flg = true;
            jerrySpeed = -25; //Gravedad de jerry
        }
        return true;
    }

    public void ponerImagen (){

        if(score>=0 && score<50){
            fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.dia);
        }else if(score>=50 && score<100){
            fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.noche);
        } else if(score>=100 && score<150){
            fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.horizonte);
        }else if(score>=150 && score<200){
            fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.montana);
        }else if(score>=200 && score<250){
            fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.dasierto);
        }else if (score>=250 && score<300){
            fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.fondoflappy);
        }else if (score>=300 && score<350){
            score = 0;
            level++;
            fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.dia);
            cambiarVelocidadJuego(level);
        }else{
            fondoImage = BitmapFactory.decodeResource(getResources(), R.drawable.dasierto);
            System.out.println("No encuentro la foto de fondo");
        }

        scaledFondoImagen = Bitmap.createScaledBitmap(fondoImage,canvasWidth,canvasHeight,false);

    }

    public void cambiarVelocidadJuego(int level){
        if(level == 2) {
            jabonLeftSpeed = 20;
            jabonLeftSpeed1 = 25;
            virusLeftSpeed = 20;
            virusLeftSpeed1 = 25;
            rayaLeftSpeed1 = 25;
        }else if(level == 3){
            jabonLeftSpeed = 25;
            jabonLeftSpeed1 = 30;
            virusLeftSpeed = 25;
            virusLeftSpeed1 = 30;
            rayaLeftSpeed1 = 30;
        }else if(level == 4){
            jabonLeftSpeed = 30;
            jabonLeftSpeed1 = 35;
            virusLeftSpeed = 30;
            virusLeftSpeed1 = 35;
            rayaLeftSpeed1 = 35;
        }else if(level == 5){
            jabonLeftSpeed = 35;
            jabonLeftSpeed1 = 40;
            virusLeftSpeed = 35;
            virusLeftSpeed1 = 40;
            rayaLeftSpeed1 = 40;
        }
    }

    public static int totalScore(){

        total_score = ((level*300) + score)-300;

        return  total_score;

    }

    private void llamarGameOver() {

        Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
        gameOverIntent.putExtra("score", FlappyVirusActivity.totalScore());
        gameOverIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        getContext().startActivity(gameOverIntent);
        MediaPlayer sb = MediaPlayer.create(getContext(), R.raw.coronado2);
        sb.start();
        level = 1;

    }

}