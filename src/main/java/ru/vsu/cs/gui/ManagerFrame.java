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

public class ManagerFrame extends JDialog {
    JPanel panel;

    public ManagerFrame(Player player, PlayingField playingField) {
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.pack();
        this.setSize(410, 600);
        this.setTitle("Manager Frame for " + player.getName());
        this.setResizable(false);
        int numberButton = 0;
        panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1, 0, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        panel.setBackground(new Color(61,60,59));

        int heightPanel = 600;
        panel.setPreferredSize(new  Dimension(400, heightPanel));
        JScrollPane jScrollPane = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Font font = new Font("BOLD", Font.BOLD, 12);

        Cell [] cells = playingField.getCells();
        for (int i = 0; i < cells.length; i++) {
            Cell cell = cells[i];
            if (cell.getClass() == Street.class){
                if (((Street) cell).getOwner() == player) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(" Продать улицу ' ").append(((Street) cell).getName()).append(" ' за ").append(((Street) cell).getCost()/2);
                    String str = sb.toString();
                    JButton button = new JButton(str);
                    button.setPreferredSize(new Dimension(400, 80));
                    button.setFont(font);
                    button.setForeground(Color.BLACK);
                    button.setBackground(Color.WHITE);
                    if (numberButton > 7) heightPanel += 80;
                    panel.setPreferredSize(new Dimension(400, heightPanel));
                    numberButton++;
                    panel.setLayout(new GridLayout(numberButton, 1, 0, 0));
                    panel.add(button, BorderLayout.WEST);
                    panel.setVisible(true);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            ((Street) cell).deleteOwner(playingField);
                            player.setLiberalValues(player.getLiberalValues() + ((Street) cell).getCost()/2);
                            panel.remove(button);
                            panel.setVisible(true);
                        }
                    });

                    if (((Street) cell).availabilityOfStreets(player, playingField)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(" Построить отель на улице ' ").append(((Street) cell).getName()).append(" ' за ").
                                append(((Street) cell).getCostHotel());
                        String str2 = sb2.toString();
                        JButton button2 = new JButton(str2);
                        button2.setPreferredSize(new Dimension(400, 80));
                        button2.setFont(font);
                        button2.setForeground(Color.BLACK);
                        button2.setBackground(Color.ORANGE);
                        if (numberButton > 7) heightPanel += 80;
                        panel.setPreferredSize(new Dimension(400, heightPanel));
                        numberButton++;
                        panel.setLayout(new GridLayout(numberButton, 1, 0, 0));
                        panel.add(button2, BorderLayout.WEST);
                        panel.setVisible(true);

                        button2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                ((Street) cell).buyHotel(player, playingField);
                            }
                        });
                    }
                }
            }
            if (cell.getClass() == RailRoad.class){
                if (((RailRoad) cell).getOwner() == player) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(" Продать железную дорогу ' ").append(((RailRoad) cell).getName()).append(" ' за ").append(((RailRoad) cell).getCost()/2);
                    String str = sb.toString();
                    JButton button = new JButton(str);
                    button.setPreferredSize(new Dimension(400, 80));
                    button.setFont(font);
                    button.setForeground(Color.BLACK);
                    button.setBackground(Color.LIGHT_GRAY);
                    if (numberButton > 7) heightPanel += 80;
                    panel.setPreferredSize(new Dimension(400, heightPanel));
                    numberButton++;
                    panel.setLayout(new GridLayout(numberButton, 1, 0, 0));
                    panel.add(button, BorderLayout.WEST);
                    panel.setVisible(true);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            ((RailRoad) cell).deleteOwner();
                            player.setLiberalValues(player.getLiberalValues() + ((RailRoad) cell).getCost()/2);
                            panel.remove(button);
                            panel.setVisible(true);
                        }
                    });
                }
            }
            if (cell.getClass() == UtilityCompany.class){
                if (((UtilityCompany) cell).getOwner() == player) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(" Продать коммунальную службу ' ").append(((UtilityCompany) cell).getName()).
                            append(" ' за ").append(((UtilityCompany) cell).getCost()/2);
                    String str = sb.toString();
                    JButton button = new JButton(str);
                    button.setPreferredSize(new Dimension(400, 80));
                    button.setFont(font);
                    button.setForeground(Color.BLACK);
                    button.setBackground(Color.GRAY);
                    if (numberButton > 7) heightPanel += 80;
                    panel.setPreferredSize(new Dimension(400, heightPanel));
                    numberButton++;
                    panel.setLayout(new GridLayout(numberButton, 1, 0, 0));
                    panel.add(button, BorderLayout.WEST);
                    panel.setVisible(true);

                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            ((UtilityCompany) cell).deleteOwner();
                            player.setLiberalValues(player.getLiberalValues() + ((UtilityCompany) cell).getCost()/2);
                            panel.remove(button);
                            panel.setVisible(true);
                        }
                    });
                }
            }
        }

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(jScrollPane);
        this.setVisible(true);
    }

    public  ManagerFrame() {
    }
}
