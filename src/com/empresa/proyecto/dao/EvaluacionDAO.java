package com.empresa.proyecto.dao;

import com.empresa.proyecto.model.Evaluacion;
import com.empresa.proyecto.model.Llamada;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionDAO {
    private Connection connection;

    public EvaluacionDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarEvaluacion(Evaluacion evaluacion) throws SQLException {
        String sql = "INSERT INTO Evaluacion (idLlamada, fecha, notaFinal, comentarios) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, evaluacion.getLlamada().getIdLlamada());
        statement.setDate(2, new java.sql.Date(evaluacion.getFecha().getTime()));
        statement.setBigDecimal(3, evaluacion.getNotaFinal());
        statement.setString(4, evaluacion.getComentarios());
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            evaluacion.setIdEvaluacion(generatedKeys.getInt(1));
        }
    }

    public List<Evaluacion> obtenerTodasEvaluaciones() throws SQLException {
        List<Evaluacion> evaluaciones = new ArrayList<>();
        String sql = "SELECT e.idEvaluacion, e.idLlamada, e.fecha, e.notaFinal, e.comentarios, l.nombreCliente, l.nombreEvaluador FROM Evaluacion e JOIN Llamada l ON e.idLlamada = l.idLlamada";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int idEvaluacion = resultSet.getInt("idEvaluacion");
            int idLlamada = resultSet.getInt("idLlamada");
            Date fecha = resultSet.getDate("fecha");
            BigDecimal notaFinal = resultSet.getBigDecimal("notaFinal");
            String comentarios = resultSet.getString("comentarios");
            String nombreCliente = resultSet.getString("nombreCliente");
            String nombreEvaluador = resultSet.getString("nombreEvaluador");

            Llamada llamada = new Llamada(idLlamada, nombreCliente, nombreEvaluador, null, null);
            Evaluacion evaluacion = new Evaluacion(idEvaluacion, llamada, fecha, notaFinal, comentarios);
            evaluaciones.add(evaluacion);
        }
        return evaluaciones;
    }

    public void actualizarEvaluacion(Evaluacion evaluacion) throws SQLException {
        String sql = "UPDATE Evaluacion SET idLlamada = ?, fecha = ?, notaFinal = ?, comentarios = ? WHERE idEvaluacion = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, evaluacion.getLlamada().getIdLlamada());
        statement.setDate(2, new java.sql.Date(evaluacion.getFecha().getTime()));
        statement.setBigDecimal(3, evaluacion.getNotaFinal());
        statement.setString(4, evaluacion.getComentarios());
        statement.setInt(5, evaluacion.getIdEvaluacion());
        statement.executeUpdate();
    }
}