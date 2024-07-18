package com.empresa.proyecto.dao;

import com.empresa.proyecto.model.Llamada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LlamadaDAO {
    private Connection connection;

    public LlamadaDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarLlamada(Llamada llamada) throws SQLException {
        String sql = "INSERT INTO Llamada (nombreCliente, nombreEvaluador, numeroLlamada, fechaLlamada, resumenLlamada) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, llamada.getNombreCliente());
            statement.setString(2, llamada.getNombreEvaluador());
            statement.setString(3, llamada.getNumeroLlamada());
            statement.setDate(4, new java.sql.Date(llamada.getFechaLlamada().getTime()));
            statement.setString(5, llamada.getResumenLlamada());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    llamada.setIdLlamada(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Failed to obtain ID for Llamada.");
                }
            }
        }
    }

    public Llamada obtenerLlamadaPorId(int idLlamada) throws SQLException {
        String sql = "SELECT * FROM Llamada WHERE idLlamada = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLlamada);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nombreCliente = resultSet.getString("nombreCliente");
                    String nombreEvaluador = resultSet.getString("nombreEvaluador");
                    String numeroLlamada = resultSet.getString("numeroLlamada");
                    Date fechaLlamada = resultSet.getDate("fechaLlamada");
                    String resumenLlamada = resultSet.getString("resumenLlamada");
                    return new Llamada(idLlamada, nombreCliente, nombreEvaluador, numeroLlamada, fechaLlamada, resumenLlamada);
                } else {
                    return null;
                }
            }
        }
    }

    public void actualizarLlamada(Llamada llamada) throws SQLException {
        String sql = "UPDATE Llamada SET nombreCliente = ?, nombreEvaluador = ?, numeroLlamada = ?, fechaLlamada = ?, resumenLlamada = ? WHERE idLlamada = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, llamada.getNombreCliente());
            statement.setString(2, llamada.getNombreEvaluador());
            statement.setString(3, llamada.getNumeroLlamada());
            statement.setDate(4, new java.sql.Date(llamada.getFechaLlamada().getTime()));
            statement.setString(5, llamada.getResumenLlamada());
            statement.setInt(6, llamada.getIdLlamada());
            statement.executeUpdate();
        }
    }

    public void eliminarLlamada(int idLlamada) throws SQLException {
        String sql = "DELETE FROM Llamada WHERE idLlamada = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idLlamada);
            statement.executeUpdate();
        }
    }
}
