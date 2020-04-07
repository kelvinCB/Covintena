package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.covintena.interfaces.recomendacionesApi;
import com.example.covintena.model.Recomendacion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecomendacionesActivity extends AppCompatActivity {

    TextView textView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomendaciones);

        String idSubCategoria = "", nombreSubCategoria = "";
        idSubCategoria += getIntent().getExtras().getString("idSubCategoria");
        nombreSubCategoria += getIntent().getExtras().getString("nombreSubCategoria");

        textView = findViewById(R.id.tvSubCategoria);
        linearLayout = findViewById(R.id.contenedordato1);
        textView.setText(nombreSubCategoria);

        getRecomendaciones(idSubCategoria);
    }

    private void getRecomendaciones(final String idSubCategoria){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-hackathon.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final recomendacionesApi subCategApi = retrofit.create(recomendacionesApi.class);
        Call<List<Recomendacion>> call = subCategApi.getRecomendaciones();
        call.enqueue(new Callback<List<Recomendacion>>() {
            @Override
            public void onResponse(Call<List<Recomendacion>> call,
                                   Response<List<Recomendacion>> response) {
                //Si la respuesta no es satisfactoria
                if (!response.isSuccessful()){
                    return;
                }

                List<Recomendacion> recomendacionList = response.body();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                for (Recomendacion recomendacion: recomendacionList){
                    if (recomendacion.getSubCategoria().get_id().equals(idSubCategoria)){
                        Button button = new Button(getApplicationContext());
                        button.setText(recomendacion.getRecomendacion());
                        //poner background al boton
                        //button.setBackgroundResource(R.drawable.botones3);
                        button.setTextSize(50);
                        //color del texto del boton
                        //button.setTextColor(getApplication().getResources()
                        // .getColor(R.color.blanco));
                        button.setHeight(170);
                        button.setLayoutParams(lp);
                        linearLayout.addView(button);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Recomendacion>> call, Throwable t) {

            }
        });
    }
}
