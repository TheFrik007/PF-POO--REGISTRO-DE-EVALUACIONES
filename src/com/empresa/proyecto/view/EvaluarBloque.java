package com.empresa.proyecto.view;

import com.empresa.proyecto.dao.EvaluacionDAO;
import com.empresa.proyecto.dao.LlamadaDAO;
import com.empresa.proyecto.dao.RespuestaDAO;
import com.empresa.proyecto.model.Bloque;
import com.empresa.proyecto.model.Evaluacion;
import com.empresa.proyecto.model.Llamada;
import com.empresa.proyecto.model.Pregunta;
import com.empresa.proyecto.model.Respuesta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EvaluarBloque extends JFrame {

    private List<Bloque> bloques;
    private List<Respuesta> respuestas;
    private int bloqueActualIndex = 0;
    private int preguntaActualIndex = 0;

    private JLabel preguntaLabel;
    private JRadioButton siButton;
    private JRadioButton noButton;
    private JRadioButton naButton;
    private JButton siguienteButton;
    private JButton anteriorButton;
    private ButtonGroup group;
    private int idLlamada;
    private Llamada llamada;

    public EvaluarBloque(List<Bloque> bloques, int idLlamada) {
        if (bloques == null || bloques.isEmpty()) {
            throw new IllegalArgumentException("La lista de bloques no puede ser nula o vacía");
        }
        this.bloques = bloques;
        this.idLlamada = idLlamada;
        this.respuestas = new ArrayList<>();

        for (Bloque bloque : bloques) {
            if (bloque.getPreguntas() != null && !bloque.getPreguntas().isEmpty()) {
                for (Pregunta pregunta : bloque.getPreguntas()) {
                    respuestas.add(new Respuesta(0, null, pregunta, ""));
                }
            }
        }

        // Obtener la llamada desde la base de datos
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluaciones_db", "root", "")) {
            LlamadaDAO llamadaDAO = new LlamadaDAO(connection);
            llamada = llamadaDAO.obtenerLlamadaPorId(idLlamada);
            if (llamada == null) {
                throw new SQLException("No se encontró la llamada con id: " + idLlamada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al obtener la llamada: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        setTitle("Evaluar Bloque");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        preguntaLabel = new JLabel();
        panel.add(preguntaLabel);

        siButton = new JRadioButton("Sí");
        noButton = new JRadioButton("No");
        naButton = new JRadioButton("N/A");

        group = new ButtonGroup();
        group.add(siButton);
        group.add(noButton);
        group.add(naButton);

        panel.add(siButton);
        panel.add(noButton);
        panel.add(naButton);

        siguienteButton = new JButton("Siguiente");
        anteriorButton = new JButton("Anterior");

        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarSiguiente();
            }
        });

        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manejarAnterior();
            }
        });

        panel.add(anteriorButton);
        panel.add(siguienteButton);

        add(panel);
        actualizarPregunta();
    }

    private void actualizarPregunta() {
        if (bloques.isEmpty() || bloques.get(bloqueActualIndex).getPreguntas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay preguntas disponibles en el bloque actual.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Pregunta preguntaActual = bloques.get(bloqueActualIndex).getPreguntas().get(preguntaActualIndex);
        preguntaLabel.setText(preguntaActual.getTextoPregunta());

        Respuesta respuestaActual = respuestas.stream()
                .filter(respuesta -> respuesta.getPregunta().equals(preguntaActual))
                .findFirst()
                .orElse(new Respuesta());

        switch (respuestaActual.getRespuesta()) {
            case "Sí":
                siButton.setSelected(true);
                break;
            case "No":
                noButton.setSelected(true);
                break;
            case "N/A":
                naButton.setSelected(true);
                break;
            default:
                group.clearSelection();
                break;
        }
    }

    private void guardarRespuesta() {
        if (bloques.isEmpty() || bloques.get(bloqueActualIndex).getPreguntas().isEmpty()) {
            return;
        }
        Pregunta preguntaActual = bloques.get(bloqueActualIndex).getPreguntas().get(preguntaActualIndex);
        Respuesta respuestaActual = respuestas.stream()
                .filter(respuesta -> respuesta.getPregunta().equals(preguntaActual))
                .findFirst()
                .orElse(new Respuesta());

        if (siButton.isSelected()) {
            respuestaActual.setRespuesta("Sí");
        } else if (noButton.isSelected()) {
            respuestaActual.setRespuesta("No");
        } else if (naButton.isSelected()) {
            respuestaActual.setRespuesta("N/A");
        }
    }

    private void manejarSiguiente() {
        guardarRespuesta();
        if (preguntaActualIndex < bloques.get(bloqueActualIndex).getPreguntas().size() - 1) {
            preguntaActualIndex++;
        } else if (bloqueActualIndex < bloques.size() - 1) {
            bloqueActualIndex++;
            preguntaActualIndex = 0;
        } else {
            JOptionPane.showMessageDialog(null, "Has completado la evaluación.");
            guardarEvaluacion();
            new MenuPrincipal().setVisible(true);
            dispose();
            return;
        }
        actualizarPregunta();
    }

    private void manejarAnterior() {
        guardarRespuesta();
        if (preguntaActualIndex > 0) {
            preguntaActualIndex--;
        } else if (bloqueActualIndex > 0) {
            bloqueActualIndex--;
            preguntaActualIndex = bloques.get(bloqueActualIndex).getPreguntas().size() - 1;
        }
        actualizarPregunta();
    }

    private void guardarEvaluacion() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluaciones_db", "root", "")) {
            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(connection);
            RespuestaDAO respuestaDAO = new RespuestaDAO(connection);

            BigDecimal notaFinal = calcularNotaFinal();

            Evaluacion evaluacion = new Evaluacion(0, llamada, new Date(), notaFinal, "Comentarios opcionales");
            evaluacionDAO.agregarEvaluacion(evaluacion);

            for (Respuesta respuesta : respuestas) {
                respuesta.setEvaluacion(evaluacion);
                respuestaDAO.agregarRespuesta(respuesta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private BigDecimal calcularNotaFinal() {
        double notaInicial = 100.0;
        int totalBloques = bloques.size();

        for (Bloque bloque : bloques) {
            List<Pregunta> preguntas = bloque.getPreguntas();
            int totalPreguntas = preguntas.size();
            double porcentajePorPregunta = (100.0 / totalBloques) / totalPreguntas;

            for (Pregunta pregunta : preguntas) {
                Respuesta respuesta = respuestas.stream()
                        .filter(r -> r.getPregunta().equals(pregunta))
                        .findFirst()
                        .orElse(null);

                if (respuesta != null) {
                    if ("No".equals(respuesta.getRespuesta())) {
                        notaInicial -= porcentajePorPregunta;
                    } else if ("N/A".equals(respuesta.getRespuesta())) {
                        double ajuste = porcentajePorPregunta / (totalPreguntas - 1);
                        for (Pregunta p : preguntas) {
                            if (!p.equals(pregunta)) {
                                Respuesta r = respuestas.stream()
                                        .filter(res -> res.getPregunta().equals(p))
                                        .findFirst()
                                        .orElse(null);
                                if (r != null && !"N/A".equals(r.getRespuesta())) {
                                    notaInicial += ajuste;
                                }
                            }
                        }
                    }
                }
            }
        }

        return BigDecimal.valueOf(notaInicial).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        List<Bloque> bloques = Arrays.asList(
            new Bloque(1, "Enlaza", Arrays.asList(
                new Pregunta(1, "¿Cómo se presenta el asesor?", null),
                new Pregunta(2, "¿Solicita consentimiento para brindar la propuesta?", null)
            )),
            new Bloque(2, "Mejora", Arrays.asList(
                new Pregunta(3, "¿Gestiona el ofrecimiento del equipo según lo registrado?", null),
                new Pregunta(4, "¿Brinda información completa del plan y equipo?", null),
                new Pregunta(5, "¿Promueve la entrega por delivery adecuadamente?", null),
                new Pregunta(6, "¿Ofrece y argumenta la venta de accesorios?", null)
            )),
            new Bloque(3, "Prospecta", Arrays.asList(
                new Pregunta(7, "¿Realiza preguntas filtro sobre líneas adicionales?", null),
                new Pregunta(8, "¿Identifica la necesidad y presupuesto del cliente?", null)
            )),
            new Bloque(4, "Argumenta", Arrays.asList(
                new Pregunta(9, "¿Demuestra habilidad para contrarrestar el motivo de no venta?", null),
                new Pregunta(10, "¿Utiliza frases potentes de pre-cierre?", null),
                new Pregunta(11, "¿Ofrece líneas adicionales como argumento de venta?", null)
            )),
            new Bloque(5, "Asegura", Arrays.asList(
                new Pregunta(12, "¿Lee contrato?", null)
            )),
            new Bloque(6, "Comunica", Arrays.asList(
                new Pregunta(13, "¿Mantiene buena comunicación y personalización durante la llamada?", null)
            )),
            new Bloque(7, "Fideliza/Agradece", Arrays.asList(
                new Pregunta(14, "¿Fideliza y agradece al cliente de manera adecuada?", null)
            ))
        );

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EvaluarBloque(bloques, 1).setVisible(true);
            }
        });
    }
}
