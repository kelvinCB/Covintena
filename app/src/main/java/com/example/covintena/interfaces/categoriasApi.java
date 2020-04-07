package com.example.covintena.interfaces;

import com.example.covintena.model.Categoria;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface categoriasApi {

    @GET("categorias")
    Call<List<Categoria>> getCategoria();

}
