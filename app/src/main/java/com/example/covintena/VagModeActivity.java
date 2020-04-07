package com.example.covintena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.covintena.interfaces.categoriasApi;
import com.example.covintena.model.Categoria;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VagModeActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vag_mode);

        linearLayout = findViewById(R.id.contenedordato1);

        getCategorias();

    }

    private void getCategorias(){
        //Consultar a la api
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-hackathon.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        categoriasApi categApi = retrofit.create(categoriasApi.class);
        Call<List<Categoria>> call = categApi.getCategoria();
        call.enqueue(new Callback<List<Categoria>>() {
            @Override
            public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                //Si la respuesta no es satisfactoria
                if (!response.isSuccessful()){
                    return;
                }

                List<Categoria> categoriaList= response.body();
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                for (final Categoria categoria: categoriaList){
                    Button button = new Button(getApplicationContext());
                    button.setText(categoria.getNombre());
                    //button.setBackgroundResource(R.drawable.botones3); //poner background al boton
                    button.setTextSize(50);
                    //color del texto del boton
                    //button.setTextColor(getApplication().getResources().getColor(R.color.blanco));
                    button.setHeight(170);
                    button.setLayoutParams(lp);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(VagModeActivity.this,
                                    SubCategoriaActivity.class);
                            intent.putExtra("idCategoria", categoria.get_id());
                            intent.putExtra("nombreCategoria", categoria.getNombre());
                            startActivity(intent);
                        }
                    });
                    linearLayout.addView(button);
                }
            }

            @Override
            public void onFailure(Call<List<Categoria>> call, Throwable t) {

            }
        });




    }
}
