package ru.vsu.cs;

import org.json.simple.parser.ParseException;
import ru.vsu.cs.gui.MainFrame;
import ru.vsu.cs.gui.StartFrame;

import java.io.IOException;
import java.util.Locale;


public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);
        java.awt.EventQueue.invokeLater(() -> {
            new StartFrame().setVisible(true);
        });
    }
}
