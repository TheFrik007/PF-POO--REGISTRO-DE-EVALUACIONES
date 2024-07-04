package com.empresa.proyecto.dao;

import com.empresa.proyecto.model.Bloque;
import com.empresa.proyecto.model.Pregunta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BloqueDAO {
    private Connection connection;

    public BloqueDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Bloque> obtenerTodosBloques() throws SQLException {
        List<Bloque> bloques = new ArrayList<>();
        String sql = "SELECT * FROM Bloque";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int idBloque = resultSet.getInt("idBloque");
            String nombreBloque = resultSet.getString("nombreBloque");
            List<Pregunta> preguntas = obtenerPreguntasPorBloque(idBloque);
            Bloque bloque = new Bloque(idBloque, nombreBloque, preguntas);
            bloques.add(bloque);
        }

        return bloques;
    }

    private List<Pregunta> obtenerPreguntasPorBloque(int idBloque) throws SQLException {
        List<Pregunta> preguntas = new ArrayList<>();
        String sql = "SELECT * FROM Pregunta WHERE idBloque = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idBloque);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int idPregunta = resultSet.getInt("idPregunta");
            String textoPregunta = resultSet.getString("textoPregunta");
            Pregunta pregunta = new Pregunta(idPregunta, textoPregunta, null);
            preguntas.add(pregunta);
        }

        return preguntas;
    }
}
