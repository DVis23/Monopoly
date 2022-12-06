package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.GoToJail;
import ru.vsu.cs.cells.StartCell;
import ru.vsu.cs.cells.Street;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class GUIStreet extends GUICell {
    private static Font font1;
    private final Street street;
    private final JPanel mainPanel;
    private final JTextArea streetInform;

    static {
        Function<Street, GUIStreet> function = c -> new GUIStreet(c);
        GUICellFactory.registerType(Street.class, function);
        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font1 = fontNew.deriveFont(6f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public GUIStreet(Street  street)  {
        this.street = street;
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(70, 70));
        streetInform = new JTextArea();
        streetInform.setBackground(Color.BLACK);
        streetInform.setBorder(BorderFactory.createEmptyBorder(5,3,0,0));
        streetInform.setPreferredSize(new Dimension(68, 63));
        streetInform.setEditable(false);
        streetInform.setFont(font1);
        streetInform.setForeground(Color.WHITE);
        streetInform.setLineWrap(true);
        streetInform.setWrapStyleWord(true);



        Street.Color color = street.getColor();
        if (color == Street.Color.BROWN) {
            mainPanel.setBackground(new Color(142, 8, 157));
        }
        else if (color == Street.Color.WHITE) {
            mainPanel.setBackground(new Color(250, 231, 229));
        }
        else if (color == Street.Color.RED) {
            mainPanel.setBackground(new Color(234, 0, 59));
        }
        else if (color == Street.Color.YELLOW) {
            mainPanel.setBackground(new Color(241, 250, 120));
        }
        else if (color == Street.Color.ORANGE) {
            mainPanel.setBackground(new Color(255, 119, 54));
        }
        else if (color == Street.Color.ROSE) {
            mainPanel.setBackground(new Color(255, 33, 93));
        }
        else if (color == Street.Color.BLUE) {
            mainPanel.setBackground(new Color(104, 0, 205));
        }
        else if (color == Street.Color.GREEN) {
            mainPanel.setBackground(new Color(44, 255, 125));
        } else {
            mainPanel.setBackground(Color.BLACK);
        }

        write();
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void write(){
        StringBuilder sb = new StringBuilder();
        sb.append(street.getName()).append("\n");
        if (street.getOwner() == null) {
            sb.append(" ").append(street.getCost());
        } else {
            sb.append(" ").append(street.getIncome()).append("\n");
            sb.append(" ").append(street.getOwner().getName());
        }
        if (street.getNumberHotel() != 0) sb.append("\n").append(" Отели: ").append(street.getNumberHotel());
        String str = sb.toString();
        streetInform.setText(str);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        int o = 3;
        if (street.getOwner() == null) {
            if (playerNow.getLiberalValues() > street.getCost()) {
                String str = " Купить '" + street.getName() + "'?";
                o = JOptionPane.showConfirmDialog(board,  str);
            } else {
                String str = " Вам не хватает денег купить улицу";
                JOptionPane.showMessageDialog(board,  str);
            }
        } else if (playerNow.equals(street.getOwner())) {
            JOptionPane.showMessageDialog(board, "Вы на своей улице");
        } else {
            JOptionPane.showMessageDialog(board, "Вы на улице игрока " + street.getOwner().getName() + "," +
                    "\n" + "заптатите ему " + street.getIncome());
        }
        if (o == 0) {
            street.action2(playerNow, playingField);
            //update();
        }
    }

    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        streetInform.setPreferredSize(new Dimension(x, y - 20));
        if (x > 180) {
            font1 = font1.deriveFont(12f);
            streetInform.setPreferredSize(new Dimension(x, y - 30));
        } else if (x > 120) {
            font1 = font1.deriveFont(10f);
            streetInform.setPreferredSize(new Dimension(x, y - 25));
        } else {
            streetInform.setPreferredSize(new Dimension(x, y - 15));
        }
        streetInform.setFont(font1);
        mainPanel.add(streetInform, BorderLayout.SOUTH);
    }

    @Override
    public void update(){
        write();
    }
}
