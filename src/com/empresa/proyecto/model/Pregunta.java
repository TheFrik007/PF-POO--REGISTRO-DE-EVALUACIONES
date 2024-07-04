package com.empresa.proyecto.model;

public class Pregunta {
    private int idPregunta;
    private String textoPregunta;
    private Bloque bloque;

    // Constructor, getters y setters

    public Pregunta(int idPregunta, String textoPregunta, Bloque bloque) {
        this.idPregunta = idPregunta;
        this.textoPregunta = textoPregunta;
        this.bloque = bloque;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public Bloque getBloque() {
        return bloque;
    }

    public void setBloque(Bloque bloque) {
        this.bloque = bloque;
    }
}
