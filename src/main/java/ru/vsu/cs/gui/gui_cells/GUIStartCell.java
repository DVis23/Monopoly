package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Chance;
import ru.vsu.cs.cells.GoToJail;
import ru.vsu.cs.cells.RailRoad;
import ru.vsu.cs.cells.StartCell;
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

public class GUIStartCell extends GUICell {
    private final JPanel mainPanel;
    private final JLabel label1;
    private static Font font;

    static {
        Function<StartCell, GUIStartCell> function = c -> {
            try {
                return new GUIStartCell(c);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
        GUICellFactory.registerType(StartCell.class, function);

        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font = fontNew.deriveFont(20f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public GUIStartCell(StartCell cell) throws IOException {
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);

        label1 = new JLabel("GO");
        label1.setFont(font);
        label1.setForeground(Color.WHITE);

        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){

    }
    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        if (x > 180) {
            font = font.deriveFont(60f);
        } else if (x > 120) {
            font = font.deriveFont(35f);
        }
        label1.setFont(font);
        mainPanel.add(label1);
    }
    @Override
    public void update(){
    }
}
