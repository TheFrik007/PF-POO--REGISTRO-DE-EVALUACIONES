package com.empresa.proyecto.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Evaluacion {
    private int idEvaluacion;
    private int idLlamada;
    private Date fecha;
    private BigDecimal notaFinal;
    private String comentarios;
    private List<Respuesta> respuestas;
    private String nombreCliente;
    private String nombreEvaluador;
    private Llamada llamada;

    public Evaluacion(int idEvaluacion, Llamada llamada, Date fecha, BigDecimal notaFinal, String comentarios) {
        this.idEvaluacion = idEvaluacion;
        this.llamada = llamada;
        this.fecha = fecha;
        this.notaFinal = notaFinal;
        this.comentarios = comentarios;
    }

    // Getters y setters

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public int getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(int idLlamada) {
        this.idLlamada = idLlamada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(BigDecimal notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
    
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getNombreEvaluador() {
        return nombreEvaluador;
    }

    public void setNombreEvaluador(String nombreEvaluador) {
        this.nombreEvaluador = nombreEvaluador;
    }
    
    public Llamada getLlamada() {
        return llamada;
    }
    public void setLlamada(Llamada llamada) {
        this.llamada = llamada;
    }
}
