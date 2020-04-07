package com.example.covintena.interfaces;

import com.example.covintena.model.Pregunta;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


public interface preguntasApi {

    @GET("preguntas")
    Call<List<Pregunta>> getPreguntas();
}
