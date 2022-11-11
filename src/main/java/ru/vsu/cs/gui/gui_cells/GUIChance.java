package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Chance;
import ru.vsu.cs.gui.GUICell;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUIChance extends GUICell {

    private JPanel MainPanel;
    private Chance chance;

    public GUIChance(Chance chance) throws IOException {
        this.chance = chance;
        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(70, 70));
        MainPanel.setBackground(Color.WHITE);

        BufferedImage myPicture = ImageIO.read(new File("image/chance_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        MainPanel.add(picLabel);


        this.add(MainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        if (chance.getI() == 1) {
            JOptionPane.showMessageDialog(board, " Поле шанс: вы сделали взнос в размере 2000 ");
        } else if (chance.getI() == 2) {
            JOptionPane.showMessageDialog(board, " Поле шанс: вы выиграли в лотерее 5000 ");
        } else if (chance.getI() == 3) {
            JOptionPane.showMessageDialog(board, " Поле шанс: каждый игрок скидывается вам на банкет по 500 ");
        } else if (chance.getI() == 4) {
            JOptionPane.showMessageDialog(board, " Поле шанс: вы выплатили каждому игроку по 500 в знак дружбы ");
        }
    }
}
