package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.cells.RailRoad;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIRailRoad extends  JPanel{
    private JPanel MainPanel;
    private JTextArea RailRoadInform;

    public GUIRailRoad(RailRoad railRoad) throws IOException {

        this.setPreferredSize(new Dimension(70, 70));
        this.setLayout(new BorderLayout());
        this.setLayout(new BorderLayout(0,0));
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.setBorder(new LineBorder(Color.BLACK));

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
}
