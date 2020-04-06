package com.example.covintena.interfaces;

import com.example.covintena.model.SubCategoria;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface subCategoriaApi {

    @GET("subCategorias")
    Call<List<SubCategoria>> getSubCategoria();

}
