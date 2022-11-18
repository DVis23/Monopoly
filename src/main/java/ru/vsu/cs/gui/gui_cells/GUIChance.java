package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Chance;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

public class GUIChance extends GUICell {

    private final Chance chance;

    static {
        Function<Chance, GUIChance> function = c -> {
            try {
                return new GUIChance(c);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
        GUICellFactory.registerType(Chance.class, function);
    }

    public GUIChance(Chance chance) throws IOException {
        this.chance = chance;
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(70, 70));
        mainPanel.setBackground(Color.WHITE);

        BufferedImage myPicture = ImageIO.read(new File("image/chance_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        mainPanel.add(picLabel);

        this.add(mainPanel, BorderLayout.CENTER);
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
