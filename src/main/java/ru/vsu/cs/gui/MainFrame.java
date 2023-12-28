package ru.vsu.cs.gui;

import org.json.simple.parser.ParseException;
import ru.vsu.cs.Cell;
import ru.vsu.cs.Game;
import ru.vsu.cs.Player;
import ru.vsu.cs.PlayingField;

import javax.swing.Timer;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class MainFrame extends JFrame {
    private final ResourceBundle messages;

    private final Game game;
    private final List<Player> players;
    private final Map<Player, Color> map;
    private final PlayingField playingField;
    private Player playerNow;

    private final JPanel panelMain;
    private final JPanel board;
    private final JPanel panelPlayer;
    private final JPanel panelButton;
    private final JPanel panelRight;
    private final JButton makeAMove;
    private final JButton manager;
    private final JButton back;

    private final JPanel panelCenter;

    private Dialog managerDialog = new ManagerDialog();
    private final GUICell [] guiCells;

    private static Font font;

    static {
        File fontFile2 = new File("font\\Advanced.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile2);
            font = fontNew.deriveFont(40f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public MainFrame(Map<Player, Color> map, int wight, int height, Locale locale, String dir) throws IOException, ParseException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MONOPOLY");
        this.pack();
        this.setSize(wight, height);
        this.setResizable(false);

        messages = ResourceBundle.getBundle("messages", locale);

        this.map = map;

        players = new ArrayList<>(map.keySet());

        panelMain = new JPanel();
        this.setContentPane(panelMain);
        this.getContentPane().setLayout(new BorderLayout());

        game = new Game(players, dir);
        playingField = game.getPlayingField();

        panelRight = new JPanel();
        panelRight.setPreferredSize(new Dimension(300, 770));
        panelRight.setBackground(new Color(23, 4, 41));

        panelButton = new JPanel();
        panelButton.setPreferredSize(new Dimension(300, 200));
        panelButton.setBackground(new Color(23, 4, 41));
        makeAMove = new SuperButton(messages.getString("makeMove"));
        manager = new SuperButton(messages.getString("manager"));
        back = new SuperButton(messages.getString("exit"));


        GridLayout layout = new GridLayout(6, 1, 0, 0);
        panelPlayer = new JPanel();
        panelPlayer.setLayout(layout);
        panelPlayer.setPreferredSize(new Dimension(250, 400));
        panelPlayer.setBackground(new Color(23, 4, 41));

        panelButton.add(makeAMove);
        panelButton.add(manager);
        panelButton.add(back);
        makeAMove.setLocation(20, 10);
        makeAMove.setSize(300, 60);
        manager.setLocation(20, 60);
        manager.setSize(230, 60);
        back.setLocation(20, 110);
        back.setSize(200, 60);

        panelButton.setLayout(null);

        panelRight.add(panelButton, BorderLayout.NORTH);
        panelRight.add(panelPlayer, BorderLayout.SOUTH);

        int sizeBoard = 770;
        int countCell = playingField.getConstCell();
        int countHorCell = countCell/4 + 1;
        int cellX =  sizeBoard/countHorCell;
        int cellY =  sizeBoard/countHorCell - 2;
        guiCells = GUICellFactory.guiCells(playingField, cellX, cellY);
        for (GUICell guiCell : guiCells) guiCell.setPreferredSize(new Dimension(cellX, cellY));

        board = new JPanel();
        board.setLayout(new GridBagLayout());

        panelCenter = new CenterPanel();
        panelCenter.setLayout(new GridLayout(countHorCell, countHorCell));

        drawPlayersBoard();
        drawBoardPanel();
        drawBoardMini();

        playerNow = players.get(0);

        makeAMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                makeAMove.setForeground(Color.RED);
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        makeAMove.setForeground(Color.CYAN);
                    }
                });
                timer.setRepeats(false);
                timer.start();
                Timer timer2 = new Timer(300, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        managerDialog.setVisible(false);
                        game.playerAction(playerNow);
                        try {
                            updateView();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                        guiCells[playerNow.getStep()].show(board, playerNow, game.getPlayingField(), locale);

                        nextPlayer();
                        if (game.getGameState() == Game.GameState.GAME_OVER) {
                            JOptionPane.showMessageDialog(board, messages.getString("gameOver") +
                                    " '" + players.get(0).getName() + "'");
                            makeAMove.setVisible(false);
                            manager.setVisible(false);
                        }
                        try {
                            updateView();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                timer2.setRepeats(false);
                timer2.start();
            }


        });

        manager.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                manager.setForeground(Color.RED);
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        manager.setForeground(Color.CYAN);
                    }
                });
                timer.setRepeats(false);
                timer.start();
                Timer timer2 = new Timer(300, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        managerDialog = new ManagerDialog(playerNow, playingField, locale);
                    }
                });
                timer2.setRepeats(false);
                timer2.start();
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back.setForeground(Color.RED);
                Timer timer = new Timer(200, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        back.setForeground(Color.CYAN);
                    }
                });
                timer.setRepeats(false);
                timer.start();
                Timer timer2 = new Timer(300, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new StartFrame();
                    }
                });
                timer2.setRepeats(false);
                timer2.start();
            }
        });


        this.getContentPane().add(board, BorderLayout.WEST);
        this.getContentPane().add(panelRight, BorderLayout.EAST);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private class SuperButton extends JButton{

        protected SuperButton(String str) {
            this.setText(str);
            this.setFont(font);
            this.setForeground(Color.CYAN);
            this.setBorderPainted(false);
            this.setFocusPainted (false);
            this.setContentAreaFilled(false);
            this.setHorizontalAlignment(SwingConstants.LEFT);
        }
    }

    private class CenterPanel extends JPanel{

        protected CenterPanel(){
        }

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            Graphics2D g2d = (Graphics2D)g;
            Color c1 = new Color(30, 12, 48);
            Color c2 = new Color(130, 39, 107);
            GradientPaint gradient = new GradientPaint(0,700,c2,0,0,c1,true);
            g2d.setPaint(gradient);
            g2d.fillRect(0,0,700,700);
        }
    }

    private void drawBoardPanel() {
        Cell[] cells = playingField.getCells();
        int countCell = playingField.getConstCell();
        int countVerCell = countCell/4 - 1;
        int countHorCell = countCell/4 + 1;

        board.removeAll();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = countVerCell;
        gbc.gridheight = countVerCell;
        board.add(panelCenter, gbc);

        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        for (int i = 0; i < countHorCell; i++) {
            gbc.gridx = i;
            gbc.gridy = 0;
            board.add(guiCells[i], gbc);
        }
        for (int i = 0; i < countVerCell; i++) {
            for (int j = 0; j < countHorCell; j++) {
                gbc.gridx = j;
                gbc.gridy = i + 1;
                if (j == 0) {
                    board.add(guiCells[cells.length - (i + 1)], gbc);
                } else if (j == countHorCell - 1) {
                    board.add(guiCells[countHorCell + i], gbc);
                }
            }
        }
        for (int i = 0; i < countHorCell; i++) {
            gbc.gridx = i;
            gbc.gridy = countHorCell - 1;
            board.add(guiCells[cells.length - 1 - countVerCell - i], gbc);
        }
    }

    private void drawBoardMini() {
        panelCenter.removeAll();
        Cell [] cells = playingField.getCells();
        int countHorCell = cells.length/4 + 1;
        int countVerCell = cells.length/4 - 1;
        for (int i = 0; i < countHorCell; i++) {
            drawCellMini(panelCenter, i, players);
        }
        for (int i = 0; i < countVerCell; i++) {
            for (int j = 0; j < countHorCell; j++) {
                if (j == 0) {
                    drawCellMini(panelCenter, cells.length - (i + 1), players);
                } else if (j == countHorCell - 1){
                    drawCellMini(panelCenter, countHorCell + i, players);
                } else {
                    panelCenter.add(new JLabel(""));;
                }
            }
        }
        for (int i = 0; i < countHorCell; i++) {
            drawCellMini(panelCenter, cells.length - 1 - countVerCell - i, players);
        }
    }


    private void drawCellMini(JPanel panel, int i, List<Player> players) {
        JPanel panelMini = new JPanel();
        GridLayout layout = new GridLayout(players.size(), 1, 0, 0);
        panelMini.setLayout(layout);
        panelMini.setBorder(new LineBorder(Color.WHITE, 1));
        panelMini.setBackground(new Color(47, 23, 52));
        for (Player value : players) {
            if (value.getStep() == i) {
                JPanel player = new JPanel();
                Color color = map.get(value);
                player.setBackground(color);
                panelMini.add(player);
            }
        }
        panel.add(panelMini);
    }


    private void nextPlayer() {
        int i = players.indexOf(playerNow);
        i++;
        if (i == players.size()) i = 0;
        playerNow = players.get(i);
    }

    private void updateView() throws IOException {
        updatePlayersBoard();
        for (GUICell guiCell : guiCells) {
            guiCell.update();
        }
        drawBoardPanel();
        this.setVisible(true);
    }

    private void drawPlayersBoard() {
        for (Player player : players) {
            InformPlayer informPlayer = new InformPlayer(player);
            JPanel colorPanel = new JPanel();
            colorPanel.setPreferredSize(new Dimension(11, 11));
            Color color = map.get(player);
            colorPanel.setBackground(color);
            if (player.equals(playerNow)) {
                informPlayer.textRed();
            }
            informPlayer.add(colorPanel);
            panelPlayer.add(informPlayer);
        }
    }

    private void updatePlayersBoard() {
        panelPlayer.removeAll();
        panelPlayer.repaint();
        drawPlayersBoard();
        drawBoardMini();
    }

}
