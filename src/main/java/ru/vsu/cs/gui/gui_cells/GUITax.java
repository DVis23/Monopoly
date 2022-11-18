package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.StartCell;
import ru.vsu.cs.cells.Street;
import ru.vsu.cs.cells.Tax;
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

public class GUITax extends GUICell {

    static {
        Function<Tax, GUITax> function = c -> {
            try {
                return new GUITax(c);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
        GUICellFactory.registerType(Tax.class, function);
    }


    public GUITax(Tax cell) throws IOException {

        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(70, 70));
        mainPanel.setBackground(Color.WHITE);

        BufferedImage myPicture = ImageIO.read(new File("image/tax_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        mainPanel.add(picLabel);


        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){

    }
}
