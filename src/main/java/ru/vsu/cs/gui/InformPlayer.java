package ru.vsu.cs.gui;

import ru.vsu.cs.Player;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class InformPlayer extends JPanel {
    private static Font font = new Font("BOLD", Font.BOLD, 25);
    private final JTextArea textArea;

    static {
        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font = fontNew.deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public InformPlayer(Player player) {
        this.setPreferredSize(new Dimension(250, 70));
        this.setBackground(new Color(23, 4, 41));

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(new Color(23, 4, 41));
        textArea.setForeground(Color.WHITE);
        textArea.setFont(font);
        textArea.setPreferredSize(new Dimension(200, 70));

        StringBuilder sb = new StringBuilder();
        sb.append(player.getName()).append(" ").append("\n");
        sb.append(player.getLiberalValues()).append("\n").append("\n");

        textArea.setText(sb.toString());
        this.add(textArea);
        this.setVisible(true);
    }

    public void textRed() {
       textArea.setForeground(Color.RED);
    }
}
