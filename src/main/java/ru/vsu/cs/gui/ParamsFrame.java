package ru.vsu.cs.gui;

import org.json.simple.parser.ParseException;
import ru.vsu.cs.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.Timer;

public class ParamsFrame extends JFrame {
    private final List<JTextField> textFields = new ArrayList<>();
    private final List<JButton> colorButtons = new ArrayList<>();
    private JTextField startLiberalValues;
    private final Color colorBackground = new Color(23, 4, 41);
    private static Font font1;
    private static Font font2;
    private static Font font3;
    private static Font font4;
    private static Font font5;
    private JButton begin;
    private JButton back;

    private int sizeBoard;

    static {
        File fontFile1 = new File("font\\future.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile1);
            font1 = fontNew.deriveFont(20f);
            font2 = fontNew.deriveFont(25f);
            font5 = fontNew.deriveFont(12f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        File fontFile2 = new File("font\\Advanced.ttf");
        try {
            Font fontNew = Font.createFont(Font.TRUETYPE_FONT, fontFile2);
            font3 = fontNew.deriveFont(45f);
            font4 = fontNew.deriveFont(40f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

    }

    public ParamsFrame(int width, int height, Locale locale) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MONOPOLY");
        this.pack();
        this.setSize(width, height);
        this.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_ESCAPE -> back.doClick();
                    case KeyEvent.VK_ENTER -> begin.doClick();
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });

        JPanel panelMain = new JPanel();
        JPanel panelRight = new PanelRight(width, height, locale);
        JPanel panelLeft = new PanelLeft(width, height, locale);
        panelMain.setLayout(new BorderLayout());
        panelMain.add(panelRight, BorderLayout.EAST);
        panelMain.add(panelLeft, BorderLayout.WEST);

        UIManager.put("Panel.background", colorBackground);
        UIManager.put("Button.background", colorBackground);
        UIManager.put("Button.foreground", Color.WHITE);
        UIManager.put("Button.font", font1);
        UIManager.put("Button.focus", colorBackground);
        UIManager.put("OptionPane.background", colorBackground);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("OptionPane.messageFont", font5);
        UIManager.put("OptionPane.questionIcon", new ImageIcon("image\\dialog_icon\\lvFly.gif"));


        this.getContentPane().add(panelMain, "Center");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.setVisible(true);
    }

    public class PanelLeft extends JPanel {
        private final int wight;
        private final int height;
        private Locale locale;
        private ResourceBundle messages;

        public PanelLeft(int wight, int height, Locale locale) {
            this.wight = wight/3*2 + 30;
            this.height = height;
            this.locale = locale;
            messages = ResourceBundle.getBundle("messages", locale);
            setPreferredSize(new Dimension(this.wight, this.height));

            JButton buttonAddPlayer = new JButton("+");
            buttonAddPlayer.setFont(font2);
            buttonAddPlayer.setBackground(colorBackground);
            buttonAddPlayer.setForeground(Color.WHITE);
            buttonAddPlayer.setFocusPainted(false);
            buttonAddPlayer.setBorder(new LineBorder(Color.WHITE, 1));
            this.add(buttonAddPlayer);
            buttonAddPlayer.setLocation(8, 160);
            buttonAddPlayer.setSize(new Dimension(60, 40));
            buttonAddPlayer.setFocusable(false);

            JButton buttonRemovePlayer = new JButton("-");
            buttonRemovePlayer.setFont(font2);
            buttonRemovePlayer.setBackground(colorBackground);
            buttonRemovePlayer.setForeground(Color.WHITE);
            buttonRemovePlayer.setFocusPainted(false);
            buttonRemovePlayer.setBorder(new LineBorder(Color.WHITE, 1));
            this.add(buttonRemovePlayer);
            buttonRemovePlayer.setLocation(8, 200);
            buttonRemovePlayer.setSize(new Dimension(60, 40));
            buttonRemovePlayer.setFocusable(false);

            JButton buttonAddSizeField = new JButton("+");
            buttonAddSizeField.setFont(font2);
            buttonAddSizeField.setBackground(colorBackground);
            buttonAddSizeField.setForeground(Color.WHITE);
            buttonAddSizeField.setFocusPainted(false);
            buttonAddSizeField.setBorder(new LineBorder(Color.WHITE, 1));
            this.add(buttonAddSizeField);
            buttonAddSizeField.setLocation(8, 470);
            buttonAddSizeField.setSize(new Dimension(60, 40));
            buttonAddSizeField.setFocusable(false);

            JButton buttonSubSizeField = new JButton("-");
            buttonSubSizeField.setFont(font2);
            buttonSubSizeField.setBackground(colorBackground);
            buttonSubSizeField.setForeground(Color.WHITE);
            buttonSubSizeField.setFocusPainted(false);
            buttonSubSizeField.setBorder(new LineBorder(Color.WHITE, 1));
            this.add(buttonSubSizeField);
            buttonSubSizeField.setLocation(68, 470);
            buttonSubSizeField.setSize(new Dimension(60, 40));
            buttonSubSizeField.setFocusable(false);

            JTextField liberalValues = new SuperTextField(" 5000");
            startLiberalValues = liberalValues;
            this.add(liberalValues);
            liberalValues.setLocation(8, 50);
            liberalValues.setSize(460, 40);

            JTextField player1 = new SuperTextField(" player 1");
            JTextField player2 = new SuperTextField(" player 2");
            this.add(player1);
            this.add(player2);
            player1.setLocation(68, 160);
            player1.setSize(new Dimension(400, 40));
            player2.setLocation(68, 200);
            player2.setSize(new Dimension(400, 40));
            textFields.add(player1);
            textFields.add(player2);
            JButton bc1 = new ButtonColor(Color.RED);
            JButton bc2 = new ButtonColor(Color.CYAN);
            this.add(bc1);
            this.add(bc2);
            bc1.setLocation(483, 165);
            bc2.setLocation(483, 205);
            colorButtons.add(bc1);
            colorButtons.add(bc2);
            setLayout(null);

            int[] sizesBoard = new int[]{12, 20, 32, 40};
            JTextField sizeBoardField = new SuperTextField("  40");
            sizeBoard = Integer.parseInt(sizeBoardField.getText().trim());
            sizeBoardField.setEnabled(false);
            this.add(sizeBoardField);
            sizeBoardField.setLocation(128, 470);
            sizeBoardField.setSize(new Dimension(340, 40));

            buttonAddPlayer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int cTextField = textFields.size() + 1;
                    if (cTextField <= 6) {
                        JTextField textField = new SuperTextField(" player " + cTextField);
                        add(textField);
                        textField.setLocation(68, 160 + 40 * (cTextField - 1));
                        textField.setSize(new Dimension(400, 40));
                        textFields.add(textField);
                    }
                    int cButton = colorButtons.size();
                    Color color = Color.BLACK;
                    if (cButton <= 5) {
                        if (cButton == 2) color = Color.ORANGE;
                        else if (cButton == 3) color = Color.MAGENTA;
                        else if (cButton == 4) color = Color.PINK;
                        else if (cButton == 5) color = Color.YELLOW;
                        JButton button = new ButtonColor(color);
                        add(button);
                        button.setLocation(483, 165 + cButton*40);
                        colorButtons.add(button);
                    }
                }

            });

            buttonRemovePlayer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int cTextField = textFields.size();
                    if (cTextField >= 3) {
                        remove(textFields.get(cTextField - 1));
                        remove(colorButtons.get(cTextField - 1));
                        repaint();
                        textFields.remove(cTextField - 1);
                        colorButtons.remove(cTextField - 1);
                    }
                }

            });

            buttonAddSizeField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    sizeBoard = Integer.parseInt(sizeBoardField.getText().trim());
                    for (int i = 0; i < sizesBoard.length; i++) {
                        if (sizeBoard == sizesBoard[i] && i != sizesBoard.length - 1) {
                            sizeBoardField.setText("  " + Integer.toString(sizesBoard[i + 1]));
                            break;
                        }
                    }
                    sizeBoard = Integer.parseInt(sizeBoardField.getText().trim());
                }
            });

            buttonSubSizeField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    sizeBoard = Integer.parseInt(sizeBoardField.getText().trim());
                    for (int i = 0; i < sizesBoard.length; i++) {
                        if (sizeBoard == sizesBoard[i] && i != 0) {
                            sizeBoardField.setText("  " + Integer.toString(sizesBoard[i - 1]));
                            break;
                        }
                    }
                    sizeBoard = Integer.parseInt(sizeBoardField.getText().trim());
                }

            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            Graphics2D g2d = (Graphics2D)g;
            Color c1 = new Color(30, 12, 48);
            Color c2 = new Color(130, 39, 107);
            GradientPaint gradient = new GradientPaint(0,700,c2,0,0,c1,true);
            g2d.setPaint(gradient);
            g2d.fillRect(0,0,wight,height);
            g.setFont(font3);
            g.setColor(Color.CYAN);
            g.drawString(messages.getString("initialSaving"), 8, 40);
            g.drawString(messages.getString("enterPlayer"), 8, 150);
            g.drawString(messages.getString("selectFieldSize"), 8, 460);
        }

        private class SuperTextField extends JTextField{

            public SuperTextField(String str){
                this.setText(str);
                this.setFont(font1);
                this.setBorder(new LineBorder(Color.WHITE, 1));
                this.setBackground(colorBackground);
                this.setForeground(Color.WHITE);
                this.setFont(font2);
                this.addKeyListener(new KeyListener() {
                    public void keyPressed(KeyEvent e) {
                        int keyCode = e.getKeyCode();
                        switch (keyCode) {
                            case KeyEvent.VK_ESCAPE -> back.doClick();
                            case KeyEvent.VK_ENTER -> begin.doClick();
                        }
                    }
                    public void keyReleased(KeyEvent e) {
                    }
                    public void keyTyped(KeyEvent e) {
                    }
                });
            }
        }

        private class ButtonColor extends JButton{

            public ButtonColor(Color color) {
                this.setSize(new Dimension(30, 30));
                this.setBackground(color);
                this.setBorder(new LineBorder(Color.WHITE, 2));
                this.setFocusPainted(false);
                this.setFocusable(false);
                this.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        JDialog c = new ColorPanel(ButtonColor.this);
                        c.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    }
                });
            }


        }
    }

    public class PanelRight extends JPanel {
        private final int wight;
        private final int height;
        private final ResourceBundle messages;

        public PanelRight(int wight, int height, Locale locale) {
            this.wight = wight/3 - 30;
            this.height = height;
            messages = ResourceBundle.getBundle("messages", locale);
            setPreferredSize(new Dimension(this.wight, this.height));
            Color c1 = new Color(23, 4, 41);
            this.setBackground(c1);

            back = new JButton(messages.getString("back"));
            begin = new JButton(messages.getString("start"));
            back.setFont(font4);
            back.setForeground(Color.CYAN);
            back.setBorderPainted(false);
            back.setFocusPainted (false);
            back.setContentAreaFilled(false);
            this.add(back);
            back.setLocation(0, 650);
            back.setSize(new Dimension(170, 40));
            begin.setFont(font4);
            begin.setForeground(Color.CYAN);
            begin.setBorderPainted(false);
            begin.setFocusPainted (false);
            begin.setContentAreaFilled(false);
            this.add(begin);
            begin.setLocation(8, 700);
            begin.setSize(new Dimension(170, 40));
            setLayout(null);

            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
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
                            new StartFrame(locale);
                        }
                    });
                    timer2.setRepeats(false);
                    timer2.start();
                }
            });
            begin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    begin.setForeground(Color.RED);
                    Timer timer = new Timer(200, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            begin.setForeground(Color.CYAN);
                        }
                    });
                    timer.start();
                    Timer timer2 = new Timer(300, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            Font font1 = new Font("BOLD", Font.BOLD, 20);
                            File font_file = new File("font\\future.ttf");
                            try {
                                Font font1New = Font.createFont(Font.TRUETYPE_FONT, font_file);
                                font1 = font1New.deriveFont(20f);
                            } catch (FontFormatException | IOException e1) {
                                e1.printStackTrace();
                            }
                            try {
                                int lv = Integer.parseInt(startLiberalValues.getText().trim());
                                if (lv > 1000) {
                                    Map<Player, Color> map = new LinkedHashMap<>();
                                    List<Color> colors = new ArrayList<>();
                                    for (int i = 0; i < textFields.size(); i++) {
                                        Player player = new Player(textFields.get(i).getText(), lv);
                                        Color color = colorButtons.get(i).getBackground();
                                        colors.add(color);
                                        map.put(player, color);
                                    }
                                    boolean sameColors = false;
                                    outerLoop :
                                    for (int i = 0; i < colors.size(); i++) {
                                        for (int j = 0; j < colors.size(); j++) {
                                            if (i != j && colors.get(i).equals(colors.get(j))) {
                                                sameColors = true;
                                                break outerLoop;
                                            }
                                        }
                                    }
                                    if (!sameColors) {
                                        try {
                                            if (sizeBoard == 12) {
                                                new MainFrame(map, wight, height, locale, "configs\\cells_12");
                                            } else if (sizeBoard == 20) {
                                                new MainFrame(map, wight, height, locale, "configs\\cells_20");
                                            } else if (sizeBoard == 32) {
                                                new MainFrame(map, wight, height, locale, "configs\\cells_32");
                                            } else if (sizeBoard == 40) {
                                                new MainFrame(map, wight, height, locale, "configs\\cells_40");
                                            }
                                        } catch (IOException | ParseException e1) {
                                            e1.printStackTrace();
                                        }
                                        dispose();
                                    } else {
                                        JLabel label = new JLabel(messages.getString("errorColorMatch"));
                                        label.setFont(font1);
                                        label.setForeground(Color.WHITE);
                                        JOptionPane op = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
                                        op.setOpaque(true);
                                        op.setBackground(c1);
                                        op.setIcon(null);
                                        op.createDialog(null,
                                                messages.getString("tittleErrorDialog")).setVisible(true);
                                    }
                                } else {
                                    JLabel label = new JLabel(messages.getString("errorLowValue"));
                                    label.setFont(font1);
                                    label.setForeground(Color.WHITE);
                                    JOptionPane op = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
                                    op.setOpaque(true);
                                    op.setBackground(c1);
                                    op.setIcon(new ImageIcon("image\\dialog_icon\\lv.gif"));
                                    op.createDialog(null,
                                            messages.getString("tittleErrorDialog")).setVisible(true);
                                }

                            } catch(NumberFormatException e1) {
                                JLabel label = new JLabel(messages.getString("errorNumberFormat"));
                                label.setFont(font1);
                                label.setForeground(Color.WHITE);
                                JOptionPane op = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
                                op.setOpaque(true);
                                op.setBackground(c1);
                                op.setIcon(new ImageIcon("image\\dialog_icon\\lv.gif"));
                                op.createDialog(null,
                                        messages.getString("tittleErrorDialog")).setVisible(true);
                            }
                        }
                    });
                    timer2.setRepeats(false);
                    timer2.start();
                }
            });
        }

        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);
            Font font = new Font("BOLD", Font.BOLD, 40);
            Image img = Toolkit.getDefaultToolkit().getImage("image\\background\\city.gif");
            g.drawImage(img, 12, 30, null);
            g.setFont(font);
            g.setColor(Color.CYAN);
            this.repaint ();
        }
    }

}
