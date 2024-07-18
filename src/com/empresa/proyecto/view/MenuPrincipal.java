package com.empresa.proyecto.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton nuevaEvaluacionButton = new JButton("Nueva Evaluación");
        nuevaEvaluacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual antes de abrir la nueva evaluación
                new DatosLlamada().setVisible(true);
            }
        });
        panel.add(nuevaEvaluacionButton);

        JButton verEvaluacionesButton = new JButton("Ver Evaluaciones");
        verEvaluacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana actual antes de abrir la ventana de ver evaluaciones
                new VerEvaluaciones().setVisible(true);
            }
        });
        panel.add(verEvaluacionesButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }
}
