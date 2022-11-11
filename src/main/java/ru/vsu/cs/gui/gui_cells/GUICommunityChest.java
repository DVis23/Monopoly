package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.CommunityChest;
import ru.vsu.cs.gui.GUICell;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUICommunityChest extends GUICell {

    private JPanel MainPanel;
    private CommunityChest communityChest;

    public GUICommunityChest(CommunityChest communityChest) throws IOException {
        this.communityChest = communityChest;
        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(70, 70));
        MainPanel.setBackground(Color.WHITE);

        BufferedImage myPicture = ImageIO.read(new File("image/community_chest_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        MainPanel.add(picLabel);


        this.add(MainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        if (communityChest.getI() == 1) {
            JOptionPane.showMessageDialog(board, " Поле казна: вы заплатили налог 2500 ");
        } else if (communityChest.getI() == 2) {
            JOptionPane.showMessageDialog(board, " Поле казна: вам выплатили 1000 за вашу улыбку ");
        } else if (communityChest.getI() == 3) {
            JOptionPane.showMessageDialog(board, " Поле казна: вы получили в наследство 3000 ");
        } else if (communityChest.getI() == 4) {
            JOptionPane.showMessageDialog(board, " Поле казна: вы выплатили каждому игроку по 1000 в знак уважения ");
        }
    }
}
