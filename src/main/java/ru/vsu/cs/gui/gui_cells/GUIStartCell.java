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
    private final JLabel label2;
    private static Font font1;
    private static Font font2;
    private StartCell startCell;

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
            font1 = fontNew.deriveFont(20f);
            font2 = fontNew.deriveFont(10f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public GUIStartCell(StartCell startCell) throws IOException {
        this.startCell = startCell;
        this.setLayout(new BorderLayout());
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());

        label1 = new JLabel("GO");
        label1.setFont(font1);
        label1.setForeground(Color.WHITE);
        label1.setBorder(BorderFactory.createEmptyBorder(15,4,0,0));
        label2 = new JLabel("+" + startCell.getPrize());
        label2.setFont(font2);
        label2.setBorder(BorderFactory.createEmptyBorder(0,4,5,0));
        label2.setForeground(Color.RED);

        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        JOptionPane.showMessageDialog(board, "Вы на стартовом поле, отдыхайте");
    }

    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        if (x > 180) {
            font1 = font1.deriveFont(60f);
            font2 = font2.deriveFont(30f);
        } else if (x > 120) {
            font1 = font1.deriveFont(35f);
            font2 = font2.deriveFont(12f);
        }
        label1.setFont(font1);
        label2.setFont(font2);
        mainPanel.add(label1, BorderLayout.NORTH);
        mainPanel.add(label2, BorderLayout.SOUTH);
    }
    @Override
    public void update(){
    }
}
