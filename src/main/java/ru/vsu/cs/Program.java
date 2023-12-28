package ru.vsu.cs;

import ru.vsu.cs.gui.StartFrame;

import java.util.Locale;


public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);
        java.awt.EventQueue.invokeLater(() -> {
            new StartFrame().setVisible(true);
        });
    }
}
