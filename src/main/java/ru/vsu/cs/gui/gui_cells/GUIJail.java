package ru.vsu.cs.gui.gui_cells;

import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;
import ru.vsu.cs.cells.Jail;
import ru.vsu.cs.gui.GUICell;
import ru.vsu.cs.gui.GUICellFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Function;

public class GUIJail extends GUICell {
    private JPanel mainPanel;
    private Image picture;
    private JLabel picLabel;

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
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new BorderLayout());
        picture = ImageIO.read(new File("image\\cell_icon\\jail.png"));
        this.add(mainPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    @Override
    public void show(JPanel board, Player playerNow, PlayingField playingField, Locale locale){
        ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
        int skipping = playerNow.getSkipping();
        JOptionPane.showMessageDialog(board, messages.getString("jail") + " " + skipping);
    }

    @Override
    public void setScaledInstance(int x, int y){
        mainPanel.setPreferredSize(new Dimension(x, y));
        picture = picture.getScaledInstance(x - 20, y - 20, Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(picture));
        mainPanel.add(picLabel, BorderLayout.CENTER);
    }
    @Override
    public void update(){
    }
}
