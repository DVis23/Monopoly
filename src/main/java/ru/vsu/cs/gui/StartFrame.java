package ru.vsu.cs.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private JPanel contentPanel;

    private int width = 1070, height = 798;

    public StartFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle ("Monopoly");

        contentPanel  = new ContentPanel(width, height, this);
        getContentPane().add(contentPanel, "Center");
        this.setSize(width, height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
