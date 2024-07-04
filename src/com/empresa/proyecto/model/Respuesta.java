package com.empresa.proyecto.model;

public class Respuesta {
    private int idRespuesta;
    private Evaluacion evaluacion;
    private Pregunta pregunta;
    private String respuesta;

    // Constructores, getters y setters
    public Respuesta() {}

    public Respuesta(int idRespuesta, Evaluacion evaluacion, Pregunta pregunta, String respuesta) {
        this.idRespuesta = idRespuesta;
        this.evaluacion = evaluacion;
        this.pregunta = pregunta;
        this.respuesta = respuesta;
    }

    // Getters y Setters
    public int getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(int idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        if (respuesta.equals("Sí") || respuesta.equals("No") || respuesta.equals("N/A")) {
            this.respuesta = respuesta;
        } else {
            throw new IllegalArgumentException("Respuesta debe ser 'Sí', 'No' o 'N/A'");
        }
    }
}
