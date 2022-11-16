package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.StartCell;
import ru.vsu.cs.cells.Street;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUIStreet extends GUICell {

    private final Street street;
/*
    static {
        GUICellFactory.registerType(Street.class, c -> c.getClass().getName());
    }*/

    public GUIStreet(Street  street)  {
        this.street = street;
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(70, 70));
        JTextArea streetInform = new JTextArea();
        streetInform.setPreferredSize(new Dimension(68, 63));
        streetInform.setEditable(false);
        Font font = new Font("BOLD", Font.BOLD, 9);
        streetInform.setFont(font);
        streetInform.setForeground(Color.BLACK);
        streetInform.setLineWrap(true);
        streetInform.setWrapStyleWord(true);



        Street.Color color = street.getColor();
        if (color == Street.Color.BROWN) {
            mainPanel.setBackground(new Color(59, 34, 6));
        }
        else if (color == Street.Color.WHITE) {
            mainPanel.setBackground(Color.LIGHT_GRAY);
        }
        else if (color == Street.Color.RED) {
            mainPanel.setBackground(Color.RED);
        }
        else if (color == Street.Color.YELLOW) {
            mainPanel.setBackground(Color.YELLOW);
        }
        else if (color == Street.Color.ORANGE) {
            mainPanel.setBackground(Color.ORANGE);
        }
        else if (color == Street.Color.ROSE) {
            mainPanel.setBackground(Color.PINK);
        }
        else if (color == Street.Color.BLUE) {
            mainPanel.setBackground(Color.BLUE);
        }
        else if (color == Street.Color.GREEN) {
            mainPanel.setBackground(Color.GREEN);
        } else {
            mainPanel.setBackground(Color.BLACK);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(street.getName()).append("\n");
        if (street.getOwner() == null) {
            sb.append(street.getCost());
        } else {
            sb.append(street.getIncome()).append("\n");
            sb.append(street.getOwner().getName());
        }
        if (street.getNumberHotel() != 0) sb.append("\n").append("Отели: ").append(street.getNumberHotel());
        String str = sb.toString();
        streetInform.setText(str);

        mainPanel.add(streetInform, BorderLayout.SOUTH);
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        int o = 3;
        if (street.getOwner() == null) {
            if (playerNow.getLiberalValues() > street.getCost()) {
                o = JOptionPane.showConfirmDialog(board, "Купить улицу?");
            }
        }
        if (o == 0) street.action2(playerNow, playingField);
    }
}
