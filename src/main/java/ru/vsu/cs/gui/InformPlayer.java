package ru.vsu.cs.gui;

import ru.vsu.cs.Player;

import javax.swing.*;
import java.awt.*;

public class InformPlayer extends JPanel {

    private JTextArea textArea;
    public InformPlayer(Player player) {
        this.setPreferredSize(new Dimension(250, 60));
        this.setBackground(new Color(60,60,59));

        textArea = new JTextArea();
        Font font = new Font("BOLD", Font.BOLD, 15);
        textArea.setEditable(false);
        textArea.setBackground(new Color(60,60,59));
        textArea.setForeground(Color.WHITE);
        textArea.setFont(font);
        textArea.setPreferredSize(new Dimension(200, 60));

        StringBuilder sb = new StringBuilder();
        sb.append(player.getName()).append("\n");
        sb.append(player.getLiberalValues()).append("\n").append("\n");

        textArea.setText(sb.toString());
        this.add(textArea);
        this.setVisible(true);
    }

    public void textRed() {
       textArea.setForeground(Color.RED);
    }
}
