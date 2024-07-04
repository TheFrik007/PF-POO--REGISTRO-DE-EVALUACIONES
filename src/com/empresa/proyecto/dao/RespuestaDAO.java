package com.empresa.proyecto.dao;

import com.empresa.proyecto.model.Respuesta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RespuestaDAO {
    private Connection connection;

    public RespuestaDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarRespuesta(Respuesta respuesta) throws SQLException {
        String sql = "INSERT INTO Respuesta (idEvaluacion, idPregunta, respuesta) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, respuesta.getEvaluacion().getIdEvaluacion());
        statement.setInt(2, respuesta.getPregunta().getIdPregunta());
        statement.setString(3, respuesta.getRespuesta());
        statement.executeUpdate();
    }

    public List<Respuesta> obtenerRespuestasPorEvaluacion(int idEvaluacion) throws SQLException {
        List<Respuesta> respuestas = new ArrayList<>();
        String sql = "SELECT * FROM Respuesta WHERE idEvaluacion = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, idEvaluacion);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int idRespuesta = resultSet.getInt("idRespuesta");
            int idPregunta = resultSet.getInt("idPregunta");
            String respuestaTexto = resultSet.getString("respuesta");

            // Se asume que ya tienes métodos para obtener una pregunta por su ID y una evaluación por su ID
            // Pregunta pregunta = obtenerPreguntaPorId(idPregunta);
            // Evaluacion evaluacion = obtenerEvaluacionPorId(idEvaluacion);

            // Por simplicidad, se deja como null por ahora
            Respuesta respuesta = new Respuesta(idRespuesta, null, null, respuestaTexto);
            respuestas.add(respuesta);
        }

        return respuestas;
    }
}
