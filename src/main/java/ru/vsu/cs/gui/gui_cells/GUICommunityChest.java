package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Chance;
import ru.vsu.cs.cells.CommunityChest;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class GUICommunityChest extends GUICell {

    private final CommunityChest communityChest;

    static {
        Function<CommunityChest, GUICommunityChest> function = c -> {
            try {
                return new GUICommunityChest(c);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
        GUICellFactory.registerType(CommunityChest.class, function);
    }

    public GUICommunityChest(CommunityChest communityChest) throws IOException {
        this.communityChest = communityChest;
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(70, 70));
        mainPanel.setBackground(Color.WHITE);

        BufferedImage myPicture = ImageIO.read(new File("image/community_chest_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        mainPanel.add(picLabel);


        this.add(mainPanel, BorderLayout.CENTER);
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
