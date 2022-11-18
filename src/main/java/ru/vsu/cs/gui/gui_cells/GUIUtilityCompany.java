package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Tax;
import ru.vsu.cs.cells.UtilityCompany;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

public class GUIUtilityCompany extends GUICell {

    private final UtilityCompany utilityCompany;

    static {
        Function<UtilityCompany, GUIUtilityCompany> function = c -> {
            try {
                return new GUIUtilityCompany(c);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        };
        GUICellFactory.registerType(UtilityCompany.class, function);
    }

    public GUIUtilityCompany(UtilityCompany utilityCompany) throws IOException {
        this.utilityCompany = utilityCompany;
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(70, 70));
        mainPanel.setBackground(Color.WHITE);

        if (Objects.equals(utilityCompany.getName(), "Электростанция")) {
            BufferedImage myPicture = ImageIO.read(new File("image/electric_icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setLayout(new BorderLayout(0,0));
            picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            mainPanel.add(picLabel, BorderLayout.NORTH);
        } else if (Objects.equals(utilityCompany.getName(), "Водопровод")) {
            BufferedImage myPicture = ImageIO.read(new File("image/water_icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setLayout(new BorderLayout(0,0));
            picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            mainPanel.add(picLabel, BorderLayout.NORTH);
        }

        JTextArea UCInform = new JTextArea();
        UCInform.setPreferredSize(new Dimension(68, 53));
        UCInform.setEditable(false);
        Font font = new Font("BOLD", Font.BOLD, 10);
        UCInform.setFont(font);
        UCInform.setForeground(Color.BLACK);
        UCInform.setLineWrap(true);
        UCInform.setWrapStyleWord(true);

        StringBuilder sb = new StringBuilder();
        if (utilityCompany.getOwner() == null) {
            sb.append(utilityCompany.getCost());
        } else {
            sb.append(utilityCompany.getIncome()).append(" ");
            sb.append(utilityCompany.getOwner().getName());
        }
        String str = sb.toString();
        UCInform.setText(str);

        mainPanel.add(UCInform, BorderLayout.SOUTH);


        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        int o = 3;
        if (utilityCompany.getOwner() == null) {
            if (playerNow.getLiberalValues() > utilityCompany.getCost()) {
                o = JOptionPane.showConfirmDialog(board, "Купить коммунальную службу?");
            }
            if (o == 0)
                utilityCompany.action2(playerNow, playingField);
        }
    }
}
