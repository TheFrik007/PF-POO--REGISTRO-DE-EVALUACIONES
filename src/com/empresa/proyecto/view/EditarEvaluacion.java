package com.empresa.proyecto.view;

import com.empresa.proyecto.dao.EvaluacionDAO;
import com.empresa.proyecto.dao.LlamadaDAO;
import com.empresa.proyecto.model.Evaluacion;
import com.empresa.proyecto.model.Llamada;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditarEvaluacion extends JFrame {
    private Evaluacion evaluacion;
    private JTextField nombreClienteField;
    private JTextField nombreEvaluadorField;
    private JTextField numeroLlamadaField;
    private JTextField fechaLlamadaField;
    private JTextArea resumenLlamadaArea;
    private JTextField notaFinalField;
    private JTextArea comentariosArea;

    public EditarEvaluacion(int idEvaluacion) {
        setTitle("Editar Evaluación");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluaciones_db", "root", "")) {
            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(connection);
            evaluacion = evaluacionDAO.obtenerEvaluacionPorId(idEvaluacion);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(8, 2));

            panel.add(new JLabel("Nombre del Cliente:"));
            nombreClienteField = new JTextField(evaluacion.getLlamada().getNombreCliente());
            panel.add(nombreClienteField);

            panel.add(new JLabel("Nombre del Evaluador:"));
            nombreEvaluadorField = new JTextField(evaluacion.getLlamada().getNombreEvaluador());
            panel.add(nombreEvaluadorField);

            panel.add(new JLabel("Número de Llamada:"));
            numeroLlamadaField = new JTextField(evaluacion.getLlamada().getNumeroLlamada());
            panel.add(numeroLlamadaField);

            panel.add(new JLabel("Fecha de la Llamada (DD-MM-YYYY):"));
            fechaLlamadaField = new JTextField(new SimpleDateFormat("dd-MM-yyyy").format(evaluacion.getLlamada().getFechaLlamada()));
            panel.add(fechaLlamadaField);

            panel.add(new JLabel("Resumen de la Llamada:"));
            resumenLlamadaArea = new JTextArea(evaluacion.getLlamada().getResumenLlamada());
            panel.add(new JScrollPane(resumenLlamadaArea));

            panel.add(new JLabel("Nota Final:"));
            notaFinalField = new JTextField(evaluacion.getNotaFinal().toString());
            panel.add(notaFinalField);

            panel.add(new JLabel("Comentarios:"));
            comentariosArea = new JTextArea(evaluacion.getComentarios());
            panel.add(new JScrollPane(comentariosArea));

            JButton guardarButton = new JButton("Guardar");
            guardarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    guardarCambios();
                }
            });

            panel.add(guardarButton);
            add(panel);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarCambios() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluaciones_db", "root", "")) {
            LlamadaDAO llamadaDAO = new LlamadaDAO(connection);
            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(connection);

            String nombreCliente = nombreClienteField.getText();
            String nombreEvaluador = nombreEvaluadorField.getText();
            String numeroLlamada = numeroLlamadaField.getText();
            String fechaLlamadaStr = fechaLlamadaField.getText();
            String resumenLlamada = resumenLlamadaArea.getText();
            BigDecimal notaFinal = new BigDecimal(notaFinalField.getText());
            String comentarios = comentariosArea.getText();

            Date fechaLlamada;
            try {
                fechaLlamada = new SimpleDateFormat("dd-MM-yyyy").parse(fechaLlamadaStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Formato de fecha inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Llamada llamada = evaluacion.getLlamada();
            llamada.setNombreCliente(nombreCliente);
            llamada.setNombreEvaluador(nombreEvaluador);
            llamada.setNumeroLlamada(numeroLlamada);
            llamada.setFechaLlamada(fechaLlamada);
            llamada.setResumenLlamada(resumenLlamada);
            llamadaDAO.actualizarLlamada(llamada);

            evaluacion.setNotaFinal(notaFinal);
            evaluacion.setComentarios(comentarios);
            evaluacionDAO.actualizarEvaluacion(evaluacion);

            JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente.");
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
