package com.empresa.proyecto.model;

import java.util.List;

public class Bloque {
    private int idBloque;
    private String nombreBloque;
    private List<Pregunta> preguntas;

    // Constructor, getters y setters

    public Bloque(int idBloque, String nombreBloque, List<Pregunta> preguntas) {
        this.idBloque = idBloque;
        this.nombreBloque = nombreBloque;
        this.preguntas = preguntas;
    }

    public int getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(int idBloque) {
        this.idBloque = idBloque;
    }

    public String getNombreBloque() {
        return nombreBloque;
    }

    public void setNombreBloque(String nombreBloque) {
        this.nombreBloque = nombreBloque;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}

