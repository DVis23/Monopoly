package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Jail;
import ru.vsu.cs.cells.RailRoad;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIRailRoad extends GUICell {

    private final RailRoad railRoad;
/*
    static {
        GUICellFactory.registerType(RailRoad.class, c -> c.getClass().getName());
    }*/

    public GUIRailRoad(RailRoad railRoad) throws IOException {
        this.railRoad = railRoad;
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(70, 70));
        mainPanel.setBackground(Color.WHITE);
        JTextArea railRoadInform = new JTextArea();
        railRoadInform.setPreferredSize(new Dimension(68, 53));
        BufferedImage myPicture = ImageIO.read(new File("image/train_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setLayout(new BorderLayout(0,0));
        picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        mainPanel.add(picLabel, BorderLayout.NORTH);
        railRoadInform.setEditable(false);
        Font font = new Font("BOLD", Font.BOLD, 8);
        railRoadInform.setFont(font);
        railRoadInform.setForeground(Color.BLACK);
        railRoadInform.setLineWrap(true);
        railRoadInform.setWrapStyleWord(true);


        StringBuilder sb = new StringBuilder();
        sb.append(railRoad.getName()).append("\n");
        if (railRoad.getOwner() == null) {
            sb.append(railRoad.getCost());
        } else {
            sb.append(railRoad.getIncome()).append(" ");
            sb.append(railRoad.getOwner().getName());
        }
        String str = sb.toString();
        railRoadInform.setText(str);

        mainPanel.add(railRoadInform, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        int o = 3;
        if (railRoad.getOwner() == null) {
            if (playerNow.getLiberalValues() > railRoad.getCost()) {
                o = JOptionPane.showConfirmDialog(board, "Купить железную дорогу?");
            }
            if (o == 0) railRoad.action2(playerNow, playingField);
        }
    }
}
