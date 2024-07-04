package com.empresa.proyecto.service;

import com.empresa.proyecto.dao.EvaluacionDAO;
import com.empresa.proyecto.model.Evaluacion;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class EvaluacionService {
    private EvaluacionDAO evaluacionDAO;
    private CalculadorEvaluacion calculadorEvaluacion;

    public EvaluacionService(EvaluacionDAO evaluacionDAO, CalculadorEvaluacion calculadorEvaluacion) {
        this.evaluacionDAO = evaluacionDAO;
        this.calculadorEvaluacion = calculadorEvaluacion;
    }

    public void agregarEvaluacion(Evaluacion evaluacion) throws SQLException {
        BigDecimal notaFinal = BigDecimal.valueOf(calculadorEvaluacion.calcularNotaFinal(evaluacion));
        evaluacion.setNotaFinal(notaFinal);
        evaluacionDAO.agregarEvaluacion(evaluacion);
    }

    public List<Evaluacion> obtenerTodasEvaluaciones() throws SQLException {
        return evaluacionDAO.obtenerTodasEvaluaciones();
    }

    public Evaluacion obtenerEvaluacionPorId(int idEvaluacion) throws SQLException {
        return evaluacionDAO.obtenerEvaluacionPorId(idEvaluacion);
    }

    public void actualizarEvaluacion(Evaluacion evaluacion) throws SQLException {
    BigDecimal notaFinal = BigDecimal.valueOf(calculadorEvaluacion.calcularNotaFinal(evaluacion));
    evaluacion.setNotaFinal(notaFinal);
    evaluacionDAO.actualizarEvaluacion(evaluacion);
}

    public void eliminarEvaluacion(int idEvaluacion) throws SQLException {
        evaluacionDAO.eliminarEvaluacion(idEvaluacion);
    }
}
