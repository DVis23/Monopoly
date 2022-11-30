package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.GoToJail;
import ru.vsu.cs.cells.Jail;
import ru.vsu.cs.cells.RailRoad;
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

public class GUIRailRoad extends GUICell {
    private JPanel mainPanel;
    private JTextArea railRoadInform;
    private Image picture;
    private JLabel picLabel;
    private final RailRoad railRoad;
    private static Font font;

    static {
        Function<RailRoad, GUIRailRoad> function = c -> {
            try {
                return new GUIRailRoad(c);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        };
        GUICellFactory.registerType(RailRoad.class, function);

        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font = fontNew.deriveFont(5.5f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public GUIRailRoad(RailRoad railRoad) throws IOException {
        this.railRoad = railRoad;
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        railRoadInform = new JTextArea();
        picture = ImageIO.read(new File("image/78.png"));
        picLabel = new JLabel(new ImageIcon(picture));
        picLabel.setLayout(new BorderLayout(0,0));
        picLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        railRoadInform.setEditable(false);
        railRoadInform.setBorder(BorderFactory.createEmptyBorder(0,2,0,0));
        railRoadInform.setFont(font);
        railRoadInform.setBackground(Color.BLACK);
        railRoadInform.setForeground(Color.WHITE);
        railRoadInform.setLineWrap(true);
        railRoadInform.setWrapStyleWord(true);

        write();

        mainPanel.add(railRoadInform, BorderLayout.SOUTH);
        this.add(mainPanel);
        this.setVisible(true);
    }

    private void write(){
        StringBuilder sb = new StringBuilder();
        sb.append(railRoad.getName()).append("\n");
        if (railRoad.getOwner() == null) {
            sb.append(" ").append(railRoad.getCost());
        } else {
            sb.append(" ").append(railRoad.getIncome()).append(" ");
            sb.append(railRoad.getOwner().getName());
        }
        String str = sb.toString();
        railRoadInform.setText(str);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        int o = 3;
        if (railRoad.getOwner() == null) {
            if (playerNow.getLiberalValues() > railRoad.getCost()) {
                o = JOptionPane.showConfirmDialog(board, "Купить железную дорогу?");
            }
            if (o == 0) {
                railRoad.action2(playerNow, playingField);
                update();
            }
        }
    }
    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        railRoadInform.setPreferredSize(new Dimension(x, y/2));
        picture = picture.getScaledInstance(x, y/2, Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(picture));
        mainPanel.add(picLabel, BorderLayout.NORTH);
        mainPanel.add(railRoadInform, BorderLayout.SOUTH);
        if (x > 180) {
            font = font.deriveFont(12f);
        } else if (x > 120) {
            font = font.deriveFont(10f);
        }
        railRoadInform.setFont(font);
    }
    @Override
    public void update(){
        write();
    }
}
