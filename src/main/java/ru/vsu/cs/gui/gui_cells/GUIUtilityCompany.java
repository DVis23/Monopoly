package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Cell;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.UtilityCompany;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

public class GUIUtilityCompany extends GUICell {
    private final JPanel mainPanel;
    private JLabel picLabel;
    private Image picture;
    private final JTextArea UCInform;
    private final UtilityCompany utilityCompany;
    private static Font font;

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

        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font = fontNew.deriveFont(8f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public GUIUtilityCompany(UtilityCompany utilityCompany) throws IOException {
        this.utilityCompany = utilityCompany;
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        if (Objects.equals(utilityCompany.getName(), "Электростанция")) {
            picture = ImageIO.read(new File("image\\79.png"));
            picLabel = new JLabel(new ImageIcon(picture));
            picLabel.setLayout(new BorderLayout(0,0));
            picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
        } else if (Objects.equals(utilityCompany.getName(), "Водопровод")) {
            picture = ImageIO.read(new File("image\\80.png"));
            picLabel = new JLabel(new ImageIcon(picture));
            picLabel.setLayout(new BorderLayout(0,0));
            picLabel.setBorder(BorderFactory.createEmptyBorder(0,0,5,0));
        }

        UCInform = new JTextArea();
        UCInform.setEditable(false);
        UCInform.setForeground(Color.WHITE);
        UCInform.setBackground(Color.BLACK);
        UCInform.setBorder(BorderFactory.createEmptyBorder(5,3,0,0));
        UCInform.setLineWrap(true);
        UCInform.setWrapStyleWord(true);

        write();

        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void write(){
        StringBuilder sb = new StringBuilder();
        if (utilityCompany.getOwner() == null) {
            sb.append(utilityCompany.getCost());
        } else {
            sb.append(utilityCompany.getIncome()).append(" ");
            sb.append(utilityCompany.getOwner().getName());
        }
        String str = sb.toString();
        UCInform.setText(str);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField){
        int o = 3;
        if (utilityCompany.getOwner() == null) {
            if (playerNow.getLiberalValues() > utilityCompany.getCost()) {
                o = JOptionPane.showConfirmDialog(board, "Купить коммунальную службу?");
            }
            if (o == 0) {
                utilityCompany.action2(playerNow, playingField);
            }
        } else if (playerNow.equals(utilityCompany.getOwner())) {
            JOptionPane.showMessageDialog(board, "Вы приехали с проверкой в свою коммунальную службу ");
        } else {
            JOptionPane.showMessageDialog(board, "Вы воспользовались услугами коммунальной службы игрока "
                    + utilityCompany.getOwner().getName() + "," +
                    "\n" + "заптатите ему " + utilityCompany.getIncome());
        }
    }

    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        UCInform.setPreferredSize(new Dimension(x - 10, y/7*3));
        picture = picture.getScaledInstance(x - 10, y/7*4, Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(picture));
        mainPanel.add(picLabel, BorderLayout.NORTH);
        mainPanel.add(UCInform, BorderLayout.SOUTH);
        if (x > 180) {
            font = font.deriveFont(12f);
        } else if (x > 120) {
            font = font.deriveFont(10f);
        }
        UCInform.setFont(font);
    }

    @Override
    public void update(){
        write();
    }
}
