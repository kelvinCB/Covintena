package com.example.covintena.interfaces;

import com.example.covintena.model.Recomendacion;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface recomendacionesApi {

    @GET("recomendaciones")
    Call<List<Recomendacion>> getRecomendaciones();

}
