package com.empresa.proyecto.view;

import com.empresa.proyecto.dao.EvaluacionDAO;
import com.empresa.proyecto.model.Evaluacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

public class VerEvaluaciones extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;

    public VerEvaluaciones() {
        setTitle("Ver Evaluaciones");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new String[]{
                "ID Evaluación", "Nombre Cliente", "Nombre Evaluador", "Número Llamada", 
                "Fecha Llamada", "Resumen Llamada", "Nota Final", "Comentarios"
        }, 0);
        table = new JTable(tableModel);
        cargarDatos();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton editarButton = new JButton("Editar Evaluación");
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int idEvaluacion = (int) tableModel.getValueAt(selectedRow, 0);
                    EditarEvaluacion editarEvaluacion = new EditarEvaluacion(idEvaluacion);
                    editarEvaluacion.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            recargarDatos();
                        }
                    });
                    editarEvaluacion.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una evaluación para editar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonPanel.add(editarButton);

        JButton volverButton = new JButton("Volver al Menú Principal");
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual antes de abrir el menú principal
                new MenuPrincipal().setVisible(true);
            }
        });
        buttonPanel.add(volverButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluaciones_db", "root", "")) {
            EvaluacionDAO evaluacionDAO = new EvaluacionDAO(connection);
            List<Evaluacion> evaluaciones = evaluacionDAO.obtenerTodasEvaluacionesConLlamada();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            for (Evaluacion evaluacion : evaluaciones) {
                Object[] row = new Object[]{
                        evaluacion.getIdEvaluacion(),
                        evaluacion.getLlamada().getNombreCliente(),
                        evaluacion.getLlamada().getNombreEvaluador(),
                        evaluacion.getLlamada().getNumeroLlamada(),
                        dateFormat.format(evaluacion.getLlamada().getFechaLlamada()),
                        evaluacion.getLlamada().getResumenLlamada(),
                        evaluacion.getNotaFinal(),
                        evaluacion.getComentarios()
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void recargarDatos() {
        tableModel.setRowCount(0); // Limpia la tabla
        cargarDatos(); // Vuelve a cargar los datos
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VerEvaluaciones().setVisible(true);
            }
        });
    }
}
