package com.empresa.proyecto.dao;

import com.empresa.proyecto.model.Pregunta;
import com.empresa.proyecto.model.Bloque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreguntaDAO {
    private Connection connection;

    public PreguntaDAO(Connection connection) {
        this.connection = connection;
    }

    public Pregunta obtenerPreguntaPorId(int idPregunta) throws SQLException {
        String sql = "SELECT * FROM Pregunta WHERE idPregunta = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idPregunta);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idBloque = resultSet.getInt("idBloque");
                    String textoPregunta = resultSet.getString("textoPregunta");
                    
                    BloqueDAO bloqueDAO = new BloqueDAO(connection);
                    Bloque bloque = bloqueDAO.obtenerBloquePorId(idBloque);
                    
                    return new Pregunta(idPregunta, textoPregunta, bloque);
                }
            }
        }
        return null;
    }
}
