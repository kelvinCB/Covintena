package com.example.covintena.model;

import java.util.List;

public class Pregunta {

    private String _id;
    private String pregunta;
    private List<_respuesta> respuesta;
    private String nota;

    public String get_id() {
        return _id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public List<_respuesta> getRespuesta() {
        return respuesta;
    }

    public String getNota() {
        return nota;
    }
}
