package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.covintena.interfaces.subCategoriasApi;
import com.example.covintena.model.SubCategoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubCategoriaActivity extends AppCompatActivity {

    TextView textView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categoria);

        String idCategoria = "", nombreCategoria = "";
        idCategoria += getIntent().getExtras().getString("idCategoria");
        nombreCategoria += getIntent().getExtras().getString("nombreCategoria");

        textView = findViewById(R.id.tvCategoria);

        linearLayout = findViewById(R.id.contenedordato1);

        textView.setText(nombreCategoria);

        getSubCategorias(idCategoria);

    }

    private void getSubCategorias(final String idCategoria){
        //Consultar a la api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-hackathon.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final subCategoriasApi subCategApi = retrofit.create(subCategoriasApi.class);
        Call<List<SubCategoria>> cal1 = subCategApi.getSubCategoria();
        cal1.enqueue(new Callback<List<SubCategoria>>() {
            @Override
            public void onResponse(Call<List<SubCategoria>> call,
                                   Response<List<SubCategoria>> response) {
                //Si la respuesta no es satisfactoria
                if (!response.isSuccessful()){
                    return;
                }

                List<SubCategoria> subCategoriasList = response.body();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                for (final SubCategoria subCategoria: subCategoriasList){
                    if (subCategoria.getCategoria().get_id().equals(idCategoria)){
                        Button button = new Button(getApplicationContext());
                        button.setText(subCategoria.getNombre());
                        //poner background al boton
                        //button.setBackgroundResource(R.drawable.botones3);
                        button.setTextSize(50);
                        //color del texto del boton
                        //button.setTextColor(getApplication().getResources()
                        // .getColor(R.color.blanco));
                        button.setHeight(170);
                        button.setLayoutParams(lp);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent(SubCategoriaActivity.this,
                                        RecomendacionesActivity.class);
                                intent.putExtra("idSubCategoria", subCategoria.get_id());
                                intent.putExtra("nombreSubCategoria", subCategoria.getNombre());
                                startActivity(intent);

                            }
                        });
                        linearLayout.addView(button);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<SubCategoria>> call, Throwable t) {

            }
        });


    }
}
