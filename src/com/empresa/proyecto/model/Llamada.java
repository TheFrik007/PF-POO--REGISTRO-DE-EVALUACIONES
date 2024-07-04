package com.empresa.proyecto.model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Llamada {
    private int idLlamada;
    private String nombreCliente;
    private String nombreEvaluador;
    private String numeroLlamada;
    private Date fechaLlamada;
    private String resumenLlamada;

    // Constructores, getters y setters
    public Llamada() {}

    public Llamada(int idLlamada, String nombreCliente, String nombreEvaluador, String numeroLlamada, Date fechaLlamada, String resumenLlamada) {
        this.idLlamada = idLlamada;
        this.nombreCliente = nombreCliente;
        this.nombreEvaluador = nombreEvaluador;
        this.numeroLlamada = numeroLlamada;
        this.fechaLlamada = fechaLlamada;
        this.resumenLlamada = resumenLlamada;
    }

    // Getters y Setters
    public int getIdLlamada() {
        return idLlamada;
    }

    public void setIdLlamada(int idLlamada) {
        this.idLlamada = idLlamada;
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

    public String getNumeroLlamada() {
        return numeroLlamada;
    }

    public void setNumeroLlamada(String numeroLlamada) {
        this.numeroLlamada = numeroLlamada;
    }

    public Date getFechaLlamada() {
        return fechaLlamada;
    }

public void setFechaLlamada(Date fechaLlamada) {
    this.fechaLlamada = fechaLlamada;
}

    public String getResumenLlamada() {
        return resumenLlamada;
    }

    public void setResumenLlamada(String resumenLlamada) {
        this.resumenLlamada = resumenLlamada;
    }
}
