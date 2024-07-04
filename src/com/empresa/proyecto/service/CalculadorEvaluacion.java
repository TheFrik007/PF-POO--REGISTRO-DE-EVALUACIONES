package com.empresa.proyecto.service;

import com.empresa.proyecto.model.Bloque;
import com.empresa.proyecto.model.Evaluacion;
import com.empresa.proyecto.model.Pregunta;
import com.empresa.proyecto.model.Respuesta;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculadorEvaluacion {

    public double calcularNotaFinal(Evaluacion evaluacion) {
        double notaFinal = 100.0;
        List<Respuesta> respuestas = evaluacion.getRespuestas();
        
        // Agrupar respuestas por bloques
        Map<Bloque, List<Respuesta>> respuestasPorBloque = respuestas.stream()
                .collect(Collectors.groupingBy(respuesta -> respuesta.getPregunta().getBloque()));
        
        for (Map.Entry<Bloque, List<Respuesta>> entry : respuestasPorBloque.entrySet()) {
            Bloque bloque = entry.getKey();
            List<Respuesta> respuestasDelBloque = entry.getValue();
            int numPreguntas = respuestasDelBloque.size();
            
            // Calcular el porcentaje por bloque y por pregunta
            double porcentajePorBloque = 100.0 / respuestasPorBloque.size();
            double porcentajePorPregunta = porcentajePorBloque / numPreguntas;

            // Contar las respuestas "NA" y "No"
            long countNA = respuestasDelBloque.stream().filter(respuesta -> "N/A".equals(respuesta.getRespuesta())).count();
            long countNo = respuestasDelBloque.stream().filter(respuesta -> "No".equals(respuesta.getRespuesta())).count();

            // Ajustar el porcentaje por pregunta si hay respuestas "NA"
            if (countNA > 0) {
                porcentajePorPregunta = porcentajePorBloque / (numPreguntas - countNA);
            }

            // Restar el porcentaje correspondiente por cada respuesta "No"
            notaFinal -= countNo * porcentajePorPregunta;
        }

        return Math.round(notaFinal * 100.0) / 100.0; // Redondear a dos decimales
    }
}