package ru.vsu.cs;

import org.json.simple.parser.ParseException;
import ru.vsu.cs.gui.MainFrame;
import java.io.IOException;
import java.util.Locale;


public class Program {
    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.ROOT);
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new MainFrame();
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        });

    }
}
