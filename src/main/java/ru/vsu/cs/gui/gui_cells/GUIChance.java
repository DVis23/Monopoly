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
    private final JPanel mainPanel;
    private final Chance chance;
    private Image picture;
    private JLabel picLabel;
    private static Font font;

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

        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font = fontNew.deriveFont(12f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public GUIChance(Chance chance) throws IOException {
        this.chance = chance;
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());
        picture = ImageIO.read(new File("image\\77.png"));

        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        JLabel label;
        if (chance.getI() == 1) {
            label= new JLabel(" Поле шанс: вы сделали взнос в размере 2000 ");
        } else if (chance.getI() == 2) {
            label= new JLabel(" Поле шанс: вы выиграли в лотерее 5000 ");
        } else if (chance.getI() == 3) {
            label= new JLabel(" Поле шанс: каждый игрок скидывается вам на банкет по 500 ");
        } else  {
            label= new JLabel(" Поле шанс: вы выплатили каждому игроку по 500 в знак дружбы ");
        }
        label.setForeground(Color.WHITE);
        label.setFont(font);
        JOptionPane op = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
        op.setOpaque(true);
        op.setIcon(new ImageIcon("image\\lv.gif"));
        op.createDialog(null).setVisible(true);
    }

    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        picture = picture.getScaledInstance(x - 20, y - 20,Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(picture));
        mainPanel.add(picLabel, BorderLayout.CENTER);
    }

    @Override
    public void update(){
    }

}
