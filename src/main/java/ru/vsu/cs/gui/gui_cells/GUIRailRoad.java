package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.RailRoad;
import ru.vsu.cs.gui.GUICell;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIRailRoad extends GUICell {
    private JPanel MainPanel;
    private JTextArea RailRoadInform;
    private RailRoad railRoad;

    public GUIRailRoad(RailRoad railRoad) throws IOException {
        this.railRoad = railRoad;
        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(70, 70));
        MainPanel.setBackground(Color.WHITE);
        RailRoadInform = new JTextArea();
        RailRoadInform.setPreferredSize(new Dimension(68, 53));
        BufferedImage myPicture = ImageIO.read(new File("image/train_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setLayout(new BorderLayout(0,0));
        picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        MainPanel.add(picLabel, BorderLayout.NORTH);
        RailRoadInform.setEditable(false);
        Font font = new Font("BOLD", Font.BOLD, 8);
        RailRoadInform.setFont(font);
        RailRoadInform.setForeground(Color.BLACK);
        RailRoadInform.setLineWrap(true);
        RailRoadInform.setWrapStyleWord(true);


        StringBuilder sb = new StringBuilder();
        sb.append(railRoad.getName()).append("\n");
        if (railRoad.getOwner() == null) {
            sb.append(railRoad.getCost());
        } else {
            sb.append(railRoad.getIncome()).append(" ");
            sb.append(railRoad.getOwner().getName());
        }
        String str = sb.toString();
        RailRoadInform.setText(str);

        MainPanel.add(RailRoadInform, BorderLayout.SOUTH);
        this.add(MainPanel);
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
