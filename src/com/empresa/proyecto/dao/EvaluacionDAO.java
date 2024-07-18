package com.empresa.proyecto.dao;

import com.empresa.proyecto.model.Evaluacion;
import com.empresa.proyecto.model.Llamada;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EvaluacionDAO {
    private Connection connection;

    public EvaluacionDAO(Connection connection) {
        this.connection = connection;
    }

    public Evaluacion obtenerEvaluacionPorId(int idEvaluacion) throws SQLException {
        String sql = "SELECT * FROM Evaluacion WHERE idEvaluacion = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEvaluacion);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idLlamada = resultSet.getInt("idLlamada");
                    java.sql.Date fecha = resultSet.getDate("fecha");
                    BigDecimal notaFinal = resultSet.getBigDecimal("notaFinal");
                    String comentarios = resultSet.getString("comentarios");

                    LlamadaDAO llamadaDAO = new LlamadaDAO(connection);
                    Llamada llamada = llamadaDAO.obtenerLlamadaPorId(idLlamada);

                    return new Evaluacion(idEvaluacion, llamada, fecha, notaFinal, comentarios);
                } else {
                    return null;
                }
            }
        }
    }

    public List<Evaluacion> obtenerTodasEvaluacionesConLlamada() throws SQLException {
        List<Evaluacion> evaluaciones = new ArrayList<>();
        String sql = "SELECT e.idEvaluacion, e.fecha, e.notaFinal, e.comentarios, " +
                     "l.idLlamada, l.nombreCliente, l.nombreEvaluador, l.numeroLlamada, l.fechaLlamada, l.resumenLlamada " +
                     "FROM Evaluacion e " +
                     "JOIN Llamada l ON e.idLlamada = l.idLlamada";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idEvaluacion = resultSet.getInt("idEvaluacion");
                int idLlamada = resultSet.getInt("idLlamada");
                java.sql.Date fecha = resultSet.getDate("fecha");
                BigDecimal notaFinal = resultSet.getBigDecimal("notaFinal");
                String comentarios = resultSet.getString("comentarios");
                String nombreCliente = resultSet.getString("nombreCliente");
                String nombreEvaluador = resultSet.getString("nombreEvaluador");
                String numeroLlamada = resultSet.getString("numeroLlamada");
                Date fechaLlamada = resultSet.getDate("fechaLlamada");
                String resumenLlamada = resultSet.getString("resumenLlamada");

                Llamada llamada = new Llamada(idLlamada, nombreCliente, nombreEvaluador, numeroLlamada, fechaLlamada, resumenLlamada);
                evaluaciones.add(new Evaluacion(idEvaluacion, llamada, fecha, notaFinal, comentarios));
            }
        }
        return evaluaciones;
    }

    public void agregarEvaluacion(Evaluacion evaluacion) throws SQLException {
        String sql = "INSERT INTO Evaluacion (idLlamada, fecha, notaFinal, comentarios) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, evaluacion.getLlamada().getIdLlamada());
            statement.setDate(2, new java.sql.Date(evaluacion.getFecha().getTime()));
            statement.setBigDecimal(3, evaluacion.getNotaFinal());
            statement.setString(4, evaluacion.getComentarios());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    evaluacion.setIdEvaluacion(generatedKeys.getInt(1));
                }
            }
        }
    }

    public void actualizarEvaluacion(Evaluacion evaluacion) throws SQLException {
        String sql = "UPDATE Evaluacion SET notaFinal = ?, comentarios = ? WHERE idEvaluacion = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBigDecimal(1, evaluacion.getNotaFinal());
            statement.setString(2, evaluacion.getComentarios());
            statement.setInt(3, evaluacion.getIdEvaluacion());
            statement.executeUpdate();
        }
    }

    public void eliminarEvaluacion(int idEvaluacion) throws SQLException {
        String sql = "DELETE FROM Evaluacion WHERE idEvaluacion = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEvaluacion);
            statement.executeUpdate();
        }
    }
}
