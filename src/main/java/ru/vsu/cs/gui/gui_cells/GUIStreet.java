package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Street;
import ru.vsu.cs.gui.GUICell;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUIStreet extends GUICell {

    private JPanel MainPanel;
    private JTextArea StreetInform;
    private Street street;

    public GUIStreet(Street street)  {
        this.street = street;
        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(70, 70));
        StreetInform = new JTextArea();
        StreetInform.setPreferredSize(new Dimension(68, 63));
        StreetInform.setEditable(false);
        Font font = new Font("BOLD", Font.BOLD, 9);
        StreetInform.setFont(font);
        StreetInform.setForeground(Color.BLACK);
        StreetInform.setLineWrap(true);
        StreetInform.setWrapStyleWord(true);



        Street.Color color = street.getColor();
        if (color == Street.Color.BROWN) {
            MainPanel.setBackground(new Color(59, 34, 6));
        }
        else if (color == Street.Color.WHITE) {
            MainPanel.setBackground(Color.LIGHT_GRAY);
        }
        else if (color == Street.Color.RED) {
            MainPanel.setBackground(Color.RED);
        }
        else if (color == Street.Color.YELLOW) {
            MainPanel.setBackground(Color.YELLOW);
        }
        else if (color == Street.Color.ORANGE) {
            MainPanel.setBackground(Color.ORANGE);
        }
        else if (color == Street.Color.ROSE) {
            MainPanel.setBackground(Color.PINK);
        }
        else if (color == Street.Color.BLUE) {
            MainPanel.setBackground(Color.BLUE);
        }
        else if (color == Street.Color.GREEN) {
            MainPanel.setBackground(Color.GREEN);
        } else {
            MainPanel.setBackground(Color.BLACK);
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
        StreetInform.setText(str);

        MainPanel.add(StreetInform, BorderLayout.SOUTH);
        this.add(MainPanel, BorderLayout.CENTER);
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
