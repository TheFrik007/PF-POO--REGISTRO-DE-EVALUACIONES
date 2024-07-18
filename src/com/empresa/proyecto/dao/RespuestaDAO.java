package com.empresa.proyecto.dao;

import com.empresa.proyecto.model.Respuesta;
import com.empresa.proyecto.model.Pregunta;
import com.empresa.proyecto.model.Evaluacion;

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
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, respuesta.getEvaluacion().getIdEvaluacion());
            statement.setInt(2, respuesta.getPregunta().getIdPregunta());
            statement.setString(3, respuesta.getRespuesta());
            statement.executeUpdate();
        }
    }

    public List<Respuesta> obtenerRespuestasPorEvaluacion(int idEvaluacion) throws SQLException {
        List<Respuesta> respuestas = new ArrayList<>();
        String sql = "SELECT * FROM Respuesta WHERE idEvaluacion = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEvaluacion);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idRespuesta = resultSet.getInt("idRespuesta");
                    int idPregunta = resultSet.getInt("idPregunta");
                    String respuestaTexto = resultSet.getString("respuesta");

                    PreguntaDAO preguntaDAO = new PreguntaDAO(connection);
                    Pregunta pregunta = preguntaDAO.obtenerPreguntaPorId(idPregunta);

                    EvaluacionDAO evaluacionDAO = new EvaluacionDAO(connection);
                    Evaluacion evaluacion = evaluacionDAO.obtenerEvaluacionPorId(idEvaluacion);

                    Respuesta respuesta = new Respuesta(idRespuesta, evaluacion, pregunta, respuestaTexto);
                    respuestas.add(respuesta);
                }
            }
        }
        return respuestas;
    }
}
