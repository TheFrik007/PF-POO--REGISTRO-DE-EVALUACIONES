
package com.empresa.proyecto.view;

import com.empresa.proyecto.dao.EvaluacionDAO;
import com.empresa.proyecto.dao.LlamadaDAO;
import com.empresa.proyecto.model.Evaluacion;
import com.empresa.proyecto.model.Llamada;
import com.empresa.proyecto.service.CalculadorEvaluacion;

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

    private JTextField fechaField;
    private JTextField notaFinalField;
    private JTextArea comentariosArea;
    private JTextField nombreClienteField;
    private JTextField nombreEvaluadorField;
    private int idEvaluacion;

    public EditarEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
        setTitle("Editar Evaluación");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        panel.add(new JLabel("Nombre del Cliente:"));
        nombreClienteField = new JTextField();
        panel.add(nombreClienteField);

        panel.add(new JLabel("Nombre del Evaluador:"));
        nombreEvaluadorField = new JTextField();
        panel.add(nombreEvaluadorField);

        panel.add(new JLabel("Fecha (DD/MM/YYYY):"));
        fechaField = new JTextField();
        panel.add(fechaField);

        panel.add(new JLabel("Nota Final:"));
        notaFinalField = new JTextField();
        panel.add(notaFinalField);

        panel.add(new JLabel("Comentarios:"));
        comentariosArea = new JTextArea();
        panel.add(new JScrollPane(comentariosArea));

        // Añadir otros campos de edición aquí...

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarEvaluacion();
            }
        });
        panel.add(guardarButton);

        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(cancelarButton);

        add(panel);
        cargarDatosEvaluacion();
    }

    private void cargarDatosEvaluacion() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluaciones_db", "root", "");
            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(connection);
            Evaluacion evaluacion = evaluacionDAO.obtenerEvaluacionPorId(idEvaluacion);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            fechaField.setText(dateFormat.format(evaluacion.getFecha()));
            notaFinalField.setText(evaluacion.getNotaFinal().toString());
            comentariosArea.setText(evaluacion.getComentarios());

            // Obtener los datos de llamada asociados para llenar los campos de nombre del cliente y evaluador
            LlamadaDAO llamadaDAO = new LlamadaDAO(connection);
            Llamada llamada = llamadaDAO.obtenerLlamadaPorId(evaluacion.getIdLlamada());

            nombreClienteField.setText(llamada.getNombreCliente());
            nombreEvaluadorField.setText(llamada.getNombreEvaluador());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarEvaluacion() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluaciones_db", "root", "");
            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(connection);
            CalculadorEvaluacion calculadorEvaluacion = new CalculadorEvaluacion();

            Date fecha = null;
            try {
                fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fechaField.getText());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Fecha inválida. Formato correcto: DD/MM/YYYY", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BigDecimal notaFinal = new BigDecimal(notaFinalField.getText());
            String comentarios = comentariosArea.getText();
            Llamada llamada = new Llamada(idLlamada, nombreCliente, nombreEvaluador, null, null);
            Evaluacion evaluacion = new Evaluacion(idEvaluacion, 0, fecha, notaFinal, comentarios);
            evaluacionDAO.actualizarEvaluacion(evaluacion);

            JOptionPane.showMessageDialog(null, "Evaluación actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            new VerEvaluaciones().setVisible(true);
            dispose();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
