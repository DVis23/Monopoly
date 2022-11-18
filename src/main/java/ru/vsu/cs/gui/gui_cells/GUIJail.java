package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.FreeParking;
import ru.vsu.cs.cells.GoToJail;
import ru.vsu.cs.cells.Jail;
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

public class GUIJail extends GUICell {

    static {
        Function<Jail, GUIJail> function = c -> {
            try {
                return new GUIJail(c);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
        GUICellFactory.registerType(Jail.class, function);
    }

    public GUIJail(Jail cell) throws IOException {


        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(70, 70));
        mainPanel.setBackground(Color.WHITE);

        BufferedImage myPicture = ImageIO.read(new File("image/jake_icon.png"));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        mainPanel.add(picLabel);


        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){

    }
}
