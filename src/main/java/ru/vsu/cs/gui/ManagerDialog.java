package ru.vsu.cs.gui;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.RailRoad;
import ru.vsu.cs.cells.Street;
import ru.vsu.cs.cells.UtilityCompany;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ManagerDialog extends JDialog {
    private JPanel mainPanel;
    private static Font font;

    static {
        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font = fontNew.deriveFont(12f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public ManagerDialog(Player player, PlayingField playingField) {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(650, 600);
        this.setTitle("MANAGER FOR " + player.getName() + " (" + player.getLiberalValues() + ")");
        this.setResizable(false);
        setModal(true);
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        Color colorBackground = new Color(23, 4, 41);
        mainPanel.setBackground(colorBackground);

        int numberButton = 0;
        int widthButton = this.getWidth();
        int heightButton = 50;
        int heightPanel = 600;
        mainPanel.setPreferredSize(new Dimension(400, heightPanel));
        JScrollPane jScrollPane = new JScrollPane(mainPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        Cell[] cells = playingField.getCells();

        for (Cell cell : cells) {
            if (cell.getClass() == Street.class) {
                if (((Street) cell).getOwner() == player) {
                    String str = " Продать улицу ' " + ((Street) cell).getName() + " ' за " +
                            ((Street) cell).getCost() / 2;
                    JButton button = new JButton(str);
                    button.setFont(font);
                    button.setForeground(Color.BLACK);
                    button.setBackground(Color.WHITE);
                    button.setFocusable(false);
                    if (numberButton > heightPanel/heightButton - 1) heightPanel += heightButton;
                    mainPanel.setPreferredSize(new Dimension(widthButton, heightPanel));
                    mainPanel.add(button);
                    button.setSize(new Dimension(widthButton, heightButton));
                    button.setLocation(0, numberButton * heightButton);
                    numberButton++;

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            ((Street) cell).deleteOwner(playingField);
                            player.setLiberalValues(player.getLiberalValues() + ((Street) cell).getCost() / 2);
                            mainPanel.remove(button);
                            setTitle("MANAGER FOR " + player.getName() + " (" + player.getLiberalValues() + ")");
                            repaint();
                        }
                    });

                    if (((Street) cell).availabilityOfStreets(player, playingField)) {
                        String str2 = " Построить отель на улице ' " + ((Street) cell).getName() + " ' за " +
                                ((Street) cell).getCostHotel() + " (" + ((Street) cell).getNumberHotel() + ")";
                        JButton button2 = new JButton(str2);
                        button2.setFont(font);
                        button2.setForeground(Color.BLACK);
                        button2.setBackground(Color.ORANGE);
                        button2.setFocusable(false);
                        if (numberButton > heightPanel/heightButton - 1) heightPanel += heightButton;
                        mainPanel.setPreferredSize(new Dimension(widthButton, heightPanel));
                        mainPanel.add(button2);
                        button2.setSize(new Dimension(widthButton, heightButton));
                        button2.setLocation(0, numberButton * heightButton);
                        numberButton++;

                        button2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                ((Street) cell).buyHotel(player, playingField);
                                String str = " Построить отель на улице ' " + ((Street) cell).getName() + " ' за " +
                                        ((Street) cell).getCostHotel() + " (" + ((Street) cell).getNumberHotel() + ")";
                                button2.setText(str);
                                setTitle("MANAGER FOR " + player.getName() + " (" + player.getLiberalValues() + ")");
                                repaint();

                            }
                        });
                    }
                }
            }
            if (cell.getClass() == RailRoad.class) {
                if (((RailRoad) cell).getOwner() == player) {
                    String str = " Продать железную дорогу ' " + ((RailRoad) cell).getName() + " ' за " +
                            ((RailRoad) cell).getCost() / 2;
                    JButton button = new JButton(str);
                    button.setFont(font);
                    button.setForeground(Color.BLACK);
                    button.setBackground(Color.LIGHT_GRAY);
                    button.setFocusable(false);
                    if (numberButton > heightPanel/heightButton - 1) heightPanel += heightButton;
                    mainPanel.setPreferredSize(new Dimension(widthButton, heightPanel));
                    mainPanel.add(button);
                    button.setSize(new Dimension(widthButton, heightButton));
                    button.setLocation(0, numberButton * heightButton);
                    numberButton++;

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            ((RailRoad) cell).deleteOwner(player, playingField);
                            player.setLiberalValues(player.getLiberalValues() + ((RailRoad) cell).getCost() / 2);
                            mainPanel.remove(button);
                            setTitle("MANAGER FOR " + player.getName() + " (" + player.getLiberalValues() + ")");
                            repaint();
                        }
                    });

                }
            }
            if (cell.getClass() == UtilityCompany.class) {
                if (((UtilityCompany) cell).getOwner() == player) {
                    String str = " Продать коммунальную службу ' " + ((UtilityCompany) cell).getName() +
                            " ' за " + ((UtilityCompany) cell).getCost() / 2;
                    JButton button = new JButton(str);
                    button.setFont(font);
                    button.setForeground(Color.BLACK);
                    button.setBackground(Color.GRAY);
                    button.setFocusable(false);
                    if (numberButton > heightPanel/heightButton - 1) heightPanel += heightButton;
                    mainPanel.setPreferredSize(new Dimension(widthButton, heightPanel));
                    mainPanel.add(button);
                    button.setSize(new Dimension(widthButton, heightButton));
                    button.setLocation(0, numberButton * heightButton);
                    numberButton++;

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            ((UtilityCompany) cell).deleteOwner(player, playingField);
                            player.setLiberalValues(player.getLiberalValues() + ((UtilityCompany) cell).getCost() / 2);
                            mainPanel.remove(button);
                            setTitle("MANAGER FOR " + player.getName() + " (" + player.getLiberalValues() + ")");
                            repaint();
                        }
                    });
                }
            }
        }

        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(jScrollPane);
        this.setVisible(true);
    }

    public ManagerDialog(){
    }
}
