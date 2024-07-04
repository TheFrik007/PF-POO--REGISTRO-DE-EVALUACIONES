package com.empresa.proyecto.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Menú Principal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        JButton nuevaEvaluacionButton = new JButton("Nueva Evaluación");
        JButton verEvaluacionesButton = new JButton("Ver Evaluaciones");
        
        nuevaEvaluacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DatosLlamada().setVisible(true);
                dispose();
            }
        });
        
        verEvaluacionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerEvaluaciones().setVisible(true);
                dispose();
            }
        });
        
        panel.add(nuevaEvaluacionButton);
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
