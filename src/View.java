import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class View extends JFrame {
    Bouton jeu;

    final static int WIDTH = 700, HEIGHT = 700;
    JPanel menu;
    JPanel jouer;
    JPanel entre;
    JTextField nombre;
    Cell[] list;
    Controller controller;

    public View(Controller controller) {
        this.controller = controller;
        init();


        jeu.getBouton().addActionListener((ActionEvent e) -> {
            // VERIFICATION N INT POUR LE NOMBRE DE CELLULES
            if (!isInt(nombre.getText())) {
                return;
            }
            // SI N EST UN INT ALORS ON CREER N CELLULES

            controller.setN(Integer.parseInt(nombre.getText()));
            Cell[] firstCells = new Cell[controller.getN()];
            for (int i = 0; i < controller.getN(); i++) {
                firstCells[i] = new Cell(false);
            }
            this.list = firstCells;

            // setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            setSize(WIDTH, HEIGHT);
            // CODE
            this.menu.setVisible(false);
            this.jouer.setVisible(false);
            CellCircle cellCircle = new CellCircle(HEIGHT / 3, list.length, WIDTH / 2,
                    HEIGHT / 2, list);
            GamePanel gamePanel = new GamePanel(cellCircle);
            gamePanel.setBackground(Color.BLACK);
            Bouton startButton = new Bouton("Start");
            gamePanel.setLayout(new FlowLayout());
            startButton.setHorizontalAlignment(SwingConstants.CENTER);
            startButton.setVerticalAlignment(SwingConstants.CENTER);
            startButton.setBackground(Color.CYAN); // couleur fond
            startButton.setForeground(Color.BLACK);// Couleur ecriture
            startButton.setText("Start");
            startButton.setPreferredSize(new Dimension(100, 50));
            startButton.setMaximumSize(new Dimension(100, 50));
            startButton.setMinimumSize(new Dimension(100, 50));
            cellCircle.setList(list);
            gamePanel.add(startButton);
            this.add(gamePanel);
            this.repaint();
            this.revalidate();
            startButton.addActionListener(event -> {
                list = gamePanel.cells.getCells();
                Thread t = new Thread(() -> {
                    while (true) {
                        refreshCells(gamePanel, cellCircle);
                        //JOptionPane
                        list = controller.startGameOfLife(list);
                        int x = -1;
                        String[] option = {"choose a new configuration", "Quit"};
                        JLabel j = new JLabel("Cycle detected", SwingConstants.CENTER);
                        j.setFont(new Font("Serif", Font.BOLD, 20));
                        if (controller.isCycleFound()) {
                            //JOptionPane.showOptionDialog(null,"cycle detected",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,option);
                            x = JOptionPane.showOptionDialog(null, j,
                                    "",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
                        }
                        if (x == 1) {
                            System.exit(0);
                        } else if (x == 0) {
                            controller.reset();
                            break;
                        } else {

                        }
                        try {
                            Thread.sleep(800);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                    }
                });
                t.start();
            });
        });
    }

    private void refreshCells(GamePanel gamePanel, CellCircle cells) {
        cells.setList(list);
        gamePanel.setCells(cells);
        gamePanel.repaint();
        gamePanel.revalidate();
        this.repaint();
        this.revalidate();
    }

    public static boolean isInt(String str) {
        try {
            @SuppressWarnings("unused")
            int x = Integer.parseInt(str);
            return true; // String is an Integer
        } catch (NumberFormatException e) {
            return false; // String is not an Integer
        }
    }

    private void init() {
        this.setTitle("Jeu de la vie"); // Titre de la fenetre
        this.setSize(400, 600); // Taille de la fenetre
        this.setVisible(true); // visible
        this.setResizable(true); // taille fixe
        setDefaultCloseOperation(EXIT_ON_CLOSE); // exit
        setLocationRelativeTo(null);
        //icon
        ImageIcon logo = new ImageIcon("index.jpeg");
        this.setIconImage(logo.getImage());
        // JPanel boutons / show
        menu = new JPanel(new GridLayout(4, 0));
        jouer = new JPanel();
        entre = new JPanel();
        jeu = new Bouton("jouer");
        menu.add(jouer);
        JLabel title = new JLabel("Choisissez le nombre de cellule :");
        title.setForeground(Color.white);
        title.setHorizontalAlignment(JLabel.CENTER);
        menu.add(title);
        jouer.add(jeu.getBouton());
        jouer.setBackground(Color.BLACK);
        entre.setBackground(Color.BLACK);
        menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        menu.setBackground(Color.BLACK);
        nombre = new JTextField();
        nombre.setPreferredSize(new Dimension(150, 25));
        entre.add(nombre);
        menu.add(entre);
        menu.add(jouer);
        this.add(menu);
        this.validate();
    }

}