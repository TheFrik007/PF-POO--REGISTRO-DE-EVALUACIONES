package com.empresa.proyecto.view;

import com.empresa.proyecto.dao.BloqueDAO;
import com.empresa.proyecto.dao.LlamadaDAO;
import com.empresa.proyecto.model.Bloque;
import com.empresa.proyecto.model.Llamada;
import com.empresa.proyecto.service.DateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class DatosLlamada extends JFrame {

    private JTextField nombreClienteField;
    private JTextField nombreEvaluadorField;
    private JTextField numeroLlamadaField;
    private JTextField fechaLlamadaField;
    private JTextArea resumenLlamadaArea;

    public DatosLlamada() {
        setTitle("Datos de la Llamada");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Nombre del Cliente:"));
        nombreClienteField = new JTextField();
        panel.add(nombreClienteField);

        panel.add(new JLabel("Nombre del Evaluador:"));
        nombreEvaluadorField = new JTextField();
        panel.add(nombreEvaluadorField);

        panel.add(new JLabel("Número de Llamada:"));
        numeroLlamadaField = new JTextField();
        panel.add(numeroLlamadaField);

        panel.add(new JLabel("Fecha de la Llamada (DD-MM-YYYY):"));
        fechaLlamadaField = new JTextField();
        panel.add(fechaLlamadaField);

        panel.add(new JLabel("Resumen de la Llamada:"));
        resumenLlamadaArea = new JTextArea();
        panel.add(new JScrollPane(resumenLlamadaArea));

        JButton siguienteButton = new JButton("Siguiente");
        siguienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarLlamada();
            }
        });

        panel.add(siguienteButton);
        add(panel);
    }

    private void guardarLlamada() {
        String nombreCliente = nombreClienteField.getText();
        String nombreEvaluador = nombreEvaluadorField.getText();
        String numeroLlamada = numeroLlamadaField.getText();
        String fechaLlamadaStr = fechaLlamadaField.getText();
        String resumenLlamada = resumenLlamadaArea.getText();

        if (nombreCliente.isEmpty() || nombreEvaluador.isEmpty() || numeroLlamada.isEmpty() || fechaLlamadaStr.isEmpty() || resumenLlamada.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date fechaLlamada;
        try {
            fechaLlamada = DateUtil.convertirStringADate(fechaLlamadaStr);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Fecha inválida. Formato correcto: DD-MM-YYYY", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/evaluaciones_db", "root", "")) {
            LlamadaDAO llamadaDAO = new LlamadaDAO(connection);

            Llamada llamada = new Llamada(0, nombreCliente, nombreEvaluador, numeroLlamada, fechaLlamada, resumenLlamada); // Agregar todos los campos necesarios
            llamadaDAO.agregarLlamada(llamada);

            List<Bloque> bloques = obtenerBloques(connection);

            if (bloques == null || bloques.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se pudieron obtener los bloques de la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            new EvaluarBloque(bloques, llamada.getIdLlamada()).setVisible(true);
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private List<Bloque> obtenerBloques(Connection connection) {
        List<Bloque> bloques = null;
        try {
            BloqueDAO bloqueDAO = new BloqueDAO(connection);
            bloques = bloqueDAO.obtenerTodosBloques();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bloques;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DatosLlamada().setVisible(true);
            }
        });
    }
}
