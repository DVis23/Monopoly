package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.GoToJail;
import ru.vsu.cs.gui.GUICell;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIGoToJail extends GUICell {

    private JPanel MainPanel;

    public GUIGoToJail(GoToJail goToJail) throws IOException {


        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(70, 70));
        MainPanel.setBackground(Color.WHITE);

        BufferedImage myPicture = ImageIO.read(new File("image/go_to_jail_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        MainPanel.add(picLabel, BorderLayout.CENTER);


        this.add(MainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){

    }
}
