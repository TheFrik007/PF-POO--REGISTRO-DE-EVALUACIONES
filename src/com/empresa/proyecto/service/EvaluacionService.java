package com.empresa.proyecto.service;

import com.empresa.proyecto.dao.EvaluacionDAO;
import com.empresa.proyecto.dao.LlamadaDAO;
import com.empresa.proyecto.model.Evaluacion;
import com.empresa.proyecto.model.Llamada;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EvaluacionService {
    private EvaluacionDAO evaluacionDAO;
    private LlamadaDAO llamadaDAO;
    private CalculadorEvaluacion calculadorEvaluacion;

    public EvaluacionService(EvaluacionDAO evaluacionDAO, LlamadaDAO llamadaDAO, CalculadorEvaluacion calculadorEvaluacion) {
        this.evaluacionDAO = evaluacionDAO;
        this.llamadaDAO = llamadaDAO;
        this.calculadorEvaluacion = calculadorEvaluacion;
    }

    public Evaluacion obtenerEvaluacionPorId(int idEvaluacion) throws SQLException {
        return evaluacionDAO.obtenerEvaluacionPorId(idEvaluacion);
    }

    public List<Evaluacion> obtenerTodasEvaluacionesConLlamada() throws SQLException {
        return evaluacionDAO.obtenerTodasEvaluacionesConLlamada();
    }

    public void agregarEvaluacion(Evaluacion evaluacion) throws SQLException {
        BigDecimal notaFinal = BigDecimal.valueOf(calculadorEvaluacion.calcularNotaFinal(evaluacion));
        evaluacion.setNotaFinal(notaFinal);
        evaluacionDAO.agregarEvaluacion(evaluacion);
    }

    public void actualizarEvaluacion(Evaluacion evaluacion) throws SQLException {
        BigDecimal notaFinal = BigDecimal.valueOf(calculadorEvaluacion.calcularNotaFinal(evaluacion));
        evaluacion.setNotaFinal(notaFinal);
        evaluacionDAO.actualizarEvaluacion(evaluacion);
    }

    public void eliminarEvaluacion(int idEvaluacion) throws SQLException {
        evaluacionDAO.eliminarEvaluacion(idEvaluacion);
    }

    public Llamada obtenerLlamadaPorId(int idLlamada) throws SQLException {
        return llamadaDAO.obtenerLlamadaPorId(idLlamada);
    }

    public void agregarLlamada(Llamada llamada) throws SQLException {
        llamadaDAO.agregarLlamada(llamada);
    }

    public void actualizarLlamada(Llamada llamada) throws SQLException {
        llamadaDAO.actualizarLlamada(llamada);
    }

    public void eliminarLlamada(int idLlamada) throws SQLException {
        llamadaDAO.eliminarLlamada(idLlamada);
    }
}
