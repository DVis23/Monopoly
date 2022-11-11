package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.UtilityCompany;
import ru.vsu.cs.gui.GUICell;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class GUIUtilityCompany extends GUICell {

    private JPanel MainPanel;
    private JTextArea UCInform;
    private UtilityCompany utilityCompany;

    public GUIUtilityCompany(UtilityCompany utilityCompany) throws IOException {
        this.utilityCompany = utilityCompany;
        MainPanel = new JPanel();
        MainPanel.setPreferredSize(new Dimension(70, 70));
        MainPanel.setBackground(Color.WHITE);

        if (Objects.equals(utilityCompany.getName(), "Электростанция")) {
            BufferedImage myPicture = ImageIO.read(new File("image/electric_icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setLayout(new BorderLayout(0,0));
            picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            MainPanel.add(picLabel, BorderLayout.NORTH);
        } else if (Objects.equals(utilityCompany.getName(), "Водопровод")) {
            BufferedImage myPicture = ImageIO.read(new File("image/water_icon.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setLayout(new BorderLayout(0,0));
            picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            MainPanel.add(picLabel, BorderLayout.NORTH);
        }

        UCInform = new JTextArea();
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

        MainPanel.add(UCInform, BorderLayout.SOUTH);


        this.add(MainPanel, BorderLayout.CENTER);
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
