package com.empresa.proyecto.dao;

import com.empresa.proyecto.model.Llamada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LlamadaDAO {
    private Connection connection;

    public LlamadaDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarLlamada(Llamada llamada) throws SQLException {
        String sql = "INSERT INTO Llamada (nombreCliente, nombreEvaluador, numeroLlamada, fechaLlamada, resumenLlamada) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, llamada.getNombreCliente());
        statement.setString(2, llamada.getNombreEvaluador());
        statement.setString(3, llamada.getNumeroLlamada());
        statement.setDate(4, new java.sql.Date(llamada.getFechaLlamada().getTime()));
        statement.setString(5, llamada.getResumenLlamada());
        statement.executeUpdate();

        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            llamada.setIdLlamada(generatedKeys.getInt(1));
        }
    }


    public List<Llamada> obtenerTodasLlamadas() throws SQLException {
        List<Llamada> llamadas = new ArrayList<>();
        String sql = "SELECT * FROM Llamada";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Llamada llamada = new Llamada();
            llamada.setIdLlamada(resultSet.getInt("idLlamada"));
            llamada.setNombreCliente(resultSet.getString("nombreCliente"));
            llamada.setNombreEvaluador(resultSet.getString("nombreEvaluador"));
            llamada.setNumeroLlamada(resultSet.getString("numeroLlamada"));
            llamada.setFechaLlamada(resultSet.getDate("fechaLlamada"));
            llamada.setResumenLlamada(resultSet.getString("resumenLlamada"));
            llamadas.add(llamada);
        }

        return llamadas;
    }

    public Llamada obtenerLlamadaPorId(int idLlamada) throws SQLException {
        String sql = "SELECT * FROM Llamada WHERE idLlamada = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idLlamada);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            Llamada llamada = new Llamada();
            llamada.setIdLlamada(resultSet.getInt("idLlamada"));
            llamada.setNombreCliente(resultSet.getString("nombreCliente"));
            llamada.setNombreEvaluador(resultSet.getString("nombreEvaluador"));
            llamada.setNumeroLlamada(resultSet.getString("numeroLlamada"));
            llamada.setFechaLlamada(resultSet.getDate("fechaLlamada"));
            llamada.setResumenLlamada(resultSet.getString("resumenLlamada"));
            return llamada;
        }

        return null;
    }

    public void actualizarLlamada(Llamada llamada) throws SQLException {
        String sql = "UPDATE Llamada SET nombreCliente = ?, nombreEvaluador = ?, numeroLlamada = ?, fechaLlamada = ?, resumenLlamada = ? WHERE idLlamada = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, llamada.getNombreCliente());
        statement.setString(2, llamada.getNombreEvaluador());
        statement.setString(3, llamada.getNumeroLlamada());
        statement.setDate(4, new java.sql.Date(llamada.getFechaLlamada().getTime()));
        statement.setString(5, llamada.getResumenLlamada());
        statement.setInt(6, llamada.getIdLlamada());
        statement.executeUpdate();
    }

    public void eliminarLlamada(int idLlamada) throws SQLException {
        String sql = "DELETE FROM Llamada WHERE idLlamada = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idLlamada);
        statement.executeUpdate();
    }
}
