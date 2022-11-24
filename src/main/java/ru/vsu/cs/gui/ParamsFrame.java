package ru.vsu.cs.gui;

import org.json.simple.parser.ParseException;
import ru.vsu.cs.Player;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParamsFrame extends JFrame {
    private ArrayList<Player> players = new ArrayList<>();
    private List<JTextField> textFields = new ArrayList<>();
    private JTextField startLiberalValues;


    public ParamsFrame(int width, int height) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("MONOPOLY");
        this.pack();
        this.setSize(width, height);
        JPanel panelMain = new JPanel();
        JPanel panelRight = new PanelRight(width, height);
        JPanel panelLeft = new PanelLeft(width, height);
        panelMain.setLayout(new BorderLayout());
        panelMain.add(panelRight, BorderLayout.EAST);
        panelMain.add(panelLeft, BorderLayout.WEST);
        this.getContentPane().add(panelMain, "Center");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public class PanelLeft extends JPanel {
        int wight;
        int height;
        Color c1 = new Color(23, 4, 41);

        public PanelLeft(int wight, int height) {
            this.wight = wight/3*2 + 30;
            this.height = height;
            setPreferredSize(new Dimension(this.wight, this.height));

            Font font2 = new Font("BOLD", Font.BOLD, 90);
            File font_file = new File("font\\future.ttf");
            try {
                Font font1New = Font.createFont(Font.TRUETYPE_FONT, font_file);
                font2 = font1New.deriveFont(40f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            JButton buttonAddPlayer = new JButton("+");
            buttonAddPlayer.setFont(font2);
            buttonAddPlayer.setBackground(c1);
            buttonAddPlayer.setForeground(Color.WHITE);
            buttonAddPlayer.setFocusPainted(false);
            buttonAddPlayer.setBorder(new LineBorder(Color.WHITE, 1));
            this.add(buttonAddPlayer);
            buttonAddPlayer.setLocation(8, 160);
            buttonAddPlayer.setSize(new Dimension(60, 40));
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
            setLayout(null);
            textFields.add(player1);
            textFields.add(player2);

            buttonAddPlayer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    int size = textFields.size() + 1;
                    if (size <= 6) {
                        JTextField textField = new SuperTextField(" player " + size);
                        add(textField);
                        textField.setLocation(68, 160 + 40 * (size - 1));
                        textField.setSize(new Dimension(400, 40));
                        textFields.add(textField);
                    }
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

            Font font = new Font("BOLD", Font.BOLD, 45);
            File font_file = new File("font\\Advanced.ttf");
            try {
                Font fontNew = Font.createFont(Font.TRUETYPE_FONT, font_file);
                font = fontNew.deriveFont(45f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            g.setFont(font);
            g.setColor(Color.CYAN);
            g.drawString("НАЧАЛЬНЫЕ СБЕРЕЖЕНИЯ", 8, 40);
            g.drawString("ВВЕДИТЕ ИГРОКОВ", 8, 150);
        }

        public class SuperTextField extends JTextField{

            public SuperTextField(String str){
                this.setText(str);
                Font font1 = new Font("BOLD", Font.BOLD, 30);
                File font_file = new File("font\\future.ttf");
                try {
                    Font font1New = Font.createFont(Font.TRUETYPE_FONT, font_file);
                    font1 = font1New.deriveFont(30f);
                } catch (FontFormatException | IOException e) {
                    e.printStackTrace();
                }
                this.setFont(font1);
                this.setBorder(new LineBorder(Color.WHITE, 1));
                this.setBackground(c1);
                this.setForeground(Color.WHITE);
                this.setFont(font1);
            }
        }
    }

    public class PanelRight extends JPanel {
        int wight;
        int height;

        public PanelRight(int wight, int height) {
            this.wight = wight/3 - 30;
            this.height = height;
            setPreferredSize(new Dimension(this.wight, this.height));
            Color c1 = new Color(23, 4, 41);
            this.setBackground(c1);

            Font font1 = new Font("BOLD", Font.BOLD, 45);
            File font_file = new File("font\\Advanced.ttf");
            try {
                Font font1New = Font.createFont(Font.TRUETYPE_FONT, font_file);
                font1 = font1New.deriveFont(40f);
            } catch (FontFormatException | IOException e) {
                e.printStackTrace();
            }
            JButton back = new JButton("НАЗАД");
            JButton begin = new JButton("НАЧАТЬ");
            back.setFont(font1);
            back.setForeground(Color.CYAN);
            back.setBorderPainted(false);
            back.setFocusPainted (false);
            back.setContentAreaFilled(false);
            this.add(back);
            back.setLocation(0, 650);
            back.setSize(new Dimension(170, 40));
            begin.setFont(font1);
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
                    dispose();
                    new StartFrame();
                }
            });
            begin.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    try {
                        int lv = Integer.parseInt(startLiberalValues.getText().trim());
                        if (lv > 1000) {
                            for (int i = 0; i < textFields.size(); i++) {
                                Player player = new Player(textFields.get(i).getText(), lv);
                                players.add(player);
                            }
                            try {
                                new MainFrame(players);
                            } catch (IOException | ParseException e) {
                                e.printStackTrace();
                            }
                            dispose();
                        } else {
                            Font font1 = new Font("BOLD", Font.BOLD, 20);
                            File font_file = new File("font\\future.ttf");
                            try {
                                Font font1New = Font.createFont(Font.TRUETYPE_FONT, font_file);
                                font1 = font1New.deriveFont(20f);
                            } catch (FontFormatException | IOException e) {
                                e.printStackTrace();
                            }
                            UIManager.put("Panel.background", c1);
                            UIManager.put("Button.background", c1);
                            UIManager.put("Button.foreground", Color.WHITE);
                            UIManager.put("Button.font", font1);
                            JLabel label = new JLabel("Введите сумму больше 1000");
                            label.setFont(font1);
                            label.setForeground(Color.WHITE);
                            JOptionPane op = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
                            op.setOpaque(true);
                            op.setBackground(c1);
                            op.setIcon(new ImageIcon("image/lv.gif"));
                            op.createDialog(null, "Уп-сс").setVisible(true);
                        }

                    } catch(NumberFormatException e) {
                        Font font1 = new Font("BOLD", Font.BOLD, 20);
                        File font_file = new File("font\\future.ttf");
                        try {
                            Font font1New = Font.createFont(Font.TRUETYPE_FONT, font_file);
                            font1 = font1New.deriveFont(20f);
                        } catch (FontFormatException | IOException e1) {
                            e.printStackTrace();
                        }
                        UIManager.put("Panel.background", c1);
                        UIManager.put("Button.background", c1);
                        UIManager.put("Button.foreground", Color.WHITE);
                        UIManager.put("Button.font", font1);
                        JLabel label = new JLabel("Введите число, а не...");
                        label.setFont(font1);
                        label.setForeground(Color.WHITE);
                        JOptionPane op = new JOptionPane(label, JOptionPane.INFORMATION_MESSAGE);
                        op.setOpaque(true);
                        op.setBackground(c1);
                        op.setIcon(new ImageIcon("image/lv.gif"));
                        op.createDialog(null, "Уп-сс").setVisible(true);
                    }
                }
            });
        }

        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);
            Font font = new Font("BOLD", Font.BOLD, 40);
            Image img = Toolkit.getDefaultToolkit().getImage("image\\city.gif");
            g.drawImage(img, 12, 30, null);
            g.setFont(font);
            g.setColor(Color.CYAN);
            this.repaint ();
        }
    }

}
