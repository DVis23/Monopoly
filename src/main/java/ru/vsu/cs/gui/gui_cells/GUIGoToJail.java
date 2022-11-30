package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Chance;
import ru.vsu.cs.cells.FreeParking;
import ru.vsu.cs.cells.GoToJail;
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

public class GUIGoToJail extends GUICell {
    private JPanel mainPanel;
    private Image picture;
    private JLabel picLabel;

    static {
        Function<GoToJail, GUIGoToJail> function = c -> {
            try {
                return new GUIGoToJail(c);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
        GUICellFactory.registerType(GoToJail.class, function);
    }

    public GUIGoToJail(GoToJail cell) throws IOException {


        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        picture = ImageIO.read(new File("image/go_to_jail_icon.png"));
        picLabel = new JLabel(new ImageIcon(picture));

        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
    }

    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        //mainPanel.setBackground(Color.BLACK);
        picture = picture.getScaledInstance(x - 10, y - 10, Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(picture));
        mainPanel.add(picLabel);
    }
    @Override
    public void update(){
    }
}
